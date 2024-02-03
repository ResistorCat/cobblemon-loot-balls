package ripio.lootballs.world;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import ripio.lootballs.LootBalls;

public class LootBallsGamerules {
    public static final GameRules.Key<GameRules.BooleanRule> GENERATE_LOOT_BALLS = GameRuleRegistry.register(
            "generateLootBalls", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)
    );
    public static void registerGamerules() {
        LootBalls.LOGGER.info("Registering gamerules for " + LootBalls.MOD_ID);
    }
}
