package projlab.equipment;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Virologist;
import projlab.agent.Agent;

import java.io.Serializable;

/**
 * A kesztyű felszerelés szerepe, hogy a virológusra rákent ágenst, vissza tudja
 * kenni a támadóra az áldozat. Egy használat után elszakad és ekkor eltűnik a
 * virológus felszerelései közül.
 */
public class GlovesEquipment extends Equipment implements Serializable {
    /**
     * A kesztyű élete.
     */
    int health = 3;

    /**
     * GlovesEquipment ctor
     */
    public GlovesEquipment() {
        Prototype.addObject(this);
    }

    /**
     * Kitörlődik a birtoklójának a felszerelés tárából, majd a támadó
     * virológust visszafertőzi a paraméterül kapott ágenssel.
     *
     * @param agent az az ágens, amellyel vissza kell fertőzni a támadó virológust
     * @param from  a támadó virológus
     * @return igaz
     */
    @Override
    public boolean repel(Agent agent, Virologist from) {
        Skeleton.printWithTabs("GlovesEquipment.repel(agent, from): boolean");
        Skeleton.increaseTabs();

        if (from == null) {
            Skeleton.printWithTabs("if (from == null) : true");
            Skeleton.increaseTabs();

            Skeleton.printWithTabs("return false");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return false;
        }

        health--;
        if (health <= 0) {
            Skeleton.printWithTabs("if (health <= 0)  : true");
            Skeleton.increaseTabs();
            owner.removeEquipment(this);
            Skeleton.decreaseTabs();
        }

        from.getInfected(agent, owner);

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Leklónozza a kesztyű felszerelést.
     *
     * @return klón
     */
    @Override
    public GlovesEquipment clone() {
        Skeleton.printWithTabs("GlovesEquipment.clone(): GlovesEquipment");
        Skeleton.increaseTabs();

        GlovesEquipment clone = (GlovesEquipment) super.clone();

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();
        return clone;
    }
}
