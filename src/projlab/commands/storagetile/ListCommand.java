package projlab.commands.storagetile;

import projlab.Prototype;
import projlab.util.Util;
import projlab.commands.ICommand;
import projlab.material.Material;
import projlab.tile.StorageTile;

import java.util.ArrayList;

/**
 * Kilistázza az összes anyagot.
 */
public class ListCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String tileName = args[0];
        StorageTile storageTile = (StorageTile) Prototype.getObjects().get(tileName);
        ArrayList<Material> materials = storageTile.getMaterials();
        for (Material material : materials) {
            String key = Util.getKeyByValue(Prototype.getObjects(), material);
            System.out.println(key);
        }
    }
}
