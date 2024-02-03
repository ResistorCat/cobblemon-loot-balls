package ripio.lootballs.item.custom;

import com.cobblemon.mod.common.CobblemonSounds;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ripio.lootballs.block.custom.LootBallBlock;
import ripio.lootballs.datagen.LootBallsBlockTagProvider;

import java.util.List;

public class LootBallFinder extends Item {
    private boolean onCooldown = false;
    public LootBallFinder(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            BlockPos origin = user.getBlockPos().up();
            BlockPos blockPos = searchForLootBall(origin, world);
            if (blockPos == null) {
                // Nothing found
                user.sendMessage(Text.translatable("item.lootballs.loot_ball_finder.not_found").formatted(Formatting.GRAY), true);
                user.playSound(CobblemonSounds.PC_OFF, SoundCategory.PLAYERS, 0.5F, 1.0F);
            } else {
                // Found a lootball
                double distance = origin.getSquaredDistance(blockPos);
                if (distance < 64) {
                    // Spawn particles in block and play sound
                    Vec3d centerPos = blockPos.toCenterPos();
                    world.addParticle(ParticleTypes.WITCH, centerPos.getX(), centerPos.getY(), centerPos.getZ(), 0.0, 0.5,0.0);
                    world.addParticle(ParticleTypes.WITCH, centerPos.getX(), centerPos.getY(), centerPos.getZ(), 0.3, 0.3,0.3);
                    world.addParticle(ParticleTypes.WITCH, centerPos.getX(), centerPos.getY(), centerPos.getZ(), -0.3, 0.3,-0.3);

                    world.playSoundAtBlockCenter(blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F, true);

                    user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.85F, 1.0F);
                    user.sendMessage(Text.translatable("item.lootballs.loot_ball_finder.very_near").formatted(Formatting.LIGHT_PURPLE), true);
                } else if (distance < 256) {
                    user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.55F, 1.0F);
                    user.sendMessage(Text.translatable("item.lootballs.loot_ball_finder.near").formatted(Formatting.YELLOW), true);
                } else {
                    user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.35F, 1.0F);
                    user.sendMessage(Text.translatable("item.lootballs.loot_ball_finder.chunks").formatted(Formatting.AQUA), true);
                }
            }
            // Set cooldown to 5 seconds
            user.getItemCooldownManager().set(this, 100);
            this.onCooldown = true;
        }

        return TypedActionResult.success(user.getStackInHand(hand), world.isClient());
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
            return state.get(LootBallBlock.HIDDEN);
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) {
            if (entity.isPlayer()) {
                PlayerEntity player = (PlayerEntity) entity;
                if (this.onCooldown && !player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                    this.onCooldown = false;
                    if (selected) player.playSound(CobblemonSounds.PC_ON, SoundCategory.PLAYERS, 0.75F, 1.0F);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.lootballs.loot_ball_finder.tooltip").formatted(Formatting.YELLOW));
    }
}