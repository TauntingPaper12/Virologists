package projlab;

import projlab.components.MainFrame;
import projlab.components.MenuFrame;
import projlab.equipment.Equipment;
import projlab.geneticcode.GeneticCode;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.Material;
import projlab.tile.Tile;
import projlab.util.LearnGeneticCodeEnum;
import projlab.util.MoveEnum;
import projlab.util.PickupEquipmentEnum;

import javax.swing.*;
import java.util.ArrayList;

/**
 * A játék elindításáért és befejezéséért felelős.
 */
public class Game {
    /**
     * A pálya, ahol a játék játszódik.
     */
    private Map map;
    /**
     * Az összes játékban lévő virológus.
     */
    private ArrayList<Virologist> virologists;
    private Virologist currentVirologist;
    private int turnsLeft;
    private static Game game = null;
    private MainFrame frame;

    /**
     * Game ctor
     */
    private Game() {
        map = new Map();
        virologists = new ArrayList<>();
    }

    /**
     * Visszaadja a Game singleton példányát.
     *
     * @return a példány
     */
    public static Game instance() {
        Skeleton.printWithTabs("Game.instance(): Game");
        Skeleton.increaseTabs();

        if (game == null) {
            Skeleton.printWithTabs("if (game == null) : true");
            Skeleton.increaseTabs();

            Skeleton.printWithTabs("game = new Game()");
            game = new Game();

            Skeleton.decreaseTabs();
        }

        Skeleton.printWithTabs("return game");
        Skeleton.decreaseTabs();
        return game;
    }


    /**
     * Megvizsgálja, hogy a virológus megnyerte-e a játékot.
     */
    public void endGame(Virologist winner) {
        Skeleton.printWithTabs("Game.endGame()");
        Skeleton.increaseTabs();

        int index = virologists.indexOf(winner);
        JOptionPane.showMessageDialog(null, "A " + (index + 1) + ". virológus megnyerte a játékot!");
        frame.dispose();
        MenuFrame mf = new MenuFrame();
        mf.setVisible(true);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Legeneráltatja a mezőket és elindítja a játékot.
     */
    public void startGame(MainFrame frame) {
        Skeleton.printWithTabs("Game.startGame()");
        Skeleton.increaseTabs();

        Timer.instance().clear();
        this.frame = frame;
        this.turnsLeft = 2;
        this.map = new Map();
        this.virologists = map.generateMap(4);
        this.currentVirologist = this.virologists.get(0);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Minden körben kiírja a következő virológust, csökkenti a visszamaradt körök számát az adott virológusnak.
     */
    private void doingThisEveryTurn() {
        turnsLeft--;
        if (turnsLeft <= 0) {
            Timer.instance().tick();
            int index = (virologists.indexOf(currentVirologist) + 1) % virologists.size();
            currentVirologist = virologists.get(index);
            turnsLeft = 2;
            while (!currentVirologist.beforeRound()) {
                index = (virologists.indexOf(currentVirologist) + 1) % virologists.size();
                currentVirologist = virologists.get(index);
            }

            JOptionPane.showMessageDialog(null, "A " + (index + 1) + ". játékos következik.");
        }
    }

    /**
     * Az aktív virológus megtanul egy genetikai kódot.
     *
     * @return állapot
     */
    public LearnGeneticCodeEnum learnGeneticCode() {
        LearnGeneticCodeEnum status = currentVirologist.learnGeneticCode();
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus felvesz egy eszközt.
     *
     * @return állapot
     */
    public PickupEquipmentEnum pickupEquipment() {
        PickupEquipmentEnum status = currentVirologist.pickupEquipment();
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus felvesz egy nyersanyagot.
     *
     * @return állapot
     */
    public boolean pickupMaterials(ArrayList<Material> materials) {
        boolean status = currentVirologist.pickupMaterials(materials);
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus ellop egy felszerelést egy másik virológustól.
     *
     * @return állapot
     */
    public boolean stealEquipment(Virologist target, Equipment equipm) {
        boolean status = currentVirologist.stealEquipment(target, equipm);
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus lop nyersanyagot egy másik virológustól.
     *
     * @return állapot
     */
    public boolean stealMaterials(Virologist target, ArrayList<Material> materials) {
        boolean status = currentVirologist.stealMaterials(target, materials);
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus átlép egy másik mezőre.
     *
     * @return állapot
     */
    public MoveEnum move(Tile toTile) {
        MoveEnum status = currentVirologist.move(toTile);
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus megfertőz egy másik virológust egy kiválasztott ágensel.
     *
     * @return állapot
     */
    public boolean infect(Virologist target, InactiveAgent inactiveAgent) {
        boolean status = currentVirologist.infectVirologistWith(inactiveAgent, target);
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus készít egy ágenst a nyersanyagaiból.
     *
     * @return állapot
     */
    public boolean makeInactiveAgent(GeneticCode code) {
        boolean status = currentVirologist.makeInactiveAgent(code);
        doingThisEveryTurn();
        return status;
    }

    /**
     * Az aktív virológus használja az egyik felszerelését egy másik virológuson.
     *
     * @return állapot
     */
    public void useEquipment(Virologist target, Equipment equipm) {
        currentVirologist.useEquipment(target, equipm);
        doingThisEveryTurn();
    }

    /**
     * Az aktív virológus eldobja a nyersanyagát egy lerakóban.
     *
     * @return állapot
     */
    public boolean dumpMaterials(ArrayList<Material> materials) {
        boolean status = currentVirologist.dumpMaterials(materials);
        doingThisEveryTurn();
        return status;
    }

    /**
     * A következő virológus jön.
     */
    public void nextTurn() {
        turnsLeft = 0;
        doingThisEveryTurn();
    }

    /**
     * Az aktív virológustól elvesz egy felszerelést.
     */
    public void removeEquipment(Equipment equipm) {
        currentVirologist.removeEquipment(equipm);
        doingThisEveryTurn();
    }

    /**
     * Visszaadja a játékban szereplő virológusokat.
     *
     * @return virológusok
     */
    public ArrayList<Virologist> getVirologists() {
        return virologists;
    }

    /**
     * Visszaadja az aktív virológust.
     *
     * @return aktív virológus
     */
    public Virologist getCurrentVirologist() {
        return currentVirologist;
    }
}
