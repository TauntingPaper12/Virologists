package projlab;

import projlab.agent.AmnesiaAgent;
import projlab.agent.DancingAgent;
import projlab.agent.ProtectionAgent;
import projlab.agent.StunAgent;
import projlab.equipment.AxeEquipment;
import projlab.equipment.BackpackEquipment;
import projlab.equipment.CoatEquipment;
import projlab.equipment.GlovesEquipment;
import projlab.geneticcode.AmnesiaGeneticCode;
import projlab.geneticcode.DancingGeneticCode;
import projlab.geneticcode.ProtectionGeneticCode;
import projlab.geneticcode.StunGeneticCode;
import projlab.inactiveagent.AmnesiaInactiveAgent;
import projlab.inactiveagent.DancingInactiveAgent;
import projlab.inactiveagent.ProtectionInactiveAgent;
import projlab.inactiveagent.StunInactiveAgent;
import projlab.material.AminoAcidMaterial;
import projlab.material.Material;
import projlab.material.NucleotideMaterial;
import projlab.tile.*;

import java.util.ArrayList;

public class TestCases {
    /*
     * A tesztesetek nincsenek sorba rendezve, éredemes inkább a Skeleton
     * main osztályából megkeresni a tesztesetek implementálását vagy Ctrl-F-ezni
     */

    /**
     * Ágens magamra kenése mikor kesztyű és köpeny és védő ágens van rajtam
     */
    public static void InfectSelfWithGlovesCoatProtectionAgent() {
        // Test case initialization
        Virologist v1 = new Virologist();
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ProtectionAgent agent = new ProtectionAgent();
        v1.getInfected(agent, null);

        ShelterTile glovesTile = new ShelterTile();
        glovesTile.addEquipment(gloves);
        ShelterTile coatTile = new ShelterTile();
        coatTile.addEquipment(coat);
        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(coatTile);
        glovesTile.addNeighbours(neighbours);
        for (int i = 0; i < 4; i++) {
            glovesTile.step();
            coatTile.step();
        }

        v1.move(glovesTile);
        v1.pickupEquipment();
        v1.move(coatTile);
        v1.pickupEquipment();

        StunInactiveAgent stunInactiveAgent = new StunInactiveAgent(v1);

        // Test case
        v1.infectVirologistWith(stunInactiveAgent, v1);
    }

    /**
     * Ágens kenése bénultan
     */
    public static void InfectWhileStunned() {
        // Test case initialization
        Virologist v1 = new Virologist();
        Virologist target = new Virologist();
        StunAgent stunAgent = new StunAgent();
        StunInactiveAgent stunInactiveAgent = new StunInactiveAgent(v1);

        v1.getInfected(stunAgent, null);

        // Test case
        v1.infectVirologistWith(stunInactiveAgent, target);
    }

    /**
     * Lopás nem bénult virológustól
     */
    public static void StealFromNonStunnedVirologist() {
        // Test case initialization
        Virologist target = new Virologist();
        CoatEquipment equipment = new CoatEquipment();
        Virologist v1 = new Virologist();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(equipment);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();
        equipment = (CoatEquipment) target.getEquipment().get(0);

        // Test case
        v1.stealEquipment(target, equipment);
    }

    /**
     * Lopás bénultan
     */
    public static void StealWhileStunned() {
        // Test case initialization
        Virologist target = new Virologist();
        CoatEquipment equipment = new CoatEquipment();
        Virologist v1 = new Virologist();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(equipment);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        StunAgent stunAgent = new StunAgent();
        v1.getInfected(stunAgent, null);
        equipment = (CoatEquipment) target.getEquipment().get(0);

        // Test case
        v1.stealEquipment(target, equipment);
    }

    /**
     * Lopás bénult virológustól, akinek vannak anyagai
     */
    public static void StealFromStunnedVirologistMaterials() {
        // Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StorageTile storageTile = new StorageTile();

        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new AminoAcidMaterial());
        materials.add(new NucleotideMaterial());
        storageTile.addMaterial(materials);

        target.move(storageTile);
        target.pickupMaterials(materials);
        StunAgent stunAgent = new StunAgent();
        target.getInfected(stunAgent, null);

