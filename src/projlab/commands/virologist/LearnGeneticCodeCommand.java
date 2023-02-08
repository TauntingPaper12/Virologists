package projlab.commands.virologist;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.ICommand;
import projlab.geneticcode.GeneticCode;
import projlab.util.LearnGeneticCodeEnum;

import java.util.Objects;

/**
 * A virológus megtanulja a mezőn lévő genetikai kódot.
 */
public class LearnGeneticCodeCommand implements ICommand {

    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Virologist v = (Virologist) Prototype.getObjects().get(objectName);

        LearnGeneticCodeEnum status = v.learnGeneticCode();

        switch (status) {
            case SUCCESSFUL:
                System.out.println("Genetikai kód sikeresen megtanulva.");
                break;
            case FAILED:
                System.out.println("Genetikai kód megtanulása sikertelen.");
                break;
            case ALREADY_LEARNT:
                System.out.println("Genetikai kód már meg volt tanulva.");
                break;
        }
    }
}
