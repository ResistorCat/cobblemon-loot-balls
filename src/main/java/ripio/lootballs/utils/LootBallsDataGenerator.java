package ripio.lootballs.utils;

import com.cobblemon.mod.common.CobblemonItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import ripio.lootballs.Lootballs;

import java.util.function.BiConsumer;

public class LootBallsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator){
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(LootBallsLootTables::new);
    }

    private static class LootBallsLootTables extends SimpleFabricLootTableProvider {
        public LootBallsLootTables(FabricDataOutput dataGenerator) {
            super(dataGenerator, LootContextTypes.CHEST);
        }

        @Override
        public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
            biConsumer.accept(Lootballs.POKE_LOOT_TABLE, LootTable.builder()
                    .pool(LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(CobblemonItems.POTION)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F,2.0F)))
                                    .weight(30)
                            )
                            .with(ItemEntry.builder(CobblemonItems.ETHER)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F,2.0F)))
                                    .weight(30)
                            )
                            .with(ItemEntry.builder(CobblemonItems.POKE_BALL)
                                    .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                    .weight(10)
                            )
                            .with(ItemEntry.builder(CobblemonItems.HEAL_POWDER)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F,4.0F)))
                                    .weight(10)
                            )
                            .with(ItemEntry.builder(Items.GOLD_NUGGET)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(9.0F,18.0F)))
                                    .weight(10)
                            )
                            .with(ItemEntry.builder(Items.EMERALD)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F,8.0F)))
                                    .weight(5)
                            )
                            .with(ItemEntry.builder(CobblemonItems.REVIVE)
                                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F,2.0F)))
                                    .weight(4)
                            )
                            .with(ItemEntry.builder(CobblemonItems.LEFTOVERS)
                                    .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                    .weight(1)
                            )
                    )
            );
        }

    }

}
