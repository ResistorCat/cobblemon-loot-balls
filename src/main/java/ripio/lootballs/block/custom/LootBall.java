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
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
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
import ripio.lootballs.block.entity.LootBallEntity;
import ripio.lootballs.config.LootBallsConfigs;
import ripio.lootballs.sound.LootBallsSoundEvents;
import ripio.lootballs.stat.LootBallsStats;

public class LootBall extends HorizontalFacingBlock implements Waterloggable, BlockEntityProvider {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final BooleanProperty HIDDEN = BooleanProperty.of("hidden");
    public static final BooleanProperty WAXED = BooleanProperty.of("waxed");
    private float multiplier = 1.0F;

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
        this.multiplier = 1.0F;
        boolean hidden = (random.nextFloat() <= LootBallsConfigs.HIDDEN_CHANCE);
        if (hidden) this.multiplier = LootBallsConfigs.HIDDEN_MULTIPLIER;
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
        return new LootBallEntity(pos, state, this.multiplier);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WATERLOGGED, HIDDEN, WAXED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
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

            // Game mode check
            if (player.isCreative()) {
                /// Creative mode

                if (handItem.isEmpty()) {
                    // Toggle visibility (empty hand)
                    world.setBlockState(pos, state.with(HIDDEN, !state.get(HIDDEN)));
                    player.sendMessage(Text.translatable("block.lootballs.loot_ball.visibility").formatted(Formatting.AQUA), true);
                    player.playSound(SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.BLOCKS, 0.5F, 1.0F);
                } else if (handItem.isOf(Items.HONEYCOMB)) {
                    // Toggle sparks (wax with honeycomb)
                    boolean isWaxed = state.get(WAXED);
                    SoundEvent waxSound = isWaxed ? SoundEvents.ITEM_AXE_WAX_OFF : SoundEvents.ITEM_HONEYCOMB_WAX_ON;
                    MutableText waxMsg = Text.literal("")
                            .append( Text.translatable("block.lootballs.loot_ball.wax").formatted(Formatting.AQUA) )
                            .append(isWaxed ?
                                    Text.literal("ON").formatted(Formatting.GREEN).formatted(Formatting.BOLD)
                                    :
                                    Text.literal("OFF").formatted(Formatting.RED).formatted(Formatting.BOLD)
                            );

                    world.setBlockState(pos, state.with(WAXED, !isWaxed));
                    player.sendMessage(waxMsg, true);
                    player.playSound(waxSound, SoundCategory.BLOCKS, 0.5F, 1.0F);
                } else {
                    // Set Loot Ball loot (stack in hand)
                    MutableText lootMsg = Text.literal("")
                            .append( Text.translatable("block.lootballs.loot_ball.set_loot").formatted(Formatting.AQUA) )
                            .append( Text.literal(String.valueOf(handItem.getCount())).formatted(Formatting.YELLOW) )
                            .append( Text.literal(" ") )
                            .append( Text.literal(handItem.getName().getString()).formatted(Formatting.GREEN).formatted(Formatting.BOLD) );

                    blockEntity.setStack(0, handItem);
                    player.sendMessage(lootMsg, true);
                    player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.BLOCKS, 0.5F, 1.0F);
                }

            } else if (!player.isCreative() && !player.isSpectator()) {
                /// Survival, adventure and hardcore mode

                if (!blockEntity.getStack(0).isEmpty()) {
                    // Loot ball has items to retrieve

                    // STEP 1: Check if player already opened this loot (multiplayer).
                    boolean alreadyOpen = false;
                    if (LootBallsConfigs.PER_PLAYER_LOOTBALLS) {
                        if (blockEntity.isOpener(player.getUuid())) {
                            alreadyOpen = true;
                            player.sendMessage( Text.translatable("block.lootballs.loot_ball.already_open").formatted(Formatting.RED).formatted(Formatting.BOLD) );
                        }
                    }

                    // STEP 2: Loot
                    if (!alreadyOpen) {
                        float multiplier = blockEntity.getMultiplier();
                        ItemStack lootStack = blockEntity.copyStack(0);
                        MutableText lootMsg = Text.literal("");

                        if (multiplier > 1.0F) {
                            lootStack = lootStack.copyWithCount((int) (lootStack.getCount() * multiplier));
                            lootMsg = lootMsg
                                    .append( Text.translatable("block.lootballs.loot_ball.bonus_loot").formatted(Formatting.LIGHT_PURPLE) )
                                    .append( Text.literal(" [").formatted(Formatting.WHITE) )
                                    .append( Text.literal(String.valueOf(multiplier).formatted(Formatting.GREEN)).formatted(Formatting.BOLD) )
                                    .append( Text.literal("]: ").formatted(Formatting.WHITE) );
                        }
                        lootMsg = lootMsg
                                .append( Text.translatable("block.lootballs.loot_ball.open").formatted(Formatting.AQUA) )
                                .append( Text.literal(String.valueOf(lootStack.getCount())).formatted(Formatting.YELLOW) )
                                .append( Text.literal(" ") )
                                .append( Text.literal(lootStack.getName().getString()).formatted(Formatting.GREEN).formatted(Formatting.BOLD) );

                        player.getInventory().offerOrDrop(lootStack);
                        player.incrementStat(LootBallsStats.OPEN_LOOT_BALL_STAT_ID);

                        if (LootBallsConfigs.PER_PLAYER_LOOTBALLS) {
                            blockEntity.addOpener(player.getUuid());
                            if (!LootBallsConfigs.IGNORE_PER_PLAYER_LOOTBALLS_USES) {
                                blockEntity.setUses(blockEntity.getUses() - 1);
                            }
                        } else {
                            blockEntity.setUses(blockEntity.getUses() - 1);
                        }

                        if (blockEntity.getUses() <= 0) {
                            world.removeBlock(pos, false);
                        }

                        player.playSound(LootBallsSoundEvents.LOOT_BALL_OPEN_SOUND_EVENT, SoundCategory.BLOCKS, 0.7F, 1.0F);
                        player.sendMessage(lootMsg, true);
                    }
                }
            }

        }
        return ActionResult.SUCCESS;
    }
}
