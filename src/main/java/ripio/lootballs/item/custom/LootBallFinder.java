package ripio.lootballs.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ripio.lootballs.block.custom.LootBall;
import ripio.lootballs.datagen.LootBallsBlockTagProvider;

public class LootBallFinder extends Item {
    public LootBallFinder(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            BlockPos origin = user.getBlockPos().up();
            BlockPos blockPos = searchForLootBall(origin, world);
            if (blockPos == null) {
                // Nothing found
                user.sendMessage(Text.literal("No hidden loot balls found!"), true);
            } else {
                // Found a lootball
                double distance = origin.getSquaredDistance(blockPos);
                if (distance < 64) {
                    user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.75F, 1.0F);
                    user.sendMessage(Text.literal("A hidden loot ball is VERY near!"), true);
                } else if (distance < 256) {
                    user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.4F, 1.0F);
                    user.sendMessage(Text.literal("A hidden loot ball is near!"), true);
                } else {
                    user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.25F, 1.0F);
                    user.sendMessage(Text.literal("A hidden loot ball was detected within 2 chunks!"), true);
                }
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Nullable
    private BlockPos searchForLootBall(BlockPos origin, World world) {
        // Find nearest point to origin
        BlockPos bestPos = null;
        double squaredDistance = origin.add(-32, -32, -32).getSquaredDistance(origin);
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                for (int k = 0; k < 64; k++) {
                    BlockPos testPos = origin.add(i - 32, j - 32, k - 32);
                    double testDistance = testPos.getSquaredDistance(origin);
                    if (testDistance < squaredDistance){
                        BlockState state = world.getBlockState(testPos);
                        if (isLootBall(state)) {
                            bestPos = testPos;
                            squaredDistance = testDistance;
                        }
                    }
                }
            }
        }
        return bestPos;
    }

    private boolean isLootBall(BlockState state) {
        if (state.isIn(LootBallsBlockTagProvider.LOOT_BALLS_BLOCKS)){
            return state.get(LootBall.HIDDEN);
        }
        return false;
    }
}