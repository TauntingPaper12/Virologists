package projlab.material;

import projlab.Prototype;
import projlab.Skeleton;

import java.io.Serializable;

/**
 * Az inaktív ágensek elkészítéséhez szükséges nukleotid anyag.
 */
public class NucleotideMaterial extends Material implements Serializable {

    /**
     * NucleotideMaterial ctor
     */
    public NucleotideMaterial() {
        init(true);
    }

    /**
     * NucleotideMaterial ctor
     *
     * @param addToPrototype hozzá kell-e adnia az objektumot az objektum tárolóhoz.
     */
    public NucleotideMaterial(boolean addToPrototype) {
        init(addToPrototype);
    }

    private void init(boolean addToPrototype) {
        if (addToPrototype)
            Prototype.addObject(this);
    }

    /**
     * Leellenőrzi, hogy az anyag ugyanolyan típusú-e, mint a paraméter.
     *
     * @param nucleotide az ellenőrizendő anyag
     * @return true
     */
    @Override
    public boolean accept(NucleotideMaterial nucleotide) {
        Skeleton.printWithTabs("NucleotideMaterial.accept(nucleotide): boolean");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Leklónozza a nukleotid anyagot.
     *
     * @return klón
     */
    @Override
    public NucleotideMaterial clone() {
        Skeleton.printWithTabs("NucleotideMaterial.clone(): NucleotideMaterial");
        Skeleton.increaseTabs();

        NucleotideMaterial clone = (NucleotideMaterial) super.clone();

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();
        return clone;
    }
}
