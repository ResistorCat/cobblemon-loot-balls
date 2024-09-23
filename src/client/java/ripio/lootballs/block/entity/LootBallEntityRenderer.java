package ripio.lootballs.block.entity;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import ripio.lootballs.model.LootBallModel;

public class LootBallEntityRenderer implements BlockEntityRenderer<LootBallEntity> {
    private final LootBallModel model;

    public LootBallEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.model = new LootBallModel(ctx.getLayerModelPart(LootBallModel.LAYER_LOCATION));
    }

    @Override
    public void render(
            LootBallEntity entity,
            float tickDelta,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay
    ) {
        if (entity.isHidden()) return;
        // Get texture
        Identifier texture = entity.getRenderTextureIdentifier();
        // Render
        matrices.push();
        matrices.translate(0.5F, 1.5F, 0.5F);
        matrices.multiply(entity.getRotation().rotateXYZ(MathHelper.PI/2,0F,0F));
        this.model.render(
                matrices,
                vertexConsumers.getBuffer(RenderLayer.getEntityCutout(texture)),
                light,
                overlay,
                1.0F,
                1.0F,
                1.0F,
                1.0F
        );
        matrices.pop();
    }
}
