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
import ripio.lootballs.config.LootBallsConfigs;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static ripio.lootballs.block.LootBallsBlockEntities.LOOT_BALL_ENTITY;

public class LootBallEntity extends BlockEntity implements ImplementedInventory, SidedInventory {
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(1,ItemStack.EMPTY);
    public static final String LOOT_TABLE_KEY = "LootTable";
    public static final String LOOT_TABLE_SEED_KEY = "LootTableSeed";
    public static final String USES_KEY = "Uses";
    public static final String OPENERS_KEY = "Openers";
    public static final String INFINITE_KEY = "Infinite";
    public static final String MULTIPLIER_KEY = "Multiplier";
    @Nullable
    protected Identifier lootTableId;
    protected long lootTableSeed;
    protected int uses = LootBallsConfigs.USES_PER_LOOTBALL;
    protected Set<UUID> openers = new HashSet<>();
    protected boolean infinite = false;
    protected float multiplier = 1.0F;
    public LootBallEntity(BlockPos pos, BlockState state, float multiplier) {
        super(LOOT_BALL_ENTITY, pos, state);
        this.multiplier = multiplier;
    }

    public LootBallEntity(BlockPos pos, BlockState state) {
        super(LOOT_BALL_ENTITY, pos, state);
    }

    public static void setLootTable(BlockView world, Random random, BlockPos pos, Identifier id) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof LootBallEntity) {
            ((LootBallEntity)blockEntity).setLootTable(id, random.nextLong());
        }
    }
    public void setLootTable(Identifier id, long seed) {
        this.lootTableId = id;
        this.lootTableSeed = seed;
        this.markDirty();
    }
    protected void deserializeLootTable(NbtCompound nbt) {
        if (nbt.contains(LOOT_TABLE_KEY, NbtElement.STRING_TYPE)) {
            this.lootTableId = new Identifier(nbt.getString(LOOT_TABLE_KEY));
            this.lootTableSeed = nbt.getLong(LOOT_TABLE_SEED_KEY);
        }
    }
    protected void serializeLootTable(NbtCompound nbt) {
        if (this.lootTableId == null) {
            return;
        }
        nbt.putString(LOOT_TABLE_KEY, this.lootTableId.toString());
        if (this.lootTableSeed != 0L) {
            nbt.putLong(LOOT_TABLE_SEED_KEY, this.lootTableSeed);
        }
    }
    public void addOpener(UUID uuid) {
        this.openers.add(uuid);
        this.markDirty();
    }
    public boolean isOpener(UUID uuid) {
        return this.openers.contains(uuid);
    }
    protected void serializeOpeners(NbtCompound nbt) {
        if (this.openers.isEmpty()) {
            return;
        }
        StringBuilder saveString = new StringBuilder();
        for (UUID opener:
             openers) {
            if (saveString.isEmpty()) {
                saveString = new StringBuilder(opener.toString());
            } else {
                saveString.append(",").append(opener.toString());
            }
        }
        nbt.putString(OPENERS_KEY, saveString.toString());
    }
    protected void deserializeOpeners(NbtCompound nbt) {
        if (nbt.contains(OPENERS_KEY, NbtElement.STRING_TYPE)) {
            String[] uuidStrings = nbt.getString(OPENERS_KEY).split(",");
            for (String strUUID:
                 uuidStrings) {
                this.addOpener(UUID.fromString(strUUID));
            }
        }
    }
    public void setUses(int uses) {
        if (!this.infinite) {
            this.uses = uses;
            this.markDirty();
        }
    }
    public int getUses() {
        return this.uses;
    }
    public float getMultiplier() { return this.multiplier; }
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
            this.markDirty();
        }
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.items);
        this.deserializeLootTable(nbt);
        this.deserializeOpeners(nbt);
        if (nbt.contains(USES_KEY, NbtElement.INT_TYPE)) {
            this.uses = nbt.getInt(USES_KEY);
        }
        if (nbt.contains(INFINITE_KEY, NbtElement.BYTE_TYPE)) {
            this.infinite = nbt.getBoolean(INFINITE_KEY);
        }
        if (nbt.contains(MULTIPLIER_KEY, NbtElement.FLOAT_TYPE)) {
            this.multiplier = nbt.getFloat(MULTIPLIER_KEY);
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.items);
        this.serializeLootTable(nbt);
        this.serializeOpeners(nbt);
        nbt.putInt(USES_KEY, this.uses);
        nbt.putBoolean(INFINITE_KEY, this.infinite);
        nbt.putFloat(MULTIPLIER_KEY, this.multiplier);
        super.writeNbt(nbt);
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

    public ItemStack copyStack(int slot) {
        return this.items.get(slot).copy();
    }
}
