package projlab.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SelectDialog<T> extends JDialog {
    /**
     * Választható objektumok.
     */
    private ArrayList<T> items;
    /**
     * Megadja, hogy 1 vagy több objektumot választhat ki a listából a játékos.
     */
    private boolean singleSelection;
    /**
     * Ebben jelennek meg az elemek.
     */
    private JList list;
    /**
     * Megadja, hogy a felhasználó az 'OK' gombbal zárta-e be az ablakot.
     */
    private boolean okResponse = false;

    public SelectDialog(ArrayList<T> items, boolean singleSelection, String title) {
        this.items = items;
        this.singleSelection = singleSelection;
        this.setTitle(title);
        initComponents();
    }

    /**
     *Inicializálja a listából való választás megjelenítését.
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
            if ("InfectiousLabTile".equals(texts[i])) {
                texts[i] = "LabTile";
            }
        }
        list = new JList(texts);
        add(list, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            okResponse = true;
            dispose();
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(okButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * Visszatér a játékos által kiválasztott objektumokkal.
     * @return játékos által kiválasztott objektumok.
     */
    public ArrayList<T> getSelectedItems() {
        ArrayList<T> selectedItems = new ArrayList<>();
        if (singleSelection) {
            int index = list.getSelectedIndex();
            if (index != -1) {
                selectedItems.add(items.get(index));
            }
        } else {
            int[] indices = list.getSelectedIndices();
            for (int i : indices) {
                selectedItems.add(items.get(i));
            }
        }
        return selectedItems;
    }

    /**
     * Visszatér azzal, hogy a játékos 'OK'-val zárta-e le az ablakot
     * @return lezárás eredménye
     */
    public boolean isOkResponse() {
        return okResponse;
    }
}
