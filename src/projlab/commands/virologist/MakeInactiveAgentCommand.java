package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.geneticcode.GeneticCode;

/**
 * Csinál egy inaktív ágens a megadott genetikai kód alapján.
 */
public class MakeInactiveAgentCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        String geneticCodeName = args[2];
        GeneticCode geneticCode = (GeneticCode) Prototype.getObjects().get(geneticCodeName);

        if (v.makeInactiveAgent(geneticCode)) {
            System.out.println("Inaktív ágens készítése sikeres.");
        } else {
            System.out.println("Inaktív ágens készítése sikertelen.");
        }
    }
}
