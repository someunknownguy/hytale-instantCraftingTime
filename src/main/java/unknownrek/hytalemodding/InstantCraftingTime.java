package unknownrek.hytalemodding;

import com.hypixel.hytale.assetstore.event.LoadedAssetsEvent;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import unknownrek.hytalemodding.config.PluginConfig;
import unknownrek.hytalemodding.events.LoadAssetEvents;

import javax.annotation.Nonnull;

public class InstantCraftingTime extends JavaPlugin {

    private final Config<PluginConfig> config = this.withConfig(this.getClass().getSimpleName(), PluginConfig.CODEC);

    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public InstantCraftingTime(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Loading " + this.getClass().getSimpleName());
        config.save();
        setupCommands();
        setupEvents();
        setupEcs();

        LOGGER.atInfo().log("Finished loading " + this.getClass().getSimpleName());
    }

    private void setupCommands() {
        var commandReg = this.getCommandRegistry();
    }

    private void setupEvents() {
        var eventReg = this.getEventRegistry();

        LoadAssetEvents handlers = new LoadAssetEvents(this);
        eventReg.register(LoadedAssetsEvent.class, CraftingRecipe.class, handlers::onRecipeLoad);
    }

    private void setupEcs() {
        var ecsEventReg = this.getEntityStoreRegistry();
    }

    public PluginConfig getConfig() {
        return config.get();
    }
}