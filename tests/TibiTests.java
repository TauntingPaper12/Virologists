import org.junit.Assert;
import org.junit.Test;
import projlab.Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TibiTests {
    String newLine = "\r\n";
//    String newLine = "\n";    // Ha nem Windows-on fut

    /**
     * Raktárból anyagfelvétel bénultan.
     */
    @Test
    public void PickupMaterialWhileStunned() {
        String input = "Create Virologist" + newLine +
                "Create StorageTile" + newLine +
                "Create StunAgent" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Command Virologist1 Move StorageTile1" + newLine +
                "Command StunAgent1 Infect Virologist1" + newLine +
                "Command StorageTile1 AddMaterials NucleotideMaterial1" + newLine +
                "Command StorageTile1 List" + newLine +
                "Command Virologist1 PickupMaterials NucleotideMaterial1" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "Mozgás StorageTile1 mezőre sikeres." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "NucleotideMaterial1" + newLine +
                "Anyagok felvétele sikertelen.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Szabadmezőről anyagfelvétel.
     */
    @Test
    public void PickupMaterialFromNonStorageTile() {
        String input = "Create Virologist" + newLine +
                "Create Tile" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Command Virologist1 Move Tile1" + newLine +
                "Command Virologist1 PickupMaterials NucleotideMaterial1" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Tile objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "Mozgás Tile1 mezőre sikeres." + newLine +
                "Anyagok felvétele sikertelen.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Raktárból anyagfelvétel nukleotiddal és aminosavval a raktárban.
     */
    @Test
    public void PickupMaterialFromLoadedStorage() {
        String input = "Create Virologist" + newLine +
                "Create StorageTile" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create AminoAcidMaterial" + newLine +
                "Command Virologist1 Move StorageTile1" + newLine +
                "Command StorageTile1 AddMaterials NucleotideMaterial1 AnimoAcidMaterial1" + newLine +
                "Command Virologist1 PickupMaterials NucleotideMaterial1 AnimoAcidMaterial1" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "AminoAcidMaterial objektum sikeresen létrehozva." + newLine +
                "Mozgás StorageTile1 mezőre sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Raktárból anyagfelvétel anyaggal a raktárban, de a játékos tele van.
     */
    @Test
    public void PickupMaterialWhileFull() {
        String input = "Create Virologist\n" +
                "Create StorageTile\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Command Virologist1 Move StorageTile1\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial1 NucleotideMaterial2 NucleotideMaterial3 NucleotideMaterial4 NucleotideMaterial5\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial1 NucleotideMaterial2 NucleotideMaterial3 NucleotideMaterial4 NucleotideMaterial5\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial6 NucleotideMaterial7 NucleotideMaterial8 NucleotideMaterial9 NucleotideMaterial10\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial6 NucleotideMaterial7 NucleotideMaterial8 NucleotideMaterial9 NucleotideMaterial10\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial11\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial11\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "Mozgás StorageTile1 mezőre sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikertelen.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Raktárból anyagfelvétel anyaggal a raktárban, de a játékos tele van, de van zsákja, ami nincs tele.
     */
    @Test
    public void PickupMaterialWhileFullAndBackpackNotFull() {
        String input = "Create Virologist" + newLine +
                "Create StorageTile" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create NucleotideMaterial" + newLine +
                "Create ShelterTile" + newLine +
                "Create BackpackEquipment" + newLine +
                "Command Virologist1 Move ShelterTile1" + newLine +
                "Command ShelterTile1 AddEquipment BackpackEquipment1" + newLine +
                "Command Virologist1 PickupEquipment BackpackEquipment1" + newLine +
                "Command ShelterTile1 AddNeighbours StorageTile1" + newLine +
                "Command Virologist1 Move StorageTile1" + newLine +
                "Command StorageTile1 AddMaterials NucleotideMaterial1 NucleotideMaterial2 NucleotideMaterial3 NucleotideMaterial4 NucleotideMaterial5" + newLine +
                "Command Virologist1 PickupMaterials NucleotideMaterial1 NucleotideMaterial2 NucleotideMaterial3 NucleotideMaterial4 NucleotideMaterial5" + newLine +
                "Command StorageTile1 AddMaterials NucleotideMaterial6 NucleotideMaterial7 NucleotideMaterial8 NucleotideMaterial9 NucleotideMaterial10" + newLine +
                "Command Virologist1 PickupMaterials NucleotideMaterial6 NucleotideMaterial7 NucleotideMaterial8 NucleotideMaterial9 NucleotideMaterial10" + newLine +
                "Command StorageTile1 AddMaterials NucleotideMaterial11" + newLine +
                "Command Virologist1 PickupMaterials NucleotideMaterial11" + newLine;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés felvétele sikeres." + newLine +
                "Szomszéd sikeresen hozzáadva." + newLine +
                "Mozgás StorageTile1 mezőre sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Raktárból anyagfelvétel anyaggal a raktárban, de a játékos tele van, de van zsákja, ami szintén tele van.
     */
    @Test
    public void PickupMaterialWhileFullAndBackpackIsFull() {
        String input = "Create Virologist\n" +
                "Create StorageTile\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create NucleotideMaterial\n" +
                "Create ShelterTile\n" +
                "Create BackpackEquipment\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command ShelterTile1 AddEquipment BackpackEquipment1\n" +
                "Command Virologist1 PickupEquipment BackpackEquipment1\n" +
                "Command ShelterTile1 AddNeighbours StorageTile1\n" +
                "Command Virologist1 Move StorageTile1\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial1 NucleotideMaterial2 NucleotideMaterial3 NucleotideMaterial4 NucleotideMaterial5\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial1 NucleotideMaterial2 NucleotideMaterial3 NucleotideMaterial4 NucleotideMaterial5\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial6 NucleotideMaterial7 NucleotideMaterial8 NucleotideMaterial9 NucleotideMaterial10\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial6 NucleotideMaterial7 NucleotideMaterial8 NucleotideMaterial9 NucleotideMaterial10\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial11 NucleotideMaterial12 NucleotideMaterial13 NucleotideMaterial14 NucleotideMaterial15\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial11 NucleotideMaterial12 NucleotideMaterial13 NucleotideMaterial14 NucleotideMaterial15\n" +
                "Command StorageTile1 AddMaterials NucleotideMaterial16\n" +
                "Command Virologist1 PickupMaterials NucleotideMaterial16\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "StorageTile objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "NucleotideMaterial objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés felvétele sikeres." + newLine +
                "Szomszéd sikeresen hozzáadva." + newLine +
                "Mozgás StorageTile1 mezőre sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikeres." + newLine +
                "Anyagok sikeresen hozzáadva." + newLine +
                "Anyagok felvétele sikertelen.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Óvóhelyről felszerelésfelvétel bénultan.
     */
    @Test
    public void PickupEquipmentWhileStunned() {
        String input = "Create Virologist\n" +
                "Create ShelterTile\n" +
                "Create BackpackEquipment\n" +
                "Create StunAgent\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command ShelterTile1 AddEquipment BackpackEquipment1\n" +
                "Command StunAgent1 Infect Virologist1\n" +
                "Command Virologist1 PickupEquipment BackpackEquipment1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "StunAgent objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés felvétele sikertelen.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Szabadmezőről felszerelésfelvétel.
     */
    @Test
    public void PickupEquipmentFromNoneShelterTile() {
        String input = "Create Virologist\n" +
                "Create Tile\n" +
                "Create BackpackEquipment\n" +
                "Command Virologist1 Move Tile1\n" +
                "Command Virologist1 PickupEquipment BackpackEquipment1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Tile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás Tile1 mezőre sikeres." + newLine +
                "Nincs a mezőn felszerelés.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Üres óvóhelyről felszerelésfelvétel.
     */
    @Test
    public void PickupEquipmentFromEmptyShelter() {
        String input = "Create Virologist\n" +
                "Create ShelterTile\n" +
                "Create BackpackEquipment\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command Virologist1 PickupEquipment BackpackEquipment1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Nincs a mezőn felszerelés.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Óvóhelyről felszerelésfelvétel, ahol van felszerelés.
     */
    @Test
    public void PickupEquipmentFromShelter() {
        String input = "Create Virologist\n" +
                "Create ShelterTile\n" +
                "Create BackpackEquipment\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command ShelterTile1 AddEquipment BackpackEquipment1\n" +
                "Command Virologist1 PickupEquipment BackpackEquipment1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés felvétele sikeres.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Óvóhelyről felszerelésfelvétel, ahol van felszerelés, de a játékos tele van.
     */
    @Test
    public void PickupEquipmentWhileFull() {
        String input = "Create Virologist\n" +
                "Create ShelterTile\n" +
                "Create BackpackEquipment\n" +
                "Create CoatEquipment\n" +
                "Create GlovesEquipment\n" +
                "Create BackpackEquipment\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command ShelterTile1 AddEquipment BackpackEquipment2\n" +
                "Command Virologist1 AddEquipment CoatEquipment1\n" +
                "Command Virologist1 AddEquipment BackpackEquipment1\n" +
                "Command Virologist1 AddEquipment GlovesEquipment1\n" +
                "Command Virologist1 PickupEquipment BackpackEquipment2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "BackpackEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Túl sok felszerelése van a virológusnak.", byteArrayOutputStream.toString().trim());
    }

    /**
     * Óvóhelyről balta felvétele.
     */
    @Test
    public void PickupAxe() {
        String input = "Create Virologist\n" +
                "Create ShelterTile\n" +
                "Create AxeEquipment\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command ShelterTile1 AddEquipment AxeEquipment1\n" +
                "Command Virologist1 PickupEquipment AxeEquipment1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "AxeEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés felvétele sikeres.", byteArrayOutputStream.toString().trim());
    }

    /**
     * A játékos eldob egy nála lévő felszerelést.
     */
    @Test
    public void DropEquipment() {
        String input = "Create Virologist\n" +
                "Create ShelterTile\n" +
                "Create AxeEquipment\n" +
                "Command Virologist1 Move ShelterTile1\n" +
                "Command ShelterTile1 AddEquipment AxeEquipment1\n" +
                "Command Virologist1 PickupEquipment AxeEquipment1\n" +
                "Command Virologist1 RemoveEquipment AxeEquipment1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "ShelterTile objektum sikeresen létrehozva." + newLine +
                "AxeEquipment objektum sikeresen létrehozva." + newLine +
                "Mozgás ShelterTile1 mezőre sikeres." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés felvétele sikeres." + newLine +
                "Felszerelés eltávolítva.", byteArrayOutputStream.toString().trim());
    }


}
