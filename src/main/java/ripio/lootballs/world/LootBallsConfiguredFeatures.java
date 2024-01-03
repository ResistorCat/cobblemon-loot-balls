package ripio.lootballs.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import ripio.lootballs.Lootballs;
import ripio.lootballs.world.feature.LootBallFeatureConfig;

import static ripio.lootballs.datagen.LootBallsLootTableProvider.*;
import static ripio.lootballs.world.LootBallsFeatures.LOOT_BALL_FEATURE;

public class LootBallsConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> POKE_LOOT_KEY = registerKey("poke_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> CITRINE_LOOT_KEY = registerKey("citrine_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> VERDANT_LOOT_KEY = registerKey("verdant_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> AZURE_LOOT_KEY = registerKey("azure_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> ROSEATE_LOOT_KEY = registerKey("roseate_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> SLATE_LOOT_KEY = registerKey("slate_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> PREMIER_LOOT_KEY = registerKey("premier_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> SAFARI_LOOT_KEY = registerKey("safari_loot_config");
    public static final RegistryKey<ConfiguredFeature<?,?>> GREAT_LOOT_KEY = registerKey("great_loot_config");


    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context) {

        register(
                context,
                POKE_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "poke_loot_ball"),
                        POKE_LOOT_TABLE
                )
        );

        register(
                context,
                CITRINE_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "citrine_loot_ball"),
                        CITRINE_LOOT_TABLE
                )
        );

        register(
                context,
                VERDANT_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "verdant_loot_ball"),
                        VERDANT_LOOT_TABLE
                )
        );

        register(
                context,
                AZURE_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "azure_loot_ball"),
                        AZURE_LOOT_TABLE
                )
        );

        register(
                context,
                ROSEATE_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "roseate_loot_ball"),
                        ROSEATE_LOOT_TABLE
                )
        );

        register(
                context,
                SLATE_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "slate_loot_ball"),
                        SLATE_LOOT_TABLE
                )
        );

        register(
                context,
                PREMIER_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "premier_loot_ball"),
                        PREMIER_LOOT_TABLE
                )
        );

        register(
                context,
                SAFARI_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "safari_loot_ball"),
                        SAFARI_LOOT_TABLE
                )
        );

        register(
                context,
                GREAT_LOOT_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(
                        new Identifier(Lootballs.MOD_ID, "great_loot_ball"),
                        GREAT_LOOT_TABLE
                )
        );

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Lootballs.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
