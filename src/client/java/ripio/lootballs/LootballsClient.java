package ripio.lootballs;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static ripio.lootballs.block.LootBallsBlocks.*;

public class LootballsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(AZURE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BEAST_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(CHERISH_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(CITRINE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(DIVE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(DREAM_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(DUSK_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(FAST_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(FRIEND_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(GREAT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HEAL_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(HEAVY_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LEVEL_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LOVE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LURE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(LUXURY_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MASTER_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MOON_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(NEST_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(NET_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PARK_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(POKE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(PREMIER_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(QUICK_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(REPEAT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ROSEATE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SAFARI_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SLATE_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(SPORT_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(TIMER_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ULTRA_LOOT_BALL, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(VERDANT_LOOT_BALL, RenderLayer.getCutout());
	}
}