package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.inactiveagent.InactiveAgent;

/**
 * Megfertőzi a célpontot a megadott inaktív ágenssel.
 */
public class InfectCommand implements ICommand {

    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        String targetName = args[2];
        Virologist target = (Virologist) Prototype.getObjects().get(targetName);

        String inactiveAgentName = args[3];
        InactiveAgent inactiveAgent = (InactiveAgent) Prototype.getObjects().get(inactiveAgentName);

        if (v.infectVirologistWith(inactiveAgent, target)) {
            System.out.println("Inaktív ágens kenése sikeres.");
        } else {
            System.out.println("Inaktív ágens kenése sikertelen.");
        }
    }
}
