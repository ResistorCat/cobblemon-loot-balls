package ripio.lootballs.world.gen;

import ripio.lootballs.Lootballs;

public class LootBallsWorldGeneration {
    public static void generateLootBallsWorldGen() {
        LootBallsLootBallGeneration.generateLootBalls();
    }

    public static void registerWorldGen() {
        Lootballs.LOGGER.info("Registering world generation for " + Lootballs.MOD_ID);
        generateLootBallsWorldGen();
    }
}
