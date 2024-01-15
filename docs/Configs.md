# Configurations

# Global configs (server)
> Note: Some configurations only apply when generating new chunks.
- ```maxLootballsPerChunk```: (_Boolean_) Defaults to ```3```. It limits how many loot balls can generate in a chunk.
- ```perPlayerLootballs```: (_Boolean_) Defaults to ```false```. It determines if Loot Balls
  can be obtained by every player in the server (and it won't dissapear when used). **Loot will be randomized for every player if a loot table is set.**
- ```lootballsUsagePerBall```: (_Integer_) Defaults to ```1```. It determines the number of uses each naturally generated loot ball will have. **Doesn't affect creative loot balls.**
- ```ignorePerPlayerLootballsUses```: (_Boolean_) Defaults to ```false```. When ```perPlayerLootballs``` is ```true```, it determines if the number of uses of a ball will be ignored or not.

# Gamerules
> Note: Some gamerules only apply when generating new chunks.
- ```generateLootBalls```: Default to ```true```. It determines if
  Loot Balls will be generated naturally or not.
- ```doLootBallFishing```: Default to ```true```. It determines if Loot Balls
  can be obtained from fishing.