package ripio.lootballs.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record LootBallFeatureConfig(float chance) implements FeatureConfig {
    public static final Codec<LootBallFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codecs.POSITIVE_FLOAT.fieldOf("chance").forGetter(LootBallFeatureConfig::chance)
            ).apply(instance, LootBallFeatureConfig::new)
    );
}
