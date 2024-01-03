package ripio.lootballs.world;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import ripio.lootballs.Lootballs;

public class LootBallsGamerules {
    public static final GameRules.Key<GameRules.BooleanRule> GENERATE_LOOT_BALLS = GameRuleRegistry.register(
            "generateLootBalls", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)
    );
    public static final GameRules.Key<GameRules.BooleanRule> DO_LOOT_BALL_SPAWNING = GameRuleRegistry.register(
            "doLootBallSpawning", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false)
    );
    public static final GameRules.Key<GameRules.BooleanRule> DO_LOOT_BALL_FISHING = GameRuleRegistry.register(
            "doLootBallFishing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)
    );
    public static void registerGamerules() {
        Lootballs.LOGGER.info("Registering gamerules for " + Lootballs.MOD_ID);
    }
}
