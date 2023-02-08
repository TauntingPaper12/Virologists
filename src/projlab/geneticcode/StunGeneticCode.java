package projlab.geneticcode;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.inactiveagent.AmnesiaInactiveAgent;
import projlab.inactiveagent.InactiveAgent;
import projlab.inactiveagent.StunInactiveAgent;
import projlab.material.AminoAcidMaterial;
import projlab.material.Material;
import projlab.material.NucleotideMaterial;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A bénító inaktív ágens létrehozásáért felelős.
 */
public class StunGeneticCode extends GeneticCode implements Serializable {
    /**
     * StunGeneticCode ctor
     * <p>
     * Beállítja a receptet.
     */
    public StunGeneticCode() {
        recipe = new ArrayList<>();
        int NUMBER_OF_AMINOACID_NEEDED = 2;
        int NUMBER_OF_NUCLEOTID_NEEDED = 4;

        for (int i = 0; i < NUMBER_OF_AMINOACID_NEEDED; i++) {
            recipe.add(new AminoAcidMaterial(false));
        }
        for (int i = 0; i < NUMBER_OF_NUCLEOTID_NEEDED; i++) {
            recipe.add(new NucleotideMaterial(false));
        }
        Prototype.addObject(this);
    }

    /**
     * A paraméterként kapott virológus létrehoz a paraméterként átadott anyagokkal egy inaktív védő ágenst.
     *
     * @param maker     a virológus, aki létrehozza az inaktívágenst.
     * @param inventory a virológusnál lévő anyagok.
     * @return a létrehozott inaktív bénító ágens.
     */
    @Override
    public InactiveAgent makeInactiveAgent(Virologist maker, ArrayList<Material> inventory) {

        Skeleton.printWithTabs("StuntGeneticCode.makeInactiveAgent(maker, inventory) : InactiveAgent");
        Skeleton.increaseTabs();

        ArrayList<Material> requiredMaterials = isCraftable(inventory);

        if (requiredMaterials == null) {
            Skeleton.printWithTabs("if (requiredMaterials == null) : true");
            Skeleton.increaseTabs();

            Skeleton.printWithTabs("return null");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return null;
        }

        maker.removeMaterials(requiredMaterials);

        Skeleton.printWithTabs("newAgent = new StunInactiveAgent(maker)");
        InactiveAgent newAgent = new StunInactiveAgent(maker);

        Timer.instance().addSteppable(newAgent);

        Skeleton.printWithTabs("return newAgent");
        Skeleton.decreaseTabs();

        return newAgent;
    }

    /**
     * Visszajelez, ha bénító genetikus kódot kap paraméterként.
     *
     * @param code a genetikai kód
     * @return true
     */
    @Override
    public boolean accept(StunGeneticCode code) {

        Skeleton.printWithTabs("StuntGeneticCode.accept(code) : boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();

        return true;
    }

    /**
     * Leklónozza a bénító genetikai kódot.
     *
     * @return klón
     */
    @Override
    public StunGeneticCode clone() {

        Skeleton.printWithTabs("StuntGeneticCode.clone() : StunGeneticCode");
        Skeleton.increaseTabs();

        StunGeneticCode clone = (StunGeneticCode) super.clone();

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();

        return clone;
    }
}
