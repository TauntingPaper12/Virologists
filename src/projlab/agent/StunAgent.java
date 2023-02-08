package projlab.agent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Virologist;

import java.io.Serializable;

/**
 * A bénulás ágens hatására nem tud semmit se csinálni a virológus és lehet tőle lopni.
 */
public class StunAgent extends Agent implements Serializable {
    /**
     * StunAgent ctor
     */
    public StunAgent() {
        duration = 1;
        Prototype.addObject(this);
    }

    /**
     * Ágens hatása miatt le van bénulva a virológus.
     *
     * @return igaz
     */
    @Override
    public boolean isStunned() {
        Skeleton.printWithTabs("StunAgent.isStunned(): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }
}
