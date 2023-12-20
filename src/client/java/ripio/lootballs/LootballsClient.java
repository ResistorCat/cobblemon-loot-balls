package ripio.lootballs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class LootballsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.POKE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.GREAT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.ULTRA_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.MASTER_LOOT_BALL, RenderLayer.getCutout());
	}
}