package unknownrek.hytalemodding.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.codecs.EnumCodec;

import java.util.Arrays;

public class PluginConfig {

    public static final BuilderCodec<PluginConfig> CODEC = BuilderCodec.builder(PluginConfig.class, PluginConfig::new)
            .appendInherited(new KeyedCodec<>("WhitelistMode", new EnumCodec<>(WhitelistMode.class)),
                    PluginConfig::setWhitelistMode,
                    PluginConfig::getWhitelistMode,
                    (o, p) -> o.setWhitelistMode(p.getWhitelistMode()))
            .documentation("whitelist mode, one of " + String.join(", ", Arrays.stream(WhitelistMode.values()).map(WhitelistMode::name).toList()))
            .add()
            .appendInherited(new KeyedCodec<>("WhitelistIds", Codec.STRING_ARRAY),
                    PluginConfig::setWhitelistIds,
                    PluginConfig::getWhitelistIds,
                    (o, p) -> o.setWhitelistIds(p.getWhitelistIds())).add()
            .build();

    private WhitelistMode whitelistMode = WhitelistMode.DISABLED;
    private String[] whitelistIds = new String[0];

    public String[] getWhitelistIds() {
        return whitelistIds;
    }

    public void setWhitelistIds(String[] whitelistIds) {
        this.whitelistIds = whitelistIds;
    }

    public WhitelistMode getWhitelistMode() {
        return whitelistMode;
    }

    public void setWhitelistMode(WhitelistMode whitelistMode) {
        this.whitelistMode = whitelistMode;
    }
}
