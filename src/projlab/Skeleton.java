package projlab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * A tesztesetek kiválasztásáért és a futtatott függvények kiiratásáért felelős.
 */
public class Skeleton {
    private static boolean skeletonMode = false;
    /**
     * A teszteseteket eltároló LinkedHashMap.
     */
    private static LinkedHashMap<String, Runnable> testCases = new LinkedHashMap<>();
    /**
     * Megmondja, hogy mennyire legyen beljebb identálva a függvényhívások.
     */
    private static int numberOfTabs = 0;

    public static void setSkeletonMode(boolean _skeletonMode) {
        skeletonMode = _skeletonMode;
    }

    /**
     * Megnöveli az identálást.
     */
    public static void increaseTabs() {
        numberOfTabs++;
    }

    /**
     * Csökkenti az identálást.
     */
    public static void decreaseTabs() {
        numberOfTabs--;
    }

    /**
     * Kiiratja a paraméterben megadott függvényhívást identálva.
     *
     * @param text a függvényhívás szöveges formában
     */
    public static void printWithTabs(String text) {
        if (skeletonMode) {
            for (int i = 0; i < numberOfTabs; i++) {
                System.out.print('\t');
            }
            System.out.println(text);
        }
    }

    public static void main(String[] args) {
        skeletonMode = true;

        testCases.put("MakingStunAgent", TestCases::MakingStunAgent);
        testCases.put("MakingDancingAgent", TestCases::MakingDancingAgent);
        testCases.put("MakingProtectionAgent", TestCases::MakingProtectionAgent);
        testCases.put("MakingAmnesiaAgent", TestCases::MakingAmnesiaAgent);

        testCases.put("MovingToNeighbourTile", TestCases::MovingToNeighbourTile);
        testCases.put("MovingWhileStunned", TestCases::MovingWhileStunned);
        testCases.put("MovingWhileDancing", TestCases::MovingWhileDancing);
        testCases.put("MovingToNotNeighbourTile", TestCases::MovingToNotNeighbourTile);

        testCases.put("LearningNewGeneticCodeWhileStunned", TestCases::LearningNewGeneticCodeWhileStunned);
        testCases.put("LearningNewGeneticCode", TestCases::LearningNewGeneticCode);
        testCases.put("LearningOldGeneticCode", TestCases::LearningOldGeneticCode);
        testCases.put("LearningNewGeneticCodeAndWinningTheGame", TestCases::LearningNewGeneticCodeAndWinningTheGame);

        testCases.put("PickupMaterialWhileStunned", TestCases::PickupMaterialWhileStunned);
        testCases.put("PickupMaterialFromNonStorageTile", TestCases::PickupMaterialFromNonStorageTile);
        testCases.put("PickupMaterialFromLoadedStorage", TestCases::PickupMaterialFromLoadedStorage);
        testCases.put("PickupMaterialWhileFull", TestCases::PickupMaterialWhileFull);
        testCases.put("PickupMaterialWhileFullAndBackpackNotFull", TestCases::PickupMaterialWhileFullAndBackpackNotFull);
        testCases.put("PickupMaterialWhileFullAndBackpackIsFull", TestCases::PickupMaterialWhileFullAndBackpackIsFull);

        testCases.put("PickupEquipmentWhileStunned", TestCases::PickupEquipmentWhileStunned);
        testCases.put("PickupEquipmentFromNoneShelterTile", TestCases::PickupEquipmentFromNoneShelterTile);
        testCases.put("PickupEquipmentFromEmptyShelter", TestCases::PickupEquipmentFromEmptyShelter);
        testCases.put("PickupGlovesEquipmentFromShelter", TestCases::PickupGlovesEquipmentFromShelter);
        testCases.put("PickupCoatEquipmentFromShelter", TestCases::PickupCoatEquipmentFromShelter);
        testCases.put("PickupBackpackEquipmentFromShelter", TestCases::PickupBackpackEquipmentFromShelter);
        testCases.put("PickupEquipmentWhileFull", TestCases::PickupEquipmentWhileFull);

        testCases.put("InfectSelfWithGlovesCoatProtectionAgent", TestCases::InfectSelfWithGlovesCoatProtectionAgent);
        testCases.put("InfectWhileStunned", TestCases::InfectWhileStunned);

        testCases.put("StealFromNonStunnedVirologist", TestCases::StealFromNonStunnedVirologist);
        testCases.put("StealWhileStunned", TestCases::StealWhileStunned);
        testCases.put("StealFromStunnedVirologistMaterials", TestCases::StealFromStunnedVirologistMaterials);
        testCases.put("StealFromStunnedVirologistMaterialsWhileFull", TestCases::StealFromStunnedVirologistMaterialsWhileFull);
        testCases.put("StealFromStunnedVirologistCoat", TestCases::StealFromStunnedVirologistCoat);
        testCases.put("StealFromStunnedVirologistBackpackNoMaterial", TestCases::StealFromStunnedVirologistBackpackNoMaterial);
        testCases.put("StealFromStunnedVirologistBackpackWithMaterials", TestCases::StealFromStunnedVirologistBackpackWithMaterials);
        testCases.put("StealFromStunnedVirologistBackpackWithMaterialsFullEquipment", TestCases::StealFromStunnedVirologistBackpackWithMaterialsFullEquipment);


        //Kenés bénultan
        testCases.put("StunnedVirologistUsingStunAgent", TestCases::StunnedVirologistUsingStunAgent);
        testCases.put("StunnedVirologistUsingProtectionAgent", TestCases::StunnedVirologistUsingProtectionAgent);
        testCases.put("StunnedVirologistUsingDancingAgent", TestCases::StunnedVirologistUsingDancingAgent);
        testCases.put("StunnedVirologistUsingAmnesiaAgent", TestCases::StunnedVirologistUsingAmnesiaAgent);

        //Kenés védtelen virológusra
        testCases.put("UsingStunAgentOnNotProtectedVirologist", TestCases::UsingStunAgentOnNotProtectedVirologist);
        testCases.put("UsingProtectionAgentOnNotProtectedVirologist", TestCases::UsingProtectionAgentOnNotProtectedVirologist);
        testCases.put("UsingDancingAgentOnNotProtectedVirologist", TestCases::UsingDancingAgentOnNotProtectedVirologist);
        testCases.put("UsingAmnesiaAgentOnNotProtectedVirologist", TestCases::UsingAmnesiaAgentOnNotProtectedVirologist);

        //Kenés köpennyel rendelkező virológusra
        testCases.put("UsingStunAgentOnVirologistWithCoat", TestCases::UsingStunAgentOnVirologistWithCoat);
        testCases.put("UsingProtectionAgentOnVirologistWithCoat", TestCases::UsingProtectionAgentOnVirologistWithCoat);
        testCases.put("UsingDancingAgentOnVirologistWithCoat", TestCases::UsingDancingAgentOnVirologistWithCoat);
        testCases.put("UsingAmnesiaAgentOnVirologistWithCoat", TestCases::UsingAmnesiaAgentOnVirologistWithCoat);

        //Kenés védő ágenssel rendelkező virológusra
        testCases.put("UsingStunAgentOnVirologistWithProtectionAgent", TestCases::UsingStunAgentOnVirologistWithProtectionAgent);
        testCases.put("UsingProtectionAgentOnVirologistWithProtectionAgent", TestCases::UsingProtectionAgentOnVirologistWithProtectionAgent);
        testCases.put("UsingDancingAgentOnVirologistWithProtectionAgent", TestCases::UsingDancingAgentOnVirologistWithProtectionAgent);
        testCases.put("UsingAmnesiaAgentOnVirologistWithProtectionAgent", TestCases::UsingAmnesiaAgentOnVirologistWithProtectionAgent);

        //Kenés kesztyűvel rendelkező virológusra
        testCases.put("UsingStunAgentOnVirologistWithGloves", TestCases::UsingStunAgentOnVirologistWithGloves);
        testCases.put("UsingProtectionAgentOnVirologistWithGloves", TestCases::UsingProtectionAgentOnVirologistWithGloves);
        testCases.put("UsingDancingAgentOnVirologistWithGloves", TestCases::UsingDancingAgentOnVirologistWithGloves);
        testCases.put("UsingAmnesiaAgentOnVirologistWithGloves", TestCases::UsingAmnesiaAgentOnVirologistWithGloves);

        //Kenés köpennyel és kesztyűvel rendelkező virológusra
        testCases.put("UsingStunAgentOnVirologistWithGlovesAndCoat", TestCases::UsingStunAgentOnVirologistWithGlovesAndCoat);
        testCases.put("UsingProtectionAgentOnVirologistWithGlovesAndCoat", TestCases::UsingProtectionAgentOnVirologistWithGlovesAndCoat);
        testCases.put("UsingDancingAgentOnVirologistWithGlovesAndCoat", TestCases::UsingDancingAgentOnVirologistWithGlovesAndCoat);
        testCases.put("UsingAmnesiaAgentOnVirologistWithGlovesAndCoat", TestCases::UsingAmnesiaAgentOnVirologistWithGlovesAndCoat);

        //Kenés kesztyűvel egy kesztyűvel rendelkező virológusra
        testCases.put("UsingStunAgentWithGlovesOnVirologistWithGloves", TestCases::UsingStunAgentWithGlovesOnVirologistWithGloves);
        testCases.put("UsingProtectionAgentWithGlovesOnVirologistWithGloves", TestCases::UsingProtectionAgentWithGlovesOnVirologistWithGloves);
        testCases.put("UsingDancingAgentWithGlovesOnVirologistWithGloves", TestCases::UsingDancingAgentWithGlovesOnVirologistWithGloves);
        testCases.put("UsingAmnesiaAgentWithGlovesOnVirologistWithGloves", TestCases::UsingAmnesiaAgentWithGlovesOnVirologistWithGloves);

        //Kenés köpennyel egy kesztyűvel rendelkező virológusra
        testCases.put("UsingStunAgentWithCoatOnVirologistWithGloves", TestCases::UsingStunAgentWithCoatOnVirologistWithGloves);
        testCases.put("UsingProtectionAgentWithCoatOnVirologistWithGloves", TestCases::UsingProtectionAgentWithCoatOnVirologistWithGloves);
        testCases.put("UsingDancingAgentWithCoatOnVirologistWithGloves", TestCases::UsingDancingAgentWithCoatOnVirologistWithGloves);
        testCases.put("UsingAmnesiaAgentWithCoatOnVirologistWithGloves", TestCases::UsingAmnesiaAgentWithCoatOnVirologistWithGloves);

        //Kenés védő rajtam védő ágenssel egy kesztyűvel rendelkező virológusra
        testCases.put("UsingStunAgentWithProtectionAgentOnMyselfOnVirologistWithGloves", TestCases::UsingStunAgentWithProtectionAgentOnMyselfOnVirologistWithGloves);
        testCases.put("UsingProtectionAgentWithProtectionAgentOnMyselfOnVirologistWithGloves", TestCases::UsingProtectionAgentWithProtectionAgentOnMyselfOnVirologistWithGloves);
        testCases.put("UsingDancingAgentWithProtectionAgentOnMyselfOnVirologistWithGloves", TestCases::UsingDancingAgentWithProtectionAgentOnMyselfOnVirologistWithGloves);
        testCases.put("UsingAmnesiaAgentWithProtectionAgentOnMyselfOnVirologistWithGloves", TestCases::UsingAmnesiaAgentWithProtectionAgentOnMyselfOnVirologistWithGloves);

        testCases.put("ProtectionAgentStep", TestCases::ProtectionAgentStep);
        testCases.put("DancingAgentStep", TestCases::DancingAgentStep);
        testCases.put("StunAgentStep", TestCases::StunAgentStep);
        testCases.put("AmnesiaAgentStep", TestCases::AmnesiaAgentStep);
        testCases.put("StorageTileStep", TestCases::StorageTileStep);
        testCases.put("VirologistDropMaterials", TestCases::VirologistDropMaterials);
        testCases.put("PickupAxe", TestCases::PickupAxe);
        testCases.put("DropEquipment", TestCases::DropEquipment);

        ArrayList<String> keys = new ArrayList<>(testCases.keySet());

        while (true) {
            // Kilistázás
            System.out.println("-1. Exit");
            System.out.println("0. Run all testcases");
            for (int i = 0; i < keys.size(); i++) {
                System.out.println((i + 1) + ". " + keys.get(i));
            }

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (1 <= choice && choice <= keys.size()) {
                runTestCase(keys.get(choice - 1));
            } else if (choice == 0) {
                for (String key : keys) {
                    runTestCase(key);
                }
            } else if (choice == -1) {
                break;
            }

            System.out.println("Press Enter to continue.");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void runTestCase(String testCaseKey) {
        System.out.println("--------------------");
        System.out.println(testCaseKey);
        System.out.println("--------------------");
        testCases.get(testCaseKey).run();
        System.out.println();
        System.out.println();
    }
}
