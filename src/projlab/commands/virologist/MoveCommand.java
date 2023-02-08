package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.tile.Tile;
import projlab.util.MoveEnum;

/**
 * Rálép a megadott mezőre.
 */
public class MoveCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        String tileName = args[2];
        Tile tile = (Tile) Prototype.getObjects().get(tileName);

        MoveEnum status = v.move(tile);
        switch (status) {
            case SUCCESFUL:
                System.out.println("Mozgás " + tileName + " mezőre sikeres.");
                break;
            case FAILED:
                System.out.println("Mozgás " + tileName + " mezőre sikertelen.");
                break;
            case RANDOM_TILE:
                System.out.println("Mozgás " + tileName + " mezőre sikertelen. Véletlenszerű mozgás.");
                break;
        }
    }
}
