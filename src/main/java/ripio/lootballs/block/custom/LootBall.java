package ripio.lootballs.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import ripio.lootballs.Lootballs;
import ripio.lootballs.block.entity.LootBallEntity;
import ripio.lootballs.config.LootBallsConfigs;
import ripio.lootballs.sound.LootBallsSoundEvents;
import ripio.lootballs.stat.LootBallsStats;

public class LootBall extends HorizontalFacingBlock implements Waterloggable, BlockEntityProvider {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty HIDDEN = BooleanProperty.of("hidden");
    public static final BooleanProperty WAXED = BooleanProperty.of("waxed");
    private boolean doubleLoot = false;

    public LootBall(Settings settings) {
        super(settings.nonOpaque().noBlockBreakParticles());
        setDefaultState(getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
                .with(WATERLOGGED, false)
                .with(HIDDEN, false)
                .with(WAXED,false)
        );
    }

    public BlockState getGenerationState(Random random, BlockPos pos, StructureWorldAccess world) {
        // 20% Chance to be hidden on generation
        this.doubleLoot = false;
        boolean hidden = (random.nextFloat() <= 0.2F);
        if (hidden && LootBallsConfigs.NATURAL_DOUBLE_LOOT) this.doubleLoot = true;
        return this.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, Direction.fromHorizontal(random.nextInt(3)))
                .with(HIDDEN, hidden)
                .with(WATERLOGGED, world.getFluidState(pos).isOf(Fluids.WATER));
    }

    @Override
    public void randomDisplayTick(
            BlockState state,
            World world,
            BlockPos pos,
            Random random
    ) {
        if (state.get(HIDDEN) & !state.get(WAXED)) {
            if (random.nextInt(18) == 0) {
                // Generate 3 sparks
                for (int i = 0; i < 3; i++) {
                    double d = (double) pos.getX() + 0.9 - (double) (random.nextFloat() * 0.8f);
                    double e = (double) pos.getY() + 0.6 - (double) (random.nextFloat() * 0.1f);
                    double f = (double) pos.getZ() + 0.9 - (double) (random.nextFloat() * 0.8f);
                    world.addParticle(
                            ParticleTypes.ELECTRIC_SPARK,
                            d,
                            e,
                            f,
                            random.nextGaussian() * 0.1,
                            random.nextGaussian() * 0.1,
                            random.nextGaussian() * 0.1
                    );
                }
            }
        }
    }

    @Override
    public BlockEntity createBlockEntity(
            BlockPos pos,
            BlockState state) {
        return new LootBallEntity(pos, state, this.doubleLoot);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WATERLOGGED, HIDDEN, WAXED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState()
                .with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            WorldAccess world,
            BlockPos pos,
            BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public VoxelShape getOutlineShape(
            BlockState state,
            BlockView world,
            BlockPos pos,
            ShapeContext context) {
        return VoxelShapes.cuboid(0.25, 0.0, 0.25, 0.75, 0.5, 0.75);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        if (state.get(HIDDEN)) {
            return BlockRenderType.INVISIBLE;
        }
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit) {
        if (!world.isClient) {
            LootBallEntity blockEntity = (LootBallEntity) world.getBlockEntity(pos);
            assert blockEntity != null;
            ItemStack handItem = player.getStackInHand(hand).copy();

            if ( player.getStackInHand(hand).isEmpty() & player.isCreative() ) {
                player.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.PLAYERS, 0.4F, 1.0F);
                world.setBlockState(pos, state.with(HIDDEN, !state.get(HIDDEN)));
                player.sendMessage(Text.translatable("block.lootballs.loot_ball.visibility"), true);
            } else if (player.isCreative() & handItem.isOf(Items.HONEYCOMB) & state.get(HIDDEN)) {
                // Toggle waxed
                Boolean isWaxed = !state.get(WAXED);
                world.setBlockState(pos, state.with(WAXED, isWaxed));
                SoundEvent waxSnd = isWaxed ? SoundEvents.ITEM_HONEYCOMB_WAX_ON : SoundEvents.ITEM_AXE_WAX_OFF;
                player.playSound(waxSnd, SoundCategory.PLAYERS, 0.4F,1.0F);
                String waxMsg = Text.translatable("block.lootballs.loot_ball.wax").getString() + (isWaxed ? "OFF" : "ON");
                player.sendMessage(Text.of(waxMsg), true);
            } else if (player.isCreative()) {
                // Set loot to full stack in hand
                player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.4F, 1.0F);
                String lootMsg = Text.translatable("block.lootballs.loot_ball.set_loot").getString() + handItem.getCount() + " " + handItem.getItem().getName().getString();
                player.sendMessage(Text.of(lootMsg), true);
                blockEntity.setStack(0, handItem);
            } else {
                if (!player.isCreative() & !player.isSpectator() & !blockEntity.getStack(0).isEmpty()) {
                    // Check if per player lootballs is true
                    boolean canOpen = true;
                    if (LootBallsConfigs.PER_PLAYER_LOOTBALLS) {
                        if (blockEntity.isOpener(player.getUuid())) {
                            canOpen = false;
                            player.sendMessage(Text.translatable("block.lootballs.loot_ball.already_open"), true);
                        }
                    }

                    if (canOpen) {
                        // Open loot
                        player.playSound(LootBallsSoundEvents.LOOT_BALL_OPEN_SOUND_EVENT, SoundCategory.PLAYERS, 0.5F, 1.0F);
                        ItemStack lootItem = blockEntity.getStack(0);
                        blockEntity.setStack(0,lootItem.copy()); // Workaround for re-stock the loot ball.
                        String lootMsg = Text.translatable("block.lootballs.loot_ball.open").getString() + lootItem.getCount() + " " + lootItem.getItem().getName().getString();
                        if (blockEntity.hasDoubleLoot()) {
                            lootItem = lootItem.copyWithCount(lootItem.getCount() * 2);
                            lootMsg = Text.translatable("block.lootballs.loot_ball.double_loot").getString() + Text.translatable("block.lootballs.loot_ball.open").getString() + lootItem.getCount() + " " + lootItem.getItem().getName().getString();
                        }
                        player.sendMessage(Text.of(lootMsg),true);
                        player.getInventory().offerOrDrop(lootItem);
                        player.incrementStat(LootBallsStats.OPEN_LOOT_BALL_STAT_ID);
                        blockEntity.setUses(blockEntity.getUses() - 1);

                        if (LootBallsConfigs.PER_PLAYER_LOOTBALLS) {
                            blockEntity.addOpener(player.getUuid());
                            if (!LootBallsConfigs.IGNORE_PER_PLAYER_LOOTBALLS_USES) {
                                if (blockEntity.getUses() <= 0) {
                                    world.removeBlock(pos, false);
                                }
                            }
                        } else {
                            if (blockEntity.getUses() <= 0) {
                                world.removeBlock(pos, false);
                            }
                        }

                    }

                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
