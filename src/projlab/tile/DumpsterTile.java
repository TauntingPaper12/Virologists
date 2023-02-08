package projlab.tile;

import projlab.Skeleton;
import projlab.Virologist;
import projlab.material.Material;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A lerakóhely mezőn dobhatnak ki anyagokat a virológusok.
 */
public class DumpsterTile extends Tile implements Serializable {
    /**
     * DumpsterTile ctor
     */
    public DumpsterTile() {
        super();
    }

    /**
     * A paraméterként kapott anyagokat kitörli a virológus anyagkészletéből.
     *
     * @param materials az anyagok, amelyeket ki kell törölni a paraméterül kapott virológus anyagkészletéből.
     * @param who       az a virológus, akitől ki kell törölni az anyagokat.
     * @return true
     */
    @Override
    public boolean dumpMaterials(ArrayList<Material> materials, Virologist who) {
        Skeleton.printWithTabs("DumpsterTile.dumpMaterials(materials, who): boolean");
        Skeleton.increaseTabs();

        who.removeMaterials(materials);

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }
}
