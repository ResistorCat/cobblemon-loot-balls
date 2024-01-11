package ripio.lootballs.event;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.util.Identifier;

public class LootBallsLootTableModifiers {
    private static final Identifier DESERT_PYRAMID_ID =
            new Identifier("minecraft","archaeology/desert_pyramid");
    private static final Identifier DESERT_WELL_ID =
            new Identifier("minecraft","archaeology/desert_well");
    private static final Identifier OCEAN_RUIN_COLD_ID =
            new Identifier("minecraft","archaeology/ocean_ruin_cold");
    private static final Identifier OCEAN_RUIN_WARM_ID =
            new Identifier("minecraft","archaeology/ocean_ruin_warm");
    private static final Identifier TRAILS_RUINS_COMMON_ID =
            new Identifier("minecraft","archaeology/trail_ruins_common");
    private static final Identifier TRAIL_RUINS_RARE_ID =
            new Identifier("minecraft","archaeology/desert_pyramid");

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && DESERT_PYRAMID_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder();

                tableBuilder.pool(poolBuilder);
            }
        });
    }

}
