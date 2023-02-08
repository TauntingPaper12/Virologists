package projlab.commands;

import projlab.agent.*;
import projlab.equipment.*;
import projlab.inactiveagent.*;
import projlab.geneticcode.*;
import projlab.material.*;
import projlab.tile.*;
import projlab.Virologist;

import java.util.Arrays;

/**
 * Létrehoz egy adott típusú objektumot a megadott névvel.
 */
public class CreateCommand implements ICommand {
    private String[] allowedClasses = {"AmnesiaAgent", "BearDeezNutsInYourMouthAgent", "DancingAgent", "ProtectionAgent", "StunAgent",
            "AxeEquipment", "BackpackEquipment", "CoatEquipment", "GlovesEquipment",
            "AmnesiaGeneticCode", "DancingGeneticCode", "ProtectionGeneticCode", "StunGeneticCode",
            "AmnesiaInactiveAgent", "DancingInactiveAgent", "ProtectionInactiveAgent", "StunInactiveAgent",
            "AminoAcidMaterial", "NucleotideMaterial",
            "DumpsterTile", "LabTile", "ShelterTile", "StorageTile", "InfectiousLabTile", "Tile",
            "Virologist"};

    // TODO: Dogshit implementacio, at kene irni, de lusta vagyok
    @Override
    public void run(String[] args) {
        String classTypeString = args[0];
        if (Arrays.asList(allowedClasses).contains(classTypeString)) {
            System.out.println(classTypeString + " objektum sikeresen létrehozva.");

            switch (classTypeString) {
                case "AmnesiaAgent":
                    new AmnesiaAgent();
                    break;
                case "BearDeezNutsInYourMouthAgent":
                    new BearDeezNutsInYourMouthAgent();
                    break;
                case "DancingAgent":
                    new DancingAgent();
                    break;
                case "ProtectionAgent":
                    new ProtectionAgent();
                    break;
                case "StunAgent":
                    new StunAgent();
                    break;
                case "AxeEquipment":
                    new AxeEquipment();
                    break;
                case "BackpackEquipment":
                    new BackpackEquipment();
                    break;
                case "CoatEquipment":
                    new CoatEquipment();
                    break;
                case "GlovesEquipment":
                    new GlovesEquipment();
                    break;
                case "AmnesiaGeneticCode":
                    new AmnesiaGeneticCode();
                    break;
                case "ProtectionGeneticCode":
                    new ProtectionGeneticCode();
                    break;
                case "StunGeneticCode":
                    new StunGeneticCode();
                    break;
                case "DancingGeneticCode":
                    new DancingGeneticCode();
                    break;
                case "AmnesiaInactiveAgent":
                    new AmnesiaInactiveAgent(null);
                    break;
                case "DancingInactiveAgent":
                    new DancingInactiveAgent(null);
                    break;
                case "ProtectionInactiveAgent":
                    new ProtectionInactiveAgent(null);
                    break;
                case "StunInactiveAgent":
                    new StunInactiveAgent(null);
                    break;
                case "AminoAcidMaterial":
                    new AminoAcidMaterial();
                    break;
                case "NucleotideMaterial":
                    new NucleotideMaterial();
                    break;
                case "Tile":
                    new Tile();
                    break;
                case "DumpsterTile":
                    new DumpsterTile();
                    break;
                case "LabTile":
                    new LabTile();
                    break;
                case "ShelterTile":
                    new ShelterTile();
                    break;
                case "StorageTile":
                    new StorageTile();
                    break;
                case "InfectiousLabTile":
                    new InfectiousLabTile();
                    break;
                case "Virologist":
                    new Virologist();
                    break;
            }
        }
    }
}
