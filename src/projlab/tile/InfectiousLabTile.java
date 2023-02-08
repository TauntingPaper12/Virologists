package projlab.tile;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.agent.BearDeezNutsInYourMouthAgent;

import java.io.Serializable;

/**
 * A fertőző labor mezőben tudják megtanulni a genetikai kódokat
 * a virológusok, amelyek a játék megnyeréséhez kellenek.
 * Ha rálépnek a mezőre, akkor a virológus megfertőződik medveágenssel.
 */
public class InfectiousLabTile extends LabTile implements Serializable {

    /**
     * InfectiousLabTile ctor
     */
    public InfectiousLabTile() {
        super();
    }

    /**
     * A paraméterként kapott virológust felveszi a mezőn szereplő virológusok közé.
     * Ha sikerült felvenni, akkor a virológus megfertőződik medveágenssel.
     *
     * @param virologist az a virológus, aki a mezőre lép, tehát fel kell venni a mezőn szerepló virológusok közé.
     */
    @Override
    public void accept(Virologist virologist) {
        Skeleton.printWithTabs("InfectiousLabTile.accept(virologist)");
        Skeleton.increaseTabs();

        super.accept(virologist);
        BearDeezNutsInYourMouthAgent newAgent = new BearDeezNutsInYourMouthAgent();
        Timer.instance().addSteppable(newAgent);
        virologist.getInfected(newAgent, null);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
