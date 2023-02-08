package projlab.commands;

import projlab.Prototype;

/**
 * Kilistázza az összes létrehozott objektumot.
 */
public class ListCommand implements ICommand {
    @Override
    public void run(String[] args) {
        Prototype.getObjects().forEach((key, value) -> System.out.println(key));
    }
}
