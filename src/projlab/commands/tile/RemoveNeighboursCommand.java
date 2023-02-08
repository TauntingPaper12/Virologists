package projlab.commands.tile;

import projlab.Prototype;
import projlab.commands.ICommand;
import projlab.material.Material;
import projlab.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Eltávolítja a megadott szomszédokat a megadott mezőn.
 */
public class RemoveNeighboursCommand implements ICommand {

    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Tile tile = (Tile) Prototype.getObjects().get(objectName);

        ArrayList<Tile> neighbours = new ArrayList<>();
        List<String> tileNames = Arrays.asList(args).subList(2, args.length);
        for (String tileName : tileNames) {
            Tile neighbour = (Tile) Prototype.getObjects().get(tileName);
            if (!Objects.isNull(neighbour)) {
                neighbours.add(neighbour);
            }
        }
        tile.removeNeighbours(neighbours);
        ArrayList<Tile> temp = new ArrayList();
        temp.add(tile);
        for (Tile neighbour : neighbours) {
            neighbour.removeNeighbours(temp);
        }
        System.out.println("Szomszéd sikeresen eltávolítva.");
    }

}
