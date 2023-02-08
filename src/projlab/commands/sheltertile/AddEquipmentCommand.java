package projlab.commands.sheltertile;

import projlab.Prototype;
import projlab.commands.ICommand;
import projlab.equipment.Equipment;
import projlab.tile.ShelterTile;

/**
 * Beállítja azt, hogy milyen felszerelése generálódik az adott óvóhelyen.
 */
public class AddEquipmentCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String tileName = args[0];
        ShelterTile shelterTile = (ShelterTile) Prototype.getObjects().get(tileName);
        String equipmentName = args[2];
        Equipment equipment = (Equipment) Prototype.getObjects().get(equipmentName);
        shelterTile.addEquipment(equipment);
        System.out.println("Felszerelés sikeresen hozzáadva.");
    }
}
