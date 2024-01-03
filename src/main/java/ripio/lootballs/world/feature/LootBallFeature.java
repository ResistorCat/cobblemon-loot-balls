package ripio.lootballs.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import ripio.lootballs.block.entity.LootBallEntity;

import static ripio.lootballs.block.custom.LootBall.HIDDEN;

public class LootBallFeature extends Feature<LootBallFeatureConfig> {
    public LootBallFeature(Codec<LootBallFeatureConfig> configCodec){
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<LootBallFeatureConfig> context) {
        // Get context
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        LootBallFeatureConfig config = context.getConfig();

        if (world.getServer() != null) {
            // Get config parameters
            Identifier blockId = config.blockId();
            Identifier lootTableId = config.lootTableId();
            BlockState blockState = Registries.BLOCK.get(blockId).getDefaultState()
                    .with(Properties.HORIZONTAL_FACING, Direction.fromHorizontal(random.nextInt(3)))
                    .with(HIDDEN,random.nextBoolean());
            LootTable lootTable = world.getServer().getLootManager().getLootTable(lootTableId);
            if (blockState == null) throw new IllegalStateException(blockId + " could not be parsed to a valid block identifier!");
            // Get surface of the world
            BlockPos testPos = new BlockPos(origin);
            for (int y = 0; y < world.getHeight(); y++){
                testPos = testPos.up();
                if (testPos.getY() >= world.getTopY()) break;
                if (world.getBlockState(testPos).isOf(Blocks.AIR)) {
                    if (world.getBlockState(testPos.down()).isIn(BlockTags.DIRT)) {
                        world.setBlockState(testPos, blockState, Block.NOTIFY_LISTENERS);
                        LootBallEntity blockEntity = (LootBallEntity) world.getBlockEntity(testPos);
                        assert blockEntity != null;
                        blockEntity.setLootTable(lootTableId, random.nextLong());
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
