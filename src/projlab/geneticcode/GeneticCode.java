package projlab.geneticcode;

import projlab.Skeleton;
import projlab.Virologist;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.AminoAcidMaterial;
import projlab.material.Material;
import projlab.material.NucleotideMaterial;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Az inaktív ágensek létrehozásáért felelős.
 */
public abstract class GeneticCode implements Cloneable, Serializable {
    /**
     * Eltárolja az összes létező genetikai kód típust.
     */
    public static Class[] GENETICCODES = new Class[]{AmnesiaGeneticCode.class,
            DancingGeneticCode.class,
            StunGeneticCode.class,
            ProtectionGeneticCode.class
    };
    /**
     * Eltárolja az inaktív ágens létrehozásához szükséges anyagokat.
     */
    protected ArrayList<Material> recipe;

    /**
     * A paraméterként kapott virológus létrehoz a paraméterként átadott anyagokkal egy inaktív ágenst.
     *
     * @param maker     a létrehozó
     * @param inventory a létrehozónál lévő anyagok
     * @return ágens
     */
    public abstract InactiveAgent makeInactiveAgent(Virologist maker, ArrayList<Material> inventory);

    /**
     * Megvizsgálja, hogy a paraméterül kapott anyagokból előállítható-e az ágens.
     *
     * @param materials a virológusnál lévő anyagok, amelyekből elő szeretné állítani az ágenst.
     * @return az előállításhoz szükséges anyagok, ha elő tudja állítani egyébként null.
     */
    public ArrayList<Material> isCraftable(ArrayList<Material> materials) {

        Skeleton.printWithTabs("GeneticCode.isCraftable(materials): ArrayList<Material>");
        Skeleton.increaseTabs();

        ArrayList<Material> cloneMaterials = (ArrayList<Material>) materials.clone();
        ArrayList<Material> requiredMaterials = new ArrayList<>();

        for (Material ingredient : recipe) {
            boolean goodMaterialFound = false;
            Material goodMaterial = null;

            for (Material material : cloneMaterials) {
                if (material.accept(ingredient)) {
                    goodMaterial = material;
                    goodMaterialFound = true;
                    break;

                }
            }
            if (goodMaterialFound) {
                requiredMaterials.add(goodMaterial);
                cloneMaterials.remove(goodMaterial);
            } else {
                Skeleton.printWithTabs("if(goodMaterialFound) : false");
                requiredMaterials.clear();

                Skeleton.printWithTabs("return null");
                Skeleton.decreaseTabs();

                return null;
            }
        }

        Skeleton.printWithTabs("return requiredMaterials");
        Skeleton.decreaseTabs();

        return requiredMaterials;
    }

    /**
     * Leellenőrzi, hogy a genetikai kód ugyanolyan típusú-e, mint a paraméter.
     *
     * @param code az ellenőrizendő genetikai kód
     * @return true, ha igen, egyébként false
     */
    public boolean accept(GeneticCode code) {

        Skeleton.printWithTabs("GeneticCode.accept(code) : boolean");
        Skeleton.increaseTabs();

        if (code instanceof AmnesiaGeneticCode) {
            Skeleton.printWithTabs("if (code instanceof AmnesiaGeneticCode) : true");
            Skeleton.increaseTabs();

            boolean accept = accept((AmnesiaGeneticCode) code);

            Skeleton.printWithTabs("return accept");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();

            return accept;
        } else if (code instanceof ProtectionGeneticCode) {
            Skeleton.printWithTabs("if (code instanceof ProtectionGeneticCode) : true");
            Skeleton.increaseTabs();

            boolean accept = accept((ProtectionGeneticCode) code);

            Skeleton.printWithTabs("return accept");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();

            return accept;
        } else if (code instanceof StunGeneticCode) {
            Skeleton.printWithTabs("if (code instanceof StunGeneticCode) : true");
            Skeleton.increaseTabs();

            boolean accept = accept((StunGeneticCode) code);

            Skeleton.printWithTabs("return accept");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();

            return accept;
        } else if (code instanceof DancingGeneticCode) {
            Skeleton.printWithTabs("if (code instanceof DancingGeneticCode) : true");
            Skeleton.increaseTabs();

            boolean accept = accept((DancingGeneticCode) code);

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
     * Leellenőrzi, hogy a genetikai kód ugyanolyan típusú-e, mint a paraméter.
     *
     * @param code az ellenőrizendő genetikai kód
     * @return false
     */
    public boolean accept(AmnesiaGeneticCode code) {
        Skeleton.printWithTabs("GeneticCode.accept(code) : boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();

        return false;
    }

    /**
     * Leellenőrzi, hogy a genetikai kód ugyanolyan típusú-e, mint a paraméter.
     *
     * @param code az ellenőrizendő genetikai kód
     * @return false
     */
    public boolean accept(ProtectionGeneticCode code) {
        Skeleton.printWithTabs("GeneticCode.accept(code) : boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();

        return false;
    }

    /**
     * Leellenőrzi, hogy a genetikai kód ugyanolyan típusú-e, mint a paraméter.
     *
     * @param code az ellenőrizendő genetikai kód
     * @return false
     */
    public boolean accept(StunGeneticCode code) {
        Skeleton.printWithTabs("GeneticCode.accept(code) : boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();

        return false;
    }

    /**
     * Leellenőrzi, hogy a genetikai kód ugyanolyan típusú-e, mint a paraméter.
     *
     * @param code az ellenőrizendő genetikai kód
     * @return false
     */
    public boolean accept(DancingGeneticCode code) {
        Skeleton.printWithTabs("GeneticCode.accept(code) : boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();

        return false;
    }

    /**
     * Leklónozza a genetikai kódot.
     *
     * @return klón
     */
    @Override
    public GeneticCode clone() {
        Skeleton.printWithTabs("GeneticCode.clone() : GeneticCode");
        Skeleton.increaseTabs();

        try {
            GeneticCode clone = (GeneticCode) super.clone();
            clone.recipe = (ArrayList<Material>) recipe.clone();

            Skeleton.printWithTabs("return clone");
            Skeleton.decreaseTabs();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }


    }
}
