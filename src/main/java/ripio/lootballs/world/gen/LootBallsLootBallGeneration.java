package ripio.lootballs.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import ripio.lootballs.world.LootBallsPlacedFeatures;

public class LootBallsLootBallGeneration {
    public static void generateLootBalls() {
        BiomeModifications.addFeature(
                BiomeSelectors.all(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                LootBallsPlacedFeatures.LOOT_BALL_PLACED_KEY
        );
    }
}
