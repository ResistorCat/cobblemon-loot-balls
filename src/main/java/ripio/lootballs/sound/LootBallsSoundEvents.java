package ripio.lootballs.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import ripio.lootballs.LootBalls;

public class LootBallsSoundEvents {
    private static final Identifier LOOT_BALL_OPEN_SOUND_ID = new Identifier(LootBalls.MOD_ID,"lootball_open_sound");
    public static SoundEvent LOOT_BALL_OPEN_SOUND_EVENT = Registry.register(
            Registries.SOUND_EVENT,
            LOOT_BALL_OPEN_SOUND_ID,
            SoundEvent.of(LOOT_BALL_OPEN_SOUND_ID)
    );

    public static void registerSoundEvents() {
        LootBalls.LOGGER.info("Registering sound events for " + LootBalls.MOD_ID);
    }
}
