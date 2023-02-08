package projlab.agent;

import projlab.Prototype;
import projlab.Skeleton;
import projlab.Timer;
import projlab.Virologist;
import projlab.material.Material;
import projlab.tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A medveágens szerepe, hogy amikor egy virológusra rá van kenve,
 * akkor elveszti a mozgás feletti irányítást a virológus és a raktárban lévő
 * összes anyagot megsemmisíti. Ha a virológust lebaltázzák amikor rajta van az
 * ágens, akkor game over.
 */
public class BearDeezNutsInYourMouthAgent extends DancingAgent implements Serializable {
    /**
     * BearDeezNutsInYourMouthAgent ctor
     */
    public BearDeezNutsInYourMouthAgent() {
        super();
        duration = -1; // Nem jár le
    }

    /**
     * Felülírja az alap step() függvényt, nem csinál semmit.
     */
    @Override
    public void step() {
        Skeleton.printWithTabs("BearDeezNutsInYourMouthAgent.step()");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Terjeszti magát a medveágens.
     *
     * @param virologists akikre terjed az ágens
     */
    public void spread(ArrayList<Virologist> virologists) {
        Skeleton.printWithTabs("BearDeezNutsInYourMouthAgent.spread(virologists)");
        Skeleton.increaseTabs();

        for (Virologist virologist : virologists) {
            if (virologist != infected) {
                BearDeezNutsInYourMouthAgent newAgent = new BearDeezNutsInYourMouthAgent();
                Timer.instance().addSteppable(newAgent);
                virologist.getInfected(newAgent, null);
            }
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Elpusztítja az összes mezőn lévő anyagot, ha olyan az ágens.
     *
     * @param tile
     */
    @Override
    public void destroyMaterialsOnTile(Tile tile) {
        Skeleton.printWithTabs("BearDeezNutsInYourMouthAgent.destroyMaterialsOnTile(tile)");
        Skeleton.increaseTabs();

        ArrayList<Material> materials = tile.getMaterials();
        tile.removeMaterial(materials);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Ha a virológust megütik baltával, akkor az ágens kifejti hatását.
     */
    @Override
    public void getHit() {
        Skeleton.printWithTabs("BearDeezNutsInYourMouthAgent.getHit()");
        Skeleton.increaseTabs();

        infected.die();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
