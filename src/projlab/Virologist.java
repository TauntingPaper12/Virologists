package projlab;

import projlab.agent.Agent;
import projlab.equipment.Equipment;
import projlab.geneticcode.GeneticCode;
import projlab.inactiveagent.InactiveAgent;
import projlab.material.Material;
import projlab.tile.Tile;
import projlab.util.LearnGeneticCodeEnum;
import projlab.util.MoveEnum;
import projlab.util.PickupEquipmentEnum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A játékos által irányított karakter, felvehet anyagokat, felszereléseket és
 * leadhat anyagokat. Megtanulhat genetikai kódokat, amiket ágensek készítéséhez
 * használhat, amelyeket önmagára vagy más virológusokra kenhet. A fertőzések esetén
 * a virológuson lévő védekezéseket figyelembe veszi. Bénult játékostól lophat felszerelést vagy anyagot.
 */
public class Virologist implements Serializable {
    /**
     * Meghatározza, hogy mennyi anyagot vihet magával a virológus.
     */
    private final int MAX_MATERIAL_COUNT = 10;
    /**
     * Meghatározza, hogy mennyi felszerelést vihet magával a virológus.
     */
    private final int MAX_EQUIPMENT_COUNT = 3;
    /**
     * A megtanult genetikai kódok.
     */
    private ArrayList<GeneticCode> geneticCodes;
    /**
     * A virológusnál lévő inaktív ágensek.
     */
    private ArrayList<InactiveAgent> inactiveAgents;
    /**
     * A virológuson lévő ágensek.
     */
    private ArrayList<Agent> agents;
    /**
     * A mező, amin a virológus áll.
     */
    private Tile tile;
    /**
     * A virológusnál lévő anyagok.
     */
    private ArrayList<Material> materials;
    /**
     * A virológusnál lévő felszerelések.
     */
    private ArrayList<Equipment> equipment;
    /**
     * Eltárolja azt, hogy a virológus halott-e.
     */
    private boolean dead = false;

    /**
     * Virologist ctor
     */
    public Virologist() {
        geneticCodes = new ArrayList<>();
        inactiveAgents = new ArrayList<>();
        agents = new ArrayList<>();
        materials = new ArrayList<>();
        equipment = new ArrayList<>();
        tile = null;
        Prototype.addObject(this);
    }

