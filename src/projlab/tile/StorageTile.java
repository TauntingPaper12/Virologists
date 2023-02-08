package projlab.tile;

import projlab.util.CustomRandom;
import projlab.Skeleton;
import projlab.Steppable;
import projlab.material.Material;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * A raktár mezőben találhatóak az aminosavak és a nukleotidok, amelyeket a virológusok tudnak felvenni.
 */
public class StorageTile extends Tile implements Steppable, Serializable {
    /**
     * Az anyag újragenerálásához hátralévő idő.
     */
    private int counter;
    /**
     * Az anyagok, amik az adott pillanatban találhatóak.
     */
    private ArrayList<Material> materials;
    /**
     * A felszerelés újragenerálásának ideje.
     */
    private final int RESPAWN_TIME = 3;
    /**
     * A raktárban tárolható anyagok maximális száma.
     */
    private final int MAX_COUNT = 5;

    /**
     * StorageTile ctor
     */
    public StorageTile() {
        super();
        counter = RESPAWN_TIME;
        materials = new ArrayList<>();
    }

    /**
     * Számláló értékét csökkenti eggyel. Ha eléri a 0-t, akkor létrehoz egy új anyagot,
     * majd visszaállítja a számlálót eredeti értékére.
     */
    public void step() {
        Skeleton.printWithTabs("StorageTile.step()");
        Skeleton.increaseTabs();

        if (counter <= 0 && materials.size() < MAX_COUNT) {
            Skeleton.printWithTabs("if (counter <= 0 && materials.size() < MAX_COUNT) : true");
            Skeleton.increaseTabs();

            counter = RESPAWN_TIME;
            CustomRandom rand = new CustomRandom();
            try {
                materials.add((Material) Material.MATERIALS[rand.nextInt(Material.MATERIALS.length)].getConstructor().newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            Skeleton.decreaseTabs();
        } else {
            counter--;
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja a raktárban található anyagokat.
     *
     * @return a raktárban található anyagok.
     */
    @Override
    public ArrayList<Material> getMaterials() {
        Skeleton.printWithTabs("StorageTile.getMaterials(): ArrayList<Material>");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return materials");
        Skeleton.decreaseTabs();
        return materials;
    }

    /**
     * A paraméterként kapott anyagot elhelyezi a mezőn.
     *
     * @param materials azok az anyagok, amelyeket a mezőn el kell helyezni.
     */
    @Override
    public void addMaterial(ArrayList<Material> materials) {
        Skeleton.printWithTabs("StorageTile.addMaterial(materials)");
        Skeleton.increaseTabs();

        this.materials.addAll(materials);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Kitörli a mezőről a paraméterül kapott anyagokat.
     *
     * @param materials azok az anyagok, amelyeket ki kell törölni.
     * @return true, azaz sikerült a kitörlés
     */
    @Override
    public boolean removeMaterial(ArrayList<Material> materials) {
        Skeleton.printWithTabs("StorageTile.removeMaterial(materials): boolean");
        Skeleton.increaseTabs();

        this.materials.removeAll(materials);

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }
}
