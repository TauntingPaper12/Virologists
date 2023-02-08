package projlab.commands;

import projlab.util.CustomRandom;

import java.util.Locale;

/**
 * Beállítja a véletlenszerűséget.
 */
public class SetRandomCommand implements ICommand {
    @Override
    public void run(String[] args) {
        if (args.length == 1) {
            String arg = args[0].toLowerCase(Locale.ROOT).trim();
            if (arg.equals("true")) {
                CustomRandom.setRandomness(true);
                System.out.println("Véletlenszerűség sikeresen átállítva true állapotba.");
            } else if (arg.equals("false")) {
                CustomRandom.setRandomness(false);
                System.out.println("Véletlenszerűség sikeresen átállítva false állapotba.");
            }
        }
    }
}
