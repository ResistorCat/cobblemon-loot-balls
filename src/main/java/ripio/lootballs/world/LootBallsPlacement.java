package ripio.lootballs.world;

import net.minecraft.world.gen.placementmodifier.*;
import ripio.lootballs.config.LootBallsConfigs;

import java.util.List;

public class LootBallsPlacement {
    public static List<PlacementModifier> modifiers() {
        return List.of(
                SquarePlacementModifier.of(),
                BiomePlacementModifier.of(),
                CountPlacementModifier.of(LootBallsConfigs.MAX_LOOTBALLS_PER_CHUNK)
        );
    }
}
