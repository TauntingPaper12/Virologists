package projlab.components;

import projlab.Game;
import projlab.Virologist;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewPanel extends JPanel {
    /**
     * A gombot tároló lista.
     */
    ArrayList<ViewButton> buttons = new ArrayList<>();
    /**
     * A háttér aminek meg kell jelennie.
     */
    Image background;
    /**
     * A szöveg, aminek meg kell jelennie lent.
     */
    String statusText = "";
    /**
     * A virológusokhoz tartozó képeket tartalmazza.
     */
    HashMap<Virologist, Image> virologistIcons = new HashMap<>();
    /**
     * A mezőkhöz tartózó háttérképek.
     */
    HashMap<String, Image> tileBackgrounds = new HashMap<>();
    /**
     * A jelenlegi mező nevét tárolja.
     */
    String currentTileName = "";

    /**
     * ViewPanel ctor
     */
    public ViewPanel() {
        try {
            tileBackgrounds.put("LabTile", ImageIO.read(new File("./assets/tiles/labTile.png")));
            tileBackgrounds.put("InfectiousLabTile", ImageIO.read(new File("./assets/tiles/infectiousLabTile.png")));
            tileBackgrounds.put("ShelterTile", ImageIO.read(new File("./assets/tiles/shelterTile.png")));
            tileBackgrounds.put("DumpsterTile", ImageIO.read(new File("./assets/tiles/dumpsterTile.png")));
            tileBackgrounds.put("StorageTile", ImageIO.read(new File("./assets/tiles/storageTile.png")));
            tileBackgrounds.put("Tile", ImageIO.read(new File("./assets/tiles/tile.png")));

            ArrayList<Virologist> virologists = Game.instance().getVirologists();
            virologistIcons.put(virologists.get(0), ImageIO.read(new File("./assets/virologists/virologistRed.png")));
            virologistIcons.put(virologists.get(1), ImageIO.read(new File("./assets/virologists/virologistCyan.png")));
            virologistIcons.put(virologists.get(2), ImageIO.read(new File("./assets/virologists/virologistOrange.png")));
            virologistIcons.put(virologists.get(3), ImageIO.read(new File("./assets/virologists/virologistGreen.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initComponents();
    }

    /**
     * Létrehozza a ViewPanel összes komponensét.
     */
    public void initComponents() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonClickedMaybe(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        int buttonSize = 100;


        ViewButton stealEquipmentButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/stealEquipment.png");
        stealEquipmentButton.setFunction(ButtonFunctions.StealEquipment);
        buttons.add(stealEquipmentButton);

        ViewButton stealMaterialsButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/stealMaterials.png");
        stealMaterialsButton.setFunction(ButtonFunctions.StealMaterial);
        buttons.add(stealMaterialsButton);

        ViewButton useEquipmentButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/useEquipment.png");
        useEquipmentButton.setFunction(ButtonFunctions.UseEquipment);
        buttons.add(useEquipmentButton);

        ViewButton dumpMaterialsButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/dumpMaterials.png");
        dumpMaterialsButton.setFunction(ButtonFunctions.DumpMaterials);
        buttons.add(dumpMaterialsButton);

        ViewButton pickupMaterialsButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/pickupMaterials.png");
        pickupMaterialsButton.setFunction(ButtonFunctions.PickupMaterials);
        buttons.add(pickupMaterialsButton);

        ViewButton infectButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/infect.png");
        infectButton.setFunction(ButtonFunctions.Infect);
        buttons.add(infectButton);

        ViewButton pickupEquipmentButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/pickupEquipment.png");
        pickupEquipmentButton.setFunction(ButtonFunctions.PickupEquipment);
        buttons.add(pickupEquipmentButton);

        ViewButton learnGeneticCodeButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/learnGeneticCode.png");
        learnGeneticCodeButton.setFunction(ButtonFunctions.LearnGeneticCode);
        buttons.add(learnGeneticCodeButton);

        ViewButton makeInactiveAgentButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/makeInactiveAgent.png");
        makeInactiveAgentButton.setFunction(ButtonFunctions.MakeInactiveAgent);
        buttons.add(makeInactiveAgentButton);

        ViewButton moveButton = new ViewButton(0, 820, buttonSize, buttonSize, "./assets/icons/move.png");
        moveButton.setFunction(ButtonFunctions.Move);
        buttons.add(moveButton);

        int startX = 1280 / 2 - (buttons.size() * buttonSize) / 2;
        for (int i = 0; i < buttons.size(); i++) {
            ViewButton button = buttons.get(i);
            button.setX(startX + i * buttonSize);
            button.setY(820);
        }

        int padding = 20;
        int iter = 0;
        ViewButton inactiveAgentButton = new ViewButton(1280 - buttonSize - 50, 50 + (buttonSize + padding) * iter++, buttonSize, buttonSize, "./assets/icons/inactiveAgent.png");
        inactiveAgentButton.setFunction(ButtonFunctions.InactiveAgentInventory);
        buttons.add(inactiveAgentButton);

        ViewButton materialsButton = new ViewButton(1280 - buttonSize - 50, 50 + (buttonSize + padding) * iter++, buttonSize, buttonSize, "./assets/icons/material.png");
        materialsButton.setFunction(ButtonFunctions.MaterialInventory);
        buttons.add(materialsButton);

        ViewButton equipmentButton = new ViewButton(1280 - buttonSize - 50, 50 + (buttonSize + padding) * iter++, buttonSize, buttonSize, "./assets/icons/equipment.png");
        equipmentButton.setFunction(ButtonFunctions.EquipmentInventory);
        buttons.add(equipmentButton);

        ViewButton activeAgentButton = new ViewButton(1280 - buttonSize - 50, 50 + (buttonSize + padding) * iter++, buttonSize, buttonSize, "./assets/icons/activeAgent.png");
        activeAgentButton.setFunction(ButtonFunctions.ActiveAgentInventory);
        buttons.add(activeAgentButton);

        ViewButton skipButton = new ViewButton(50, 50, buttonSize, buttonSize, "./assets/icons/skip.png");
        skipButton.setFunction(ButtonFunctions.SkipTurn);
        buttons.add(skipButton);

        String tileName = Game.instance().getCurrentVirologist().getTile().getClass().getSimpleName();
        if (!currentTileName.equals(tileName)) {
            background = tileBackgrounds.get(tileName);
            repaint();
        }
    }

    /**
     * Megnézi, hogy a megadott koordináta benne van-e valamelyik gombban, és meghívja annak a függvényét.
     *
     * @param x a pont X koordinátája.
     * @param y a pont Y koordinátája.
     */
    public void buttonClickedMaybe(int x, int y) {
        for (ViewButton button : buttons) {
            int buttonX = button.getX();
            int buttonY = button.getY();
            if (buttonX < x && x < buttonX + button.getWidth() &&
                    buttonY < y && y < buttonY + button.getHeight()) {
                String status = button.runFunction(Game.instance().getCurrentVirologist(),
                        Game.instance().getVirologists());
                if (!"".equals(status)) {
                    statusText = status;
                }
                break;
            }
        }
        String tileName = Game.instance().getCurrentVirologist().getTile().getClass().getSimpleName();
        if (!currentTileName.equals(tileName)) {
            background = tileBackgrounds.get(tileName);
            repaint();
        }
    }

    /**
     * Kirajzolja a hátteret, a gombokat, a virológusokat és a státuszszöveget.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            int x = 1280 / 2 - background.getWidth(null) / 2;
            int y = 0;
            g.drawImage(background, x, y, null);
        }

        g.setColor(Color.GRAY);
        g.fillRect(0, 820, 1280, 100);

        g.setColor(Color.GRAY);
        g.fillRect(0, 720, 1280, 100);
        g.setColor(Color.BLACK);
        g.drawRect(0 - 1, 720, 1280 + 2, 100);
        Font f = null;
        try {
            f = Font.createFont(Font.TRUETYPE_FONT, Files.newInputStream(Paths.get("./assets/PROPAGAN.ttf")));
            f = f.deriveFont(40.0f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        if (!"".equals(statusText)) {
            drawCenteredString(g, statusText, new Rectangle(0, 720, 1280, 100), f);
        }
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).paintButton(g);
        }

        int yCurrent = 720 / 2 - 100 / 2;
        Virologist currentVirologist = Game.instance().getCurrentVirologist();
        ArrayList<Virologist> virologists = currentVirologist.getTile().getVirologists();
        g.drawImage(virologistIcons.get(currentVirologist), 300, yCurrent, null);

        int i = 0;
        for (Virologist v : virologists) {
            if (v != currentVirologist) {
                g.drawImage(virologistIcons.get(v), 1280 - 400, yCurrent - 100 + 100 * i++, null);
            }
        }
    }

    /**
     * Stackoverflow kód, a megadott téglalapon belül középre rakja a szöveget.
     *
     * @param g    amire írni kell.
     * @param text amit ki kell írni.
     * @param rect a téglalap.
     * @param font a betűtípus
     */
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}
