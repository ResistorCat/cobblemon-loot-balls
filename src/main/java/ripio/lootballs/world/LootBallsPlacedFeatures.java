package ripio.lootballs.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import ripio.lootballs.Lootballs;
import ripio.lootballs.util.LootBallsUtils;

import java.util.List;

public class LootBallsPlacedFeatures {
    public static final RegistryKey<PlacedFeature> POKE_LOOT_PLACED_KEY = registerKey("poke_loot_placed");
    public static final RegistryKey<PlacedFeature> CITRINE_LOOT_PLACED_KEY = registerKey("citrine_loot_placed");
    public static final RegistryKey<PlacedFeature> VERDANT_LOOT_PLACED_KEY = registerKey("verdant_loot_placed");
    public static final RegistryKey<PlacedFeature> AZURE_LOOT_PLACED_KEY = registerKey("azure_loot_placed");
    public static final RegistryKey<PlacedFeature> ROSEATE_LOOT_PLACED_KEY = registerKey("roseate_loot_placed");
    public static final RegistryKey<PlacedFeature> SLATE_LOOT_PLACED_KEY = registerKey("slate_loot_placed");
    public static final RegistryKey<PlacedFeature> PREMIER_LOOT_PLACED_KEY = registerKey("premier_loot_placed");
    public static final RegistryKey<PlacedFeature> SAFARI_LOOT_PLACED_KEY = registerKey("safari_loot_placed");
    public static final RegistryKey<PlacedFeature> GREAT_LOOT_PLACED_KEY = registerKey("great_loot_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, POKE_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.POKE_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, CITRINE_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.CITRINE_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, VERDANT_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.VERDANT_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, AZURE_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.AZURE_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, ROSEATE_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.ROSEATE_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, SLATE_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.SLATE_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, PREMIER_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.PREMIER_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, SAFARI_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.SAFARI_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.COMMON_CHANCE));
        register(context, GREAT_LOOT_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.GREAT_LOOT_KEY),
                LootBallsPlacement.modifiersWithRarity(LootBallsUtils.UNCOMMON_CHANCE));

    }
    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Lootballs.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
