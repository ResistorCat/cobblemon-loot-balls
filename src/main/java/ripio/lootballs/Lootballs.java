package ripio.lootballs;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
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
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lootballs implements ModInitializer {
	public static final String MOD_ID = "lootballs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Loot Balls
	public static final LootBall AZURE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall BEAST_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall CHERISH_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall CITRINE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall DIVE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall DREAM_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall DUSK_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall FAST_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall FRIEND_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall GREAT_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall HEAL_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall HEAVY_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall LEVEL_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall LOVE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall LURE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall LUXURY_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall MASTER_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall MOON_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall NEST_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall NET_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall PARK_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall POKE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall PREMIER_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall QUICK_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall REPEAT_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall ROSEATE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall SAFARI_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall SLATE_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall SPORT_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall TIMER_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall ULTRA_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));
	public static final LootBall VERDANT_LOOT_BALL = new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK));

	// BlockEntity
	public static final BlockEntityType<LootBallEntity> LOOT_BALL_ENTITY = FabricBlockEntityTypeBuilder.create(
			LootBallEntity::new,
			AZURE_LOOT_BALL,
			BEAST_LOOT_BALL,
			CHERISH_LOOT_BALL,
			CITRINE_LOOT_BALL,
			DIVE_LOOT_BALL,
			DREAM_LOOT_BALL,
			DUSK_LOOT_BALL,
			FAST_LOOT_BALL,
			FRIEND_LOOT_BALL,
			GREAT_LOOT_BALL,
			HEAL_LOOT_BALL,
			HEAVY_LOOT_BALL,
			LEVEL_LOOT_BALL,
			LOVE_LOOT_BALL,
			LURE_LOOT_BALL,
			LUXURY_LOOT_BALL,
			MASTER_LOOT_BALL,
			MOON_LOOT_BALL,
			NEST_LOOT_BALL,
			NET_LOOT_BALL,
			PARK_LOOT_BALL,
			POKE_LOOT_BALL,
			PREMIER_LOOT_BALL,
			QUICK_LOOT_BALL,
			REPEAT_LOOT_BALL,
			ROSEATE_LOOT_BALL,
			SAFARI_LOOT_BALL,
			SLATE_LOOT_BALL,
			SPORT_LOOT_BALL,
			TIMER_LOOT_BALL,
			ULTRA_LOOT_BALL,
			VERDANT_LOOT_BALL
	).build();

	// Loot Tables
	public static final Identifier POKE_LOOT_TABLE = new Identifier(MOD_ID, "balls/poke");

	// Group
	private static final ItemGroup LOOTBALLS_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(POKE_LOOT_BALL))
			.displayName(Text.translatable("itemGroup.lootballs.lootballs_group"))
			.entries((displayContext, entries) -> {
				entries.add(new ItemStack(AZURE_LOOT_BALL));
				entries.add(new ItemStack(BEAST_LOOT_BALL));
				entries.add(new ItemStack(CHERISH_LOOT_BALL));
				entries.add(new ItemStack(CITRINE_LOOT_BALL));
				entries.add(new ItemStack(DIVE_LOOT_BALL));
				entries.add(new ItemStack(DREAM_LOOT_BALL));
				entries.add(new ItemStack(DUSK_LOOT_BALL));
				entries.add(new ItemStack(FAST_LOOT_BALL));
				entries.add(new ItemStack(FRIEND_LOOT_BALL));
				entries.add(new ItemStack(GREAT_LOOT_BALL));
				entries.add(new ItemStack(HEAL_LOOT_BALL));
				entries.add(new ItemStack(HEAVY_LOOT_BALL));
				entries.add(new ItemStack(LEVEL_LOOT_BALL));
				entries.add(new ItemStack(LOVE_LOOT_BALL));
				entries.add(new ItemStack(LURE_LOOT_BALL));
				entries.add(new ItemStack(LUXURY_LOOT_BALL));
				entries.add(new ItemStack(MASTER_LOOT_BALL));
				entries.add(new ItemStack(MOON_LOOT_BALL));
				entries.add(new ItemStack(NEST_LOOT_BALL));
				entries.add(new ItemStack(NET_LOOT_BALL));
				entries.add(new ItemStack(PARK_LOOT_BALL));
				entries.add(new ItemStack(POKE_LOOT_BALL));
				entries.add(new ItemStack(PREMIER_LOOT_BALL));
				entries.add(new ItemStack(QUICK_LOOT_BALL));
				entries.add(new ItemStack(REPEAT_LOOT_BALL));
				entries.add(new ItemStack(ROSEATE_LOOT_BALL));
				entries.add(new ItemStack(SAFARI_LOOT_BALL));
				entries.add(new ItemStack(SLATE_LOOT_BALL));
				entries.add(new ItemStack(SPORT_LOOT_BALL));
				entries.add(new ItemStack(TIMER_LOOT_BALL));
				entries.add(new ItemStack(ULTRA_LOOT_BALL));
				entries.add(new ItemStack(VERDANT_LOOT_BALL));
			})
			.build();

	// Player statistic
	public static final Identifier OPEN_LOOT_BALL = new Identifier(MOD_ID, "open_loot_ball");

	// Sounds
	public static final Identifier LOOT_BALL_OPEN_SOUND_ID = new Identifier("lootballs:lootball_open");
	public static SoundEvent LOOT_BALL_OPEN_SOUND_EVENT = SoundEvent.of(LOOT_BALL_OPEN_SOUND_ID);

	// Gamerules
	public static final GameRules.Key<GameRules.BooleanRule> GENERATE_LOOT_BALLS = GameRuleRegistry.register(
			"generateLootBalls", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)
	);
	public static final GameRules.Key<GameRules.BooleanRule> DO_LOOT_BALL_SPAWNING = GameRuleRegistry.register(
			"doLootBallSpawning", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false)
	);
	public static final GameRules.Key<GameRules.BooleanRule> DO_LOOT_BALL_FISHING = GameRuleRegistry.register(
			"doLootBallFishing", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true)
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Registering Loot Balls...");
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "azure_loot_ball"), AZURE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "azure_loot_ball"), new BlockItem(AZURE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "beast_loot_ball"), BEAST_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "beast_loot_ball"), new BlockItem(BEAST_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "cherish_loot_ball"), CHERISH_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "cherish_loot_ball"), new BlockItem(CHERISH_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "citrine_loot_ball"), CITRINE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "citrine_loot_ball"), new BlockItem(CITRINE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "dive_loot_ball"), DIVE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "dive_loot_ball"), new BlockItem(DIVE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "dream_loot_ball"), DREAM_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "dream_loot_ball"), new BlockItem(DREAM_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "dusk_loot_ball"), DUSK_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "dusk_loot_ball"), new BlockItem(DUSK_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "fast_loot_ball"), FAST_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "fast_loot_ball"), new BlockItem(FAST_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "friend_loot_ball"), FRIEND_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "friend_loot_ball"), new BlockItem(FRIEND_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "great_loot_ball"), GREAT_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "great_loot_ball"), new BlockItem(GREAT_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "heal_loot_ball"), HEAL_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "heal_loot_ball"), new BlockItem(HEAL_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "heavy_loot_ball"), HEAVY_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "heavy_loot_ball"), new BlockItem(HEAVY_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "level_loot_ball"), LEVEL_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "level_loot_ball"), new BlockItem(LEVEL_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "love_loot_ball"), LOVE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "love_loot_ball"), new BlockItem(LOVE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "lure_loot_ball"), LURE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "lure_loot_ball"), new BlockItem(LURE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "luxury_loot_ball"), LUXURY_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "luxury_loot_ball"), new BlockItem(LUXURY_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "master_loot_ball"), MASTER_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "master_loot_ball"), new BlockItem(MASTER_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "moon_loot_ball"), MOON_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "moon_loot_ball"), new BlockItem(MOON_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "nest_loot_ball"), NEST_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "nest_loot_ball"), new BlockItem(NEST_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "net_loot_ball"), NET_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "net_loot_ball"), new BlockItem(NET_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "park_loot_ball"), PARK_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "park_loot_ball"), new BlockItem(PARK_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "poke_loot_ball"), POKE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "poke_loot_ball"), new BlockItem(POKE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "premier_loot_ball"), PREMIER_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "premier_loot_ball"), new BlockItem(PREMIER_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "quick_loot_ball"), QUICK_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "quick_loot_ball"), new BlockItem(QUICK_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "repeat_loot_ball"), REPEAT_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "repeat_loot_ball"), new BlockItem(REPEAT_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "roseate_loot_ball"), ROSEATE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "roseate_loot_ball"), new BlockItem(ROSEATE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "safari_loot_ball"), SAFARI_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "safari_loot_ball"), new BlockItem(SAFARI_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "slate_loot_ball"), SLATE_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "slate_loot_ball"), new BlockItem(SLATE_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "sport_loot_ball"), SPORT_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "sport_loot_ball"), new BlockItem(SPORT_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "timer_loot_ball"), TIMER_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "timer_loot_ball"), new BlockItem(TIMER_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "ultra_loot_ball"), ULTRA_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "ultra_loot_ball"), new BlockItem(ULTRA_LOOT_BALL, new FabricItemSettings()));
		Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "verdant_loot_ball"), VERDANT_LOOT_BALL);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "verdant_loot_ball"), new BlockItem(VERDANT_LOOT_BALL, new FabricItemSettings()));
		// Register block entities
		Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "loot_ball_entity"), LOOT_BALL_ENTITY);

		LOGGER.info("Registering Loot Balls misc. features...");
		// Register item groups
		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "lootballs_group"), LOOTBALLS_GROUP);
		// Register statistics
		Registry.register(Registries.CUSTOM_STAT, "open_loot_ball", OPEN_LOOT_BALL);
		Stats.CUSTOM.getOrCreateStat(OPEN_LOOT_BALL, StatFormatter.DEFAULT);
		// Register sound events
		Registry.register(Registries.SOUND_EVENT, Lootballs.LOOT_BALL_OPEN_SOUND_ID, LOOT_BALL_OPEN_SOUND_EVENT);
	}
}