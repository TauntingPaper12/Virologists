package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.equipment.Equipment;

/**
 * Használja a megadott felszerelést a célponton.
 */
public class UseEquipmentCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);
        String targetName = args[3];
        Virologist target = (Virologist) Prototype.getObjects().get(targetName);
        String equipmentName = args[3];
        Equipment equipment = (Equipment) Prototype.getObjects().get(equipmentName);

        v.useEquipment(target, equipment);
        System.out.println("Felszerelés használva.");
    }
}
