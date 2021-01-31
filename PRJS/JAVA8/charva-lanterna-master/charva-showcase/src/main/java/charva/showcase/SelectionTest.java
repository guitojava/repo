
package charva.showcase;

import charva.awt.BorderLayout;
import charva.awt.Container;
import charva.awt.event.ActionEvent;
import charva.awt.event.ActionListener;
import charva.awt.event.ItemEvent;
import charva.awt.event.ItemListener;
import charvax.swing.BoxLayout;
import charvax.swing.DefaultListModel;
import charvax.swing.JButton;
import charvax.swing.JCheckBox;
import charvax.swing.JComboBox;
import charvax.swing.JDialog;
import charvax.swing.JFrame;
import charvax.swing.JLabel;
import charvax.swing.JList;
import charvax.swing.JPanel;
import charvax.swing.JScrollPane;
import charvax.swing.JTextField;
import charvax.swing.ListSelectionModel;
import charvax.swing.border.TitledBorder;
import charvax.swing.event.ListDataEvent;
import charvax.swing.event.ListDataListener;
import charvax.swing.event.ListSelectionEvent;
import charvax.swing.event.ListSelectionListener;

/**
 * This class demonstrates how to use the JComboBox and
 * the JList components.
 */
class SelectionTest extends JDialog implements ActionListener {

    SelectionTest(JFrame owner_) {
        super(owner_, "JComboBox and JList");
        Container contentPane = getContentPane();

        JPanel northpan = new ComboBoxPanel();

        JPanel centerpan = new JListPanel();

        JPanel southpan = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);

