package ripio.lootballs.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

import static ripio.lootballs.LootBalls.lootBallsResource;

public class LootBallModel extends Model {
	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(
			lootBallsResource("main"),
			"loot_ball"
	);
	private final ModelPart main;
	private final ModelPart lid;

	public LootBallModel(ModelPart root) {
        super(RenderLayer::getEntityCutout);
        this.main = root.getChild("main");
		this.lid = this.main.getChild("lid");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(32, 20).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.25F))
				.uv(32, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData lid = main.addChild("lid", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(-1.0F, -1.0F, -8.25F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
				.uv(0, 20).cuboid(-4.0F, -4.0F, -8.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, -4.0F, 4.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	public ModelPart getLid() {
		return this.lid;
	}
}