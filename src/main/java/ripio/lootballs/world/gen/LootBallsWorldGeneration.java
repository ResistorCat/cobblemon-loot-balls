package ripio.lootballs.world.gen;

import ripio.lootballs.LootBalls;

public class LootBallsWorldGeneration {
    public static void generateLootBallsWorldGen() {
        LootBallsLootBallGeneration.generateLootBalls();
    }

    public static void registerWorldGen() {
        LootBalls.LOGGER.info("Registering world generation for " + LootBalls.MOD_ID);
        generateLootBallsWorldGen();
    }
}
