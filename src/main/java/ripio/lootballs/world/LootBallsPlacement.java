package ripio.lootballs.world;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class LootBallsPlacement {
    public static List<PlacementModifier> modifiers(RarityFilterPlacementModifier rarityModifier) {
        return List.of(SquarePlacementModifier.of(), BiomePlacementModifier.of(), rarityModifier);
    }

    public static List<PlacementModifier> modifiersWithRarity(int chance) {
        // 1/chance
        return modifiers(RarityFilterPlacementModifier.of(chance));
    }
}
