package ripio.lootballs.config;

import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "lootballs")
@Config(name = "lootballs-config", wrapperName = "LootBallsConfig")
public class LootBallsConfigModel {
    @SectionHeader("generation")
    public int maxLootBallsPerChunk = 3;
    public float hiddenMultiplier = 2.0F;
    @RangeConstraint(min = 0F,max = 1F)
    public float hiddenChance = 0.2F;
    public int naturalLootBallUses = 1;
    @SectionHeader("multiplayer")
    public boolean perPlayerLootBalls = false;
    public boolean ignorePerPlayerLootBallUses = true;
    @SectionHeader("gameplay")
    @RestartRequired
    public boolean doLootBallArchaeology = true;
    @RestartRequired
    public boolean doLootBallFishing = true;
    @RestartRequired
    public boolean doLootBallDrops = true;
    @RestartRequired
    public boolean doLootBallChests = true;
}
