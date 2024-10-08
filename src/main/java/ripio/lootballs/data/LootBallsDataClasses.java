package ripio.lootballs.data;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static ripio.lootballs.data.LootBallsCustomResources.LOOT_BALLS;

public interface LootBallsDataClasses {
    record Ball(
            String name,
            String rarity,
            Identifier table,
            Identifier texture,
            ArrayList<BallSource> sources,
            ArrayList<Ball> variants
    ){}

    record BallSource(
            String type,
            Identifier biome,
            BallHeight height,
            int weight
    ){}

    record BallHeight(
            String min,
            String max
    ){}

    @Nullable
    static Ball getBallData(Identifier id, int variant) {
        if (LOOT_BALLS.get(id) == null) return null;
        if (variant < 0) return LOOT_BALLS.get(id);

        if (variant < LOOT_BALLS.get(id).variants.size()) {
            return LOOT_BALLS.get(id).variants.get(variant);
        }

        return LOOT_BALLS.get(id);
    }

    static boolean isValidBall(Identifier id, int variant) {
        if (id == null) return false;
        return getBallData(id, variant) != null;
    }
}
