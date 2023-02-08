package projlab.inactiveagent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.agent.AmnesiaAgent;
import projlab.agent.StunAgent;
import projlab.inactiveagent.InactiveAgent;

import java.io.Serializable;

/**
 * Egy virológus bénító ágenssel megfertőzéséért felelős.
 */
public class StunInactiveAgent extends InactiveAgent implements Serializable {
    /**
     * StunInactiveAgent ctor
     * <p>
     * Beállítja a tulajdonost.
     *
     * @param owner a tulajdonos
     */
    public StunInactiveAgent(Virologist owner) {
        super(owner);
        Prototype.addObject(this);
    }

    /**
     * Létrehoz egy aktív bénító ágenst, amelyet ráken a paraméterként kapott célpont virológusra.
     *
     * @param target a megtámadott virológus
     * @param from   a támadó virológus.
     */
    @Override
    public void infect(Virologist target, Virologist from) {
        Skeleton.printWithTabs("StunInactiveAgent.infect(target, from)");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("newAgent = new StunAgent()");
        Agent newAgent = new StunAgent();

        Timer.instance().addSteppable(newAgent);
        target.getInfected(newAgent, from);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
