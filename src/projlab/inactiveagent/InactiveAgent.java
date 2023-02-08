package projlab.inactiveagent;

import projlab.Skeleton;
import projlab.Steppable;
import projlab.Timer;
import projlab.Virologist;

import java.io.Serializable;

/**
 * Egy virológus ágenssel megfertőzéséért felelős.
 */
public abstract class InactiveAgent implements Steppable, Serializable {
    /**
     * Az inaktív ágens lejárati ideje.
     */
    protected int duration;
    /**
     * Az inaktív ágens tulajdonosa.
     */
    private Virologist owner;

    /**
     * InactiveAgent ctor
     * <p>
     * Beállítja a tulajdonost.
     *
     * @param owner a tulajdonos
     */
    public InactiveAgent(Virologist owner) {
        this.owner = owner;
        duration = 2;
    }

    /**
     * Csökkenti a durationt 1-gyel, ha 0 lesz akkor kiveszi önmagát a
     * virológusnál lévő inaktív ágensek közül illetve kiveszi magát a Timerből.
     */
    public void step() {

        Skeleton.printWithTabs("InactiveAgent.step()");
        Skeleton.increaseTabs();

        if (duration <= 0) {
            Skeleton.printWithTabs("if (duration <= 0) : true");
            Skeleton.increaseTabs();
            owner.removeInactiveAgent(this);
            Timer.instance().removeSteppable(this);

            Skeleton.decreaseTabs();
        }
        duration--;

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();

    }

    /**
     * Létrehoz egy aktív ágenst, amelyet ráken a paraméterként kapott célpont virológusra.
     *
     * @param target a célpont
     * @param from   aki rákente az ágenst
     */
    public abstract void infect(Virologist target, Virologist from);
}
