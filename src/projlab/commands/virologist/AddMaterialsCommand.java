package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Hozzáadja a megadott anyago(ka)t a virológushoz.
 */
public class AddMaterialsCommand implements ICommand {
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

        v.addMaterials(materials);
        System.out.println("Anyagok sikeresen hozzáadva.");
    }
}
