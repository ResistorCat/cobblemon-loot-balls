package ripio.lootballs.render.item;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import ripio.lootballs.model.LootBallModel;

import java.util.Objects;

import static ripio.lootballs.block.entity.LootBallEntity.CUSTOM_DATA_KEY;
import static ripio.lootballs.block.entity.LootBallEntity.VARIANT_KEY;
import static ripio.lootballs.data.LootBallsDataClasses.getBallData;
import static ripio.lootballs.data.LootBallsDataClasses.isValidBall;

public class LootBallItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private final ModelPart model;

    public LootBallItemRenderer() {
        this.model = LootBallModel.getTexturedModelData().createModel();
        }

    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        Identifier customData = null;
        int variant = -1;
        if (stack.hasNbt()) {
            NbtCompound nbt = stack.getNbt();
            if (nbt.contains("BlockEntityTag")) {
                NbtCompound tag = nbt.getCompound("BlockEntityTag");
                if (tag.contains(CUSTOM_DATA_KEY, NbtElement.STRING_TYPE)) {
                    customData = new Identifier(tag.getString(CUSTOM_DATA_KEY));
                }
                if (tag.contains(VARIANT_KEY, NbtElement.INT_TYPE)) {
                    variant = tag.getInt(VARIANT_KEY);
                }
            }
        }
        Identifier texture = getRenderTextureIdentifier(customData, variant);

        matrices.push();
        this.model.render(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture)),
                light,
                overlay
        );
        matrices.pop();
    }

    private Identifier getRenderTextureIdentifier(Identifier customData, int variant) {
        if (isValidBall(customData, variant)) {
            return Objects.requireNonNull(getBallData(customData, variant)).texture();
        }
        return Identifier.of("cobblemon", "textures/item/poke_balls/models/poke_ball.png");
    }
}
