package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.commands.ICommand;
import projlab.equipment.Equipment;
import projlab.geneticcode.GeneticCode;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.Material;

import java.util.ArrayList;

import static projlab.util.Util.getKeyByValue;

/**
 * Kilistázza a virológus felszereléseit/anyagait/
 * inaktiv ágenseit/genetikus kódjait/rajta lévő ágenseit.
 */
public class VirologistListCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        switch (args[2]) {
            case "agents":
                ArrayList<Agent> agents = v.getAgents();
                for (Agent agent : agents) {
                    String key = getKeyByValue(Prototype.getObjects(), agent);
                    System.out.println(key);
                }
                break;
            case "equipment":
                ArrayList<Equipment> equipment = v.getEquipment();
                for (Equipment e : equipment) {
                    String key = getKeyByValue(Prototype.getObjects(), e);
                    System.out.println(key);
                }
                break;
            case "geneticCode":
                ArrayList<GeneticCode> geneticCodes = v.getGeneticCodes();
                for (GeneticCode geneticCode : geneticCodes) {
                    String key = getKeyByValue(Prototype.getObjects(), geneticCode);
                    System.out.println(key);
                }
                break;
            case "materials":
                ArrayList<Material> materials = v.getMaterials();
                for (Material material : materials) {
                    String key = getKeyByValue(Prototype.getObjects(), material);
                    System.out.println(key);
                }
                break;
            case "inactiveagents":
                ArrayList<InactiveAgent> inactiveAgents = v.getInactiveAgents();
                for (InactiveAgent inactiveAgent : inactiveAgents) {
                    String key = getKeyByValue(Prototype.getObjects(), inactiveAgent);
                    System.out.println(key);
                }
                break;
        }
    }
}
