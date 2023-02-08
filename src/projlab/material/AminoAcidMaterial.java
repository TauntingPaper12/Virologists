package projlab.material;

import projlab.Prototype;
import projlab.Skeleton;

import java.io.Serializable;

/**
 * Az inaktív ágensek elkészítéséhez szükséges aminosav anyag.
 */
public class AminoAcidMaterial extends Material implements Serializable {

    /**
     * AminoAcidMaterial ctor
     */
    public AminoAcidMaterial() {
        init(true);
    }

    /**
     * AminoAcidMaterial ctor
     *
     * @param addToPrototype hozzá kell-e adnia az objektumot az objektum tárolóhoz.
     */
    public AminoAcidMaterial(boolean addToPrototype) {
        init(addToPrototype);
    }

    private void init(boolean addToPrototype) {
        if (addToPrototype)
            Prototype.addObject(this);
    }

    /**
     * Leellenőrzi, hogy az anyag ugyanolyan típusú-e, mint a paraméter.
     *
     * @param amino az ellenőrizendő anyag
     * @return true
     */
    @Override
    public boolean accept(AminoAcidMaterial amino) {
        Skeleton.printWithTabs("AminoAcidMaterial.accept(amino): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Leklónozza az aminosav anyagot.
     *
     * @return klón
     */
    @Override
    public AminoAcidMaterial clone() {
        Skeleton.printWithTabs("AminoAcidMaterial.clone(): AminoAcidMaterial");
        Skeleton.increaseTabs();

        AminoAcidMaterial clone = (AminoAcidMaterial) super.clone();

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();
        return clone;
    }
}