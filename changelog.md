# Changes
- Added config file in ```config``` folder with 6 new configurations.
  - From now, you can configure loot balls to allow loot for multiple players.
  - You can tweak the max amount of loot balls generated per chunk.
  - Check the details in the [config docs](https://github.com/ResistorCat/cobblemon-loot-balls/blob/main/docs/Configs.md)
- Added new NBT tags to lootballs for mapmakers:
  - ```Uses```: Indicates the uses remaining in a loot ball. When it reaches 0, the lootball is destroyed.
  - ```Infinite```: Boolean byte that indicates if loot ball has infinite uses.
  - ```Multiplier```: Boolean byte that indicates if loot ball will grant double loot.
- Tweaked loot ball generation and particles.
  - By default, 3 attempts will be made per chunk (configurable) to spawn loot balls.
  - Hidden loot balls will now generate 3 sparks instead of 1.
- Added **Loot Ball Finder** item.
  - Currently, it only helps to find **hidden** loots in a `64x64x64` area centered in the player.
  - It can be obtained by crafting. See the [features docs](https://github.com/ResistorCat/cobblemon-loot-balls/blob/main/docs/Features.md)
- Updated language files.

## Fixes
- **Critical fix** to NBT saving/loading for loot balls.
  - Manually placed loot balls should now remember the items inside correctly.
- Fixed: Loot balls aren't generating in y-levels below 0.
- Fixed: generated hidden loot balls not giving double loot after world reload.
  - **_Only newly generated hidden loot balls will have double loot._**