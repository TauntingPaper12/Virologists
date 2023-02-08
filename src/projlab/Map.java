package projlab;

import projlab.equipment.AxeEquipment;
import projlab.equipment.BackpackEquipment;
import projlab.equipment.CoatEquipment;
import projlab.equipment.GlovesEquipment;
import projlab.geneticcode.AmnesiaGeneticCode;
import projlab.geneticcode.DancingGeneticCode;
import projlab.geneticcode.ProtectionGeneticCode;
import projlab.geneticcode.StunGeneticCode;
import projlab.tile.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * A pályát alkotó mezők tárolásáért és a pálya legenerálásáért felelős.
 */
public class Map {
    /**
     * A pályát alkotó mezők.
     */
    private ArrayList<Tile> tiles;

    /**
     * Legenerálja a mezőket és a hozzájuk tartozó anyagokat, felszereléseket és a genetikai kódokat.
     */
    public ArrayList<Virologist> generateMap(int numberOfVirologist) {
        Skeleton.printWithTabs("Map.generateMap(numberOfVirologist): ArrayList<Virologist>");
        Skeleton.increaseTabs();
        Random rnd = new Random();

        tiles = new ArrayList<>();

        int numberOfTiles = 30;

        ArrayList<LabTile> labTiles = new ArrayList<>();
        labTiles.add(new LabTile());
        labTiles.get(0).addGeneticCode(new AmnesiaGeneticCode());
        labTiles.add(new LabTile());
        labTiles.get(1).addGeneticCode(new DancingGeneticCode());
        labTiles.add(new LabTile());
        labTiles.get(2).addGeneticCode(new ProtectionGeneticCode());
        labTiles.add(new LabTile());
        labTiles.get(3).addGeneticCode(new StunGeneticCode());
        labTiles.add(new InfectiousLabTile());
        labTiles.get(4).addGeneticCode(new AmnesiaGeneticCode());
        labTiles.add(new InfectiousLabTile());
        labTiles.get(5).addGeneticCode(new DancingGeneticCode());
        labTiles.add(new InfectiousLabTile());
        labTiles.get(6).addGeneticCode(new ProtectionGeneticCode());
        labTiles.add(new InfectiousLabTile());
        labTiles.get(7).addGeneticCode(new StunGeneticCode());

        ArrayList<ShelterTile> shelterTiles = new ArrayList<>();
        shelterTiles.add(new ShelterTile());
        shelterTiles.get(0).addEquipment(new AxeEquipment());
        shelterTiles.add(new ShelterTile());
        shelterTiles.get(1).addEquipment(new BackpackEquipment());
        shelterTiles.add(new ShelterTile());
        shelterTiles.get(2).addEquipment(new CoatEquipment());
        shelterTiles.add(new ShelterTile());
        shelterTiles.get(3).addEquipment(new GlovesEquipment());

        int numberOfStorageTiles = 3;
        int numberOfDumpsterTiles = 3;
        int numberOfNormalTiles = numberOfTiles - numberOfDumpsterTiles - numberOfStorageTiles
                - shelterTiles.size() - labTiles.size();
        for (int i = 0; i < numberOfNormalTiles; i++) {
            tiles.add(new Tile());
        }

        for (int i = 0; i < labTiles.size(); i++) {
            tiles.add(rnd.nextInt(tiles.size()), labTiles.get(i));
        }

        for (int i = 0; i < shelterTiles.size(); i++) {
            tiles.add(rnd.nextInt(tiles.size()), shelterTiles.get(i));
            Timer.instance().addSteppable(shelterTiles.get(i));
        }

        for (int i = 0; i < numberOfStorageTiles; i++) {
            StorageTile storageTile = new StorageTile();
            tiles.add(rnd.nextInt(tiles.size()), storageTile);
            Timer.instance().addSteppable(storageTile);
        }

        for (int i = 0; i < numberOfDumpsterTiles; i++) {
            tiles.add(rnd.nextInt(tiles.size()), new DumpsterTile());
        }

        for (int i = 1; i < numberOfTiles; i++) {
            Tile iTile = tiles.get(i);
            for (int j = 0; j < i; j++) {
                Tile jTile = tiles.get(j);
                int chance = rnd.nextInt(100);
                if (chance <= 10) {
                    iTile.addNeighbour(jTile);
                    jTile.addNeighbour(iTile);
                }
            }
        }
        for (int i = 0; i < numberOfTiles; i++) {
            Tile iTile = tiles.get(i);
            if (iTile.getNeighbours().size() == 0) {
                Tile randomTile = iTile;
                while (randomTile == iTile) {
                    randomTile = tiles.get(rnd.nextInt(numberOfTiles));
                }
                iTile.addNeighbour(randomTile);
                randomTile.addNeighbour(iTile);
            }
        }


        ArrayList<Virologist> virologists = new ArrayList<>();
        for (int i = 0; i < numberOfVirologist; i++) {
            Virologist virologist = new Virologist();
            virologists.add(virologist);
            virologist.move(tiles.get(rnd.nextInt(numberOfTiles)));
        }

        Skeleton.printWithTabs("return virologists");
        Skeleton.decreaseTabs();
        return virologists;
    }
}
