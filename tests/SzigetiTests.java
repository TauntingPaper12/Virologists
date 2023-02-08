import org.junit.Assert;
import org.junit.Test;
import projlab.Prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SzigetiTests {

    String newLine = "\r\n";
//    String newLine = "\n";    // Ha nem Windows-on fut

    /**
     * A virológus megpróbál megfertőzni egy másik virológust, miközben le van bénulva.
     */
    @Test
    public void StunnedVirologistUsingAgent() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create StunAgent\n" +
                "Create StunInactiveAgent\n" +
                "Command StunAgent1 Infect Virologist1\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist2 List agents";
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
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikertelen.", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológus megpróbál megfertőzni egy védtelen virológust.
     */
    @Test
    public void UsingAgentOnNotProtectedVirologist() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create StunInactiveAgent\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "StunAgent1", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológus megpróbál megfertőzni egy virológust, akin köpeny van és az véd.
     */
    @Test
    public void UsingAgentOnVirologistWithCoat() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create CoatEquipment\n" +
                "Create StunInactiveAgent\n" +
                "SetRandom false\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist2 AddEquipment CoatEquipment1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Véletlenszerűség sikeresen átállítva false állapotba." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres.", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológus megpróbál megfertőzni egy virológust, akin védő ágens van.
     */
    @Test
    public void UsingAgentOnVirologistWithProtectionAgent() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create ProtectionAgent\n" +
                "Create StunInactiveAgent\n" +
                "Command ProtectionAgent1 Infect Virologist2\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "ProtectionAgent1", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológus megpróbál megfertőzni egy virológust, akin kesztyű van.
     */
    @Test
    public void UsingAgentOnVirologistWithGloves() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create GlovesEquipment\n" +
                "Create StunInactiveAgent\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist2 AddEquipment GlovesEquipment1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist1 List agents\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "StunAgent1", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológus megpróbál megfertőzni egy virológust, akin kesztyű és köpeny van.
     */
    @Test
    public void UsingAgentOnVirologistWithGlovesAndCoat() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create GlovesEquipment\n" +
                "Create CoatEquipment\n" +
                "Create StunInactiveAgent\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist2 AddEquipment GlovesEquipment1\n" +
                "Command Virologist2 AddEquipment CoatEquipment1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist1 List agents\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "StunAgent1", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológus kesztyűvel megpróbál megfertőzni egy virológust, akin kesztyű van.
     */
    @Test
    public void UsingAgentWithGlovesOnVirologistWithGloves() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create GlovesEquipment\n" +
                "Create GlovesEquipment\n" +
                "Create StunInactiveAgent\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist1 AddEquipment GlovesEquipment1\n" +
                "Command Virologist2 AddEquipment GlovesEquipment2\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "StunAgent1", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológusnak köpenye van és megpróbál megfertőzni egy másik virológust, akin kesztyű van.
     */
    @Test
    public void UsingAgentWithCoatOnVirologistWithGloves() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create GlovesEquipment\n" +
                "Create CoatEquipment\n" +
                "Create StunInactiveAgent\n" +
                "SetRandom false\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command Virologist1 AddEquipment CoatEquipment1\n" +
                "Command Virologist2 AddEquipment GlovesEquipment1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist1 List agents\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "CoatEquipment objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Véletlenszerűség sikeresen átállítva false állapotba." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres.", byteArrayOutputStream.toString().trim());
    }

    /**
     * A virológuson védő ágens van, miközben megfertőz egy másik virológust akinél kesztyű van.
     */
    @Test
    public void UsingAgentWithProtectionAgentOnMyselfOnVirologistWithGloves() {
        String input = "Create Virologist\n" +
                "Create Virologist\n" +
                "Create GlovesEquipment\n" +
                "Create ProtectionAgent\n" +
                "Create StunInactiveAgent\n" +
                "Command Virologist1 AddInactiveAgent StunInactiveAgent1\n" +
                "Command ProtectionAgent1 Infect Virologist1\n" +
                "Command Virologist2 AddEquipment GlovesEquipment1\n" +
                "Command Virologist1 Infect Virologist2 StunInactiveAgent1\n" +
                "Command Virologist1 List agents\n" +
                "Command Virologist2 List agents";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);

        Prototype.main(new String[]{});

        Assert.assertEquals("Virologist objektum sikeresen létrehozva." + newLine +
                "Virologist objektum sikeresen létrehozva." + newLine +
                "GlovesEquipment objektum sikeresen létrehozva." + newLine +
                "ProtectionAgent objektum sikeresen létrehozva." + newLine +
                "StunInactiveAgent objektum sikeresen létrehozva." + newLine +
                "Inaktív ágens sikeresen hozzáadva." + newLine +
                "Virológus sikeresen megfertőzve." + newLine +
                "Felszerelés sikeresen hozzáadva." + newLine +
                "Inaktív ágens kenése sikeres." + newLine +
                "ProtectionAgent1", byteArrayOutputStream.toString().trim());
    }

}
