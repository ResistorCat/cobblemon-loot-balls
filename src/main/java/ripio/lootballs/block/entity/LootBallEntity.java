package ripio.lootballs.block.entity;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import static ripio.lootballs.block.LootBallsBlockEntities.LOOT_BALL_ENTITY;

public class LootBallEntity extends BlockEntity implements ImplementedInventory, SidedInventory {
    private DefaultedList<ItemStack> items = DefaultedList.ofSize(1,ItemStack.EMPTY);
    public LootBallEntity(BlockPos pos, BlockState state) {
        super(LOOT_BALL_ENTITY, pos, state);
    }

    public static final String LOOT_TABLE_KEY = "LootTable";
    public static final String LOOT_TABLE_SEED_KEY = "LootTableSeed";
    @Nullable
    protected Identifier lootTableId;
    protected long lootTableSeed;

    public static void setLootTable(BlockView world, Random random, BlockPos pos, Identifier id) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof LootBallEntity) {
            ((LootBallEntity)blockEntity).setLootTable(id, random.nextLong());
        }
    }
    public void setLootTable(Identifier id, long seed) {
        this.lootTableId = id;
        this.lootTableSeed = seed;
    }

    protected boolean deserializeLootTable(NbtCompound nbt) {
        if (nbt.contains(LOOT_TABLE_KEY, NbtElement.STRING_TYPE)) {
            this.lootTableId = new Identifier(nbt.getString(LOOT_TABLE_KEY));
            this.lootTableSeed = nbt.getLong(LOOT_TABLE_SEED_KEY);
            return true;
        }
        return false;
    }

    protected boolean serializeLootTable(NbtCompound nbt) {
        if (this.lootTableId == null) {
            return false;
        }
        nbt.putString(LOOT_TABLE_KEY, this.lootTableId.toString());
        if (this.lootTableSeed != 0L) {
            nbt.putLong(LOOT_TABLE_SEED_KEY, this.lootTableSeed);
        }
        return true;
    }

    @Override
    public void checkLootInteraction(@Nullable PlayerEntity player) {
        if (this.lootTableId != null && this.world.getServer() != null) {
            LootTable lootTable = this.world.getServer().getLootManager().getLootTable(this.lootTableId);
            if (player instanceof ServerPlayerEntity) {
                Criteria.PLAYER_GENERATES_CONTAINER_LOOT.trigger((ServerPlayerEntity)player, this.lootTableId);
            }
            this.lootTableId = null;
            LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld)this.world).add(LootContextParameters.ORIGIN, Vec3d.ofCenter(this.pos));
            if (player != null) {
                builder.luck(player.getLuck()).add(LootContextParameters.THIS_ENTITY, player);
            }
            lootTable.supplyInventory(this, builder.build(LootContextTypes.CHEST), this.lootTableSeed);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        //Inventories.readNbt(nbt, items);
        this.items = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.items);
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        //Inventories.writeNbt(nbt, items);
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.items);
        }
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        // Just return an array of all slots
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction direction) {
        return false;
    }

}
