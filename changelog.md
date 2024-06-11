# Changes
- **Crítical**: Modified `Openers` NBT save/loading. It works as
a list of strings now, to avoid issues with larger UUIDs strings.
> **NOTE**: The old code was left in place to deserialize this NBT key
for compatibility reasons. It is recommended to load the chunks
containing loot balls already generated to be updated to the new system.
**This may be removed in the future**.
- **Removed** `perPlayerLootBalls` config. It was redundant and now loot balls can only be opened 1 time per player by default. 
> **NOTE**: In single player worlds, loot balls have 1 use by default, and loot balls 
will be destroyed when used. In multiplayer worlds, the new
`ignoreLootBallUses` setting must be changed to `true` so that everyone
can claim the loot (see below) or adjust the `naturalLootBallUses` setting
to allow more than 1 use in natural loot balls.
- **Replaced** `ignorePerPlayerLootBallUses`config with `ignoreLootBallUses`
config. When `true`, the remaining uses of a loot ball will be ignored
(infinite uses), but will still be limited to 1 loot per player. Defaults to `false`.
- **Added** `randomizeAfterUse` config. When `true`, loot balls with an
assigned loot table (like the natural ones) will grant random loot to
each player. If `false`, all players will get the same loot. Defaults to
`true`.
- Updated the docs.

## Fixes
- **Fix**: `Infinite` NBT tag in loot balls ignores `Openers` NBT.