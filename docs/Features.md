# LootBalls Features

<!-- TOC -->
* [LootBalls Features](#lootballs-features)
  * [Loot Balls](#loot-balls)
  * [Loot Ball Finder (WIP)](#loot-ball-finder-wip)
    * [Crafting Recipe](#crafting-recipe)
    * [Usage](#usage)
<!-- TOC -->

## Loot Balls
The main focus of this mod is to add poke balls with loot for all existing poké balls in cobblemon.
To see more detailed info about them, take a look at the [Loot Balls documentation](https://github.com/ResistorCat/cobblemon-loot-balls/blob/main/docs/Lootballs.md).

## Loot Ball Finder (WIP)
The **Loot Ball Finder** is a Dowsing-Machine-like tool used to detect **hidden** loot balls.

### Crafting Recipe
![](https://github.com/ResistorCat/cobblemon-loot-balls/tree/main/docs/assets/loot_ball_finder_recipe.png)

### Usage
To search for hidden loot balls, `right-click` anywhere with the item
in your hand. This will **scan** a `64x64x64 (2 chunk radius)` area centered in
the player.

There are 4 possible results:
1. If nothing is found, an error sound will play and nothing will happen.
2. If something is detected within 2 chunks, a sound will be played and
a message will be shown in the overlay. The sound will be low.
3. If something is near (16 blocks of distance), a new message will
display in the overlay and the sound will be louder.
4. If something is very near (8 blocks of distance), a new message will
display in the overlay, the sound will be much louder, and a breaking sound
will be played at the position of the loot ball with some particles on it.

> There is a 5-second cool down between uses.