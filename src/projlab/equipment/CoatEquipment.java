package projlab.equipment;

import projlab.util.CustomRandom;
import projlab.Prototype;
import projlab.Skeleton;

import java.io.Serializable;

/**
 * A köpeny felszerelés szerepe, hogy a virológusra kent ágenseket megpróbálja kivédeni.
 * Ezt egy bizonyos hatásfokkal képes megtenni. Egy bizonyos mennyiségű védés után elszakad
 * és ekkor eltűnik a virológus felszerelései közül.
 */
public class CoatEquipment extends Equipment implements Serializable {
    /**
     * A köpeny élete.
     */
    private int health;
    /**
     * A köpeny védésének hatásfoka.
     */
    private int efficiency; // 0-99 scale

    /**
     * CoatEquipment ctor
     */
    public CoatEquipment() {
        health = 3;
        efficiency = 82;
        Prototype.addObject(this);
    }

    /**
     * Megvizsgálja, hogy sikeres volt-e a védés, amennyiben igen csökkenti a köpeny életpontját.
     * Amennyiben 0-ra csökken az életpontja a köpenynek kitörlődik a birtoklójának a felszerelés tárából.
     *
     * @return igaz
     */
    @Override
    public boolean isProtected() {
        Skeleton.printWithTabs("CoatEquipment.isProtected(): boolean");
        Skeleton.increaseTabs();

        CustomRandom rand = new CustomRandom();
        if (rand.nextInt(100, true) < efficiency) {
            Skeleton.printWithTabs("if (rand.nextInt(100) < efficiency): true");
            Skeleton.increaseTabs();

            health--;
            if (health <= 0) {
                Skeleton.printWithTabs("if (health <= 0) : true");
                Skeleton.increaseTabs();

                owner.removeEquipment(this);

                Skeleton.decreaseTabs();
            }
            Skeleton.decreaseTabs();

            Skeleton.printWithTabs("return true");
            Skeleton.decreaseTabs();
            return true;
        }
        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Leklónozza a köpeny felszerelést.
     *
     * @return klón
     */
    @Override
    public CoatEquipment clone() {
        Skeleton.printWithTabs("CoatEquipment.clone(): CoatEquipment");
        Skeleton.increaseTabs();

        CoatEquipment clone = (CoatEquipment) super.clone();
        clone.health = health;
        clone.efficiency = efficiency;

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();
        return clone;
    }
}
