package projlab.commands.agent;

import projlab.Prototype;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.commands.ICommand;

/**
 * Leírás: Megfertőzi a virológust.
 */
public class InfectVirologistCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String agentName = args[0];
        Agent agent = (Agent) Prototype.getObjects().get(agentName);
        String virologistName = args[2];
        Virologist v1 = (Virologist) Prototype.getObjects().get(virologistName);
        v1.getInfected(agent, null);
        System.out.println("Virológus sikeresen megfertőzve.");
    }
}
