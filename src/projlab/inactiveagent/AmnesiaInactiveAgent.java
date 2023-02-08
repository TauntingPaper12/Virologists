package projlab.inactiveagent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.agent.AmnesiaAgent;

import java.io.Serializable;

/**
 * Egy virológus amnézia ágenssel megfertőzéséért felelős.
 */
public class AmnesiaInactiveAgent extends InactiveAgent implements Serializable {
    /**
     * AmnesiaInactiveAgent ctor
     * <p>
     * Beállítja a tulajdonost.
     *
     * @param owner a tulajdonos
     */
    public AmnesiaInactiveAgent(Virologist owner) {
        super(owner);
        Prototype.addObject(this);
    }

    /**
     * Létrehoz egy aktív amnesia ágenst, amelyet ráken a paraméterként kapott célpont virológusra.
     *
     * @param target a megtámadott virológus
     * @param from   a támadó virológus.
     */
    @Override
    public void infect(Virologist target, Virologist from) {
        Skeleton.printWithTabs("AmnesiaInactiveAgent.infect(target, from)");
        Skeleton.increaseTabs();


        Skeleton.printWithTabs("newAgent = new AmnesiaAgent()");
        Agent newAgent = new AmnesiaAgent();

        Timer.instance().addSteppable(newAgent);
        target.getInfected(newAgent, from);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
