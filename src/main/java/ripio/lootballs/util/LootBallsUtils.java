package ripio.lootballs.util;

import com.cobblemon.mod.common.api.tags.CobblemonBiomeTags;
import net.minecraft.block.Block;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import ripio.lootballs.LootBalls;
import ripio.lootballs.data.LootBallsBiomeTags;
import ripio.lootballs.datagen.LootBallsBlockTagProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LootBallsUtils {
    public static int COMMON_WEIGHT = 90;
    public static int UNCOMMON_WEIGHT = 32;
    public static int RARE_WEIGHT = 12;
    public static int ULTRA_RARE_WEIGHT = 1;
    public static int TOTAL_WEIGHT = COMMON_WEIGHT + UNCOMMON_WEIGHT + RARE_WEIGHT + ULTRA_RARE_WEIGHT;
    private static final Identifier[] COMMON_LOOTS = {
            new Identifier(LootBalls.MOD_ID, "poke_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "safari_loot_ball"),

    };
    private static final Identifier[] UNCOMMON_LOOTS = {
            new Identifier(LootBalls.MOD_ID, "dive_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "great_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "heal_loot_ball"),

    };
    private static final Identifier[] RARE_LOOTS = {
            new Identifier(LootBalls.MOD_ID, "luxury_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "ultra_loot_ball"),

    };
    private static final Identifier[] ULTRA_RARE_LOOTS = {
            new Identifier(LootBalls.MOD_ID, "master_loot_ball"),

    };
    private static final Identifier[] POKE_LOOT_EQUIVALENTS = {
            new Identifier(LootBalls.MOD_ID, "azure_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "citrine_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "premier_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "roseate_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "slate_loot_ball"),
            new Identifier(LootBalls.MOD_ID, "verdant_loot_ball"),
    };

    @Nullable
    public static Identifier randomLootBall(Random random, RegistryEntry<Biome> biomeRegistryEntryFilter) {
        int randint = random.nextInt(TOTAL_WEIGHT);

        if (randint < COMMON_WEIGHT) {
            // COMMON
            return filteredLootBall(COMMON_LOOTS, biomeRegistryEntryFilter, random);
        } else if (randint < COMMON_WEIGHT + UNCOMMON_WEIGHT) {
            // UNCOMMON
            return filteredLootBall(UNCOMMON_LOOTS, biomeRegistryEntryFilter, random);
        } else if (randint < COMMON_WEIGHT + UNCOMMON_WEIGHT + RARE_WEIGHT) {
            // RARE
            return filteredLootBall(RARE_LOOTS, biomeRegistryEntryFilter, random);
        } else {
            // ULTRA RARE
            return filteredLootBall(ULTRA_RARE_LOOTS, biomeRegistryEntryFilter, random);
        }
    }

    @Nullable
    public static Identifier getLootBallLootTable(Identifier identifier) {
        if (identifier == null) return null;
        String path = identifier.getPath().split("_")[0] + "_loot_table";
        return new Identifier(LootBalls.MOD_ID, path);
    }

    public static TagKey<Biome> getLootBallBiomeTags(Identifier blockId, RegistryEntry<Biome> biome) {
        // Check Biome Tags for specified lootball id
        String path = blockId.getPath();

        // Safari LootBall
        if (Objects.equals(path, "safari_loot_ball")) {
            return CobblemonBiomeTags.IS_SAVANNA;
        }
        // Heal LootBall
        if (Objects.equals(path, "heal_loot_ball")) {
            return LootBallsBiomeTags.HAS_LOOTBALL_HEAL;
        }
        // Dive LootBall
        if (Objects.equals(path, "dive_loot_ball")) {
            return BiomeTags.IS_OCEAN;
        }

        if (biome.isIn(BiomeTags.IS_END)) return BiomeTags.IS_END;
        if (biome.isIn(BiomeTags.IS_NETHER)) return BiomeTags.IS_NETHER;

        return CobblemonBiomeTags.IS_OVERWORLD;
    }

    public static TagKey<Block>[] getLootBallBlockTags(Identifier blockId) {
        String path = blockId.getPath();
        List<TagKey<Block>> tagsList = new ArrayList<>();

        // Luxury
        if (Objects.equals(path, "luxury_loot_ball")) {
            tagsList.add(LootBallsBlockTagProvider.LUXURY_LOOT_BALL_BASE_BLOCKS);
            return tagsList.toArray(new TagKey[0]);
        }

        tagsList.add(LootBallsBlockTagProvider.LOOT_BALLS_BASE_BLOCKS);
        return tagsList.toArray(new TagKey[0]);
    }

    public static List<Integer> getLootBallHeightRange(Identifier blockId, StructureWorldAccess world) {
        // Return height for specified lootball id
        String path = blockId.getPath();

        // Dive LootBall
        if (Objects.equals(path, "dive_loot_ball")) {
            return List.of(0, 40);
        }
        // Luxury LootBall
        if (Objects.equals(path, "luxury_loot_ball")) {
            return List.of(world.getBottomY(), 40);
        }

        return List.of(world.getBottomY(), world.getTopY());
    }

    private static Identifier filteredLootBall(Identifier[] identifiers, RegistryEntry<Biome> biome, Random random) {
        ArrayList<Identifier> filteredLoots = new ArrayList<>();

        // Filter Biome
        for (Identifier identifier : identifiers) {
            if (biome.isIn(getLootBallBiomeTags(identifier, biome))) {
                filteredLoots.add(identifier);
            }
        }

        // If poke loot present, choose one of all equivalents
        for (Identifier identifier : filteredLoots) {
            if (Objects.equals(identifier.getPath(), "poke_loot_ball")) {
                filteredLoots.remove(identifier);
                filteredLoots.add(POKE_LOOT_EQUIVALENTS[random.nextInt(POKE_LOOT_EQUIVALENTS.length)]);
                break;
            }
        }

        return filteredLoots.get(random.nextInt(filteredLoots.size()));
    }
}
