package projlab.commands;

import projlab.Prototype;
import projlab.Virologist;
import projlab.commands.agent.InfectVirologistCommand;
import projlab.commands.labtile.AddGeneticCodeCommand;
import projlab.commands.storagetile.AddMaterialsToStorageTileCommand;
import projlab.commands.tile.AddNeighboursCommand;
import projlab.commands.virologist.*;

/**
 * Elvégzi a parancsot az adott objektumon. A parancsok
 * osztálytípusfüggőek, minden osztálytípusnak más parancsai vannak.
 */
public class CommandCommand implements ICommand {
    @Override
    public void run(String[] args) {
        String objectName = args[0];
        Object object = Prototype.getObjects().get(objectName);

        String className = object.getClass().getSimpleName();
        if (className.contains("Agent") && args[1].equals("Infect")) {
            new InfectVirologistCommand().run(args);
        } else if (className.contains("Tile")) {
            if (className.equals("LabTile") && args[1].equals("AddGeneticCode")) {
                new AddGeneticCodeCommand().run(args);
            } else if (className.equals("ShelterTile") && args[1].equals("AddEquipment")) {
                new projlab.commands.sheltertile.AddEquipmentCommand().run(args);
            } else if (className.equals("StorageTile")) {
                if (args[1].equals("AddMaterials")) {
                    new AddMaterialsToStorageTileCommand().run(args);
                } else if (args[1].equals("List")) {
                    new projlab.commands.storagetile.ListCommand().run(args);
                }
            } else if (args[1].equals("AddNeighbours")) {
                new AddNeighboursCommand().run(args);
            } else if (args[1].equals("RemoveNeighbours")) {
                new AddNeighboursCommand().run(args);
            }
        } else if (className.equals("Virologist")) {
            switch (args[1]) {
                case "LearnGeneticCode":
                    new LearnGeneticCodeCommand().run(args);
                    break;
                case "PickupMaterials":
                    new PickupMaterialsCommand().run(args);
                    break;
                case "PickupEquipment":
                    new PickupEquipmentCommand().run(args);
                    break;
                case "DumpMaterials":
                    new DumpMaterialsCommand().run(args);
                    break;
                case "MakeInactiveAgent":
                    new MakeInactiveAgentCommand().run(args);
                    break;
                case "Infect":
                    new InfectCommand().run(args);
                    break;
                case "Move":
                    new MoveCommand().run(args);
                    break;
                case "StealEquipment":
                    new StealEquipmentCommand().run(args);
                    break;
                case "StealMaterials":
                    new StealMaterialsCommand().run(args);
                    break;
                case "UseEquipment":
                    new UseEquipmentCommand().run(args);
                    break;
                case "RemoveEquipment":
                    new RemoveEquipmentCommand().run(args);
                    break;
                case "List":
                    new VirologistListCommand().run(args);
                    break;
                case "AddEquipment":
                    new AddEquipmentCommand().run(args);
                    break;
                case "AddInactiveAgent":
                    new AddInactiveAgentCommand().run(args);
                    break;
                case "AddMaterials":
                    new AddMaterialsCommand().run(args);
                    break;
                case "AddGeneticCode":
                    new projlab.commands.virologist.AddGeneticCodeCommand().run(args);
                    break;
                case "IsDead":
                    new IsDeadCommand().run(args);
                    break;
            }
        }
    }
}
