import org.junit.Assert;
import org.junit.Test;
import projlab.Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VendelTests {
    String newLine = "\r\n";
//    String newLine = "\n";    // Ha nem Windows-on fut


    /**
     * Virológusra kent ágens öregítése.
	 */	
    @Test
    public void ProtectionAgentStep() {
        String input = "Create ProtectionAgent" + newLine +
                "AddSteppable ProtectionAgent1" + newLine +
                "Tick";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent1 hozzáadva a léptethető objektumokhoz." + newLine +
                "ProtectionAgent1 léptetve.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Nukleotid és Aminosav eldobása.
	 */	
    @Test
    public void VirologistDropMaterial() {
        String input = "Create Virologist" + newLine +
                "Create DumpsterTile" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Command Virologist1 Move DumpsterTile1" + newLine +
                "Command Virologist1 AddMaterials AminoAcidMaterial1 NucleotideMaterial1" + newLine +
                "Command Virologist1 DumpMaterials AminoAcidMaterial1 NucleotideMaterial1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "DumpsterTile objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "Mozgás DumpsterTile1 mezőre sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok leadása sikeres.", byteArrayOutputStream.toString().trim());
    }
	
	/**
     * Objektum létrehozás.
	 */	
    @Test
    public void Create() {
        String input = "Create AmnesiaAgent\n" +
                "Create BearDeezNutsInYourMouthAgent\n" +
                "Create DancingAgent\n" +
                "Create ProtectionAgent\n" +
                "Create StunAgent\n" +
                "Create AxeEquipment\n" +
                "Create BackpackEquipment\n" +
                "Create CoatEquipment\n" +
                "Create GlovesEquipment\n" +
                "Create AmnesiaGeneticCode\n" +
                "Create DancingGeneticCode\n" +
                "Create ProtectionGeneticCode\n" +
                "Create StunGeneticCode\n" +
                "Create AmnesiaInactiveAgent\n" +
                "Create DancingInactiveAgent\n" +
                "Create ProtectionInactiveAgent\n" +
                "Create StunInactiveAgent\n" +
                "Create AminoAcidMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create DumpsterTile\n" +
                "Create InfectiousLabTile\n" +
                "Create LabTile\n" +
                "Create ShelterTile\n" +
                "Create StorageTile\n" +
                "Create Tile\n" +
                "Create Virologist";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("AmnesiaAgent objektum sikeresen létrehozva." + newLine +
                "BearDeezNutsInYourMouthAgent objektum sikeresen létrehozva." + newLine +
                "DancingAgent objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "AxeEquipment objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "AmnesiaGeneticCode objektum sikeresen létrehozva." + newLine +
                "DancingGeneticCode objektum sikeresen létrehozva." + newLine +
                "ProtectionGeneticCode objektum sikeresen létrehozva." + newLine +
                "StunGeneticCode objektum sikeresen létrehozva." + newLine +
                "AmnesiaInactiveAgent objektum sikeresen létrehozva." + newLine +
                "DancingInactiveAgent objektum sikeresen létrehozva." + newLine +
                "ProtectionInactiveAgent objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "DumpsterTile objektum sikeresen létrehozva." + newLine +
                "InfectiousLabTile objektum sikeresen létrehozva." + newLine +
                "LabTile objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "Tile objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva.", byteArrayOutputStream.toString().trim());
    }
}
