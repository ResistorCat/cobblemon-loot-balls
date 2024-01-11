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

import java.util.List;

public class LootBallsPlacedFeatures {
    public static final RegistryKey<PlacedFeature> LOOT_BALL_PLACED_KEY = registerKey("loot_ball_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(
                context,
                LOOT_BALL_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(LootBallsConfiguredFeatures.LOOT_BALL_CONFIG_KEY),
                LootBallsPlacement.modifiers()
        );

    }
    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Lootballs.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
