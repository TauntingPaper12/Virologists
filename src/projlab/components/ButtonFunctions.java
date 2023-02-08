package projlab.components;

import projlab.Game;
import projlab.Virologist;
import projlab.agent.Agent;
import projlab.equipment.Equipment;
import projlab.geneticcode.GeneticCode;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.Material;
import projlab.tile.Tile;
import projlab.util.MoveEnum;

import java.util.ArrayList;
import java.util.function.Function;

class ButtonFunctions {
    /**
     * A lopást megvalósító gomb funkció
     * A jelenlegi virológust lopásra bírja
     */
    final static Function<ViewButton.ViewButtonArgs, String> StealEquipment = (viewButtonArgs) -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<Virologist> virologists = viewButtonArgs.getVirologist().getTile().getVirologists();

        virologists.remove(virologist);
        SelectDialog<Virologist> vDialog = new SelectDialog<>(virologists, true, "Select virologist");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }
        Virologist target = vDialog.getSelectedItems().get(0);

        SelectDialog<Equipment> eDialog = new SelectDialog<>(target.getEquipment(), true, "Select equipment");
        eDialog.setVisible(true);
        if (!eDialog.isOkResponse()) {
            return "";
        }
        Equipment equipm = eDialog.getSelectedItems().get(0);

        if (Game.instance().stealEquipment(target, equipm)) {
            return "Lopás sikeres!";
        } else {
            return "Lopás sikertelen!";
        }
    };

    /**
     * A genetikai kód megtanulásáért felelő gomb funkciója
     * A jelenlegi virológusnak megtanítja a mezőjén lévő genetikai kódot
     */
    final static Function<ViewButton.ViewButtonArgs, String> LearnGeneticCode = (viewButtonArgs) -> {
        switch (Game.instance().learnGeneticCode()) {
            case FAILED:
                return "Nem sikerült megtanulni a genetikai kódot.";
            case ALREADY_LEARNT:
                return "Már ismeri ezt a genetikai kódot.";
            default:
                return "Sikeresen megtanulta a genetikai kódot.";
        }
    };

    /**
     * A felszerelés felvételéért felelő gomb funkciója
     * A jelenlegi virológussal felvetet egy felszerelést
     */
    final static Function<ViewButton.ViewButtonArgs, String> PickupEquipment = (viewButtonArgs) -> {
        switch (Game.instance().pickupEquipment()) {
            case FAILED:
                return "Nem sikerult felvenni a felszerelést.";
            case FULL:
                return "Tele van a zsebed, ezért nem sikerult felszerelést felvenni.";
            case EMPTY:
                return "Üres a raktár, ezért nem sikerült felszerelést felvenni.";
            default:
                return "Felszerelés felvétele sikeres.";
        }
    };

    /**
     * Az anyagfelvételért felelő gomb funkciója
     * A jelenlegi virológussal felvetet anyagot
     */
    final static Function<ViewButton.ViewButtonArgs, String> PickupMaterials = (viewButtonArgs) -> {
        ArrayList<Material> materials = viewButtonArgs.getVirologist().getTile().getMaterials();
        SelectDialog<Material> vDialog = new SelectDialog<>(materials, false, "Select materials");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }
        ArrayList<Material> toPickUp = vDialog.getSelectedItems();
        if (Game.instance().pickupMaterials(toPickUp)) {
            return "A felszerelés felvégtele sikeres.";
        } else {
            return "A felszerelés felvétele sikertelen.";
        }
    };

    /**
     * Az anyaglopásért felelő gomb funkciója
     * A jelenlegi virológussal ellopat anyagot
     */
    final static Function<ViewButton.ViewButtonArgs, String> StealMaterial = viewButtonArgs -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<Virologist> virologists = viewButtonArgs.getVirologist().getTile().getVirologists();

        virologists.remove(virologist);

        SelectDialog<Virologist> vDialog = new SelectDialog<>(virologists, true, "Select virologist");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }
        Virologist target = vDialog.getSelectedItems().get(0);

        SelectDialog<Material> mDialog = new SelectDialog<>(target.getMaterials(), false, "Select materials");
        mDialog.setVisible(true);
        if (!mDialog.isOkResponse()) {
            return "";
        }
        ArrayList<Material> materials = mDialog.getSelectedItems();
        if (Game.instance().stealMaterials(target, materials)) {
            return "Lopás sikeres!";
        } else {
            return "Lopás sikertelen!";
        }
    };

    /**
     * A mozgásért felelő gomb funkciója
     * A jelenlegi virológust mozgásra bírja
     */
    final static Function<ViewButton.ViewButtonArgs, String> Move = viewButtonArgs -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        Tile tile = virologist.getTile();


        SelectDialog<Tile> tDialog = new SelectDialog<>(tile.getNeighbours(), true, "Select tile");
        tDialog.setVisible(true);
        if (!tDialog.isOkResponse()) {
            return "";
        }
        Tile dest = tDialog.getSelectedItems().get(0);
        MoveEnum teszt = Game.instance().move(dest);
        if (teszt == MoveEnum.SUCCESFUL) {
            return "A lépés sikeresen megtörtént!";
        } else if (teszt == MoveEnum.FAILED) {
            return "A lépés sikertelen!";
        } else {
            return "A lépés véletlenszerű volt!";
        }
    };

    /**
     * A megfertőzésért felelő gomb funkciója
     * A jelenlegi virológussal megkenet virológust
     */
    final static Function<ViewButton.ViewButtonArgs, String> Infect = viewButtonArgs -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<Virologist> virologists = viewButtonArgs.getVirologist().getTile().getVirologists();

        virologists.remove(virologist);

        SelectDialog<Virologist> vDialog = new SelectDialog<>(virologists, true, "Select virologist");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }
        Virologist target = vDialog.getSelectedItems().get(0);

        SelectDialog<InactiveAgent> iDialog = new SelectDialog<>(virologist.getInactiveAgents(), true, "Select inactive agent");
        iDialog.setVisible(true);
        if (!iDialog.isOkResponse()) {
            return "";
        }
        InactiveAgent selected = iDialog.getSelectedItems().get(0);

        if (Game.instance().infect(target, selected)) {
            return "Virológus sikeresen megfertőzve!";
        } else
            return "Virológus megfertőzése sikertelen!";
    };

    /**
     * Az inaktív ágens gyártásért felelő gomb funkciója
     * A jelenlegi virológussal elkészíttet egy inaktív ágenst
     */
    final static Function<ViewButton.ViewButtonArgs, String> MakeInactiveAgent = (viewButtonArgs) -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<GeneticCode> geneticCodes = viewButtonArgs.getVirologist().getGeneticCodes();

        SelectDialog<GeneticCode> vDialog = new SelectDialog<>(geneticCodes, true, "Select genetic code");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }
        GeneticCode code = vDialog.getSelectedItems().get(0);

        if (Game.instance().makeInactiveAgent(code)) {
            return "Inaktív ágens létrehozása sikeres!";
        } else {
            return "Inaktív ágens létrehozása sikertelen!";
        }
    };

    /**
     * A felszerelés használatáért felelő gomb funkciója
     * A jelenlegi virológussal használtat egy felszerelést.
     */
    final static Function<ViewButton.ViewButtonArgs, String> UseEquipment = (viewButtonArgs) -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<Virologist> virologists = viewButtonArgs.getVirologist().getTile().getVirologists();

        virologists.remove(virologist);
        SelectDialog<Virologist> vDialog = new SelectDialog<>(virologists, true, "Select virologist");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }
        Virologist target = vDialog.getSelectedItems().get(0);

        SelectDialog<Equipment> eDialog = new SelectDialog<>(virologist.getEquipment(), true, "Select equipment");
        eDialog.setVisible(true);
        if (!eDialog.isOkResponse()) {
            return "";
        }
        Equipment equipm = eDialog.getSelectedItems().get(0);

        Game.instance().useEquipment(target, equipm);
        return "Felszerelés használata sikeres";
    };

    /**
     * Az anyageldobásért felelő gomb funkciója
     * A jelenlegi virológussal eldobat anyagot
     */
    final static Function<ViewButton.ViewButtonArgs, String> DumpMaterials = (viewButtonArgs) -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<Material> materials = viewButtonArgs.getVirologist().getMaterials();

        SelectDialog<Material> vDialog = new SelectDialog<>(materials, false, "Select materials");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }

        if (Game.instance().dumpMaterials(vDialog.getSelectedItems())) {
            return "Anyagok leadása sikeres";
        } else {
            return "Anyagok leadása sikertelen";
        }
    };

    /**
     * A felszerelés eldobásért felelő gomb funkciója
     * A jelenlegi virológussal eldobat felszerelést
     */
    final static Function<ViewButton.ViewButtonArgs, String> DumpEquipment = (viewButtonArgs) -> {
        Virologist virologist = viewButtonArgs.getVirologist();
        ArrayList<Equipment> equipment = viewButtonArgs.getVirologist().getEquipment();

        SelectDialog<Equipment> vDialog = new SelectDialog<>(equipment, true, "Select equipment");
        vDialog.setVisible(true);
        if (!vDialog.isOkResponse()) {
            return "";
        }

        Game.instance().removeEquipment(vDialog.getSelectedItems().get(0));
        return "Felszerelés leadása sikeres";
    };

    /**
     * A virlógusnál lévő inaktív ágensek kilistázásáért felelő gomb funkciója
     * A jelenlegi virológussal kilistáztatja a nála lévő inaktív ágenseket
     */
    final static Function<ViewButton.ViewButtonArgs, String> InactiveAgentInventory = (viewButtonArgs) -> {
        ArrayList<InactiveAgent> inactiveAgents = viewButtonArgs.getVirologist().getInactiveAgents();
        ListDialog<InactiveAgent> vDialog = new ListDialog<>(inactiveAgents, "InactiveAgentInventory");
        vDialog.setVisible(true);
        return "";
    };

    /**
     * A virológusnál lévő anyagok kilistázásáért felelő gomb funkciója
     * A jelenlegi virológussal kilistáztatja a nála lévő anyagokat
     */
    final static Function<ViewButton.ViewButtonArgs, String> MaterialInventory = (viewButtonArgs) -> {
        ArrayList<Material> materials = viewButtonArgs.getVirologist().getMaterials();
        ListDialog<Material> vDialog = new ListDialog<>(materials, "MaterialInventory");
        vDialog.setVisible(true);
        return "";
    };

    /**
     * A virológusnál lévő felszerelések kilistázásáért felelő gomb funkciója
     * A jelenlegi virológussal kilistáztatja a nála lévő felszereléseket
     */
    final static Function<ViewButton.ViewButtonArgs, String> EquipmentInventory = (viewButtonArgs) -> {
        ArrayList<Equipment> equipments = viewButtonArgs.getVirologist().getEquipment();
        ListDialog<Equipment> vDialog = new ListDialog<>(equipments, "EquipmentInventory");
        vDialog.setVisible(true);
        return "";
    };

    /**
     * A virológuson lévő aktív ágensek kilistázásáért felelő gomb funkciója
     * A jelenlegi virológussal kilistáztatja a rajta lévő aktív ágenseket
     */
    final static Function<ViewButton.ViewButtonArgs, String> ActiveAgentInventory = (viewButtonArgs) -> {
        ArrayList<Agent> agents = viewButtonArgs.getVirologist().getActiveAgents();
        ListDialog<Agent> vDialog = new ListDialog<>(agents, "ActiveAgentInventory");
        vDialog.setVisible(true);
        return "";
    };

    /**
     * A kör befejezéséért felelő gomb funkciója
     * A jelenlegi virológus körét befejezi
     */
    final static Function<ViewButton.ViewButtonArgs, String> SkipTurn = (viewButtonArgs) -> {
        Game.instance().nextTurn();
        return "";
    };
}
