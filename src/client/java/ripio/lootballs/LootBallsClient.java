package ripio.lootballs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import ripio.lootballs.render.block.LootBallEntityRenderer;
import ripio.lootballs.model.LootBallModel;
import ripio.lootballs.render.item.LootBallItemRenderer;

import static ripio.lootballs.block.LootBallsBlockEntities.LOOT_BALL_ENTITY;
import static ripio.lootballs.block.LootBallsBlocks.LOOT_BALL;

public class LootBallsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(LootBallModel.LAYER_LOCATION, LootBallModel::getTexturedModelData);
		BlockEntityRendererFactories.register(LOOT_BALL_ENTITY, LootBallEntityRenderer::new);
		BuiltinItemRendererRegistry.INSTANCE.register(LOOT_BALL, new LootBallItemRenderer());
	}
}