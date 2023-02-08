import org.junit.Assert;
import org.junit.Test;
import projlab.Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BuiTests {
    String newLine = "\r\n";
//    String newLine = "\n";    // Ha nem Windows-on fut

	/**
     * Ágens magamra kenése mikor kesztyű, köpeny és védő ágens van rajtam.
	 */	
    @Test
    public void InfectSelftWithGlovesCoatProtectionAgent() {
        String input = "Create Virologist" + newLine +
                "Create GlovesEquipment" + newLine +
                "Create CoatEquipment" + newLine +
                "Create ProtectionAgent" + newLine +
                "Create StunInactiveAgent" + newLine +
                "Command ProtectionAgent1 Infect Virologist1" + newLine +
                "Command Virologist1 AddEquipment GlovesEquipment1" + newLine +
                "Command Virologist1 AddEquipment CoatEquipment1" + newLine +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1" + newLine +
                "Command Virologist1 Infect Virologist1 StunInactiveAgent1" + newLine +
                "Command Virologist1 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "ProtectionAgent1" + newLine +
                "StunAgent1", byteArrayOutputStream.toString().trim());
    }

	/**
     * Ágens kenése bénultan.
	 */	
    @Test
    public void InfectWhileStunned() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create StunAgent" + newLine +
                "Create StunInactiveAgent" + newLine +
                "Command StunAgent1 Infect Virologist1" + newLine +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1" + newLine +
                "Command Virologist2 List agents" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Inaktív ágens kenése sikertelen.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás nem bénult virológustól.
	 */	
    @Test
    public void StealFromNonStunnedVirologist() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create CoatEquipment" + newLine +
                "Command Virologist2 AddEquipment CoatEquipment1" + newLine +
                "Command Virologist1 StealEquipment Virologist2 CoatEquipment1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés ellopása sikertelen.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás bénultan.
	 */	
    @Test
    public void StealWhileStunned() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create CoatEquipment" + newLine +
                "Create StunAgent" + newLine +
                "Create StunAgent" + newLine +
                "Command StunAgent1 Infect Virologist1" + newLine +
                "Command StunAgent2 Infect Virologist2" + newLine +
                "Command Virologist2 AddEquipment CoatEquipment1" + newLine +
                "Command Virologist1 StealEquipment Virologist2 CoatEquipment1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés ellopása sikertelen.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás bénult virológustól, akinek vannak anyagai.
	 */	
    @Test
    public void StealFromStunnedVirologistMaterials() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create StunAgent" + newLine +
                "Command Virologist2 AddMaterials AminoAcidMaterial1" + newLine +
                "Command StunAgent1 Infect Virologist2" + newLine +
                "Command Virologist1 StealMaterials Virologist2 AminoAcidMaterial1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Anyagok ellopása sikeres.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás bénult virológustól, akinek vannak anyagai és tele vagyok.
	 */	
    @Test
    public void StealFromStunnedVirologistMaterialsWhileFull() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create StunAgent" + newLine +
                "Command Virologist1 AddMaterials AminoAcidMaterial1 AminoAcidMaterial2 AminoAcidMaterial3 AminoAcidMaterial4 AminoAcidMaterial5 AminoAcidMaterial6 AminoAcidMaterial7 AminoAcidMaterial8 AminoAcidMaterial9 AminoAcidMaterial10" + newLine +
                "Command Virologist2 AddMaterials AminoAcidMaterial11" + newLine +
                "Command StunAgent1 Infect Virologist2" + newLine +
                "Command Virologist1 StealMaterials Virologist2 AminoAcidMaterial11" + newLine +
                "Command Virologist1 List materials" + newLine +
                "Command Virologist2 List materials";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Anyagok ellopása sikeres." + newLine +
                "AminoAcidMaterial1" + newLine +
                "AminoAcidMaterial2" + newLine +
                "AminoAcidMaterial3" + newLine +
                "AminoAcidMaterial4" + newLine +
                "AminoAcidMaterial5" + newLine +
                "AminoAcidMaterial6" + newLine +
                "AminoAcidMaterial7" + newLine +
                "AminoAcidMaterial8" + newLine +
                "AminoAcidMaterial9" + newLine +
                "AminoAcidMaterial10", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás bénult virológustól, akinek van köpenye.
	 */	
    @Test
    public void StealFromStunnedVirologistCoat() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create CoatEquipment" + newLine +
                "Create StunAgent" + newLine +
                "Command StunAgent1 Infect Virologist2" + newLine +
                "Command Virologist2 AddEquipment CoatEquipment1" + newLine +
                "Command Virologist1 StealEquipment Virologist2 CoatEquipment1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés ellopása sikeres.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás bénult virológustól, akinek van zsákja, ami nem üres.
	 */	
    @Test
    public void StealFromStunnedVirologistBackpackWithMaterials() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create BackpackEquipment" + newLine +
                "Create StunAgent" + newLine +
                "Command Virologist2 AddEquipment BackpackEquipment1" + newLine +
                "Command Virologist2 AddMaterials AminoAcidMaterial1 AminoAcidMaterial2 AminoAcidMaterial3 AminoAcidMaterial4 AminoAcidMaterial5 AminoAcidMaterial6 AminoAcidMaterial7 AminoAcidMaterial8 AminoAcidMaterial9 AminoAcidMaterial10 AminoAcidMaterial11" + newLine +
                "Command StunAgent1 Infect Virologist2" + newLine +
                "Command Virologist1 StealEquipment Virologist2 BackpackEquipment1" + newLine +
                "Command Virologist1 List materials" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés ellopása sikeres." + newLine +
                "AminoAcidMaterial11", byteArrayOutputStream.toString().trim());
    }

	/**
     * Lopás bénult virológustól, akinek van zsákja, ami nem üres, de tele vagyok felszerelésileg, de nem vagyok tele nyersanyagilag.
	 */	
    @Test
    public void StealFromStunnedVirologistBackpackWithMaterialsFullEquipment() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Create BackpackEquipment" + newLine +
                "Create CoatEquipment" + newLine +
                "Create CoatEquipment" + newLine +
                "Create CoatEquipment" + newLine +
                "Create StunAgent" + newLine +
                "Command Virologist2 AddEquipment BackpackEquipment1" + newLine +
                "Command Virologist2 AddMaterials AminoAcidMaterial1 AminoAcidMaterial2 AminoAcidMaterial3 AminoAcidMaterial4 AminoAcidMaterial5 AminoAcidMaterial6 AminoAcidMaterial7 AminoAcidMaterial8 AminoAcidMaterial9 AminoAcidMaterial10 AminoAcidMaterial11" + newLine +
                "Command StunAgent1 Infect Virologist2" + newLine +
                "Command Virologist1 AddEquipment CoatEquipment1" + newLine +
                "Command Virologist1 AddEquipment CoatEquipment2" + newLine +
                "Command Virologist1 AddEquipment CoatEquipment3" + newLine +
                "Command Virologist1 StealEquipment Virologist2 BackpackEquipment1" + newLine +
                "Command Virologist1 List materials" + newLine +
                "Command Virologist1 List equipment";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés ellopása sikeres." + newLine +
                "AminoAcidMaterial11" + newLine +
                "CoatEquipment1" + newLine +
                "CoatEquipment2" + newLine +
                "CoatEquipment3", byteArrayOutputStream.toString().trim());
    }

	/**
     * Laborban megfertőződés medvevírussal.
	 */	
    @Test
    public void GetInfectedInInfectiousLabTile() {
        String input = "Create Virologist" + newLine +
                "Create InfectiousLabTile" + newLine +
                "Command Virologist1 Move InfectiousLabTile1" + newLine +
                "Command Virologist1 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "InfectiousLabTile objektum sikeresen létrehozva." + newLine +
                "Mozgás InfectiousLabTile1 mezőre sikeres." + newLine +
                "BearDeezNutsInYourMouthAgent1", byteArrayOutputStream.toString().trim());
    }

	/**
     * Laborban megfertőződés medvevírussal miközben védő ágens van rajtunk.
	 */	
    @Test
    public void GetInfectedInInfectiousLabTileWithProtectionAgent() {
        String input = "Create Virologist" + newLine +
                "Create InfectiousLabTile" + newLine +
                "Create ProtectionAgent" + newLine +
                "Command ProtectionAgent1 Infect Virologist1" + newLine +
                "Command Virologist1 Move InfectiousLabTile1" + newLine +
                "Commad Virologist1 List agents" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "InfectiousLabTile objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Mozgás InfectiousLabTile1 mezőre sikeres.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Laborban megfertőződés medvevírussal.
	 */	
    @Test
    public void GetInfectedInInfectiousLabTileWithCoatEquipment() {
        String input = "Create Virologist" + newLine +
                "Create InfectiousLabTile" + newLine +
                "Create CoatEquipment" + newLine +
                "SetRandom false" + newLine +
                "Command Virologist1 AddEquipment CoatEquipment1" + newLine +
                "Command Virologist1 Move InfectiousLabTile1" + newLine +
                "Commnad Virologist1 List agents" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "InfectiousLabTile objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "Véletlenszerűség sikeresen átállítva false állapotba." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Mozgás InfectiousLabTile1 mezőre sikeres.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Medve a raktárban lévő anyagokat elpusztítja.
	 */	
    @Test
    public void BearDestroyMaterials() {
        String input = "Create Virologist" + newLine +
                "Create BearDeezNutsInYourMouthAgent" + newLine +
                "Create StorageTile" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Command BearDeezNutsInYourMouthAgent1 Infect Virologist1 " + newLine +
                "Command StorageTile1 AddMaterials NucleotideMaterial1, NucleotideMaterial2, NucleotideMaterial3" + newLine +
                "Command Virologist1 Move StorageTile1" + newLine +
                "Command StorageTile1 List" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "BearDeezNutsInYourMouthAgent objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Mozgás StorageTile1 mezőre sikeres.", byteArrayOutputStream.toString().trim());
    }

	/**
     * Medve megfertőz egy medvevírussal egy virológust.
	 */	
    @Test
    public void GetInfectedByBearAgentByBear() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create BearDeezNutsInYourMouthAgent" + newLine +
                "Create ShelterTile" + newLine +
                "Command BearDeezNutsInYourMouthAgent1 Infect Virologist1" + newLine +
                "Command Virologist1 Move ShelterTile1" + newLine +
                "Command Virologist2 Move ShelterTile1" + newLine +
                "Command Virologist2 List agents" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "BearDeezNutsInYourMouthAgent objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "BearDeezNutsInYourMouthAgent2", byteArrayOutputStream.toString().trim());
    }

	/**
     * Medve megfertőz egy medvevírussal egy virológust, akin védő ágens van.
	 */	
    @Test
    public void GetInfectedByBearAgentByBearWithProtectionAgent() {
        String input = "Create Virologist" + newLine +
                "Create Virologist" + newLine +
                "Create BearDeezNutsInYourMouthAgent" + newLine +
                "Create ProtectionAgent" + newLine +
                "Create ShelterTile" + newLine +
                "Command BearDeezNutsInYourMouthAgent1 Infect Virologist1" + newLine +
                "Command ProtectionAgent1 Infect Virologist2" + newLine +
                "Command Virologist1 Move ShelterTile1" + newLine +
                "Command Virologist2 Move ShelterTile1" + newLine +
                "Command Virologist2 List agents" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "BearDeezNutsInYourMouthAgent objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "ProtectionAgent1", byteArrayOutputStream.toString().trim());
    }
}
