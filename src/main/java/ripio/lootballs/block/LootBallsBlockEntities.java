package ripio.lootballs.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ripio.lootballs.block.entity.LootBallEntity;
import ripio.lootballs.Lootballs;

import static ripio.lootballs.block.LootBallsBlocks.*;

public class LootBallsBlockEntities {
    public static final BlockEntityType<LootBallEntity> LOOT_BALL_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Lootballs.MOD_ID, "loot_ball_entity"),
            FabricBlockEntityTypeBuilder.create(
                    LootBallEntity::new,
                    AZURE_LOOT_BALL,
                    BEAST_LOOT_BALL,
                    CHERISH_LOOT_BALL,
                    CITRINE_LOOT_BALL,
                    DIVE_LOOT_BALL,
                    DREAM_LOOT_BALL,
                    DUSK_LOOT_BALL,
                    FAST_LOOT_BALL,
                    FRIEND_LOOT_BALL,
                    GREAT_LOOT_BALL,
                    HEAL_LOOT_BALL,
                    HEAVY_LOOT_BALL,
                    LEVEL_LOOT_BALL,
                    LOVE_LOOT_BALL,
                    LURE_LOOT_BALL,
                    LUXURY_LOOT_BALL,
                    MASTER_LOOT_BALL,
                    MOON_LOOT_BALL,
                    NEST_LOOT_BALL,
                    NET_LOOT_BALL,
                    PARK_LOOT_BALL,
                    POKE_LOOT_BALL,
                    PREMIER_LOOT_BALL,
                    QUICK_LOOT_BALL,
                    REPEAT_LOOT_BALL,
                    ROSEATE_LOOT_BALL,
                    SAFARI_LOOT_BALL,
                    SLATE_LOOT_BALL,
                    SPORT_LOOT_BALL,
                    TIMER_LOOT_BALL,
                    ULTRA_LOOT_BALL,
                    VERDANT_LOOT_BALL
            ).build()
    );

    public static void registerBlockEntities() {
        Lootballs.LOGGER.info("Registering block entities for " + Lootballs.MOD_ID);
    }
}
