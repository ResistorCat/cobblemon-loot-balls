package ripio.lootballs.world;

import net.minecraft.world.gen.placementmodifier.*;
import ripio.lootballs.config.LootBallsConfigs;

import java.util.List;

public class LootBallsPlacement {
    public static List<PlacementModifier> modifiers() {
        return List.of(
                BiomePlacementModifier.of()
        );
    }
}
