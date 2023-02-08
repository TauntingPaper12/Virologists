package projlab.agent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Virologist;
import projlab.agent.Agent;

import java.io.Serializable;

/**
 * Amikor a virológusra rákenik, az elfelejti az összes addig megtanult genetikai kódjait.
 */
public class AmnesiaAgent extends Agent implements Serializable {
    /**
     * AmnesiaAgent ctor
     */
    public AmnesiaAgent() {
        duration = 1;
        Prototype.addObject(this);
    }

    /**
     * Kitörli a paraméterként kapott virológus megtanult genetikai kódjait.
     *
     * @param virologist az a virológus, akinek a megtanult genetikai kódjait ki kell törölni.
     */
    @Override
    public void initialEffect(Virologist virologist) {
        Skeleton.printWithTabs("AmnesiaAgent.initialEffect(virologist)");
        Skeleton.increaseTabs();

        super.initialEffect(virologist);
        virologist.unlearnGeneticCodes();

        Skeleton.decreaseTabs();
        Skeleton.printWithTabs("return");
    }
}