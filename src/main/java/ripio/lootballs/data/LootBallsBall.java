package ripio.lootballs.data;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class LootBallsBall {
    private final String name;
    private final String rarity;
    private final String table;
    private final ArrayList<BallSource> sources;
    @Nullable
    private final ArrayList<BallVariant> variants;

    public LootBallsBall(
            String name,
            String rarity,
            String table,
            ArrayList<BallSource> sources,
            @Nullable ArrayList<BallVariant> variants
    ) {
        this.name = name;
        this.rarity = rarity;
        this.table = table;
        this.sources = sources;
        this.variants = variants;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getTable() {
        return table;
    }

    public ArrayList<BallSource> getSources() {
        return sources;
    }

    @Nullable
    public ArrayList<BallVariant> getVariants() {
        return variants;
    }

}

class BallSource {
    public String type;
    @Nullable
    public String biome;
    @Nullable
    public String height;
    @Nullable
    public int weight;
}

class BallVariant {
    public String name;
    public String table;
}