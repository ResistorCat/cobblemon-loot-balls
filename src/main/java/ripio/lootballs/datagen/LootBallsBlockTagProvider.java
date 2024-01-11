package ripio.lootballs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import ripio.lootballs.Lootballs;

import java.util.concurrent.CompletableFuture;

public class LootBallsBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<Block> LOOT_BALLS_BASE_BLOCKS = TagKey.of(
            RegistryKeys.BLOCK,
            new Identifier(Lootballs.MOD_ID, "loot_balls_base_blocks")
    );

    public static final TagKey<Block> LOOT_BALLS_REPLACEABLES = TagKey.of(
            RegistryKeys.BLOCK,
            new Identifier(Lootballs.MOD_ID, "loot_balls_replaceables")
    );

    public LootBallsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(LOOT_BALLS_BASE_BLOCKS)
                .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
                .forceAddTag(BlockTags.DIRT)
                .forceAddTag(BlockTags.SAND)
                .forceAddTag(BlockTags.TERRACOTTA)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(BlockTags.COPPER_ORES)
                .forceAddTag(BlockTags.SNOW)
                .forceAddTag(BlockTags.ICE)
                .forceAddTag(BlockTags.BASE_STONE_NETHER)
                .forceAddTag(BlockTags.SOUL_FIRE_BASE_BLOCKS)
                .add(Blocks.GRAVEL)
                .add(Blocks.SUSPICIOUS_GRAVEL)
                .add(Blocks.SANDSTONE)
                .add(Blocks.RED_SANDSTONE)
                .add(Blocks.CALCITE)
                .add(Blocks.END_STONE)
                .add(Blocks.END_STONE_BRICKS)
                .add(Blocks.PURPUR_BLOCK)
                .add(Blocks.NETHERRACK);

        getOrCreateTagBuilder(LOOT_BALLS_REPLACEABLES)
                .forceAddTag(BlockTags.REPLACEABLE_BY_TREES)
                .add(Blocks.AIR)
                .add(Blocks.BUBBLE_COLUMN)
                .add(Blocks.CAVE_AIR)
                .add(Blocks.FIRE)
                .add(Blocks.GRASS)
                .add(Blocks.SNOW)
                .add(Blocks.SOUL_FIRE)
                .add(Blocks.STRUCTURE_VOID)
                .add(Blocks.VOID_AIR);
    }

}
