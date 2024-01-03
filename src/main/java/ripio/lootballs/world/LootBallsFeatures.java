package ripio.lootballs.world;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ripio.lootballs.Lootballs;
import ripio.lootballs.world.feature.LootBallFeature;
import ripio.lootballs.world.feature.LootBallFeatureConfig;

public class LootBallsFeatures {
    public static final LootBallFeature LOOT_BALL_FEATURE = Registry.register(
            Registries.FEATURE,
            new Identifier(Lootballs.MOD_ID, "loot_ball_feature"),
            new LootBallFeature(LootBallFeatureConfig.CODEC)
    );
    public static void registerFeatures() {
        Lootballs.LOGGER.info("Registering features for " + Lootballs.MOD_ID);
    }
}
