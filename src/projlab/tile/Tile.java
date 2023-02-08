package projlab.tile;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Virologist;
import projlab.equipment.Equipment;
import projlab.geneticcode.GeneticCode;
import projlab.material.Material;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A mezők ősosztálya, amely tárolja a rajta lévő virológusokat és a szomszédokat.
 */
public class Tile implements Serializable {
    /**
     * A szomszédos mezők.
     */
    private ArrayList<Tile> neighbours;
    /**
     * A mezőn tartozkodó virológusok.
     */
    private ArrayList<Virologist> virologists;

    /**
     * Tile ctor
     */
    public Tile() {
        neighbours = new ArrayList<>();
        virologists = new ArrayList<>();
        Prototype.addObject(this);
    }

    /**
     * Visszaadja a mezőn található virológusokat
     *
     * @return A mezőn találhatő virológusok.
     */

    public ArrayList<Virologist> getVirologists() {
        return virologists;
    }

    /**
     * A paraméterként kapott virológust felveszi a mezőn szereplő virológusok közé.
     *
     * @param virologist az a virológus, aki a mezőre lép, tehát fel kell venni a mezőn szerepló virológusok közé.
     */
    public void accept(Virologist virologist) {
        Skeleton.printWithTabs("Tile.accept(virologist)");

        ArrayList<Virologist> target = new ArrayList<>();
        target.add(virologist);
        for (Virologist v : virologists) {
            v.spreadAgents(target);
        }
        virologists.add(virologist);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * A paraméterként kapott virológust eltávolítja a mezőről.
     *
     * @param virologist az a virológus, aki a mezőről ellép, tehát ki kell törölni a mezőn szerepló virológusok közül.
     */
    public void remove(Virologist virologist) {
        Skeleton.printWithTabs("Tile.remove(virologist)");
        Skeleton.increaseTabs();
        virologists.remove(virologist);
        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Nem ad vissza genetikai kódot, mert nem labor mező.
     *
     * @return null
     */
    public GeneticCode learnGeneticCode() {
        Skeleton.printWithTabs("Tile.learnGeneticCode(): GeneticCode");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return null");
        Skeleton.decreaseTabs();
        return null;
    }

    /**
     * Nem ad vissza felszerelést, mert nem óvóhely,
     *
     * @return null.
     */
    public Equipment getEquipment() {
        Skeleton.printWithTabs("Tile.getEquipment(): Equipment");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return null");
        Skeleton.decreaseTabs();
        return null;
    }

    /**
     * Nem ad vissza anyagokat, mert nem raktár mező.
     *
     * @return üres lista.
     */
    public ArrayList<Material> getMaterials() {
        Skeleton.printWithTabs("Tile.getMaterials(): ArrayList<Material>");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return new ArrayList<>()");
        Skeleton.decreaseTabs();
        return new ArrayList<>();
    }

    /**
     * Nem töröl anyagokat, mert nem lerakóhely.
     *
     * @param materials anyagok amelyeket törölni kell.
     * @param who       a virológus akitől törölni kéne az anyagokat.
     * @return false
     */
    public boolean dumpMaterials(ArrayList<Material> materials, Virologist who) {
        Skeleton.printWithTabs("Tile.dumpMaterials(materials, who): boolean");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Visszaadja a mező szomszédainak listáját.
     *
     * @return szomszédos mezők.
     */
    public ArrayList<Tile> getNeighbours() {
        Skeleton.printWithTabs("Tile.getNeighbours(): ArrayList<Tile>");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return neighbours");
        Skeleton.decreaseTabs();
        return neighbours;
    }

    /**
     * Hozzáadja a paraméterként kapott mezőket a szomszédok listához.
     *
     * @param neighbours azok a mezők, amelyeket a szomszéd listához kell adni.
     */
    public void addNeighbours(ArrayList<Tile> neighbours) {
        Skeleton.printWithTabs("Tile.addNeighbours(neighbours)");
        Skeleton.increaseTabs();
        this.neighbours.addAll(neighbours);
        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Hozzáadja a paraméterként kapott mezőt a szomszédok listához.
     *
     * @param neighbour az a mező, amit a szomszéd listához kell adni.
     */
    public void addNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }

    /**
     * Elveszi a paraméterként kapott mezőket a szomszédok listából, ha benne vannak.
     *
     * @param neighbours azok a mezők, amelyeket a szomszéd listából el kell venni.
     */
    public void removeNeighbours(ArrayList<Tile> neighbours) {
        Skeleton.printWithTabs("Tile.removeNeighbours(neighbours)");
        Skeleton.increaseTabs();
        this.neighbours.removeAll(neighbours);
        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Nem ad hozzá anyagokat a mezőhőz, mert nem raktár mező.
     *
     * @param materials anyagok amelyeket hozzá kéne adni a mezőhőz.
     */
    public void addMaterial(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Tile.addMaterial(materials)");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Nem töröl ki anyagokat, mert nem raktár mező.
     *
     * @param materials anyagok amelyeket ki kéne törölni.
     * @return false, azaz nem sikerült a kitörlés
     */
    public boolean removeMaterial(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Tile.removeMaterial(materials) : boolean");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }
}
