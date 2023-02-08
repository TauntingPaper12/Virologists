package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.material.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A virológus felveszi a megadott mezőn lévő anyagokat.
 */
public class PickupMaterialsCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        ArrayList<Material> materials = new ArrayList<>();
        List<String> materialNames = Arrays.asList(args).subList(2, args.length);
        for (String names : materialNames) {
            Material material = (Material) Prototype.getObjects().get(names);
            if (!Objects.isNull(material)) {
                materials.add(material);
            }
        }

        if (v.pickupMaterials(materials)) {
            System.out.println("Anyagok felvétele sikeres.");
        } else {
            System.out.println("Anyagok felvétele sikertelen.");
        }
    }
}
