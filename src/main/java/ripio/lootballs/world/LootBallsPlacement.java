package ripio.lootballs.world;

import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class LootBallsPlacement {
    public static List<PlacementModifier> modifiers() {
        return List.of(SquarePlacementModifier.of(), BiomePlacementModifier.of());
    }
}
