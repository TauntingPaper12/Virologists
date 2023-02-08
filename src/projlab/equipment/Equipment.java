package projlab.equipment;

import projlab.Skeleton;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.material.Material;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A felszerelések hatásait kezeli. Amennyiben megsemmisül eltünteti önmagát
 * a virológus felszerelései közül, ekkor hatása megszűnik.
 */
public class Equipment implements Cloneable, Serializable {
    /**
     * A felszerelés tulajdonosa.
     */
    protected Virologist owner = null;

    /**
     * Beállítja a felszerelés tulajdonosát.
     *
     * @param virologist a tulajdonos
     */
    public void setOwner(Virologist virologist) {
        Skeleton.printWithTabs("Equipment.setOwner(virologist)");
        Skeleton.increaseTabs();

        owner = virologist;

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja a felszerelés tulajdonosát
     *
     * @return A felszerelés tulajdonosa
     */
    public Virologist getOwner() {
        return owner;
    }

    /**
     * Visszatérési értékével jelzi, hogy a virológus védett-e.
     *
     * @return false
     */
    public boolean isProtected() {
        Skeleton.printWithTabs("Equipment.isProtected(): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Visszatérési értékével jelzi, hogy a paraméterként kapott kenést
     * vissza tudja-e kenni a paraméterként kapott támadóra.
     *
     * @param agent a kent ágens
     * @param from  a virológus, aki a kenést végezte
     * @return false
     */
    public boolean repel(Agent agent, Virologist from) {
        Skeleton.printWithTabs("Equipment.repel(agent, from): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * A zsák esetében elrakja az anyagokat, amik elférnek.
     * Alapértelmezett esetben nem csinál semmit.
     *
     * @param remainingMaterials az anyagok
     * @return remainingMaterials
     */
    public ArrayList<Material> acceptMaterial(ArrayList<Material> remainingMaterials) {
        Skeleton.printWithTabs("Equipment.acceptMaterial(remainingMaterials): ArrayList<Material>");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return remainingMaterials");
        Skeleton.decreaseTabs();
        return remainingMaterials;
    }

    /**
     * A zsák esetében kiveszi azokat az anyagokat, amik benne voltak a zsákban.
     * Alapértelmezett esetben nem csinál semmit.
     *
     * @param materials az anyagok
     */
    public void removeMaterials(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Equipment.removeMaterial(materials)");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * A zsák esetében visszaadja a benne lévő anyagokat.
     * Alapértelmezett esetben nem csinál semmit.
     *
     * @return üres lista
     */
    public ArrayList<Material> getMaterials() {
        Skeleton.printWithTabs("Equipment.getMaterials():  ArrayList<Material>");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return empty ArrayList<>");
        Skeleton.decreaseTabs();
        return new ArrayList<>();
    }


    public void use(Virologist target) {
    }

    /**
     * Leklónozza a felszerelést.
     *
     * @return klón
     */
    @Override
    public Equipment clone() {
        Skeleton.printWithTabs("Equipment.clone(): Equipment");
        Skeleton.increaseTabs();

        try {
            Equipment clone = (Equipment) super.clone();
            clone.owner = null;

            Skeleton.printWithTabs("return clone");
            Skeleton.decreaseTabs();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
