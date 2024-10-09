package ripio.lootballs.data;

import com.google.gson.*;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import ripio.lootballs.LootBalls;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ripio.lootballs.LootBalls.lootBallsResource;
import static ripio.lootballs.util.LootBallsDefinitions.RARITIES;
import static ripio.lootballs.util.LootBallsDefinitions.SOURCE_TYPES;

public class LootBallsCustomResources {
    public static Map<Identifier, LootBallsDataClasses.Ball> LOOT_BALLS = new HashMap<>();
    private static final String FOLDER_BALL = "loot_balls";

    public static void registerCustomResources() {
        LootBalls.LOGGER.info("Registering custom data/resources for " + LootBalls.MOD_ID);

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(
                new SimpleSynchronousResourceReloadListener() {
                    @Override
                    public Identifier getFabricId() {
                        return lootBallsResource("custom_data");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        // Clear cache
                        //LOOT_BALLS.clear();
                        // Load
                        for (Identifier id : manager.findResources(FOLDER_BALL, path -> path.getPath().endsWith(".json")).keySet()) {
                            try(InputStream stream = manager.getResourceOrThrow(id).getInputStream()) {
                                JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
                                LootBallsDataClasses.Ball ball = deserializeBall(jsonObject);
                                if (id.getPath().endsWith(".json")) {
                                    String namespace = id.getNamespace();
                                    String path = id.getPath().replace(".json", "");
                                    id = new Identifier(namespace, path);
                                }
                                LOOT_BALLS.put(id, ball);
                                LootBalls.LOGGER.info("Loaded custom ball data for {}", id);
                            } catch (IOException | NullPointerException | JsonSyntaxException e) {
                                LootBalls.LOGGER.error("Failed to load custom resource {}", id, e);
                            }
                        }
                    }
                }
        );
    }

    private static LootBallsDataClasses.Ball deserializeBall(JsonObject jsonObject) {
        // Extract name
        String name = "Custom";
        if (jsonObject.has("name")) {
            name = JsonHelper.getString(jsonObject, "name");
        }

        // Extract rarity
        String rarity = "common";
        if (jsonObject.has("rarity")) {
            rarity = JsonHelper.getString(jsonObject, "rarity");
        }

        // Extract loot table
        Identifier table = null;
        if (jsonObject.has("table")) {
            String table_string = JsonHelper.getString(jsonObject, "table");
            table = new Identifier(table_string);
        }

        // Extract texture
        Identifier texture = null;
        if (jsonObject.has("texture")) {
            String model_string = JsonHelper.getString(jsonObject, "texture");
            texture = new Identifier(model_string);
        }

        // Extract sources
        ArrayList<LootBallsDataClasses.BallSource> sources = getSources(jsonObject);

        // Extract variants
        ArrayList<LootBallsDataClasses.Ball> variants = new ArrayList<>();
        if (jsonObject.has("variants")) {
            JsonArray variantsArray = JsonHelper.getArray(jsonObject, "variants");
            for (int i = 0; i < variantsArray.size(); i++) {
                if (variantsArray.get(i).isJsonObject()) {
                    JsonObject variantObject = variantsArray.get(i).getAsJsonObject();

                    // Extract variant name
                    String variantName = name;
                    if (variantObject.has("name")) {
                        variantName = JsonHelper.getString(variantObject, "name");
                    }

                    // Rarity can't be changed
                    String variantRarity = rarity;

                    // Extract variant table
                    Identifier variantTable = table;
                    if (variantObject.has("table")) {
                        variantTable = new Identifier(JsonHelper.getString(variantObject, "table"));
                    }

                    // Extract variant texture
                    Identifier variantTexture = texture;
                    if (variantObject.has("texture")) {
                        variantTexture = new Identifier(JsonHelper.getString(variantObject, "texture"));
                    }

                    // Sources can't be changed
                    ArrayList<LootBallsDataClasses.BallSource> variantSources = getSources(variantObject);

                    // Variants can't have sub-variants
                    ArrayList<LootBallsDataClasses.Ball> variantVariants = new ArrayList<>();

                    LootBallsDataClasses.Ball variant = new LootBallsDataClasses.Ball(variantName, variantRarity, variantTable, variantTexture, variantSources, variantVariants);
                    variants.add(variant);
                }
            }
        }

        return new LootBallsDataClasses.Ball(name, rarity, table, texture, sources, variants);
    }

    private static ArrayList<LootBallsDataClasses.BallSource> getSources(JsonObject jsonObject) {
        ArrayList<LootBallsDataClasses.BallSource> sources = new ArrayList<>();
        if (jsonObject.has("sources")) {
            JsonArray jsonArray = JsonHelper.getArray(jsonObject, "sources");
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.get(i).isJsonObject()) {
                    JsonObject sourceObject = jsonArray.get(i).getAsJsonObject();

                    // Extract source type
                    String sourceType = "";
                    if (sourceObject.has("type")) {
                        sourceType = JsonHelper.getString(sourceObject, "type");
                    }
                    boolean valid = false;
                    for (String s : SOURCE_TYPES) {
                        if (s.equals(sourceType)) {
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) continue;

                    // Extract source biome (Optional)
                    Identifier sourceBiome = null;
                    if (sourceObject.has("biome")) {
                        sourceBiome = new Identifier(JsonHelper.getString(sourceObject, "biome"));
                    }

                    // Extract source height (Optional)
                    LootBallsDataClasses.BallHeight sourceHeight = new LootBallsDataClasses.BallHeight("", "");
                    if (sourceObject.has("height")) {
                        String[] sourceHeightStr = JsonHelper.getString(sourceObject, "height").split(":", -1);
                        if (sourceHeightStr.length == 2) {
                            try {
                                if (!sourceHeightStr[0].isEmpty()) Integer.parseInt(sourceHeightStr[0]);
                                if (!sourceHeightStr[1].isEmpty()) Integer.parseInt(sourceHeightStr[1]);
                                sourceHeight = new LootBallsDataClasses.BallHeight(sourceHeightStr[0], sourceHeightStr[1]);
                            } catch (NumberFormatException e) {
                                LootBalls.LOGGER.error("Error loading ball data (Invalid source height): {}", e.getMessage());
                                LootBalls.LOGGER.warn("Please check your LootBalls datapack ball json files.");
                            }
                        }
                    }

                    // Extract source weight (Optional)
                    int sourceWeight = 1;
                    if (sourceObject.has("weight")) {
                        sourceWeight = JsonHelper.getInt(sourceObject, "weight");
                    }

                    LootBallsDataClasses.BallSource source = new LootBallsDataClasses.BallSource(sourceType, sourceBiome, sourceHeight, sourceWeight);
                    sources.add(source);
                }
            }
        }
        return sources;
    }

    private static int getIntRarity(JsonObject jsonObject) {
        int rarity = RARITIES.get("common");
        if (jsonObject.has("rarity")) {
            String rarity_string = JsonHelper.getString(jsonObject, "rarity");
            try {
                rarity = Integer.parseInt(rarity_string);
            } catch (NumberFormatException e) {
                for (String k : RARITIES.keySet()) {
                    if (k.equals(rarity_string)) {
                        rarity = RARITIES.get(k);
                    }
                }
            }
        }
        return rarity;
    }
}
