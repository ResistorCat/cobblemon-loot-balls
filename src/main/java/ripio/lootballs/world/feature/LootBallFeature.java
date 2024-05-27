package ripio.lootballs.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import ripio.lootballs.LootBalls;
import ripio.lootballs.block.custom.LootBallBlock;
import ripio.lootballs.block.entity.LootBallEntity;
import ripio.lootballs.datagen.LootBallsBlockTagProvider;
import ripio.lootballs.util.LootBallsUtils;
import ripio.lootballs.world.LootBallsGamerules;

import java.util.List;

public class LootBallFeature extends Feature<LootBallFeatureConfig> {
    public LootBallFeature(Codec<LootBallFeatureConfig> configCodec){
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<LootBallFeatureConfig> context) {
        // Get context
        Random random = context.getRandom();
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        LootBallFeatureConfig config = context.getConfig();

        // Check if server is null
        if (world.getServer() != null) {
            // Check if Gamerule is false
            if (!world.getServer().getGameRules().getBoolean(LootBallsGamerules.GENERATE_LOOT_BALLS)) return false;
            // Decide lootball attempts for this chunk
            int placements = random.nextBetween(LootBalls.CONFIG.minLootBallsPerChunk(), LootBalls.CONFIG.maxLootBallsPerChunk());
            // Place loot balls
            for (int i = 0; i < placements; i++) {
                placeLootBall(world, origin, random, config);
            }
            return true;
        }

        return false;
    }
    private void placeLootBall(StructureWorldAccess world, BlockPos origin, Random random, LootBallFeatureConfig config) {
        // Check rarity chance
        if (random.nextFloat() > LootBalls.CONFIG.generationChance()) return;

        // Get a random loot ball and loot table
        Identifier blockId = LootBallsUtils.randomLootBall(random, world.getBiome(origin));
        if (blockId == null) return;
        Identifier lootTableId = LootBallsUtils.getLootBallLootTable(blockId);
        if (lootTableId == null) return;

        // Get LootBall BiomeTags, BlockTags, HeightRange
        TagKey<Biome> biomeTag = LootBallsUtils.getLootBallBiomeTags(blockId, world.getBiome(origin));
        TagKey<Block>[] blockTags = LootBallsUtils.getLootBallBlockTags(blockId);
        List<Integer> heightRange = LootBallsUtils.getLootBallHeightRange(blockId, world);

        // Get a random position within chunk
        BlockPos testPos = origin.add(
                random.nextBetween(0, 15),
                random.nextBetween(heightRange.get(0), heightRange.get(1)),
                random.nextBetween(0, 15)
        );
        BlockState floorState;
        for (int y = testPos.getY(); y > heightRange.get(0); y--){
            testPos = testPos.down();
            // Check if out of range
            if (testPos.getY() <= heightRange.get(0) || testPos.getY() >= heightRange.get(1)) return;
            // Check if not in loot ball biome
            if (!world.getBiome(testPos).isIn(biomeTag)) continue;

            // Check floor tags
            floorState = world.getBlockState(testPos.down());
            if (blockOf(floorState, blockTags)) {
                // Valid floor; check valid position
                if (world.getBlockState(testPos).isIn(LootBallsBlockTagProvider.LOOT_BALLS_REPLACEABLES)) {
                    // Get Loot Ball State
                    BlockState ballState = ((LootBallBlock) Registries.BLOCK.get(blockId)).getGenerationState(random, testPos, world);
                    if (ballState == null) throw new IllegalStateException(blockId + "could could not be parsed to a valid block identifier!");
                    // Place Loot Ball
                    world.setBlockState(testPos, ballState, Block.NOTIFY_LISTENERS);
                    LootBallEntity.setLootTable(world, random, testPos, lootTableId);
                    return;
                }
            }
        }
    }
    private boolean blockOf(BlockState floorState, TagKey<Block>[] tagKeys) {
        for (TagKey<Block> tagKey : tagKeys) {
            if (floorState.isIn(tagKey)) return true;
        }
        return false;
    }
}
