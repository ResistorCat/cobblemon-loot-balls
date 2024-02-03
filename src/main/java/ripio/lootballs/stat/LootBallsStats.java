package ripio.lootballs.stat;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import ripio.lootballs.LootBalls;

public class LootBallsStats {
    public static final Identifier OPEN_LOOT_BALL_STAT_ID = new Identifier(LootBalls.MOD_ID, "open_loot_ball");
    public static final Identifier OPEN_LOOT_BALL_STAT = Registry.register(
            Registries.CUSTOM_STAT,
            "open_loot_ball",
            OPEN_LOOT_BALL_STAT_ID
    );

    public static void registerStats() {
        LootBalls.LOGGER.info("Registering stats for " + LootBalls.MOD_ID);
        Stats.CUSTOM.getOrCreateStat(OPEN_LOOT_BALL_STAT_ID, StatFormatter.DEFAULT);
    }

}
