package ripio.lootballs.event;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.function.SetLootTableLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import ripio.lootballs.LootBalls;
import ripio.lootballs.block.LootBallsBlocks;
import ripio.lootballs.datagen.LootBallsLootTableProvider;
import ripio.lootballs.util.LootBallsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LootBallsLootTableModifiers {
    private static final Identifier FISHING_TREASURE_ID = of("gameplay/fishing/treasure");
    private static final Identifier BUDDING_AMETHYST_ID = of("blocks/budding_amethyst");
    private static final Identifier DESERT_PYRAMID_ID = of("archaeology/desert_pyramid");
    private static final Identifier DESERT_WELL_ID = of("archaeology/desert_well");
    private static final Identifier OCEAN_RUIN_COLD_ID = of("archaeology/ocean_ruin_cold");
    private static final Identifier OCEAN_RUIN_WARM_ID = of("archaeology/ocean_ruin_warm");
    private static final Identifier TRAILS_RUINS_COMMON_ID = of("archaeology/trail_ruins_common");
    private static final Identifier TRAIL_RUINS_RARE_ID = of("archaeology/desert_pyramid");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin()) {
                if (FISHING_TREASURE_ID.equals(id) && LootBalls.CONFIG.doLootBallFishing()) {
                    LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .conditionally(
                                    RandomChanceLootCondition.builder(0.66F)
                            )
                            .with(
                                    ItemEntry.builder(LootBallsBlocks.DIVE_LOOT_BALL)
                                            .conditionally(
                                                    AnyOfLootCondition.builder(
                                                            biomeFilter("deep_frozen_ocean"),
                                                            biomeFilter("deep_cold_ocean"),
                                                            biomeFilter("deep_ocean"),
                                                            biomeFilter("deep_lukewarm_ocean")
                                                    )
                                            )
                                            .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.DIVE_LOOT_TABLE))
                                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)))
                                            .weight(LootBallsUtils.UNCOMMON_WEIGHT)
                            )
                            .with(
                                    ItemEntry.builder(LootBallsBlocks.LURE_LOOT_BALL)
                                            .conditionally(
                                                    AnyOfLootCondition.builder(
                                                            biomeFilter("deep_frozen_ocean"),
                                                            biomeFilter("deep_cold_ocean"),
                                                            biomeFilter("cold_ocean"),
                                                            biomeFilter("frozen_ocean")
                                                    )
                                            )
                                            .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.LURE_LOOT_TABLE))
                                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)))
                                            .weight(LootBallsUtils.UNCOMMON_WEIGHT)
                            )
                            .with(
                                    ItemEntry.builder(LootBallsBlocks.POKE_LOOT_BALL)
                                            .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.POKE_LOOT_TABLE))
                                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)))
                                            .weight(LootBallsUtils.COMMON_WEIGHT)
                            )
                            .with(
                                    ItemEntry.builder(LootBallsBlocks.GREAT_LOOT_BALL)
                                            .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.GREAT_LOOT_TABLE))
                                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)))
                                            .weight(LootBallsUtils.UNCOMMON_WEIGHT)
                            )
                            .with(
                                    ItemEntry.builder(LootBallsBlocks.ULTRA_LOOT_BALL)
                                            .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.ULTRA_LOOT_TABLE))
                                            .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1F)))
                                            .weight(LootBallsUtils.RARE_WEIGHT)
                            );

                    tableBuilder.pool(poolBuilder);
                }
                if (LootBalls.CONFIG.doLootBallDrops() && BUDDING_AMETHYST_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                            .conditionally(
                                    RandomChanceLootCondition.builder((float) LootBallsUtils.RARE_WEIGHT /LootBallsUtils.TOTAL_WEIGHT)
                            )
                            .with(
                                    ItemEntry.builder(LootBallsBlocks.LUXURY_LOOT_BALL)
                                            .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.LUXURY_LOOT_TABLE))
                            );

                    tableBuilder.pool(poolBuilder);
                }
            }
        }));

        LootTableEvents.REPLACE.register(((resourceManager, lootManager, id, original, source) -> {
            if (source.isBuiltin()) {
                if (LootBalls.CONFIG.doLootBallArchaeology() && (DESERT_PYRAMID_ID.equals(id) || DESERT_WELL_ID.equals(id))) {
                    List<LootPoolEntry> entries = new ArrayList<>(Arrays.asList(original.pools[0].entries));
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.POKE_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.POKE_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.COMMON_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.GREAT_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.GREAT_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.UNCOMMON_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.ULTRA_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.ULTRA_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.RARE_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.MASTER_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.MASTER_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.ULTRA_RARE_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.REPEAT_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.REPEAT_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.RARE_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );

                    LootPool.Builder pool = LootPool.builder().with(entries);
                    return LootTable.builder().pool(pool).build();
                }
                if (LootBalls.CONFIG.doLootBallArchaeology() && (OCEAN_RUIN_WARM_ID.equals(id) || OCEAN_RUIN_COLD_ID.equals(id) || TRAILS_RUINS_COMMON_ID.equals(id) || TRAIL_RUINS_RARE_ID.equals(id))) {
                    List<LootPoolEntry> entries = new ArrayList<>(Arrays.asList(original.pools[0].entries));
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.POKE_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.POKE_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.COMMON_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.GREAT_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.GREAT_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.UNCOMMON_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.ULTRA_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.ULTRA_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.RARE_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );
                    entries.add(
                            ItemEntry.builder(LootBallsBlocks.MASTER_LOOT_BALL)
                                    .apply(SetLootTableLootFunction.builder(BlockEntityType.CHEST, LootBallsLootTableProvider.MASTER_LOOT_TABLE))
                                    .conditionally(RandomChanceLootCondition.builder((float) LootBallsUtils.ULTRA_RARE_WEIGHT / LootBallsUtils.TOTAL_WEIGHT))
                                    .build()
                    );

                    LootPool.Builder pool = LootPool.builder().with(entries);
                    return LootTable.builder().pool(pool).build();
                }
            }

            return null;
        }));
    }

    private static Identifier of(String path) {
        return new Identifier("minecraft",path);
    }

    private static net.minecraft.loot.condition.LootCondition.Builder biomeFilter(String path) {
        return LocationCheckLootCondition.builder(LocationPredicate.Builder.create().biome(RegistryKey.of(RegistryKeys.BIOME, of(path))));
    }

    public static void registerLootTableModifiers() {
        modifyLootTables();
        LootBalls.LOGGER.info("Registering loot table modifiers for lootballs");
    }

}
