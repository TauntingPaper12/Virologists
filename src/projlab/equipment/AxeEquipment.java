package projlab.equipment;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Virologist;

import java.io.Serializable;

/**
 * A baltafelszerelés szerepe, hogy a medveágenssel megfertőzött virológust megölje.
 */
public class AxeEquipment extends Equipment implements Serializable {
    private int numberOfUses = 1;

    /**
     * AxeEquipment ctor
     */
    public AxeEquipment() {
        Prototype.addObject(this);
    }

    /**
     * Megcsapja a célpontot.
     *
     * @param target a célpont
     */
    @Override
    public void use(Virologist target) {
        Skeleton.printWithTabs("AxeEquipment.use(target)");
        Skeleton.increaseTabs();

        if (numberOfUses > 0) {
            Skeleton.printWithTabs("if (numberOfUses > 0) : true");
            Skeleton.increaseTabs();

            target.getHit();
            numberOfUses--;

            Skeleton.decreaseTabs();
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
