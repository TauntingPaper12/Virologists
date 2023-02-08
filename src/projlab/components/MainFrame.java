package projlab.components;

import projlab.Game;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        initComponents();
    }

    /**
     * Inicializálja a játék megjelenítését.
     */
    public void initComponents() {
        Game.instance().startGame(this);
        setTitle("A világtalan virológusok világa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        ViewPanel viewPanel = new ViewPanel();
        viewPanel.setPreferredSize(new Dimension(1280, 920));
        viewPanel.setSize(new Dimension(1280, 920));
        add(viewPanel);
        pack();
    }
}