        // Test case
        v1.stealMaterials(target, materials);
    }

    /**
     * Lopás bénult virológustól, akinek vannak anyagai és tele vagyok
     */
    public static void StealFromStunnedVirologistMaterialsWhileFull() {
        // Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StorageTile storageTile = new StorageTile();

        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            materials.add(new NucleotideMaterial());
        }
        storageTile.addMaterial(materials);

        v1.move(storageTile);
        v1.pickupMaterials(materials);

        materials.clear();
        materials.add(new NucleotideMaterial());
        storageTile.addMaterial(materials);
        target.move(storageTile);
        target.pickupMaterials(materials);
        StunAgent stunAgent = new StunAgent();
        target.getInfected(stunAgent, null);

        // Test case
        v1.stealMaterials(target, materials);
    }

    /**
     * Lopás bénult virológustól, akinek van kesztyűje
     */
    public static void StealFromStunnedVirologistGloves() {
        /* A virológus csak akkor vehet fel kesztyűt, ha ShelterTile-n áll és nincs lebénulva.
         * Ha le van bénulva, akkor nem tudja felvenni a kesztyűt, ha rajta van a kesztyű, akkor
         * nem lehet lebénítani. Emiatt a szükséges tesztkörnyezetet nem lehet megcsinálni, és
         * nem lehet letesztelni. */
    }

    /**
     * Lopás bénult virológustól, akinek van köpenye
     * <p>
     * A virológus csak akkor vehet fel köpenyt, ha ShelterTile-n áll és nincs lebénulva.
     * Ha le van bénulva, akkor nem tudja felvenni a köpenyt, ha rajta van a köpenyt, akkor
     * van esély arra, hogy nem lesz lebénulva.
     */
    public static void StealFromStunnedVirologistCoat() {
        System.out.println("=========AZ EGÉSZ TESTCASE EGY RNG=========");

        // Test case initialization
        Virologist target = new Virologist();
        CoatEquipment equipment = new CoatEquipment();
        Virologist v1 = new Virologist();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(equipment);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        StunAgent stunAgent = new StunAgent();
        target.getInfected(stunAgent, null);
        equipment = (CoatEquipment) target.getEquipment().get(0);

        // Test case
        v1.stealEquipment(target, equipment);
    }

    /**
     * Lopás bénult virológustól, akinek van zsákja, ami üres
     */
    public static void StealFromStunnedVirologistBackpackNoMaterial() {
        Virologist target = new Virologist();
        BackpackEquipment equipment = new BackpackEquipment();
        Virologist v1 = new Virologist();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(equipment);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        StunAgent stunAgent = new StunAgent();
        target.getInfected(stunAgent, null);
        equipment = (BackpackEquipment) target.getEquipment().get(0);

        // Test case
        v1.stealEquipment(target, equipment);
    }

    /**
     * Lopás bénult virológustól, akinek van zsákja, ami nem üres
     */
    public static void StealFromStunnedVirologistBackpackWithMaterials() {
        Virologist target = new Virologist();
        BackpackEquipment equipment = new BackpackEquipment();
        Virologist v1 = new Virologist();
        ShelterTile shelterTile = new ShelterTile();
        StorageTile storageTile = new StorageTile();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(storageTile);
        shelterTile.addNeighbours(neighbours);

        target.move(shelterTile);
        shelterTile.addEquipment(equipment);
        for (int i = 0; i < 4; i++) {
            shelterTile.step();
        }
        target.pickupEquipment();

        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            materials.add(new NucleotideMaterial());
        }
        storageTile.addMaterial(materials);

        target.move(storageTile);
        target.pickupMaterials(materials);

        StunAgent stunAgent = new StunAgent();
        target.getInfected(stunAgent, null);
        equipment = (BackpackEquipment) target.getEquipment().get(0);

        // Test case
        v1.stealEquipment(target, equipment);
    }

    /**
     * Lopás bénult virológustól, akinek van zsákja, ami nem üres, de
     * tele vagyok felszerelésileg, de nem vagyok tele nyersanyagilag
     */
    public static void StealFromStunnedVirologistBackpackWithMaterialsFullEquipment() {
        Virologist target = new Virologist();
        BackpackEquipment backpackEquipment = new BackpackEquipment();
        CoatEquipment coatEquipment = new CoatEquipment();
        Virologist v1 = new Virologist();
        ShelterTile coatShelterTile = new ShelterTile();
        ShelterTile backpackShelterTile = new ShelterTile();
        StorageTile storageTile = new StorageTile();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(storageTile);
        backpackShelterTile.addNeighbours(neighbours);

        target.move(backpackShelterTile);
        backpackShelterTile.addEquipment(backpackEquipment);
        for (int i = 0; i < 4; i++) {
            backpackShelterTile.step();
        }
        target.pickupEquipment();

        v1.move(coatShelterTile);
        coatShelterTile.addEquipment(coatEquipment);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                coatShelterTile.step();
            }
            v1.pickupEquipment();
        }

        ArrayList<Material> materials = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            materials.add(new NucleotideMaterial());
        }
        storageTile.addMaterial(materials);


        target.move(storageTile);
        target.pickupMaterials(materials);

        StunAgent stunAgent = new StunAgent();
        target.getInfected(stunAgent, null);
        backpackEquipment = (BackpackEquipment) target.getEquipment().get(0);

        // Test case
        v1.stealEquipment(target, backpackEquipment);
    }

    /**
     * Raktárból anyagfelvétel bénultan
     */
    public static void PickupMaterialWhileStunned() {
        //Test case init
        Virologist v1 = new Virologist();
        AminoAcidMaterial a1 = new AminoAcidMaterial();
        ArrayList<Material> l1 = new ArrayList<Material>();
        l1.add(a1);
        StorageTile t1 = new StorageTile();
        Virologist from = new Virologist();
        StunInactiveAgent s1 = new StunInactiveAgent(from);

        v1.move(t1);
        t1.addMaterial(l1);
        s1.infect(v1, from);

        //Test case
        v1.pickupMaterials(l1);

    }

    /**
     * Szabadmezőről anyagfelvétel
     */
    public static void PickupMaterialFromNonStorageTile() {
        //Test case init
        Virologist v1 = new Virologist();
        AminoAcidMaterial a1 = new AminoAcidMaterial();
        ArrayList<Material> l1 = new ArrayList<Material>();
        l1.add(a1);
        Tile t1 = new Tile();

        v1.move(t1);

        //Test case
        v1.pickupMaterials(l1);
    }

    /**
     * Raktárból anyagfelvétel nukleotiddal és aminosavval a raktárban
     */
    public static void PickupMaterialFromLoadedStorage() {
        //Test case init
        Virologist v1 = new Virologist();
        AminoAcidMaterial a1 = new AminoAcidMaterial();
        NucleotideMaterial n1 = new NucleotideMaterial();
        ArrayList<Material> l1 = new ArrayList<Material>();
        l1.add(a1);
        l1.add(n1);
        StorageTile t1 = new StorageTile();

        v1.move(t1);
        t1.addMaterial(l1);

        //Test case
        v1.pickupMaterials(l1);

    }

    /**
     * Raktárból anyagfelvétel anyaggal a raktárban, de a játékos tele van
     */
    public static void PickupMaterialWhileFull() {
        //Test case init
        Virologist v1 = new Virologist();
        ArrayList<Material> l1 = new ArrayList<Material>();
        for (int i = 0; i < 10; i++) {
            AminoAcidMaterial a1 = new AminoAcidMaterial();
            l1.add(a1);
        }
        StorageTile t1 = new StorageTile();
        ArrayList<Material> l2 = new ArrayList<Material>();
        NucleotideMaterial n1 = new NucleotideMaterial();
        l2.add(n1);

        v1.move(t1);
        t1.addMaterial(l1);
        v1.pickupMaterials(l1);
        t1.addMaterial(l2);

        //Test case
        v1.pickupMaterials(l2);
    }

    /**
     * Raktárból anyagfelvétel anyaggal a raktárban,de a játékos tele van, de van zsákja, ami nincs tele
     */
    public static void PickupMaterialWhileFullAndBackpackNotFull() {
        //Test case
        Virologist v1 = new Virologist();
        ArrayList<Material> l1 = new ArrayList<Material>();
        for (int i = 0; i < 10; i++) {
            AminoAcidMaterial a1 = new AminoAcidMaterial();
            l1.add(a1);
        }
        StorageTile t1 = new StorageTile();
        ArrayList<Material> l2 = new ArrayList<Material>();
        NucleotideMaterial n1 = new NucleotideMaterial();
        l2.add(n1);

        ShelterTile t2 = new ShelterTile();
        ArrayList<Tile> neighbour = new ArrayList<Tile>();
        neighbour.add(t1);
        t2.addNeighbours(neighbour);
        BackpackEquipment b1 = new BackpackEquipment();
        t2.addEquipment(b1);
        for (int i = 0; i < 4; i++) {
            t2.step();
        }

        v1.move(t2);
        v1.pickupEquipment();
        v1.move(t1);
        t1.addMaterial(l1);
        v1.pickupMaterials(l1);
        t1.addMaterial(l2);

        //Test Case
        v1.pickupMaterials(l2);
    }

    /**
     * Raktárból anyagfelvétel anyaggal a raktárban, de a játékos tele van, és a nála lévő zsák is tele van
     */
    public static void PickupMaterialWhileFullAndBackpackIsFull() {
        //Test case
        Virologist v1 = new Virologist();
        ArrayList<Material> l1 = new ArrayList<Material>();
        for (int i = 0; i < 10; i++) {
            AminoAcidMaterial a1 = new AminoAcidMaterial();
            l1.add(a1);
        }
        StorageTile t1 = new StorageTile();
        ArrayList<Material> l2 = new ArrayList<Material>();
        for (int i = 0; i < 5; i++) {
            NucleotideMaterial n1 = new NucleotideMaterial();
            l2.add(n1);
        }

        ArrayList<Material> l3 = new ArrayList<Material>();
        NucleotideMaterial n1 = new NucleotideMaterial();
        l3.add(n1);


        ShelterTile t2 = new ShelterTile();
        BackpackEquipment b1 = new BackpackEquipment();
        t2.addEquipment(b1);
        ArrayList<Tile> neighbour = new ArrayList<Tile>();
        neighbour.add(t1);
        t2.addNeighbours(neighbour);
        for (int i = 0; i < 4; i++) {
            t2.step();
        }

        v1.move(t2);
        v1.pickupEquipment();
        v1.move(t1);
        t1.addMaterial(l1);
        v1.pickupMaterials(l1);
        t1.addMaterial(l2);
        v1.pickupMaterials(l2);
        t1.addMaterial(l3);

        //Test Case
        v1.pickupMaterials(l3);
    }

    /**
     * Óvóhelyről felszerelés felvétele bénultan
     */
    public static void PickupEquipmentWhileStunned() {
        //Test case init
        Virologist v1 = new Virologist();
        Virologist from = new Virologist();
        ShelterTile t1 = new ShelterTile();
        CoatEquipment c1 = new CoatEquipment();
        StunInactiveAgent s1 = new StunInactiveAgent(from);

        v1.move(t1);
        t1.addEquipment(c1);
        for (int i = 0; i < 4; i++) {
            t1.step();
        }
        s1.infect(v1, from);

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Szabadmezőről felszerelés felvétel
     */
    public static void PickupEquipmentFromNoneShelterTile() {
        //Test case init
        Virologist v1 = new Virologist();
        Tile t1 = new Tile();
        CoatEquipment c1 = new CoatEquipment();

        v1.move(t1);

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Üres óvóhelyről felszerelés felvétel
     */
    public static void PickupEquipmentFromEmptyShelter() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();

        v1.move(t1);

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Óvóhelyről felszerelés felvétel, ahol kesztyű van
     */
    public static void PickupGlovesEquipmentFromShelter() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();
        GlovesEquipment g1 = new GlovesEquipment();

        v1.move(t1);
        t1.addEquipment(g1);
        for (int i = 0; i < 4; i++) {
            t1.step();
        }

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Óvóhelyről felszerelés felvétel, ahol köpeny van
     */
    public static void PickupCoatEquipmentFromShelter() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();
        CoatEquipment g1 = new CoatEquipment();

        v1.move(t1);
        t1.addEquipment(g1);
        for (int i = 0; i < 4; i++) {
            t1.step();
        }

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Óvóhelyről felszerelésfelvétel, ahol zsák van
     */
    public static void PickupBackpackEquipmentFromShelter() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();
        BackpackEquipment g1 = new BackpackEquipment();

        v1.move(t1);
        t1.addEquipment(g1);
        for (int i = 0; i < 4; i++) {
            t1.step();
        }

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Óvóhelyről felszerelésfelvétel ahol van felszerelés, de a játékos tele van
     */
    public static void PickupEquipmentWhileFull() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();
        BackpackEquipment g1 = new BackpackEquipment();

        v1.move(t1);
        t1.addEquipment(g1);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                t1.step();
            }
            v1.pickupEquipment();
        }

        for (int i = 0; i < 4; i++) {
            t1.step();
        }

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Bénító ágens kenése másik virológusra miközben bénultak vagyunk.
     */
    public static void StunnedVirologistUsingStunAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        StunAgent agent = new StunAgent();

        v1.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése másik virológusra miközben bénultak vagyunk.
     */
    public static void StunnedVirologistUsingProtectionAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        StunAgent agent = new StunAgent();

        v1.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Táncoló ágens kenése másik virológusra miközben bénultak vagyunk.
     */
    public static void StunnedVirologistUsingDancingAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        StunAgent agent = new StunAgent();

        v1.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése másik virológusra miközben bénultak vagyunk.
     */
    public static void StunnedVirologistUsingAmnesiaAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        StunAgent agent = new StunAgent();

        v1.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése védtelen virológusra.
     */
    public static void UsingStunAgentOnNotProtectedVirologist() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése védtelen virológusra.
     */
    public static void UsingDancingAgentOnNotProtectedVirologist() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése védtelen virológusra.
     */
    public static void UsingProtectionAgentOnNotProtectedVirologist() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése védtelen virológusra.
     */
    public static void UsingAmnesiaAgentOnNotProtectedVirologist() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése virológusra aki rendelkezik köpennyel.
     */
    public static void UsingStunAgentOnVirologistWithCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése virológusra aki rendelkezik köpennyel.
     */
    public static void UsingDancingAgentOnVirologistWithCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();


        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése virológusra aki rendelkezik köpennyel.
     */
    public static void UsingProtectionAgentOnVirologistWithCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();


        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése virológusra aki rendelkezik köpennyel.
     */
    public static void UsingAmnesiaAgentOnVirologistWithCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();


        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenés védő ágenssel rendelkező virológusra.
     */
    public static void UsingStunAgentOnVirologistWithProtectionAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        ProtectionAgent agent = new ProtectionAgent();

        target.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenés védő ágenssel rendelkező virológusra.
     */
    public static void UsingProtectionAgentOnVirologistWithProtectionAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        ProtectionAgent agent = new ProtectionAgent();

        target.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenés védő ágenssel rendelkező virológusra.
     */
    public static void UsingDancingAgentOnVirologistWithProtectionAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        ProtectionAgent agent = new ProtectionAgent();

        target.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenés védő ágenssel rendelkező virológusra.
     */
    public static void UsingAmnesiaAgentOnVirologistWithProtectionAgent() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        ProtectionAgent agent = new ProtectionAgent();

        target.getInfected(agent, null);

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése kesztyűvel rendelkező virológusra.
     */
    public static void UsingStunAgentOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése kesztyűvel rendelkező virológusra.
     */
    public static void UsingProtectionAgentOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése kesztyűvel rendelkező virológusra.
     */
    public static void UsingDancingAgentOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése kesztyűvel rendelkező virológusra.
     */
    public static void UsingAmnesiaAgentOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése kesztyűvel és köpennyel rendelkező virológusra.
     */
    public static void UsingStunAgentOnVirologistWithGlovesAndCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(tile2);
        tile1.addNeighbours(neighbours);

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        target.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése kesztyűvel és köpennyel rendelkező virológusra.
     */
    public static void UsingProtectionAgentOnVirologistWithGlovesAndCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(tile2);
        tile1.addNeighbours(neighbours);

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        target.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése kesztyűvel és köpennyel rendelkező virológusra.
     */
    public static void UsingDancingAgentOnVirologistWithGlovesAndCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(tile2);
        tile1.addNeighbours(neighbours);

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        target.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése kesztyűvel és köpennyel rendelkező virológusra.
     */
    public static void UsingAmnesiaAgentOnVirologistWithGlovesAndCoat() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(tile2);
        tile1.addNeighbours(neighbours);

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        target.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése kesztyűvel rendelkező virológusra miközben nekem is van kesztyűm.
     */
    public static void UsingStunAgentWithGlovesOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        v1.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése kesztyűvel rendelkező virológusra miközben nekem is van kesztyűm.
     */
    public static void UsingProtectionAgentWithGlovesOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        v1.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése kesztyűvel rendelkező virológusra miközben nekem is van kesztyűm.
     */
    public static void UsingDancingAgentWithGlovesOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        v1.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése kesztyűvel rendelkező virológusra miközben nekem is van kesztyűm.
     */
    public static void UsingAmnesiaAgentWithGlovesOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        v1.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése kesztyűvel rendelkező virológusra miközben nekem van köpenyem.
     */
    public static void UsingStunAgentWithCoatOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        v1.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése kesztyűvel rendelkező virológusra miközben nekem van köpenyem.
     */
    public static void UsingProtectionAgentWithCoatOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        v1.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése kesztyűvel rendelkező virológusra miközben nekem van köpenyem.
     */
    public static void UsingDancingAgentWithCoatOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        v1.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése kesztyűvel rendelkező virológusra miközben nekem van köpenyem.
     */
    public static void UsingAmnesiaAgentWithCoatOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        CoatEquipment coat = new CoatEquipment();
        ShelterTile tile1 = new ShelterTile();
        ShelterTile tile2 = new ShelterTile();

        target.move(tile1);
        tile1.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile1.step();
        }
        target.pickupEquipment();

        v1.move(tile2);
        tile2.addEquipment(coat);
        for (int i = 0; i < 4; i++) {
            tile2.step();
        }
        v1.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Bénító ágens kenése kesztyűvel rendelkező virológusra miközben rajtam védő ágens van.
     */
    public static void UsingStunAgentWithProtectionAgentOnMyselfOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        StunInactiveAgent IAgent = new StunInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();
        ProtectionAgent agent = new ProtectionAgent();

        v1.getInfected(agent, null);

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens kenése kesztyűvel rendelkező virológusra miközben rajtam védő ágens van.
     */
    public static void UsingProtectionAgentWithProtectionAgentOnMyselfOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        ProtectionInactiveAgent IAgent = new ProtectionInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();
        ProtectionAgent agent = new ProtectionAgent();

        v1.getInfected(agent, null);

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Vitustáncoló ágens kenése kesztyűvel rendelkező virológusra miközben rajtam védő ágens van.
     */
    public static void UsingDancingAgentWithProtectionAgentOnMyselfOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        DancingInactiveAgent IAgent = new DancingInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();
        ProtectionAgent agent = new ProtectionAgent();

        v1.getInfected(agent, null);

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Amnesia ágens kenése kesztyűvel rendelkező virológusra miközben rajtam védő ágens van.
     */
    public static void UsingAmnesiaAgentWithProtectionAgentOnMyselfOnVirologistWithGloves() {
        //Test case initialization
        Virologist target = new Virologist();
        Virologist v1 = new Virologist();
        AmnesiaInactiveAgent IAgent = new AmnesiaInactiveAgent(v1);
        GlovesEquipment gloves = new GlovesEquipment();
        ShelterTile tile = new ShelterTile();
        ProtectionAgent agent = new ProtectionAgent();

        v1.getInfected(agent, null);

        target.move(tile);
        tile.addEquipment(gloves);
        for (int i = 0; i < 4; i++) {
            tile.step();
        }
        target.pickupEquipment();

        //Test case
        v1.infectVirologistWith(IAgent, target);
    }

    /**
     * Védő ágens öregítése
     */
    public static void ProtectionAgentStep() {
        // Test case initialization
        Virologist from = new Virologist();
        ProtectionAgent prot = new ProtectionAgent();
        Virologist target = new Virologist();

        Timer.instance().addSteppable(prot);
        target.getInfected(prot, from);

        // Test case
        for (int i = 0; i < 3; i++) {
            Timer.instance().tick();
        }
    }

    /**
     * Táncoló ágens öregítése
     */
    public static void DancingAgentStep() {
        // Test case initialization
        Virologist from = new Virologist();
        DancingAgent danc = new DancingAgent();
        Virologist target = new Virologist();

        Timer.instance().addSteppable(danc);
        target.getInfected(danc, from);

        // Test case
        for (int i = 0; i < 5; i++) {
            Timer.instance().tick();
        }
    }

    /**
     * Bénító ágens öregítése
     */
    public static void StunAgentStep() {
        // Test case initialization
        Virologist from = new Virologist();
        StunAgent stun = new StunAgent();
        Virologist target = new Virologist();

        Timer.instance().addSteppable(stun);
        target.getInfected(stun, from);

        // Test case
        for (int i = 0; i < 2; i++) {
            Timer.instance().tick();
        }
    }

    /**
     * Felejtő ágens öregítése
     */
    public static void AmnesiaAgentStep() {
        // Test case initialization
        Virologist from = new Virologist();
        AmnesiaAgent amne = new AmnesiaAgent();
        Virologist target = new Virologist();

        Timer.instance().addSteppable(amne);
        target.getInfected(amne, from);

        // Test case
        for (int i = 0; i < 2; i++) {
            Timer.instance().tick();
        }
    }

    /**
     * Raktár anyagot generál
     */
    public static void StorageTileStep() {
        StorageTile storage = new StorageTile();
        for (int i = 0; i < 4; i++) {
            storage.step();
        }
    }

    /**
     * Virológus eldobja a nála levő anyagokat
     */
    public static void VirologistDropMaterials() {
        // Test case initialization
        Virologist v = new Virologist();
        StorageTile storage = new StorageTile();
        DumpsterTile dumpster = new DumpsterTile();

        ArrayList<Material> materials = new ArrayList<>();
        materials.add(new AminoAcidMaterial());
        materials.add(new NucleotideMaterial());

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(dumpster);
        storage.addNeighbours(neighbours);

        v.move(storage);
        storage.addMaterial(materials);
        v.pickupMaterials(storage.getMaterials());
        v.move(dumpster);

        // Test case
        v.dumpMaterials(materials);
    }

    /**
     * Bénító ágens készítése
     */
    public static void MakingStunAgent() {
        //Test case initialization
        Virologist maker = new Virologist();
        StunGeneticCode stuncode = new StunGeneticCode();
        ArrayList<Material> materials = new ArrayList<>();
        StorageTile storageTile = new StorageTile();

        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        storageTile.addMaterial(materials);
        maker.move(storageTile);
        maker.pickupMaterials(materials);

        //Test case
        maker.makeInactiveAgent(stuncode);
    }

    /**
     * Vitustánc ágens készítése
     */
    public static void MakingDancingAgent() {
        //Test case initialization
        Virologist maker = new Virologist();
        DancingGeneticCode dancingcode = new DancingGeneticCode();
        ArrayList<Material> materials = new ArrayList<>();
        StorageTile storageTile = new StorageTile();

        materials.add(new AminoAcidMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        storageTile.addMaterial(materials);
        maker.move(storageTile);
        maker.pickupMaterials(materials);

        //Test case
        maker.makeInactiveAgent(dancingcode);
    }

    /**
     * Védő ágens készítése
     */
    public static void MakingProtectionAgent() {
        //Test case initialization
        Virologist maker = new Virologist();
        ProtectionGeneticCode protectioncode = new ProtectionGeneticCode();
        ArrayList<Material> materials = new ArrayList<>();
        StorageTile storageTile = new StorageTile();

        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        storageTile.addMaterial(materials);
        maker.move(storageTile);
        maker.pickupMaterials(materials);

        //Test case
        maker.makeInactiveAgent(protectioncode);
    }

    /**
     * Amnézia ágens készítése
     */
    public static void MakingAmnesiaAgent() {
        //Test case initialization
        Virologist maker = new Virologist();
        AmnesiaGeneticCode amnesiacode = new AmnesiaGeneticCode();
        ArrayList<Material> materials = new ArrayList<>();
        StorageTile storageTile = new StorageTile();

        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new AminoAcidMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        materials.add(new NucleotideMaterial());
        storageTile.addMaterial(materials);
        maker.move(storageTile);
        maker.pickupMaterials(materials);

        //Test case
        maker.makeInactiveAgent(amnesiacode);
    }

    /**
     * Lépés szomszédos mezőre
     */
    public static void MovingToNeighbourTile() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DumpsterTile dumpster = new DumpsterTile();
        ArrayList<Tile> neighbours = new ArrayList<>();

        v1.move(lab);
        neighbours.add(dumpster);
        lab.addNeighbours(neighbours);

        //Test case
        v1.move(dumpster);
    }

    /**
     * Bénítva lépés szomszédos mezőre
     */
    public static void MovingWhileStunned() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DumpsterTile trash = new DumpsterTile();
        ArrayList<Tile> neighbours = new ArrayList<>();
        StunAgent stun = new StunAgent();

        v1.move(lab);
        neighbours.add(trash);
        lab.addNeighbours(neighbours);

        v1.getInfected(stun, null);

        //Test case
        v1.move(trash);
    }

    /**
     * Vitustáncolva lépés szomszédos mezőre
     */
    public static void MovingWhileDancing() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DumpsterTile trash = new DumpsterTile();
        ArrayList<Tile> neighbours = new ArrayList<>();
        DancingAgent dancing = new DancingAgent();

        v1.move(lab);
        neighbours.add(trash);
        lab.addNeighbours(neighbours);

        v1.getInfected(dancing, v1);

        //Test case
        v1.move(trash);
    }

    /**
     * Lépés nem szomszédos mezőre
     */
    public static void MovingToNotNeighbourTile() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DumpsterTile trash = new DumpsterTile();

        v1.move(lab);

        //Test case
        v1.move(trash);
    }

    /**
     * Új genetikai kód tanulása bénítva
     */
    public static void LearningNewGeneticCodeWhileStunned() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DancingGeneticCode dancingcode = new DancingGeneticCode();
        StunAgent stun = new StunAgent();

        lab.addGeneticCode(dancingcode);
        v1.move(lab);
        v1.getInfected(stun, v1);

        //Test case
        v1.learnGeneticCode();
    }

    /**
     * Új genetikai kód tanulása
     */
    public static void LearningNewGeneticCode() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DancingGeneticCode dancingcode = new DancingGeneticCode();

        lab.addGeneticCode(dancingcode);
        v1.move(lab);

        //Test case
        v1.learnGeneticCode();
    }

    /**
     * Régi genetikai kód tanulása
     */
    public static void LearningOldGeneticCode() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab = new LabTile();
        DancingGeneticCode dancingcode = new DancingGeneticCode();

        lab.addGeneticCode(dancingcode);
        v1.move(lab);
        v1.learnGeneticCode();

        //Test case
        v1.learnGeneticCode();
    }

    /**
     * Új genetikai kód tanulása és a játék megnyerése
     */
    public static void LearningNewGeneticCodeAndWinningTheGame() {
        //Test case initialization
        Virologist v1 = new Virologist();
        LabTile lab1 = new LabTile();
        LabTile lab2 = new LabTile();
        LabTile lab3 = new LabTile();
        LabTile lab4 = new LabTile();
        AmnesiaGeneticCode amnesiacode = new AmnesiaGeneticCode();
        DancingGeneticCode dancingcode = new DancingGeneticCode();
        ProtectionGeneticCode protectioncode = new ProtectionGeneticCode();
        StunGeneticCode stuncode = new StunGeneticCode();

        ArrayList<Tile> neighbours = new ArrayList<>();
        neighbours.add(lab1);
        neighbours.add(lab2);
        neighbours.add(lab3);
        neighbours.add(lab4);

        lab1.addNeighbours(neighbours);
        lab2.addNeighbours(neighbours);
        lab3.addNeighbours(neighbours);
        lab4.addNeighbours(neighbours);

        lab1.addGeneticCode(amnesiacode);
        lab2.addGeneticCode(dancingcode);
        lab3.addGeneticCode(protectioncode);
        lab4.addGeneticCode(stuncode);

        v1.move(lab1);
        v1.learnGeneticCode();
        v1.move(lab2);
        v1.learnGeneticCode();
        v1.move(lab3);
        v1.learnGeneticCode();
        v1.move(lab4);

        //Test case
        v1.learnGeneticCode();
    }

    /**
     * Balta felvétele
     */
    public static void PickupAxe() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();
        AxeEquipment a1 = new AxeEquipment();

        t1.addEquipment(a1);
        v1.move(t1);

        //Test case
        v1.pickupEquipment();
    }

    /**
     * Felszerelés eldobása
     */
    public static void DropEquipment() {
        //Test case init
        Virologist v1 = new Virologist();
        ShelterTile t1 = new ShelterTile();
        AxeEquipment a1 = new AxeEquipment();

        t1.addEquipment(a1);
        v1.move(t1);
        v1.pickupEquipment();

        //Test case
        v1.removeEquipment(a1);
    }
}
