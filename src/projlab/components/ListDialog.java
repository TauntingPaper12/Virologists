package projlab.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListDialog<T> extends JDialog {
    /**
     * Listában szereplő objektumok.
     */
    private ArrayList<T> items;

    public ListDialog(ArrayList<T> items, String title) {
        this.items = items;
        this.setTitle(title);
        initComponents();
    }

    /**
     * Inicializálja a listából választható objektumok megjelenítését.
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setResizable(false);
        setSize(new Dimension(300, 300));
        setPreferredSize(new Dimension(300, 300));
        setLocationRelativeTo(null);
        setModalityType(Dialog.DEFAULT_MODALITY_TYPE);

        String[] texts = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            texts[i] = items.get(i).getClass().getSimpleName();
        }
        JList list = new JList(texts);
        add(list, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(okButton);

        add(panel, BorderLayout.SOUTH);
        pack();
    }
}
