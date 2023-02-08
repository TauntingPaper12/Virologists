package projlab.commands.storagetile;

import projlab.Prototype;
import projlab.commands.ICommand;
import projlab.material.Material;
import projlab.tile.StorageTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Hozzáad anyagokat az adott raktárhoz.
 */
public class AddMaterialsToStorageTileCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String tileName = args[0];
        StorageTile storageTile = (StorageTile) Prototype.getObjects().get(tileName);

        ArrayList<Material> materials = new ArrayList<>();
        List<String> materialNames = Arrays.asList(args).subList(2, args.length);
        for (int i = 0; i < materialNames.size(); i++) {
            Material temp = (Material) Prototype.getObjects().get(materialNames.get(i));
            if (!Objects.isNull(temp)) {
                materials.add(materials.size(), temp);
            }
        }
        storageTile.addMaterial(materials);
        System.out.println("Anyagok sikeresen hozzáadva.");
    }
}
