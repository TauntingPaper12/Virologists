package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.equipment.Equipment;
import projlab.geneticcode.GeneticCode;
import projlab.material.Material;
import projlab.util.PickupEquipmentEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A virológus felveszi a mezőn lévő felszerelést.
 */
public class PickupEquipmentCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        PickupEquipmentEnum status = v.pickupEquipment();
        switch (status) {
            case SUCCESFUL:
                System.out.println("Felszerelés felvétele sikeres.");
                break;
            case FAILED:
                System.out.println("Felszerelés felvétele sikertelen.");
                break;
            case EMPTY:
                System.out.println("Nincs a mezőn felszerelés.");
                break;
            case FULL:
                System.out.println("Túl sok felszerelése van a virológusnak.");
                break;
        }
    }
}
