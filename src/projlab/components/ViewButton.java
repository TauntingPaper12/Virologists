package projlab.components;

import projlab.Virologist;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class ViewButton {
    /**
     * X, Y koordináták, szélesség, magasság.
     */
    int x, y, width, height;
    /**
     * A gombhoz tartozó ikon.
     */
    BufferedImage icon;
    /**
     * A gombhoz tartozó funkció, ami meghívódik, ha megnyomják.
     */
    Function<ViewButtonArgs, String> function;

    /**
     * ViewButton ctor
     *
     * @param x          X koordináta.
     * @param y          Y koordináta.
     * @param width      szélesség.
     * @param height     magasság.
     * @param pathToIcon ikon elérési helye.
     */
    public ViewButton(int x, int y, int width, int height, String pathToIcon) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (!"".equals(pathToIcon)) {
            try {
                icon = ImageIO.read(new File(pathToIcon));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Osztály a gombba bemenő argumentumok számára.
     */
    public class ViewButtonArgs {
        Virologist virologist;
        ArrayList<Virologist> virologists;

        public ViewButtonArgs(Virologist virologist, ArrayList<Virologist> virologists) {
            this.virologist = virologist;
            this.virologists = virologists;
        }

        public Virologist getVirologist() {
            return virologist;
        }

        public ArrayList<Virologist> getVirologists() {
            return virologists;
        }
    }

    /**
     * Visszaadja az X koordinátát.
     *
     * @return X koordináta.
     */
    public int getX() {
        return x;
    }

    /**
     * Visszaadja az Y koordinátát.
     *
     * @return Y koordináta.
     */
    public int getY() {
        return y;
    }

    /**
     * Visszaadja a gomb szélességét.
     *
     * @return a gomb szélessége.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Visszaadja a gomb magasságát.
     *
     * @return a gomb magassága.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Visszaadja a gomb ikonját.
     *
     * @return a gomb ikonja.
     */
    public BufferedImage getIcon() {
        return icon;
    }

    /**
     * Beállítja a gomb funkcióját.
     *
     * @param function a funkció.
     */
    public void setFunction(Function<ViewButtonArgs, String> function) {
        this.function = function;
    }

    /**
     * Futtatja a gomb funkcióját.
     *
     * @param virologist  az éppen körönlévő virológus.
     * @param virologists az összes virológus.
     * @return státusszöveg.
     */
    public String runFunction(Virologist virologist, ArrayList<Virologist> virologists) {
        return function.apply(new ViewButtonArgs(virologist, virologists));
    }

    /**
     * Kirajzolja a felületre a gombot.
     *
     * @param g a felület.
     */
    public void paintButton(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        int imageX = x + width / 2 - icon.getWidth() / 2;
        int imageY = y + height / 2 - icon.getHeight() / 2;
        if (icon != null) {
            g.drawImage(icon, imageX, imageY, null);
        }
    }

    /**
     * Beállítja az X koordinátát.
     *
     * @param x az X koordináta.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Beállítja az Y koordinátát.
     *
     * @param y az Y koordináta.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Beállítja a gomb szélességét.
     *
     * @param width a gomb szélessége.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Beállítja a gomb magasságát.
     *
     * @param height a gomb magassága.
     */
    public void setHeight(int height) {
        this.height = height;
    }
}