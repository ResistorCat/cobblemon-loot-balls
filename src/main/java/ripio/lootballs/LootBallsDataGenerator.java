package ripio.lootballs;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import ripio.lootballs.datagen.*;
import ripio.lootballs.world.LootBallsConfiguredFeatures;
import ripio.lootballs.world.LootBallsPlacedFeatures;

public class LootBallsDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator){
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(LootBallsLootTableProvider::new);
        pack.addProvider(LootBallsWorldGenerator::new);
        pack.addProvider(LootBallsBlockTagProvider::new);
        pack.addProvider(LootBallsLanguageProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, LootBallsConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, LootBallsPlacedFeatures::bootstrap);
    }

}
