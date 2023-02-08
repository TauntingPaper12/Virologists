package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.equipment.Equipment;

/**
 * Ellopja a célponttól a megadott felszerelést.
 */
public class StealEquipmentCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String virologist1Name = args[0];
        Virologist v1 = (Virologist) Prototype.getObjects().get(virologist1Name);
        String virologist2Name = args[2];
        Virologist v2 = (Virologist) Prototype.getObjects().get(virologist2Name);
        String equipmentName = args[3];
        Equipment equipment = (Equipment) Prototype.getObjects().get(equipmentName);


        boolean steal = v1.stealEquipment(v2, equipment);
        if (steal) {
            System.out.println("Felszerelés ellopása sikeres.");
        } else {
            System.out.println("Felszerelés ellopása sikertelen.");
        }
    }
}
