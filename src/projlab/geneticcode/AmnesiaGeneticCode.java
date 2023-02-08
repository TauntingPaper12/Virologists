package projlab.geneticcode;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.agent.AmnesiaAgent;
import projlab.agent.ProtectionAgent;
import projlab.inactiveagent.AmnesiaInactiveAgent;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.AminoAcidMaterial;
import projlab.material.Material;
import projlab.material.NucleotideMaterial;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Az elfelejtés inaktív ágens létrehozásáért felelős.
 */
public class AmnesiaGeneticCode extends GeneticCode implements Serializable {
    /**
     * DancingGeneticCode ctor
     * <p>
     * Beállítja a receptet.
     */
    public AmnesiaGeneticCode() {
        recipe = new ArrayList<>();
        int NUMBER_OF_AMINOACID_NEEDED = 5;
        int NUMBER_OF_NUCLEOTID_NEEDED = 5;

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
     * @return a létrehozott inaktív amnesia ágens.
     */
    @Override
    public InactiveAgent makeInactiveAgent(Virologist maker, ArrayList<Material> inventory) {

        Skeleton.printWithTabs("AmnesiaGeneticCode.makeInactiveAgent(maker, inventory): InactiveAgent");

        Skeleton.increaseTabs();

        ArrayList<Material> requiredMaterials = isCraftable(inventory);

        if (requiredMaterials == null) {
            Skeleton.printWithTabs("if (requiredMaterials == null): true");
            Skeleton.increaseTabs();
            Skeleton.printWithTabs("return null");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();

            return null;
        }

        maker.removeMaterials(requiredMaterials);

        Skeleton.printWithTabs("newAgent = new AmnesiaInactiveAgent(maker)");
        InactiveAgent newAgent = new AmnesiaInactiveAgent(maker);

        Timer.instance().addSteppable(newAgent);

        Skeleton.printWithTabs("return newAgent");
        Skeleton.decreaseTabs();

        return newAgent;
    }

    /**
     * Visszajelez, ha amnézia genetikus kódot kap paraméterként.
     *
     * @param code a genetikai kód
     * @return true
     */
    @Override
    public boolean accept(AmnesiaGeneticCode code) {

        Skeleton.printWithTabs("AmnesiaGeneticCode.accept(code): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();

        return true;
    }

    /**
     * Leklónozza az amnézia genetikai kódot.
     *
     * @return klón
     */
    @Override
    public AmnesiaGeneticCode clone() {

        Skeleton.printWithTabs("AmnesiaGeneticCode.clone(): AmnesiaGeneticCode");
        Skeleton.increaseTabs();

        AmnesiaGeneticCode clone = (AmnesiaGeneticCode) super.clone();

        Skeleton.printWithTabs("return clone");
        Skeleton.decreaseTabs();

        return clone;
    }
}
