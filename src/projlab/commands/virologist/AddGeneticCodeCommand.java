package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.geneticcode.GeneticCode;

/**
 * Hozzáadja a megadott genetikai kódot a virológushoz
 */
public class AddGeneticCodeCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        String geneticCodeName = args[2];
        GeneticCode geneticCode = (GeneticCode) Prototype.getObjects().get(geneticCodeName);

        v.addGeneticCode(geneticCode);
        System.out.println("Genetikai kód sikeresen hozzáadva.");
    }
}
