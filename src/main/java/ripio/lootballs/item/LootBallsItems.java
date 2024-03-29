package ripio.lootballs.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ripio.lootballs.LootBalls;
import ripio.lootballs.item.custom.LootBallFinder;

public class LootBallsItems {
    public static final LootBallFinder LOOT_BALL_FINDER = (LootBallFinder) registerItem(
            "loot_ball_finder",
            new LootBallFinder(new FabricItemSettings())
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LootBalls.MOD_ID, name), item);
    }
    public static void registerModItems() {
        LootBalls.LOGGER.info("Registering items for " + LootBalls.MOD_ID);
    }
}
