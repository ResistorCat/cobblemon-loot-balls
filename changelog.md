# Changes
- Removed `perPlayerLootBalls` config. It was redundant and now loot balls can only be opened 1 time per player by default. 
> **NOTE**: In single player worlds, loot balls have 1 use by default, and loot balls 
will be destroyed when used. In multiplayer worlds, the new
`ignoreLootBallUses` setting must be changed to `true` so that everyone
can claim the loot (see below) or adjust the `naturalLootBallUses` setting
to allow more than 1 use in natural loot balls.
- Replaced `ignorePerPlayerLootBallUses`config with `ignoreLootBallUses`
config. When `true`, the remaining uses of a loot ball will be ignored
(infinite uses), but will still be limited to 1 loot per player. Defaults to `false`.
- Updated the docs.

## Fixes
- **Fix**: `Infinite` NBT tag in loot balls ignores openers.