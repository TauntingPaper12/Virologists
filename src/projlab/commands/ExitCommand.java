package projlab.commands;

/**
 * Kilép a programból.
 */
public class ExitCommand implements ICommand {
    @Override
    public void run(String[] args) {
        System.out.println("Csá!");
        System.exit(0);
    }
}
