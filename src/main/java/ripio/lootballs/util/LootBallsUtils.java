package ripio.lootballs.util;

import com.cobblemon.mod.common.api.tags.CobblemonBiomeTags;
import net.minecraft.block.Block;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import ripio.lootballs.Lootballs;
import ripio.lootballs.datagen.LootBallsBlockTagProvider;

import java.util.ArrayList;
import java.util.Objects;

public class LootBallsUtils {
    public static int COMMON_WEIGHT = 100;
    public static int UNCOMMON_WEIGHT = 30;
    public static int RARE_WEIGHT = 10;
    public static int ULTRA_RARE_WEIGHT = 1;
    private static final Identifier[] POKE_LOOT_EQUIVALENTS = {
            new Identifier(Lootballs.MOD_ID, "poke_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "citrine_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "verdant_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "azure_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "roseate_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "slate_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "premier_loot_ball"),
    };
    private static final Identifier[] COMMON_LOOTS = {
            new Identifier(Lootballs.MOD_ID, "poke_loot_ball"),
            new Identifier(Lootballs.MOD_ID, "safari_loot_ball"),
    };
    private static final Identifier[] UNCOMMON_LOOTS = {
            new Identifier(Lootballs.MOD_ID, "great_loot_ball"),
    };
    private static final Identifier[] RARE_LOOTS = {
            new Identifier(Lootballs.MOD_ID, "ultra_loot_ball"),
    };
    private static final Identifier[] ULTRA_RARE_LOOTS = {
            new Identifier(Lootballs.MOD_ID, "master_loot_ball"),
    };

    @Nullable
    public static Identifier randomLootBall(Random random, RegistryEntry<Biome> biomeRegistryEntryFilter) {
        int randint = random.nextInt(COMMON_WEIGHT + UNCOMMON_WEIGHT + RARE_WEIGHT + ULTRA_RARE_WEIGHT);

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
        return new Identifier(Lootballs.MOD_ID, path);
    }

    public static TagKey<Biome> getLootBallBiomeTags(Identifier blockId, RegistryEntry<Biome> biome) {
        // Check Biome Tags for specified lootball id
        String path = blockId.getPath();

        // Safari LootBall
        if (Objects.equals(path, "safari_loot_ball")) {
            return CobblemonBiomeTags.IS_SAVANNA;
        }

        if (biome.isIn(BiomeTags.IS_END)) return BiomeTags.IS_END;
        if (biome.isIn(BiomeTags.IS_NETHER)) return BiomeTags.IS_NETHER;

        return CobblemonBiomeTags.IS_OVERWORLD;
    }

    public static TagKey<Block>[] getLootBallBlockTags(Identifier blockId) {
        // Workaround
        return new TagKey[]{LootBallsBlockTagProvider.LOOT_BALLS_BASE_BLOCKS};
    }

    private static Identifier filteredLootBall(Identifier[] identifiers, RegistryEntry<Biome> biome, Random random) {
        ArrayList<Identifier> filteredLoots = new ArrayList<Identifier>();

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
