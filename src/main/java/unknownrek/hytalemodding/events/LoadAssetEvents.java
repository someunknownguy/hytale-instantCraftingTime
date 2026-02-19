package unknownrek.hytalemodding.events;

import com.hypixel.hytale.assetstore.event.LoadedAssetsEvent;
import com.hypixel.hytale.assetstore.map.DefaultAssetMap;
import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe;
import com.hypixel.hytale.server.core.util.WildcardMatch;
import unknownrek.hytalemodding.InstantCraftingTime;
import unknownrek.hytalemodding.config.PluginConfig;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Predicate;

import static unknownrek.hytalemodding.InstantCraftingTime.LOGGER;

public class LoadAssetEvents {

    private final Field craftingRecipeTime;

    private final PluginConfig config;

    public LoadAssetEvents(InstantCraftingTime plugin) {
        this.config = plugin.getConfig();
        craftingRecipeTime = initCraftingRecipeTime();
    }

    private Field initCraftingRecipeTime() {
        try {
            var craftingRecipeTime = CraftingRecipe.class.getDeclaredField("timeSeconds");
            craftingRecipeTime.setAccessible(true);
            return craftingRecipeTime;
        } catch (NoSuchFieldException ex) {
            LOGGER.atSevere().log("Could not access timeSeconds recipe variable - this mod will not work without it");
        }
        return null;
    }

    public void onRecipeLoad(LoadedAssetsEvent<String, CraftingRecipe, DefaultAssetMap<String, CraftingRecipe>> event) {
        var shouldUpdate = getPredicateFromWhitelistMode(config);
        try {
            for (CraftingRecipe recipe : event.getLoadedAssets().values()) {
                if (shouldUpdate.test(recipe)) {
                    craftingRecipeTime.set(recipe, 0F);
                }
            }
        } catch (IllegalAccessException ex) {
            LOGGER.atSevere().log("Unable to update timeSeconds for recipes, check yer shit unknown! - also only some recipes were updated");
        }
    }

    /**
     * Returns a predicate to identify which recipes we should make instant based on config
     * @param config plugin config to read values off of
     * @return Adequate Predicate instance
     */
    private Predicate<CraftingRecipe> getPredicateFromWhitelistMode(PluginConfig config) {
        return switch (config.getWhitelistMode()) {
            case DISABLED -> (_) -> true;
            case ITEMIDS ->  (recipe) -> Arrays.stream(config.getWhitelistIds())
                    .anyMatch((whitelistId) -> WildcardMatch.test(recipe.getId(), whitelistId));
        };
    }
}