        contentPane.add(northpan, BorderLayout.NORTH);
        contentPane.add(centerpan, BorderLayout.CENTER);
        contentPane.add(southpan, BorderLayout.SOUTH);
        pack();
    }

    public void actionPerformed(ActionEvent e_) {
        if (e_.getActionCommand().equals("OK")) {
            hide();
        }
    }

    /**
     * An inner class that displays a JComboBox.
     */
    class ComboBoxPanel extends JPanel
            implements ItemListener {
        private JComboBox _comboBox;
        private JTextField _comboBoxSelection;

        ComboBoxPanel() {
            setLayout(new BorderLayout());
            setBorder(new TitledBorder("JComboBox"));

            add(new JLabel("Press ENTER to pop up the JComboBox"),
                    BorderLayout.NORTH);

            String[] colors = {"Red", "Blue", "Green",
                    "Magenta", "Mauve", "Orange", "Black",
                    "White", "Brown"};
            _comboBox = new JComboBox(colors);
            _comboBox.setMaximumRowCount(5);
            _comboBox.addItemListener(this);
            add(_comboBox, BorderLayout.CENTER);

            JPanel southpan = new JPanel();
            southpan.add(new JLabel(" Selected item is: "));
            _comboBoxSelection = new JTextField(15);
            _comboBoxSelection.setEnabled(false);
            _comboBoxSelection.setText((String) _comboBox.getSelectedItem());
            southpan.add(_comboBoxSelection);
            add(southpan, BorderLayout.SOUTH);
        }

        public void itemStateChanged(ItemEvent e_) {
            _comboBoxSelection.setText((String) _comboBox.getSelectedItem());
        }
    }

    /**
     * An inner class that displays a JList.
     */
    class JListPanel extends JPanel
            implements ListSelectionListener, ListDataListener,
            ItemListener, ActionListener {
        
        private JCheckBox _selectionMode;
        private JList _vehicleList;
        private JTextField _listSelection;
        private JButton _deleteButton;

        JListPanel() {
            setBorder(new TitledBorder("JList"));
            setLayout(new BorderLayout());

            add(new JLabel("Use UP, DOWN, PGUP, PGDN, HOME & END to navigate"),
                    BorderLayout.NORTH);

            String[] vehicles = {"Volkswagen", "Rolls-Royce",
                    "Toyota", "Chrysler", "Mercedes Benz",
                    "Bentley", "Bugatti", "Maserati", "Porsche"};
            DefaultListModel model = new DefaultListModel();
            int columns = 0;
            for (int i = 0; i < vehicles.length; i++) {
                model.addElement(vehicles[i]);
                if (vehicles[i].length() > columns)
                    columns = vehicles[i].length();
            }
            model.addListDataListener(this);

            _vehicleList = new JList(model);
            _vehicleList.setVisibleRowCount(5);
            _vehicleList.setColumns(columns);
            _vehicleList.addListSelectionListener(this);
            JScrollPane scrollpane = new JScrollPane(_vehicleList);
            scrollpane.setBorder(new TitledBorder("Vehicles"));
            add(scrollpane, BorderLayout.WEST);


            _selectionMode = new JCheckBox("Selection Mode = Multiple");
            _selectionMode.addItemListener(this);

            _deleteButton = new JButton("Delete selected item(s)");
            _deleteButton.setActionCommand("Delete");
            _deleteButton.addActionListener(this);

            JPanel eastpan = new JPanel();
            eastpan.setLayout(new BoxLayout(eastpan, BoxLayout.Y_AXIS));
            eastpan.add(new JLabel(""));
            eastpan.add(_selectionMode);
            eastpan.add(new JLabel(""));
            eastpan.add(_deleteButton);
            add(eastpan, BorderLayout.EAST);

            JPanel southpan = new JPanel();
            southpan.add(new JLabel("Selected item(s):"));

            _listSelection = new JTextField(30);
            _listSelection.setEnabled(false);
            southpan.add(_listSelection);
            add(southpan, BorderLayout.SOUTH);
            pack();
        }

        /**
         * This method implements the ListSelectionListener interface,
         * and is called when an item is selected or deselected in the
         * JList.
         */
        public void valueChanged(ListSelectionEvent e_) {
            Object[] items = _vehicleList.getSelectedValues();
            String s = "";
            for (int i = 0; i < items.length; i++) {
                if (i != 0)
                    s += ",";
                s += (String) items[i];
            }
            _listSelection.setText(s);
        }

        public void actionPerformed(ActionEvent e_) {
            String cmd = e_.getActionCommand();
            if (cmd.equals("Delete")) {
                int[] indices = _vehicleList.getSelectedIndices();
                if (indices.length == 0)
                    return;    // there is no selected item

                DefaultListModel model =
                        (DefaultListModel) _vehicleList.getModel();

                // We must remove the last elements first, otherwise
                // (if we remove an element with a low index), the
                // higher indices will be invalid.
                for (int i = indices.length - 1; i >= 0; i--) {
                    model.removeElementAt(indices[i]);
                }

                // Having deleted some elements from the list, we must
                // ensure that:
                // (a) the first index inside the visible area is >= 0
                // (b) the "current row" is inside the visible area.
                // What constitutes the "current row" after a deletion is
                // debatable; we will assume that the last index to be
                // deleted is a close approximation.
                _vehicleList.ensureIndexIsVisible(indices[0]);
            }
        }

        /**
         * This method implements the ListDataListener interface,
         * and is called when an item is added to or removed from the
         * list, or the value of an item in the list changes.
         */
        public void contentsChanged(ListDataEvent e_) {
            _vehicleList.removeSelectionInterval(
                    e_.getIndex0(), e_.getIndex1());
            
            _vehicleList.repaint();
        }

        public void intervalAdded(ListDataEvent e_) {
            contentsChanged(e_);
        }

        public void intervalRemoved(ListDataEvent e_) {
            contentsChanged(e_);
        }

        /**
         * This method implements the ItemListener interface,
         * and is called when the SelectionMode checkbox is changed.
         */
        public void itemStateChanged(ItemEvent e_) {
            if (e_.getSource() == _selectionMode) {
                _vehicleList.setSelectionMode(_selectionMode.isSelected() ? 
                        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION 
                        : ListSelectionModel.SINGLE_SELECTION);
            }
        }
    }
}

