package net.yokohama_miyazawa.maidillager.clothconfig;

import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.yokohama_miyazawa.maidillager.config.ConfigRow;
import net.yokohama_miyazawa.maidillager.config.ModConfigs;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.function.Consumer;

public class ModClothConfig {
    ConfigBuilder builder;

    public ModClothConfig(Screen parent) {
        builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.maidillager.config"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory generalCategory = builder.getOrCreateCategory(Text.translatable("category.maidillager.general"));

        generalCategory.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.maidillager.umulike"), ModConfigs.CONFIG.getOrDefault("umuLike", false))
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> {
                            ModConfigs.setConfigRow(ConfigRow.Builder.clone(ModConfigs.getConfigRows().get("umuLike")).setValue(newValue).buildWithDescription());
                            try {
                                ModConfigs.saveConfig();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .build()
        );
    }

    public Screen getScreen() {
        return builder.build();
    }
}
