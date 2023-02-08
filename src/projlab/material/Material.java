package projlab.material;

import projlab.Skeleton;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Az inaktív ágensek elkészítéséhez szükséges objektumok alapja.
 */
public class Material implements Cloneable, Serializable {
    /**
     * Az összes létező anyagtípus.
     */
    public final static Class[] MATERIALS = {
            AminoAcidMaterial.class,
            NucleotideMaterial.class
    };

    /**
     * Leellenőrzi, hogy az anyag ugyanolyan típusú-e, mint a paraméter.
     *
     * @param material az ellenőrizendő anyag
     * @return true, ha igen, egyébként false
     */
    public boolean accept(Material material) {
        Skeleton.printWithTabs("Material.accept(material): boolean");
        Skeleton.increaseTabs();
        if (material instanceof AminoAcidMaterial) {
            Skeleton.printWithTabs("if (material instanceof AminoAcidMaterial): true");
            Skeleton.increaseTabs();
            boolean accept = accept((AminoAcidMaterial) material);

            Skeleton.printWithTabs("return accept");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return accept;
        } else if (material instanceof NucleotideMaterial) {
            Skeleton.printWithTabs("if (material instanceof NucleotideMaterial): true");
            Skeleton.increaseTabs();
            boolean accept = accept((NucleotideMaterial) material);

            Skeleton.printWithTabs("return accept");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return accept;
        }
        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Leellenőrzi, hogy az anyag ugyanolyan típusú-e, mint a paraméter.
     *
     * @param amino az ellenőrizendő anyag
     * @return false
     */
    public boolean accept(AminoAcidMaterial amino) {
        Skeleton.printWithTabs("Material.accept(amino): boolean");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Leellenőrzi, hogy az anyag ugyanolyan típusú-e, mint a paraméter.
     *
     * @param nucleotide az ellenőrizendő anyag
     * @return false
     */
    public boolean accept(NucleotideMaterial nucleotide) {
        Skeleton.printWithTabs("Material.accept(nucleotide): boolean");
        Skeleton.increaseTabs();
        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Leklónozza az anyagot.
     *
     * @return klón
     */
    @Override
    public Material clone() {
        Skeleton.printWithTabs("Material.clone(): Material");
        Skeleton.increaseTabs();
        try {
            Material clone = (Material) super.clone();
            Skeleton.printWithTabs("return clone");
            Skeleton.decreaseTabs();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}