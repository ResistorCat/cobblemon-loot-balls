# Configurations

<!-- TOC -->
* [Configurations](#configurations)
  * [Global configs (server)](#global-configs-server)
  * [Gamerules](#gamerules)
<!-- TOC -->

## Global configs (server)
In the config folder inside your minecraft instance, there is a `lootballs-config.json5` file that has the following options:

> Note: Some configurations only apply when generating new chunks.
- `maxLootBallsPerChunk`: (_Int_) Defaults to `3`. It limits how many loot balls can generate in a chunk. `0` will disable loot ball generation in all worlds. (It's better to use the gamerule for that)
- `minLootBallsPerChunk`: (_Int_) Defaults to `0`. It limits the minimum amount of loot ball generation attempts in a chunk. It must be lower or equal to `maxLootBallsPerChunk`.
- `generationChance`: (_Float_) Defaults to `0.08`. It determines the chance for a loot ball to be generated.
- `naturalLootBallUses`: (_Int_) Defaults to `1`. It determines the number of uses each naturally generated loot ball will have.
- `ignoreLootBallUses`: (_Bool_) Defaults to `false`. When `true`, the remaining uses of a loot ball will be ignored.
- `randomizeAfterUse`: (_Bool_) Defaults to `true`. When `true`, loot balls **with an assigned loot table** (like the natural ones) will grant random loot to each player. If `false`, all players will get the same loot.
- `hiddenChance`: (_Float_ `0.0 <-> 1.0`) Defaults to `0.2`. It determines the probability that a natural loot ball will spawn invisible with the formula.
- `hiddenMultiplier`: (_Float_) Defaults to `2.0`. It determines the bonus loot multiplier for natural loot balls that spawn invisible.
- `doLootBallFishing`: (_Bool_) Enable loot balls from fishing loot tables.
- `doLootBallArchaeology`: (_Bool_) Enable loot balls from archaeology loot tables.
- `doLootBallDrops`: (_Bool_) Enable loot balls from block drops.
- `doLootBallChests`: (_Bool_) Enable loot balls from chest loot tables.

> You can also modify them in-game using **Modmenu** mod!
## Gamerules
> Note: Some gamerules only apply when generating new chunks.
- ```generateLootBalls```: Default to ```true```. It determines if
  Loot Balls will be generated naturally or not.