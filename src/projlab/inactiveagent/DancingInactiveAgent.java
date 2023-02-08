package projlab.inactiveagent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.agent.AmnesiaAgent;
import projlab.agent.DancingAgent;
import projlab.inactiveagent.InactiveAgent;

import java.io.Serializable;

/**
 * Egy virológus vitustánc ágenssel megfertőzéséért felelős.
 */
public class DancingInactiveAgent extends InactiveAgent implements Serializable {
    /**
     * DancingInactiveAgent ctor
     * <p>
     * Beállítja a tulajdonost.
     *
     * @param owner a tulajdonos
     */
    public DancingInactiveAgent(Virologist owner) {
        super(owner);
        Prototype.addObject(this);
    }

    /**
     * Létrehoz egy aktív vitustánc ágenst, amelyet ráken a paraméterként kapott célpont virológusra.
     *
     * @param target a megtámadott virológus
     * @param from   a támadó virológus.
     */
    @Override
    public void infect(Virologist target, Virologist from) {

        Skeleton.printWithTabs("DancingInactiveAgent.infect(target, from)");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("newAgent = new DancingAgent()");
        Agent newAgent = new DancingAgent();

        Timer.instance().addSteppable(newAgent);
        target.getInfected(newAgent, from);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
