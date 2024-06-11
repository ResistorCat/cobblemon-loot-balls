package ripio.lootballs.config;

import io.wispforest.owo.config.annotation.*;

@Modmenu(modId = "lootballs")
@Config(name = "lootballs-config", wrapperName = "LootBallsConfig")
public class LootBallsConfigModel {
    @SectionHeader("generation")
    public int minLootBallsPerChunk = 0;
    public int maxLootBallsPerChunk = 3;
    @RangeConstraint(min = 0F,max = 1F)
    public float generationChance = 0.08F;
    public float hiddenMultiplier = 2.0F;
    @RangeConstraint(min = 0F,max = 1F)
    public float hiddenChance = 0.2F;
    public int naturalLootBallUses = 1;

    @SectionHeader("gameplay")
    public boolean ignoreLootBallUses = false;
    public boolean randomizeAfterUse = true;
    @RestartRequired
    public boolean doLootBallArchaeology = true;
    @RestartRequired
    public boolean doLootBallFishing = true;
    @RestartRequired
    public boolean doLootBallDrops = true;
    @RestartRequired
    public boolean doLootBallChests = true;
}
