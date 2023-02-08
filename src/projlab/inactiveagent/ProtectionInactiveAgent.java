package projlab.inactiveagent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.agent.AmnesiaAgent;
import projlab.agent.ProtectionAgent;
import projlab.inactiveagent.InactiveAgent;

import java.io.Serializable;

/**
 * Egy virológus védő ágenssel megfertőzéséért felelős.
 */
public class ProtectionInactiveAgent extends InactiveAgent implements Serializable {
    /**
     * ProtectionInactiveAgent ctor
     * <p>
     * Beállítja a tulajdonost.
     *
     * @param owner a tulajdonos
     */
    public ProtectionInactiveAgent(Virologist owner) {
        super(owner);
        Prototype.addObject(this);
    }

    /**
     * Létrehoz egy aktív védő ágenst, amelyet ráken a paraméterként kapott célpont virológusra.
     *
     * @param target a megtámadott virológus
     * @param from   a támadó virológus.
     */
    @Override
    public void infect(Virologist target, Virologist from) {
        Skeleton.printWithTabs("ProtectionInactiveAgent.infect(target, from)");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("newAgent = new ProtectionAgent()");
        Agent newAgent = new ProtectionAgent();
        Timer.instance().addSteppable(newAgent);
        target.getInfected(newAgent, from);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
