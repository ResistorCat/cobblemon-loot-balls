package ripio.lootballs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class LootballsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.AZURE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.BEAST_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.CHERISH_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.CITRINE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.DIVE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.DREAM_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.DUSK_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.FAST_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.FRIEND_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.GREAT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.HEAL_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.HEAVY_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.LEVEL_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.LOVE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.LURE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.LUXURY_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.MASTER_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.MOON_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.NEST_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.NET_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.PARK_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.POKE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.PREMIER_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.QUICK_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.REPEAT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.ROSEATE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.SAFARI_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.SLATE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.SPORT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.TIMER_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.ULTRA_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(Lootballs.VERDANT_LOOT_BALL, RenderLayer.getCutout());
	}
}