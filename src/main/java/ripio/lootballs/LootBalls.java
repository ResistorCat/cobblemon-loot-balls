package ripio.lootballs;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ripio.lootballs.block.LootBallsBlockEntities;
import ripio.lootballs.block.LootBallsBlocks;
import ripio.lootballs.config.LootBallsConfig;
import ripio.lootballs.event.LootBallsLootTableModifiers;
import ripio.lootballs.item.LootBallsItemGroups;
import ripio.lootballs.item.LootBallsItems;
import ripio.lootballs.sound.LootBallsSoundEvents;
import ripio.lootballs.stat.LootBallsStats;
import ripio.lootballs.world.LootBallsFeatures;
import ripio.lootballs.world.LootBallsGamerules;
import ripio.lootballs.world.gen.LootBallsWorldGeneration;

public class LootBalls implements ModInitializer {
	public static final String MOD_ID = "lootballs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final LootBallsConfig CONFIG = LootBallsConfig.createAndLoad();

	@Override
	public void onInitialize() {
		LootBallsBlocks.registerModBlocks();
		LootBallsBlockEntities.registerBlockEntities();
		LootBallsItems.registerModItems();
		LootBallsItemGroups.registerItemGroups();
		LootBallsSoundEvents.registerSoundEvents();
		LootBallsStats.registerStats();
		LootBallsGamerules.registerGamerules();
		LootBallsFeatures.registerFeatures();
		LootBallsWorldGeneration.registerWorldGen();
		LootBallsLootTableModifiers.registerLootTableModifiers();
	}

	public static Identifier lootBallsResource(String path) {
		return new Identifier(MOD_ID, path);
	}
}