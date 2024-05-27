package ripio.lootballs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import ripio.lootballs.block.LootBallsBlocks;
import ripio.lootballs.item.LootBallsItems;
import ripio.lootballs.stat.LootBallsStats;

public class LootBallsLanguageProvider extends FabricLanguageProvider {
    public LootBallsLanguageProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator, "en_us");
    }
    
    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
            // Blocks
            translationBuilder.add(LootBallsBlocks.AZURE_LOOT_BALL, "Azure Loot Ball");
            translationBuilder.add(LootBallsBlocks.BEAST_LOOT_BALL,"Beast Loot Ball");
            translationBuilder.add(LootBallsBlocks.CHERISH_LOOT_BALL,"Cherish Loot Ball");
            translationBuilder.add(LootBallsBlocks.CITRINE_LOOT_BALL,"Citrine Loot Ball");
            translationBuilder.add(LootBallsBlocks.DIVE_LOOT_BALL,"Dive Loot Ball");
            translationBuilder.add(LootBallsBlocks.DREAM_LOOT_BALL,"Dream Loot Ball");
            translationBuilder.add(LootBallsBlocks.DUSK_LOOT_BALL,"Dusk Loot Ball");
            translationBuilder.add(LootBallsBlocks.FAST_LOOT_BALL,"Fast Loot Ball");
            translationBuilder.add(LootBallsBlocks.FRIEND_LOOT_BALL,"Friend Loot Ball");
            translationBuilder.add(LootBallsBlocks.GREAT_LOOT_BALL,"Great Loot Ball");
            translationBuilder.add(LootBallsBlocks.HEAL_LOOT_BALL,"Heal Loot Ball");
            translationBuilder.add(LootBallsBlocks.HEAVY_LOOT_BALL,"Heavy Loot Ball");
            translationBuilder.add(LootBallsBlocks.LEVEL_LOOT_BALL,"Level Loot Ball");
            translationBuilder.add(LootBallsBlocks.LOVE_LOOT_BALL,"Love Loot Ball");
            translationBuilder.add(LootBallsBlocks.LURE_LOOT_BALL,"Lure Loot Ball");
            translationBuilder.add(LootBallsBlocks.LUXURY_LOOT_BALL,"Luxury Loot Ball");
            translationBuilder.add(LootBallsBlocks.MASTER_LOOT_BALL,"Master Loot Ball");
            translationBuilder.add(LootBallsBlocks.MOON_LOOT_BALL,"Moon Loot Ball");
            translationBuilder.add(LootBallsBlocks.NEST_LOOT_BALL,"Nest Loot Ball");
            translationBuilder.add(LootBallsBlocks.NET_LOOT_BALL,"Net Loot Ball");
            translationBuilder.add(LootBallsBlocks.PARK_LOOT_BALL,"Park Loot Ball");
            translationBuilder.add(LootBallsBlocks.POKE_LOOT_BALL,"Poké Loot Ball");
            translationBuilder.add(LootBallsBlocks.PREMIER_LOOT_BALL,"Premier Loot Ball");
            translationBuilder.add(LootBallsBlocks.QUICK_LOOT_BALL,"Quick Loot Ball");
            translationBuilder.add(LootBallsBlocks.REPEAT_LOOT_BALL,"Repeat Loot Ball");
            translationBuilder.add(LootBallsBlocks.ROSEATE_LOOT_BALL,"Roseate Loot Ball");
            translationBuilder.add(LootBallsBlocks.SAFARI_LOOT_BALL,"Safari Loot Ball");
            translationBuilder.add(LootBallsBlocks.SLATE_LOOT_BALL,"Slate Loot Ball");
            translationBuilder.add(LootBallsBlocks.SPORT_LOOT_BALL,"Sport Loot Ball");
            translationBuilder.add(LootBallsBlocks.TIMER_LOOT_BALL,"Timer Loot Ball");
            translationBuilder.add(LootBallsBlocks.ULTRA_LOOT_BALL,"Ultra Loot Ball");
            translationBuilder.add(LootBallsBlocks.VERDANT_LOOT_BALL,"Verdant Loot Ball");

            // Items
            translationBuilder.add(LootBallsItems.LOOT_BALL_FINDER, "Loot Ball Finder");

            // Item groups
            translationBuilder.add("itemGroup.lootballs.loot_balls","Loot Balls");

            // Statistics
            translationBuilder.add(LootBallsStats.OPEN_LOOT_BALL_STAT, "Loot balls opened");

            // Sounds
            translationBuilder.add("stat.lootballs.open_loot_ball", "Loot ball open fanfare");

            // Gamerules
            translationBuilder.add("gamerule.doLootBallFishing", "Allow loot ball fishing");
            translationBuilder.add("gamerule.generateLootBalls", "Enable loot ball natural generation");

            // Configs
            translationBuilder.add("text.config.lootballs-config.title", "Loot Balls configurations");

            translationBuilder.add("text.config.lootballs-config.section.generation", "Loot Balls generation");
            translationBuilder.add("text.config.lootballs-config.section.multiplayer", "Multiplayer");
            translationBuilder.add("text.config.lootballs-config.section.gameplay", "Gameplay\n(Requires restart)");

            translationBuilder.add("text.config.lootballs-config.option.maxLootBallsPerChunk", "Maximum Loot Balls that are attempted to be generated per chunk");
            translationBuilder.add("text.config.lootballs-config.option.maxLootBallsPerChunk.tooltip", "Positive integer");
            translationBuilder.add("text.config.lootballs-config.option.minLootBallsPerChunk", "Minimum Loot Balls that are attempted to be generated per chunk");
            translationBuilder.add("text.config.lootballs-config.option.minLootBallsPerChunk.tooltip", "Positive integer");
            translationBuilder.add("text.config.lootballs-config.option.generationChance", "Chance of generating a Loot Ball");
            translationBuilder.add("text.config.lootballs-config.option.generationChance.tooltip", "float in range [0, 1]");
            translationBuilder.add("text.config.lootballs-config.option.hiddenMultiplier", "Hidden Loot Balls bonus multiplier");
            translationBuilder.add("text.config.lootballs-config.option.hiddenChance", "Chance of generating hidden Loot Balls");
            translationBuilder.add("text.config.lootballs-config.option.hiddenChance.tooltip", "float in range [0, 1]");
            translationBuilder.add("text.config.lootballs-config.option.naturalLootBallUses", "Number of uses of naturally generated Loot Balls");
            translationBuilder.add("text.config.lootballs-config.option.naturalLootBallUses.tooltip", "Value must be >= 1");
            translationBuilder.add("text.config.lootballs-config.option.perPlayerLootBalls", "Loot Balls can be obtained by every player in the server");
            translationBuilder.add("text.config.lootballs-config.option.perPlayerLootBalls.tooltip", "(Loot Balls won't disappear on use)");
            translationBuilder.add("text.config.lootballs-config.option.ignorePerPlayerLootBallUses", "Ignore number of uses of Loot Balls in Multiplayer");
            translationBuilder.add("text.config.lootballs-config.option.ignorePerPlayerLootBallUses.tooltip", "perPlayerLootBalls must be true for this to work!");
            translationBuilder.add("text.config.lootballs-config.option.doLootBallArchaeology", "Enable loot balls from archaeology loot tables");
            translationBuilder.add("text.config.lootballs-config.option.doLootBallFishing", "Enable loot balls from fishing loot tables");
            translationBuilder.add("text.config.lootballs-config.option.doLootBallDrops", "Enable loot balls from block drops");
            translationBuilder.add("text.config.lootballs-config.option.doLootBallChests", "Enable loot balls from chest loot tables");
            translationBuilder.add("text.config.lootballs-config.option.doLootBallChests.tooltip", "Not implemented yet!");

            // Text
            translationBuilder.add("block.lootballs.loot_ball.visibility", "Loot ball visibility toggled!");
            translationBuilder.add("block.lootballs.loot_ball.wax", "Loot ball sparks set to: ");
            translationBuilder.add("block.lootballs.loot_ball.set_loot", "Loot ball loot was set to: ");
            translationBuilder.add("block.lootballs.loot_ball.already_open", "You already opened this loot ball!");
            translationBuilder.add("block.lootballs.loot_ball.open", "You found: ");
            translationBuilder.add("block.lootballs.loot_ball.bonus_loot", "BONUS LOOT!");

            translationBuilder.add("item.lootballs.loot_ball_finder.tooltip", "Right click to search for HIDDEN loot balls within a 2-chunk radius");
            translationBuilder.add("item.lootballs.loot_ball_finder.not_found", "No hidden loot balls found!");
            translationBuilder.add("item.lootballs.loot_ball_finder.very_near", "A hidden loot ball is VERY near!");
            translationBuilder.add("item.lootballs.loot_ball_finder.near", "A hidden loot ball is near!");
            translationBuilder.add("item.lootballs.loot_ball_finder.chunks", "A hidden loot ball was detected within 2 chunks!");

    }
}
