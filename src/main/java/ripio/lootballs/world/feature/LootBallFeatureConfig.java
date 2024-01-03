package ripio.lootballs.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

public record LootBallFeatureConfig(Identifier blockId, Identifier lootTableId) implements FeatureConfig {
    public static final Codec<LootBallFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    //Codecs.POSITIVE_INT.fieldOf("number").forGetter(LootBallFeatureConfig::number),
                    Identifier.CODEC.fieldOf("blockID").forGetter(LootBallFeatureConfig::blockId),
                    Identifier.CODEC.fieldOf("lootTableID").forGetter(LootBallFeatureConfig::lootTableId)
            ).apply(instance, LootBallFeatureConfig::new)
    );
}
