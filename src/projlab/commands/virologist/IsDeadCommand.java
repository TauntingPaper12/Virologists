package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;

/**
 * Megmondja, hogy halott-e a virológus.
 */
public class IsDeadCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);
        if (v.isDead()) {
            System.out.println("Halott.");
        } else {
            System.out.println("Nem halott.");
        }
    }
}
