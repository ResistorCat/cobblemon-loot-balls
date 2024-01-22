package ripio.lootballs.config;

import com.mojang.datafixers.util.Pair;
import ripio.lootballs.Lootballs;

public class LootBallsConfigs {
    public static SimpleConfig CONFIG;
    private static LootBallsConfigProvider configs;
    public static int MAX_LOOTBALLS_PER_CHUNK;
    public static boolean PER_PLAYER_LOOTBALLS;
    public static int USES_PER_LOOTBALL;
    public static boolean IGNORE_PER_PLAYER_LOOTBALLS_USES;
    public static float HIDDEN_MULTIPLIER;
    public static float HIDDEN_CHANCE;

    public static void registerConfigs() {
        configs = new LootBallsConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Lootballs.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("maxLootballsPerChunk", 3), "(integer) It limits how many loot balls can generate in a chunk.");
        configs.addKeyValuePair(new Pair<>("perPlayerLootballs", false), "(boolean) It determines if Loot Balls can be obtained by every player in the server (and it won't dissapear when used). Loot will be randomized for every player if a loot table is set.");
        configs.addKeyValuePair(new Pair<>("usesPerLootball", 1), "(integer) It determines the number of uses each naturally generated loot ball will have. Doesn't affect creative loot balls.");
        configs.addKeyValuePair(new Pair<>("ignorePerPlayerLootballsUses", true), "(boolean) When perPlayerLootballs is true, it determines if the number of uses of a ball will be ignored or not.");
        configs.addKeyValuePair(new Pair<>("hiddenMultiplier", 2.0F), "(float) It determines the loot multiplier of invisible natural loot balls.");
        configs.addKeyValuePair(new Pair<>("hiddenChance", 0.2F), "(float) It determines the chance of a natural loot ball to be invisible.");
    }

    private static void assignConfigs() {
        MAX_LOOTBALLS_PER_CHUNK = CONFIG.getOrDefault("maxLootballsPerChunk", 3);
        PER_PLAYER_LOOTBALLS = CONFIG.getOrDefault("perPlayerLootballs", false);
        USES_PER_LOOTBALL = CONFIG.getOrDefault("usesPerLootball", 1);
        IGNORE_PER_PLAYER_LOOTBALLS_USES = CONFIG.getOrDefault("ignorePerPlayerLootballsUses", true);
        HIDDEN_MULTIPLIER = (float) CONFIG.getOrDefault("hiddenMultiplier", 2.0F);
        HIDDEN_CHANCE = (float) CONFIG.getOrDefault("hiddenChance", 0.2F);

        Lootballs.LOGGER.info(Lootballs.MOD_ID + ": All " + configs.getConfigsList().size() + " have been set properly");
    }
}
