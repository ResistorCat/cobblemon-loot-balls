package ripio.lootballs.util;

import java.util.Map;

public class LootBallsDefinitions {
    public static Map<String, Integer> RARITIES = Map.ofEntries(
            Map.entry("common", 80),
            Map.entry("uncommon", 30),
            Map.entry("rare", 12),
            Map.entry("ultra_rare", 1)
    );
    public static int RARITIES_TOTAL_WEIGHT = RARITIES.get("common") + RARITIES.get("uncommon") + RARITIES.get("rare") + RARITIES.get("ultra_rare");
    public static String[] SOURCE_TYPES = {
            "generation",
            "fishing",
            "archaeology",
            "block",
            "chest"
    };
}
