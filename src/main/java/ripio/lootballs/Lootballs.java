package ripio.lootballs;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lootballs implements ModInitializer {
	public static final String MOD_ID = "lootballs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Loot Balls
	public static final LootBall POKE_LOOT_BALL = new LootBall(
			FabricBlockSettings.copyOf(Blocks.BEDROCK)
	);
	public static final LootBall GREAT_LOOT_BALL = new LootBall(
			FabricBlockSettings.copyOf(Blocks.BEDROCK)
	);
	public static final LootBall ULTRA_LOOT_BALL = new LootBall(
			FabricBlockSettings.copyOf(Blocks.BEDROCK)
	);
	public static final LootBall MASTER_LOOT_BALL = new LootBall(
			FabricBlockSettings.copyOf(Blocks.BEDROCK)
	);

	// BlockEntity
	public static final BlockEntityType<LootBallEntity> LOOT_BALL_ENTITY = FabricBlockEntityTypeBuilder.create(
			LootBallEntity::new,
			POKE_LOOT_BALL,
			GREAT_LOOT_BALL,
			ULTRA_LOOT_BALL,
			MASTER_LOOT_BALL
	).build();

	// Group
	private static final ItemGroup LOOTBALLS_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(POKE_LOOT_BALL))
			.displayName(Text.translatable("itemGroup.lootballs.lootballs_group"))
			.entries((displayContext, entries) -> {
				entries.add(new ItemStack(POKE_LOOT_BALL));
				entries.add(new ItemStack(GREAT_LOOT_BALL));
				entries.add(new ItemStack(ULTRA_LOOT_BALL));
				entries.add(new ItemStack(MASTER_LOOT_BALL));
			})
			.build();

	// Player statistic
	public static final Identifier OPEN_LOOT_BALL = new Identifier(MOD_ID, "open_loot_ball");

	@Override
	public void onInitialize() {
		LOGGER.info("Registering Loot Balls...");
		Registry.register(
				Registries.BLOCK,
				new Identifier(MOD_ID, "poke_loot_ball"),
				POKE_LOOT_BALL
		);
		Registry.register(
				Registries.BLOCK,
				new Identifier(MOD_ID, "great_loot_ball"),
				GREAT_LOOT_BALL
		);
		Registry.register(
				Registries.BLOCK,
				new Identifier(MOD_ID, "ultra_loot_ball"),
				ULTRA_LOOT_BALL
		);
		Registry.register(
				Registries.BLOCK,
				new Identifier(MOD_ID, "master_loot_ball"),
				MASTER_LOOT_BALL
		);
		LOGGER.info("Registering Loot Ball items...");
		Registry.register(
				Registries.ITEM,
				new Identifier(MOD_ID, "poke_loot_ball"),
				new BlockItem(POKE_LOOT_BALL, new FabricItemSettings())
		);
		Registry.register(
				Registries.ITEM,
				new Identifier(MOD_ID, "great_loot_ball"),
				new BlockItem(GREAT_LOOT_BALL, new FabricItemSettings())
		);
		Registry.register(
				Registries.ITEM,
				new Identifier(MOD_ID, "ultra_loot_ball"),
				new BlockItem(ULTRA_LOOT_BALL, new FabricItemSettings())
		);
		Registry.register(
				Registries.ITEM,
				new Identifier(MOD_ID, "master_loot_ball"),
				new BlockItem(MASTER_LOOT_BALL, new FabricItemSettings())
		);
		LOGGER.info("Registering Loot Ball item group...");
		Registry.register(
				Registries.ITEM_GROUP,
				new Identifier(MOD_ID, "lootballs_group"),
				LOOTBALLS_GROUP
		);
		LOGGER.info("Registering Loot Ball block entity...");
		Registry.register(
				Registries.BLOCK_ENTITY_TYPE,
				new Identifier(MOD_ID, "loot_ball_entity"),
				LOOT_BALL_ENTITY
		);
		LOGGER.info("Registering Loot Ball player stat...");
		Registry.register(
				Registries.CUSTOM_STAT,
				"open_loot_ball",
				OPEN_LOOT_BALL
		);
		Stats.CUSTOM.getOrCreateStat(OPEN_LOOT_BALL, StatFormatter.DEFAULT);
	}
}