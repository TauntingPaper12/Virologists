package projlab.equipment;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.material.Material;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A hátizsák felszerelése szerepe, hogy megnövelje az anyagok számát, amelyet magával
 * tud vinni a virológus. Nem tud elszakadni, csak akkor tűnik el a virológustól, ha ellopják tőle.
 */
public class BackpackEquipment extends Equipment implements Serializable {
    /**
     * Meghatározza, hogy mennyivel növeli az anyagok számát, amit a virológus magával tud vinni.
     */
    private int MAX_MATERIAL_COUNT = 5;
    /**
     * A zsákban tárolt anyagok.
     */
    private ArrayList<Material> materials;

    /**
     * BackpackEquipment ctor
     */
    public BackpackEquipment() {
        materials = new ArrayList<>();
        Prototype.addObject(this);
    }

    /**
     * A paraméterként kapott anyagokat eltárolja., amelyeket nem férnek bele azokkal visszatér. Ha minden belefért, akkor null-val tér vissza.
     *
     * @param remainingMaterials az anyagok, amelyeket el kell tárolni a zsákban.
     * @return azok az anyagoknak a listája, amelyek nem fértek be a zsákba, ha minden befért akkor null.
     */
    @Override
    public ArrayList<Material> acceptMaterial(ArrayList<Material> remainingMaterials) {
        Skeleton.printWithTabs("BackpackEquipment.acceptMaterials(remainingMaterials): ArrayList<Material>");
        Skeleton.increaseTabs();

        while ((this.materials.size() < MAX_MATERIAL_COUNT && remainingMaterials.size() > 0)) {
            this.materials.add(remainingMaterials.get(0));
            remainingMaterials.remove(0);
        }

        Skeleton.printWithTabs("return materials");
        Skeleton.decreaseTabs();
        return remainingMaterials;
    }

    /**
     * A paraméterként kapott anyagokat kitörli.
     *
     * @param materials azok az anyagok, amelyeket ki kell törölni a zsákból.
     */
    @Override
    public void removeMaterials(ArrayList<Material> materials) {
        Skeleton.printWithTabs("BackpackEquipment.removeMaterials()");
        Skeleton.increaseTabs();

        this.materials.remove(materials);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja a zsákban lévő anyagokat.
     *
     * @return a zsákban lévő anyagok.
     */
    @Override
    public ArrayList<Material> getMaterials() {
        Skeleton.printWithTabs("BackpackEquipment.getMaterials(): ArrayList<Material>");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return materials");
        Skeleton.decreaseTabs();
        return materials;
    }

    /**
     * Leklónozza a zsák felszerelést, de a benne lévő anyagokat nem.
     *
     * @return klón
     */
    @Override
    public BackpackEquipment clone() {
        Skeleton.printWithTabs("BackpackEquipment.clone(): BackpackEquipment");
        Skeleton.increaseTabs();

        BackpackEquipment clone = (BackpackEquipment) super.clone();
        clone.materials = new ArrayList<>();

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();
        return clone;
    }
}
