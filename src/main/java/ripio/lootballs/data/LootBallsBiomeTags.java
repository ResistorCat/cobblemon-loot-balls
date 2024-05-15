package ripio.lootballs.data;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

import static ripio.lootballs.LootBalls.lootBallsResource;

public class LootBallsBiomeTags {
    public static final TagKey<Biome> HAS_LOOTBALL_HEAL = create("has_lootball/heal");
    public static final TagKey<Biome> HAS_LOOTBALL_SAFARI = create("has_lootball/safari");
    public static final TagKey<Biome> IS_OVERWORLD = create("is_overworld");
    private static TagKey<Biome> create(String path) {
        return TagKey.of(RegistryKeys.BIOME, lootBallsResource(path));
    }
}
