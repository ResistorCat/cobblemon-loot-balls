package ripio.lootballs;

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
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import ripio.lootballs.datagen.LootBallsLootTableProvider;
import ripio.lootballs.datagen.LootBallsWorldGenerator;
import ripio.lootballs.world.LootBallsConfiguredFeatures;
import ripio.lootballs.world.LootBallsPlacedFeatures;

import java.util.function.BiConsumer;

public class LootBallsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator){
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(LootBallsLootTableProvider::new);
        pack.addProvider(LootBallsWorldGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, LootBallsConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, LootBallsPlacedFeatures::bootstrap);
    }

}
