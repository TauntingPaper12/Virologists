package projlab.commands;

import projlab.Prototype;
import projlab.Steppable;
import projlab.Timer;

import java.util.Objects;

/**
 * Hozzáadja a megadott objektumot a léptethető objektumok listájába.
 */
public class AddSteppableCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Steppable steppable = (Steppable) Prototype.getObjects().get(objectName);

        if (!Objects.isNull(steppable)) {
            Timer.instance().addSteppable(steppable);
        }
        System.out.println(objectName + " hozzáadva a léptethető objektumokhoz.");
    }
}
