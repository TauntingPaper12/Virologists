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
 * Ellopja a célponttól a megadott anyagokat.
 */
public class StealMaterialsCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        Virologist target = (Virologist) Prototype.getObjects().get(args[2]);

        ArrayList<Material> materials = new ArrayList<>();
        List<String> materialNames = Arrays.asList(args).subList(3, args.length);
        for (String names : materialNames) {
            Material material = (Material) Prototype.getObjects().get(names);
            if (!Objects.isNull(material)) {
                materials.add(material);
            }
        }

        if (v.stealMaterials(target, materials)) {
            System.out.println("Anyagok ellopása sikeres.");
        } else {
            System.out.println("Anyagok ellopása sikertelen.");
        }
    }
}
