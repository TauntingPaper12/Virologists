package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.geneticcode.GeneticCode;
import projlab.inactiveagent.InactiveAgent;

/**
 * Hozzáadja az inaktív ágenst a virológushoz
 */
public class AddInactiveAgentCommand implements ICommand {

    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        String inactiveAgentName = args[2];
        InactiveAgent inactiveAgent = (InactiveAgent) Prototype.getObjects().get(inactiveAgentName);

        v.addInactiveAgent(inactiveAgent);
        System.out.println("Inaktív ágens sikeresen hozzáadva.");
    }
}
