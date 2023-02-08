package projlab.agent;

import projlab.util.CustomRandom;
import projlab.Prototype;
import projlab.Skeleton;
import projlab.tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A vitustánc ágens szerepe, hogy amikor egy virológusra rá van kenve,
 * akkor elveszti a mozgás feletti irányítást a virológus
 */
public class DancingAgent extends Agent implements Serializable {
    /**
     * DancingAgent ctor
     */
    public DancingAgent() {
        duration = 4;
        Prototype.addObject(this);
    }

    /**
     * A paraméterül kapott mezők listájából kiválaszt egyet.
     *
     * @param tiles azok a mezők, amelyekre tud lépni a virológus.
     * @return egy random kiválasztott mező.
     */
    @Override
    public Tile dancing(ArrayList<Tile> tiles) {
        Skeleton.printWithTabs("DancingAgent.dancing(tiles): Tile");
        Skeleton.increaseTabs();

        CustomRandom rand = new CustomRandom();
        int randomTileIndex = rand.nextInt(tiles.size(), true);

        Skeleton.printWithTabs("return randomTile");
        Skeleton.decreaseTabs();
        return tiles.get(randomTileIndex);
    }
}
