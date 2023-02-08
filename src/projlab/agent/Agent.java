package projlab.agent;

import projlab.Skeleton;
import projlab.Steppable;
import projlab.Timer;
import projlab.Virologist;
import projlab.material.Material;
import projlab.tile.Tile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A virológusra kent ágensek hatásait kezeli.
 */
public abstract class Agent implements Steppable, Serializable {
    /**
     * Ez határozza meg, mennyi ideig tart a virológusra kent ágens hatása.
     */
    protected int duration;

    /**
     * Az ágenssel megfertőzött virológus.
     */
    protected Virologist infected;

    /**
     * Csökkenti a durationt 1-gyel, ha 0 lesz akkor kiveszi önmagát a virológusra ható ágensek közül illetve kiveszi magát a Timerből.
     */
    public void step() {
        Skeleton.printWithTabs("Agent.step()");
        Skeleton.increaseTabs();

        if (duration <= 0) {
            Skeleton.printWithTabs("if (duration <= 0) : true");
            Skeleton.increaseTabs();

            infected.removeAgent(this);
            Timer.instance().removeSteppable(this);

            Skeleton.decreaseTabs();
        }
        duration--;

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja, hogy a virológus védett-e.
     *
     * @return false
     */
    public boolean isProtected() {
        Skeleton.printWithTabs("Agent.isProtected(): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Visszaadja, hogy a virológus le van-e bénulva.
     *
     * @return false
     */
    public boolean isStunned() {
        Skeleton.printWithTabs("Agent.isStunned(): boolean");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Ha a virológus vitustáncol, akkor visszaadna egy véletlenszerű mezőt a listából.
     *
     * @param tiles a mezők
     * @return null
     */
    public Tile dancing(ArrayList<Tile> tiles) {
        Skeleton.printWithTabs("Agent.dancing(tiles): Tile");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return null");
        Skeleton.decreaseTabs();
        return null;
    }

    /**
     * Végrehajtja a megfertőzött virológuson végzendő műveleteket.
     *
     * @param virologist a megfertőzött virológus
     */
    public void initialEffect(Virologist virologist) {
        Skeleton.printWithTabs("Agent.initialEffect(virologist)");
        Skeleton.increaseTabs();

        infected = virologist;

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Terjeszti magát az ágens, ha olyan az ágens.
     *
     * @param virologists akikre terjed az ágens
     */
    public void spread(ArrayList<Virologist> virologists) {
        Skeleton.printWithTabs("Agent.spread(virologists)");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Elpusztítja az összes mezőn lévő anyagot, ha olyan az ágens.
     *
     * @param tile
     */
    public void destroyMaterialsOnTile(Tile tile) {
        Skeleton.printWithTabs("Agent.destroyMaterialsOnTile(tile)");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Ha a virológust megütik baltával, akkor az ágens kifejti hatását.
     */
    public void getHit() {
        Skeleton.printWithTabs("Agent.getHit()");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }
}
