# Configurations

<!-- TOC -->
* [Configurations](#configurations)
  * [Global configs (server)](#global-configs-server)
  * [Gamerules](#gamerules)
<!-- TOC -->

## Global configs (server)
In the config folder inside your minecraft instance, there is a `lootballsconfig.properties` file that has the following options:

> Note: Some configurations only apply when generating new chunks.
- `maxLootballsPerChunk`: (_Positive_) Defaults to `3`. It limits how many loot balls can generate in a chunk. `0` will disable loot ball generation in all worlds. (It's better to use the gamerule for that)
- `perPlayerLootballs`: (_Boolean_) Defaults to `false`. It determines if Loot Balls
  can be obtained by every player in the server (and it won't disappear when used).
- `usesPerLootball`: (_Integer_) Defaults to `1`. It determines the number of uses each naturally generated loot ball will have.
- `ignorePerPlayerLootballsUses`: (_Boolean_) Defaults to `true`. When `perPlayerLootballs` is `true`, it determines if the number of uses of a ball will be ignored or not.
- `hiddenChance`: (_Float_ `0.0 <-> 1.0`) Defaults to `0.2`. It determines the probability that a natural loot ball will spawn invisible with the formula.
- `hiddenMultiplier`: (_Float_) Defaults to `2.0`. It determines the bonus loot multiplier for natural loot balls that spawn invisible.

## Gamerules
> Note: Some gamerules only apply when generating new chunks.
- ```generateLootBalls```: Default to ```true```. It determines if
  Loot Balls will be generated naturally or not.
- ```doLootBallFishing```: Default to ```true```. It determines if Loot Balls
  can be obtained from fishing.