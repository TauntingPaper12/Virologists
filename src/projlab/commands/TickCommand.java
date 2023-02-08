package projlab.commands;

import projlab.Prototype;
import projlab.Steppable;
import projlab.Timer;
import projlab.util.Util;

import java.util.Objects;

/**
 * Lépteti az adott objektumot.
 */
public class TickCommand implements ICommand {
    @Override
    public void run(String[] args) {
        java.util.ArrayList<Steppable> steppables = Timer.instance().getSteppables();
        java.util.HashMap<String, Object> objects = Prototype.getObjects();
        for (Steppable s : steppables) {
            String objectKey = Util.getKeyByValue(objects, s);
            if (!Objects.isNull(objectKey)) {
                System.out.println(objectKey + " léptetve.");
                s.step();
            }
        }
    }
}
