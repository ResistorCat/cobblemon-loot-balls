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
import ripio.lootballs.block.custom.LootBall;
import ripio.lootballs.block.entity.LootBallEntity;
import ripio.lootballs.datagen.LootBallsBlockTagProvider;
import ripio.lootballs.util.LootBallsUtils;
import ripio.lootballs.world.LootBallsGamerules;

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
            /// STEP 1: Pre-generation conditions
            // Check if Gamerule is false
            if (!world.getServer().getGameRules().getBoolean(LootBallsGamerules.GENERATE_LOOT_BALLS)) return false;

            // Get config parameters
            float chance = config.chance();

            // Check chance
            if (random.nextFloat() >= chance) return false;

            // Get block id
            Identifier blockId = LootBallsUtils.randomLootBall(random, world.getBiome(origin));
            if (blockId == null) return false;

            // Get loot table id from blockid
            Identifier lootTableId = LootBallsUtils.getLootBallLootTable(blockId);
            if (lootTableId == null) return false;

            // Get LootBall BiomeTags
            TagKey<Biome> biomeTag = LootBallsUtils.getLootBallBiomeTags(blockId, world.getBiome(origin));

            // Get LootBall BlockTags
            TagKey<Block>[] blockTags = LootBallsUtils.getLootBallBlockTags(blockId);

            /// STEP 2: Natural Generation
            // Get Loot Ball State
            BlockState ballState = ((LootBall) Registries.BLOCK.get(blockId)).getGenerationState(random);
            if (ballState == null) throw new IllegalStateException(blockId + "could could not be parsed to a valid block identifier!");

            // Get a valid position
            BlockPos testPos = new BlockPos(origin);
            testPos = testPos.withY(random.nextBetween(world.getBottomY(), world.getTopY()));
            BlockState floorState;

            for (int y = testPos.getY(); y > 0; y--){
                testPos = testPos.down();
                floorState = world.getBlockState(testPos.down());

                if (testPos.getY() >= world.getTopY()) break;
                if (!world.getBiome(testPos).isIn(biomeTag)) continue;

                if (blockOf(floorState, blockTags)) {
                    if (world.getBlockState(testPos).isIn(LootBallsBlockTagProvider.LOOT_BALLS_REPLACEABLES)) {
                        world.setBlockState(testPos, ballState, Block.NOTIFY_LISTENERS);
                        LootBallEntity.setLootTable(world, random, testPos, lootTableId);
                        // DEBUG: Lootballs.LOGGER.info("LOOTBALL: " + blockId.getPath() + " " + testPos.toShortString());
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean blockOf(BlockState floorState, TagKey<Block>[] tagKeys) {
        for (TagKey<Block> tagKey : tagKeys) {
            if (floorState.isIn(tagKey)) return true;
        }
        return false;
    }
}