    /**
     * Megtanulja az adott mezőn lévő genetikai kódot.
     *
     * @return sikeres-e a tanulás
     */
    public LearnGeneticCodeEnum learnGeneticCode() {
        Skeleton.printWithTabs("Virologist.learnGeneticCode(): boolean");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return LearnGeneticCodeEnum.FAILED");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return LearnGeneticCodeEnum.FAILED;
            }
        }

        GeneticCode newGeneticCode = tile.learnGeneticCode();

        if (newGeneticCode == null) {
            return LearnGeneticCodeEnum.FAILED;
        }

        boolean isDuplicate = false;
        for (GeneticCode code : geneticCodes) {
            if (code.accept(newGeneticCode)) {
                Skeleton.printWithTabs("if (code.accept(newGeneticCode)) : true");
                Skeleton.increaseTabs();
                isDuplicate = true;
                Skeleton.printWithTabs("return LearnGeneticCodeEnum.ALREADY_LEARNT");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return LearnGeneticCodeEnum.ALREADY_LEARNT;
            }
        }

        if (!isDuplicate) {
            geneticCodes.add(newGeneticCode);
            if (geneticCodes.size() == GeneticCode.GENETICCODES.length) {
                Skeleton.printWithTabs("if (!isDuplicate && geneticCodes.size() == GeneticCode.GENETICCODES.length) : true");
                Skeleton.increaseTabs();

                Game.instance().endGame(this);

                Skeleton.decreaseTabs();
            }
        }

        Skeleton.printWithTabs("return newGeneticCode");
        Skeleton.decreaseTabs();
        return LearnGeneticCodeEnum.SUCCESSFUL;
    }

    /**
     * Felveszi a kiválasztott anyagokat, ami az adott mezőn van.
     *
     * @param materials azok az anyagok, amelyeket a mezőről felvesz.
     * @return sikerült-e a az anyagok felvétele
     */
    public boolean pickupMaterials(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Virologist.pickupMaterials(materials): boolean");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return false");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return false;
            }
        }

        if (tile.removeMaterial(materials)) {
            Skeleton.printWithTabs("if (tile.removeMaterial(materials)) : true");
            Skeleton.increaseTabs();

            ArrayList<Material> remainingMaterials = (ArrayList<Material>) materials.clone();

            while (this.materials.size() < MAX_MATERIAL_COUNT && remainingMaterials.size() > 0) {
                this.materials.add(remainingMaterials.get(0));
                remainingMaterials.remove(0);
            }
            for (Equipment eq : equipment) {
                remainingMaterials = eq.acceptMaterial(remainingMaterials);
            }

            if (remainingMaterials.size() > 0) {
                Skeleton.printWithTabs("if (tremainingMaterials.size() > 0) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return false");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return false;
            }

            Skeleton.decreaseTabs();
        } else {
            Skeleton.printWithTabs("if (tile.removeMaterial(materials)) : false");
            Skeleton.increaseTabs();

            Skeleton.printWithTabs("return false");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return false;
        }


        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Felveszi a felszerelésben lévő anyagokat.
     *
     * @param equipment a felszerelés.
     */
    public void pickupMaterials(Equipment equipment) {
        Skeleton.printWithTabs("Virologist.pickupMaterials(equipment)");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return;
            }
        }

        ArrayList<Material> equipmentMaterials = equipment.getMaterials();
        equipment.removeMaterials(equipmentMaterials);

        while (this.materials.size() < MAX_MATERIAL_COUNT && equipmentMaterials.size() > 0) {
            this.materials.add(equipmentMaterials.get(0));
            equipmentMaterials.remove(0);
        }
        for (Equipment eq : this.equipment) {
            equipmentMaterials = eq.acceptMaterial(equipmentMaterials);
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Felveszi a mező felszerelését.
     *
     * @return sikerült-e a felszerelés felvétele
     */
    public PickupEquipmentEnum pickupEquipment() {
        Skeleton.printWithTabs("Virologist.pickupEquipment(): boolean");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return PickupEquipmentEnum.FAILED");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return PickupEquipmentEnum.FAILED;
            }
        }
        Equipment tileEquipment = null;
        if (equipment.size() < MAX_EQUIPMENT_COUNT) {
            Skeleton.printWithTabs("if (equipment.size() < MAX_EQUIPMENT_COUNT) : true");
            Skeleton.increaseTabs();

            tileEquipment = tile.getEquipment();
            if (tileEquipment != null) {
                Skeleton.printWithTabs("if (tileEquipment != null) : true");
                Skeleton.increaseTabs();

                tileEquipment.setOwner(this);
                equipment.add(tileEquipment);

                Skeleton.printWithTabs("return PickupEquipmentEnum.SUCCESFUL");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return PickupEquipmentEnum.SUCCESFUL;
            } else {
                Skeleton.printWithTabs("if (equipment.size() < MAX_EQUIPMENT_COUNT) : false");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return PickupEquipmentEnum.EMPTY");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return PickupEquipmentEnum.EMPTY;
            }
        }

        Skeleton.printWithTabs("return PickupEquipmentEnum.FULL");
        Skeleton.decreaseTabs();
        return PickupEquipmentEnum.FULL;
    }

    /**
     * A kiválasztott anyagokat leadja az adott mezőn.
     *
     * @param materials az anyagok, amelyeket lead.
     * @return sikerült-e az anyagok leadása
     */
    public boolean dumpMaterials(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Virologist.dumpMaterials(materials): boolean");
        Skeleton.increaseTabs();

        if (tile.dumpMaterials(materials, this)) {
            Skeleton.printWithTabs("return true");
            Skeleton.decreaseTabs();
            return true;
        } else {
            Skeleton.printWithTabs("return false");
            Skeleton.decreaseTabs();
            return false;
        }
    }

    /**
     * Új inaktív ágenst készít.
     *
     * @param code a genetikai kód, amely alapján készül az inaktív ágens.
     */
    public boolean makeInactiveAgent(GeneticCode code) {
        Skeleton.printWithTabs("Virologist.makeInactiveAgent(code)");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return false");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return false;
            }
        }

        Skeleton.printWithTabs("ArrayList<Material> allMaterials = new ArrayList<>(materials)");
        ArrayList<Material> allMaterials = new ArrayList<>(materials);
        for (Equipment eq : equipment) {
            allMaterials.addAll(eq.getMaterials());
        }
        InactiveAgent inactiveAgent = code.makeInactiveAgent(this, allMaterials);
        if (inactiveAgent != null) {
            inactiveAgents.add(inactiveAgent);
            Skeleton.printWithTabs("return true");
            Skeleton.decreaseTabs();
            return true;
        }

        Skeleton.printWithTabs("return false");
        Skeleton.decreaseTabs();
        return false;
    }

    /**
     * Eltávolítja a megadott anyagokat a virológus készletéből.
     *
     * @param materials az anyagok, amelyeket a virológus készletéből törölni kell.
     */
    public void removeMaterials(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Virologist.removeMaterials(materials)");
        Skeleton.increaseTabs();

        this.materials.removeAll(materials);
        for (Equipment eq : equipment) {
            eq.removeMaterials(materials);
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Megfertőz egy másik virológust egy adott ágenssel.
     *
     * @param inactiveAgent az az ágens, amellyel a virológust megkenik.
     * @param target        az a virológus, akire kenik az ágenst.
     */
    public boolean infectVirologistWith(InactiveAgent inactiveAgent, Virologist target) {
        Skeleton.printWithTabs("Virologist.infectVirologistWith(inactiveAgent, target)");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return false");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return false;
            }
        }

        inactiveAgent.infect(target, this);
        removeInactiveAgent(inactiveAgent);

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Megfertőződik a virológus, lekezeli az esetleges védelmi mechanizmusokat.
     *
     * @param agent az az ágens, amellyel megfertőzték a virológust.
     * @param from  az a virológus, aki "támadott" azaz a kenést végezte.
     */
    public void getInfected(Agent agent, Virologist from) {
        Skeleton.printWithTabs("Virologist.getInfected(agent, from)");
        Skeleton.increaseTabs();

        if (from != this) {
            Skeleton.printWithTabs("if (from != this) : true");
            Skeleton.increaseTabs();
            // Protection of gloves
            boolean repelled = false;
            for (Equipment eq : equipment) {
                if (eq.repel(agent, from)) {
                    repelled = true;
                    break;
                }
            }

            // Protection of cape
            boolean capeProtected = false;
            if (repelled) {
                Skeleton.printWithTabs("if (repelled) : true");
                Skeleton.increaseTabs();


                Skeleton.printWithTabs("return");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return;
            } else {
                Skeleton.printWithTabs("if (repelled) : false");
                Skeleton.increaseTabs();

                for (Equipment eq : equipment) {
                    if (eq.isProtected()) {
                        Skeleton.printWithTabs("if (eq.isProtected()) : true");
                        Skeleton.increaseTabs();

                        capeProtected = true;

                        Skeleton.decreaseTabs();
                        break;
                    }
                }

                Skeleton.decreaseTabs();
            }

            // Protection of agent
            boolean agentProtected = false;
            if (!(repelled || capeProtected)) {
                Skeleton.printWithTabs("if (!(repelled || capeProtected)) : true");
                Skeleton.increaseTabs();

                for (Agent a : agents) {
                    if (a.isProtected()) {
                        Skeleton.printWithTabs("if (a.isProtected()) : true");
                        Skeleton.increaseTabs();

                        agentProtected = true;

                        Skeleton.decreaseTabs();
                        break;
                    }
                }

                Skeleton.decreaseTabs();
            }

            if (capeProtected || agentProtected) {
                Skeleton.printWithTabs("if (capeProtected || agentProtected) : true");
                Skeleton.increaseTabs();

                Timer.instance().removeSteppable(agent);

                Skeleton.printWithTabs("return");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return;
            }
            Skeleton.decreaseTabs();
        }

        agents.add(agent);
        agent.initialEffect(this);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Eltávolítja a virológusról az adott aktív ágenst.
     *
     * @param agent az az ágens, amelynek hatását el kell távolítani a virológusról.
     */
    public void removeAgent(Agent agent) {
        Skeleton.printWithTabs("Virologist.removeAgent(agent)");
        Skeleton.increaseTabs();

        agents.remove(agent);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Az adott mezőre lépteti a virológust.
     *
     * @param toTile az a mező, amelyikre a virológus mozog.
     * @return sikerült-e a mozgás
     */
    public MoveEnum move(Tile toTile) {
        Skeleton.printWithTabs("Virologist.move(toTile): boolean");
        Skeleton.increaseTabs();
        boolean isRandomMoving = false;


        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return MoveEnum.FAILED");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return MoveEnum.FAILED;
            }
        }

        if (tile != null) {
            Skeleton.printWithTabs("if (tile != null) : true");
            Skeleton.increaseTabs();

            ArrayList<Tile> neighbours = this.tile.getNeighbours();
            for (Agent agent : agents) {
                Tile randomTile = agent.dancing(neighbours);

                if (randomTile != null) {
                    Skeleton.printWithTabs("if (randomTile != null) : true");
                    Skeleton.increaseTabs();

                    Skeleton.printWithTabs("toTile = randomTile");
                    toTile = randomTile;
                    isRandomMoving = true;

                    Skeleton.decreaseTabs();
                    break;
                }
            }

            if (!neighbours.contains(toTile)) {
                Skeleton.printWithTabs("if (!neighbours.contains(toTile)) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return MoveEnum.FAILED");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return MoveEnum.FAILED;
            }

            Skeleton.decreaseTabs();
        }

        toTile.accept(this);

        if (tile != null) {
            tile.remove(this);
        }
        tile = toTile;

        for (Agent agent : agents) {
            agent.destroyMaterialsOnTile(tile);
        }
        spreadAgents(tile.getVirologists());

        if (isRandomMoving) {
            Skeleton.printWithTabs("if (isRandomMoving) : true");
            Skeleton.increaseTabs();
            Skeleton.printWithTabs("return MoveEnum.RANDOM_TILE");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return MoveEnum.RANDOM_TILE;
        } else {
            Skeleton.printWithTabs("if (toTile == originalTile) : false");
            Skeleton.increaseTabs();
            Skeleton.printWithTabs("return MoveEnum.SUCCESFUL");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return MoveEnum.SUCCESFUL;

        }
    }

    /**
     * Ellopja a másik virológustól az adott felszerelést.
     *
     * @param target    az a virológus, akitől lopnak.
     * @param equipment az a felszerelés, amelyiket ellopja a virológus a másik virológustól.
     */
    public boolean stealEquipment(Virologist target, Equipment equipment) {
        Skeleton.printWithTabs("Virologist.stealEquipment(tile, equipment)");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return false");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return false;
            }
        }

        equipment = target.giveEquipment(equipment);
        if (equipment != null) {
            Skeleton.printWithTabs("if (equipment != null) : true");
            Skeleton.increaseTabs();

            if (this.equipment.size() < MAX_EQUIPMENT_COUNT) {
                equipment.setOwner(this);
                this.equipment.add(equipment);
            }
            pickupMaterials(equipment);

            Skeleton.printWithTabs("return true");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return true;
        } else {
            Skeleton.printWithTabs("if (equipment != null) : false");
            Skeleton.increaseTabs();
            Skeleton.printWithTabs("return false");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();
            return false;
        }
    }

    /**
     * Visszaadja a felszerelést, amelyiket elveszik a virológustól.
     *
     * @param equipm az a felszerelés, amelyiket el akarják venni a virológustól.
     * @return ha le van bénulva, akkor az equipm felszerelés egyébként null
     */
    public Equipment giveEquipment(Equipment equipm) {
        Skeleton.printWithTabs("Virologist.giveEquipment(equipm): Equipment");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {

                equipment.remove(equipm);

                Skeleton.printWithTabs("return equipm");
                Skeleton.decreaseTabs();
                return equipm;
            }
        }

        Skeleton.printWithTabs("return null");
        Skeleton.decreaseTabs();
        return null;
    }

    /**
     * Ellopja a másik virológustól az adott anyagokat.
     *
     * @param target    az a virológus, akitől lopnak.
     * @param materials azok az anyagok, amelyeket a virológus ellop.
     * @return sikeres-e a lopás
     */
    public boolean stealMaterials(Virologist target, ArrayList<Material> materials) {
        Skeleton.printWithTabs("Virologist.stealMaterials(target, materials) boolean");
        Skeleton.increaseTabs();
        ArrayList<Material> stolenMaterials = (ArrayList<Material>) materials.clone();

        stolenMaterials = target.giveMaterials(stolenMaterials);
        if (!stolenMaterials.isEmpty()) {
            while (this.materials.size() < MAX_MATERIAL_COUNT && stolenMaterials.size() > 0) {
                this.materials.add(stolenMaterials.get(0));
                stolenMaterials.remove(0);
            }
            for (Equipment eq : equipment) {
                stolenMaterials = eq.acceptMaterial(stolenMaterials);
            }
        } else {
            Skeleton.printWithTabs("if (!stolenMaterials.isEmpty()) : false");
            Skeleton.increaseTabs();
            Skeleton.printWithTabs("return false");
            Skeleton.decreaseTabs();
            return false;
        }

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Visszaadja azokat az anyagokat, amiket elvesznek a virológustól.
     *
     * @param materials azok az anyagok, amelyeket el akarnak venni a virológustól
     * @return ha le van bénulva, akkor a materials egyébként üres lista
     */
    public ArrayList<Material> giveMaterials(ArrayList<Material> materials) {
        Skeleton.printWithTabs("Virologist.giveMaterials(materials): ArrayList<Material>");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                removeMaterials(materials);

                Skeleton.printWithTabs("return materials");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return materials;
            }
        }

        Skeleton.printWithTabs("return empty ArrayList");
        Skeleton.decreaseTabs();
        return new ArrayList<>();
    }

    /**
     * Eltávolítja az adott felszerelést az eszköztárából.
     *
     * @param equipm az a felszerelés, amelyet el kell távolítani az eszköztárból.
     */
    public void removeEquipment(Equipment equipm) {
        Skeleton.printWithTabs("Virologist.removeEquipment(equipm)");
        Skeleton.increaseTabs();

        equipment.remove(equipm);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja a virológus megtanult genetikai kódjait.
     *
     * @return virológus által megtanult genetikai kódok.
     */
    public ArrayList<GeneticCode> getGeneticCodes() {
        Skeleton.printWithTabs("Virologist.getGeneticCodes(): ArrayList<GeneticCode>");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return geneticCodes");
        Skeleton.decreaseTabs();

        return geneticCodes;
    }

    /**
     * Kitörli a virológus által megtanult genetikai kódokat.
     */
    public void unlearnGeneticCodes() {
        Skeleton.printWithTabs("Virologist.unlearnGeneticCodes()");
        Skeleton.increaseTabs();

        geneticCodes.clear();

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Eltávolítja a megadott inaktív ágenst a virológus arzenáljából.
     *
     * @param iagent az az inaktív ágens, amely eltávolítódik a virológus arzenáljából.
     */
    public void removeInactiveAgent(InactiveAgent iagent) {
        Skeleton.printWithTabs("Virologist.removeInactiveAgent(iagent)");
        Skeleton.increaseTabs();

        inactiveAgents.remove(iagent);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Ellenőrzi, hogy a virológus vitustánccal meg lett-e fertőzve és az alapján mozgatja a virológust.
     *
     * @return igazzal tér vissza, egyébként ha le van bénulva, akkor hamissal.
     */
    public boolean beforeRound() {
        Skeleton.printWithTabs("Virologist.beforeRound(): boolean");
        Skeleton.increaseTabs();

        if (dead) {
            Skeleton.printWithTabs("if (dead) : true");
            Skeleton.increaseTabs();

            Skeleton.printWithTabs("return false");
            Skeleton.decreaseTabs();
            Skeleton.decreaseTabs();

            return false;
        }

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return false");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return false;
            }
        }

        Tile randomTile = null;
        for (int i = 0; i < agents.size(); i++) {
            randomTile = agents.get(i).dancing(tile.getNeighbours());
            if (randomTile != null) {
                Skeleton.printWithTabs("if (randomTile != null) : true");
                Skeleton.increaseTabs();

                randomTile.accept(this);
                this.tile.remove(this);
                this.tile = randomTile;

                Skeleton.decreaseTabs();
            }
        }

        Skeleton.printWithTabs("return true");
        Skeleton.decreaseTabs();
        return true;
    }

    /**
     * Visszaadja a virológusnál lévő felszereléseket.
     *
     * @return a virológusnál lévő felszerelések
     */
    public ArrayList<Equipment> getEquipment() {
        Skeleton.printWithTabs("Virologist.getEquipment(): ArrayList<Equipment>");
        Skeleton.increaseTabs();

        Skeleton.printWithTabs("return equipment");
        Skeleton.decreaseTabs();
        return equipment;
    }

    /**
     * Terjeszti a fertőző ágenseket.
     *
     * @param virologists a virológusok, akikre terjeszti
     */
    public void spreadAgents(ArrayList<Virologist> virologists) {
        Skeleton.printWithTabs("Virologist.spreadAgents(virologists)");
        Skeleton.increaseTabs();

        if (!dead) {
            for (Agent agent : agents) {
                agent.spread(virologists);
            }
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Egy felszerelést használ az adott virológuson.
     *
     * @param target a célpont
     * @param equipm a felszerelés
     */
    public void useEquipment(Virologist target, Equipment equipm) {
        Skeleton.printWithTabs("Virologist.useEquipment(target, equipm)");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            if (agent.isStunned()) {
                Skeleton.printWithTabs("if (agent.isStunned()) : true");
                Skeleton.increaseTabs();

                Skeleton.printWithTabs("return");
                Skeleton.decreaseTabs();
                Skeleton.decreaseTabs();
                return;
            }
        }

        equipm.use(target);

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Megütik a virológust.
     */
    public void getHit() {
        Skeleton.printWithTabs("Virologist.getHit()");
        Skeleton.increaseTabs();

        for (Agent agent : agents) {
            agent.getHit();
        }

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Meghal a virológus.
     */
    public void die() {
        Skeleton.printWithTabs("Virologist.getHit()");
        Skeleton.increaseTabs();

        dead = true;

        Skeleton.printWithTabs("return");
        Skeleton.decreaseTabs();
    }

    /**
     * Visszaadja, hogy a virológus halott-e.
     *
     * @return halott-e a virológus.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Hozzáadja a genetikai kódot a virológushoz.
     *
     * @param geneticCode a genetikai kód
     */
    public void addGeneticCode(GeneticCode geneticCode) {
        boolean isDuplicate = false;
        for (GeneticCode code : geneticCodes) {
            isDuplicate = isDuplicate || code.accept(geneticCode);
        }

        if (!isDuplicate) {
            geneticCodes.add(geneticCode);
        }
    }

    /**
     * Hozzáadja a anyagokat a virológushoz.
     *
     * @param _materials az anyagok
     */
    public void addMaterials(ArrayList<Material> _materials) {
        ArrayList<Material> remainingMaterials = (ArrayList<Material>) _materials.clone();

        while (this.materials.size() < MAX_MATERIAL_COUNT && remainingMaterials.size() > 0) {
            this.materials.add(remainingMaterials.get(0));
            remainingMaterials.remove(0);
        }
        for (Equipment eq : equipment) {
            remainingMaterials = eq.acceptMaterial(remainingMaterials);
        }
    }

    /**
     * Hozzáadja az inaktív ágenst a virológushoz.
     *
     * @param inactiveAgent az inaktív ágens
     */
    public void addInactiveAgent(InactiveAgent inactiveAgent) {
        if (!inactiveAgents.contains(inactiveAgent)) {
            inactiveAgents.add(inactiveAgent);
        }
    }

    /**
     * Hozzáadja a felszerelést a virológushoz.
     *
     * @param _equipment a felszerelés
     */
    public void addEquipment(Equipment _equipment) {
        if (!equipment.contains(_equipment)) {
            equipment.add(_equipment);
            _equipment.setOwner(this);
        }
    }

    /**
     * Visszaadja a virológusnál lévő anyagokat.
     *
     * @return A virolugsnál lévő anyagok
     */
    public ArrayList<Material> getMaterials() {
        return materials;
    }


    /**
     * Visszaadja a virológuson lévő ágenseket.
     *
     * @return A virológuson lévő ágensek
     */
    public ArrayList<Agent> getAgents() {
        return agents;
    }

    /**
     * Visszaadja a virológusnál lévő inaktív ágenseket.
     *
     * @return A virolugsnál lévő inaktív ágensek
     */
    public ArrayList<InactiveAgent> getInactiveAgents() {
        return inactiveAgents;
    }

    /**
     * Visszaadja, hogy melyik mezőn áll a virológus.
     *
     * @return A mező amin a virológus áll
     */
    public Tile getTile() {
        return tile;
    }

    /**
     * Visszadja a virulóguson lévő aktív ágenseket.
     *
     * @return Az aktív ágensek
     */
    public ArrayList<Agent> getActiveAgents() {
        return agents;
    }
}
