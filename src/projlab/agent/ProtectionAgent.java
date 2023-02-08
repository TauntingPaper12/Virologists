package projlab.agent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Virologist;
import projlab.agent.Agent;

import java.io.Serializable;

/**
 * A védő ágens védelmet nyújt a virológusnak a többi rákent ágens ellen.
 */
public class ProtectionAgent extends Agent implements Serializable {
    /**
     * ProtectionAgent ctor
     */
    public ProtectionAgent() {
        duration = 2;
        Prototype.addObject(this);
    }

    /**
     * Ágens hatása,miatt védve van a virológus.
     *
     * @return igaz
     */
    @Override
    public boolean isProtected() {
        Skeleton.printWithTabs("ProtectionAgent.isProtected(): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }
}
