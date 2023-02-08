package projlab.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuFrame extends JFrame {
    /**
     * Menüt megjelenítő frame
     */
    MenuFrame frame;

    public MenuFrame() {
        frame = this;
        initComponents();
    }

    /**
     * Inicializálja a menű megjelenítését.
     */
    public void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("A világtalan virológusok világa");
        setLayout(new BorderLayout());
        // Set background picture
        final ImageIcon icon = new ImageIcon("./assets/menuBG.png");
        JPanel panel = new JPanel() {
            Image img = icon.getImage();

            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                graphics.drawImage(img, 0, 0, this);
            }
        };

        panel.setPreferredSize(new Dimension(1280, 720));
        panel.setSize(1280, 720);
        panel.addMouseListener(new MouseListener() {

            /**
             * Gombra kattintás esetén új játékot indít.
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                int x1 = 69;
                int y1 = 28;
                int x2 = 460;
                int y2 = 130;
                if (x1 < e.getX() && e.getX() < x2 &&
                        y1 < e.getY() && e.getY() < y2) {
                    frame.dispose();
                    MainFrame mf = new MainFrame();
                    mf.setLocationRelativeTo(frame);
                    mf.setVisible(true);
                }
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
        panel.repaint();
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}