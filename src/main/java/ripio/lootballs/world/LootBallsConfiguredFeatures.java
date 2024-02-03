package ripio.lootballs.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import ripio.lootballs.LootBalls;
import ripio.lootballs.world.feature.LootBallFeatureConfig;

import static ripio.lootballs.world.LootBallsFeatures.LOOT_BALL_FEATURE;

public class LootBallsConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?,?>> LOOT_BALL_CONFIG_KEY = registerKey("loot_ball_config");

    public static void bootstrap(Registerable<ConfiguredFeature<?,?>> context) {

        register(
                context,
                LOOT_BALL_CONFIG_KEY,
                LOOT_BALL_FEATURE,
                new LootBallFeatureConfig(0.1F)
        );

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(LootBalls.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
