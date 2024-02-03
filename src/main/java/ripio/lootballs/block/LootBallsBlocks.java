package ripio.lootballs.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ripio.lootballs.LootBalls;
import ripio.lootballs.block.custom.LootBallBlock;

public class LootBallsBlocks {
    // Loot Balls
    public static final LootBallBlock AZURE_LOOT_BALL = (LootBallBlock) registerBlock("azure_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock BEAST_LOOT_BALL = (LootBallBlock) registerBlock("beast_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock CHERISH_LOOT_BALL = (LootBallBlock) registerBlock("cherish_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock CITRINE_LOOT_BALL = (LootBallBlock) registerBlock("citrine_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock DIVE_LOOT_BALL = (LootBallBlock) registerBlock("dive_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock DREAM_LOOT_BALL = (LootBallBlock) registerBlock("dream_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock DUSK_LOOT_BALL = (LootBallBlock) registerBlock("dusk_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock FAST_LOOT_BALL = (LootBallBlock) registerBlock("fast_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock FRIEND_LOOT_BALL = (LootBallBlock) registerBlock("friend_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock GREAT_LOOT_BALL = (LootBallBlock) registerBlock("great_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock HEAL_LOOT_BALL = (LootBallBlock) registerBlock("heal_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock HEAVY_LOOT_BALL = (LootBallBlock) registerBlock("heavy_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock LEVEL_LOOT_BALL = (LootBallBlock) registerBlock("level_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock LOVE_LOOT_BALL = (LootBallBlock) registerBlock("love_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock LURE_LOOT_BALL = (LootBallBlock) registerBlock("lure_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock LUXURY_LOOT_BALL = (LootBallBlock) registerBlock("luxury_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock MASTER_LOOT_BALL = (LootBallBlock) registerBlock("master_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock MOON_LOOT_BALL = (LootBallBlock) registerBlock("moon_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock NEST_LOOT_BALL = (LootBallBlock) registerBlock("nest_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock NET_LOOT_BALL = (LootBallBlock) registerBlock("net_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock PARK_LOOT_BALL = (LootBallBlock) registerBlock("park_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock POKE_LOOT_BALL = (LootBallBlock) registerBlock("poke_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock PREMIER_LOOT_BALL = (LootBallBlock) registerBlock("premier_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock QUICK_LOOT_BALL = (LootBallBlock) registerBlock("quick_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock REPEAT_LOOT_BALL = (LootBallBlock) registerBlock("repeat_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock ROSEATE_LOOT_BALL = (LootBallBlock) registerBlock("roseate_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock SAFARI_LOOT_BALL = (LootBallBlock) registerBlock("safari_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock SLATE_LOOT_BALL = (LootBallBlock) registerBlock("slate_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock SPORT_LOOT_BALL = (LootBallBlock) registerBlock("sport_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock TIMER_LOOT_BALL = (LootBallBlock) registerBlock("timer_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock ULTRA_LOOT_BALL = (LootBallBlock) registerBlock("ultra_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBallBlock VERDANT_LOOT_BALL = (LootBallBlock) registerBlock("verdant_loot_ball",new LootBallBlock(FabricBlockSettings.copyOf(Blocks.BEDROCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(LootBalls.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(LootBalls.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        LootBalls.LOGGER.info("Registering blocks for " + LootBalls.MOD_ID);
    }
}
