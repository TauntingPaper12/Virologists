package projlab;

import java.util.ArrayList;

/**
 * Az objektumok körönkénti elvégzendő műveletek elindításáért felelős singleton osztály.
 */
public class Timer {
    private static Timer timer = null;
    /**
     * A léptethető objektumok.
     */
    private ArrayList<Steppable> steppables;

    /**
     * Timer ctor
     */
    private Timer() {
        steppables = new ArrayList<>();
    }

    /**
     * Új léptethető dolog hozzáadása.
     *
     * @param s A léptethető dolog, amelyet felveszünk a léptethető dolgok listájába.
     */
    public void addSteppable(Steppable s) {
        Skeleton.printWithTabs("Timer.addSteppable(s)");
        Skeleton.increaseTabs();

        if (!steppables.contains(s))
            steppables.add(s);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Léptethető dolog törlése.
     *
     * @param s A léptethető dolog, amelyet kiveszünk a léptethető dolgok listájából.
     */
    public void removeSteppable(Steppable s) {
        Skeleton.printWithTabs("Timer.removeSteppable(s)");
        Skeleton.increaseTabs();

        steppables.remove(s);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Minden léptethető dolog léptetése.
     */
    public void tick() {
        Skeleton.printWithTabs("Timer.tick()");
        Skeleton.increaseTabs();

        ArrayList<Steppable> cloneSteppables = (ArrayList<Steppable>) steppables.clone();

        for (Steppable s : cloneSteppables) {
            s.step();
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja a Timer singleton példányát.
     *
     * @return a példány
     */
    public static Timer instance() {
        Skeleton.printWithTabs("Timer.instance(): Timer");
        Skeleton.increaseTabs();

        if (timer == null) {
            Skeleton.printWithTabs("if (timer == null) : true");
            Skeleton.increaseTabs();


            Skeleton.printWithTabs("timer = new Timer()");
            timer = new Timer();

            Skeleton.decreaseTabs();
        }

        Skeleton.printWithTabs("return timer");
        Skeleton.decreaseTabs();
        return timer;
    }

    /**
     * Visszaadja a léptethető objektumokat.
     * @return steppables, léptethető objektumok.
     */
    public ArrayList<Steppable> getSteppables() {
        return steppables;
    }

    /**
     * Beállítja a paraméterként kapott objektumokat léptethetőre.
     * @param _steppables objektumok, amik léptethetőek lesznek.
     */
    public void setSteppables(ArrayList<Steppable> _steppables) {
        steppables = _steppables;
    }

    /**
     * Kitörli a Timerből a léptethető objektumokat.
     */
    public void clear() {
        steppables.clear();
    }
}


