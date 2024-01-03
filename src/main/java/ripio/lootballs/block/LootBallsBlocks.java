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
import ripio.lootballs.block.custom.LootBall;
import ripio.lootballs.Lootballs;

public class LootBallsBlocks {
    // Loot Balls
    public static final LootBall AZURE_LOOT_BALL = (LootBall) registerBlock("azure_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall BEAST_LOOT_BALL = (LootBall) registerBlock("beast_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall CHERISH_LOOT_BALL = (LootBall) registerBlock("cherish_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall CITRINE_LOOT_BALL = (LootBall) registerBlock("citrine_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall DIVE_LOOT_BALL = (LootBall) registerBlock("dive_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall DREAM_LOOT_BALL = (LootBall) registerBlock("dream_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall DUSK_LOOT_BALL = (LootBall) registerBlock("dusk_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall FAST_LOOT_BALL = (LootBall) registerBlock("fast_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall FRIEND_LOOT_BALL = (LootBall) registerBlock("friend_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall GREAT_LOOT_BALL = (LootBall) registerBlock("great_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall HEAL_LOOT_BALL = (LootBall) registerBlock("heal_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall HEAVY_LOOT_BALL = (LootBall) registerBlock("heavy_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall LEVEL_LOOT_BALL = (LootBall) registerBlock("level_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall LOVE_LOOT_BALL = (LootBall) registerBlock("love_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall LURE_LOOT_BALL = (LootBall) registerBlock("lure_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall LUXURY_LOOT_BALL = (LootBall) registerBlock("luxury_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall MASTER_LOOT_BALL = (LootBall) registerBlock("master_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall MOON_LOOT_BALL = (LootBall) registerBlock("moon_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall NEST_LOOT_BALL = (LootBall) registerBlock("nest_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall NET_LOOT_BALL = (LootBall) registerBlock("net_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall PARK_LOOT_BALL = (LootBall) registerBlock("park_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall POKE_LOOT_BALL = (LootBall) registerBlock("poke_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall PREMIER_LOOT_BALL = (LootBall) registerBlock("premier_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall QUICK_LOOT_BALL = (LootBall) registerBlock("quick_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall REPEAT_LOOT_BALL = (LootBall) registerBlock("repeat_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall ROSEATE_LOOT_BALL = (LootBall) registerBlock("roseate_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall SAFARI_LOOT_BALL = (LootBall) registerBlock("safari_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall SLATE_LOOT_BALL = (LootBall) registerBlock("slate_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall SPORT_LOOT_BALL = (LootBall) registerBlock("sport_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall TIMER_LOOT_BALL = (LootBall) registerBlock("timer_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall ULTRA_LOOT_BALL = (LootBall) registerBlock("ultra_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final LootBall VERDANT_LOOT_BALL = (LootBall) registerBlock("verdant_loot_ball",new LootBall(FabricBlockSettings.copyOf(Blocks.BEDROCK)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Lootballs.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Lootballs.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Lootballs.LOGGER.info("Registering blocks for " + Lootballs.MOD_ID);
    }
}
