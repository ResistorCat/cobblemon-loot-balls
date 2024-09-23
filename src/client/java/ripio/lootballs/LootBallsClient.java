package ripio.lootballs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import ripio.lootballs.block.entity.LootBallEntityRenderer;
import ripio.lootballs.model.LootBallModel;

import static ripio.lootballs.block.LootBallsBlockEntities.LOOT_BALL_ENTITY;

public class LootBallsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(LootBallModel.LAYER_LOCATION, LootBallModel::getTexturedModelData);
		BlockEntityRendererFactories.register(LOOT_BALL_ENTITY, LootBallEntityRenderer::new);
	}
}