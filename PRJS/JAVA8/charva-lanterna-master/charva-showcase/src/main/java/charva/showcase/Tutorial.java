/* class Tutorial
 *
 * R Pitman, 2003
 * Last updated: 20 June, 2003.
 *
 * This class performs a general test of the functionality of the
 * CHARVA library.
 */

package charva.showcase;

import charva.awt.BorderLayout;
import charva.awt.ColorScheme;
import charva.awt.Component;
import charva.awt.Container;
import charva.awt.Toolkit;
import charva.awt.Window;
import charva.awt.event.ActionEvent;
import charva.awt.event.ActionListener;
import charvax.swing.BoxLayout;
import charvax.swing.JCheckBoxMenuItem;
import charvax.swing.JDialog;
import charvax.swing.JFrame;
import charvax.swing.JLabel;
import charvax.swing.JMenu;
import charvax.swing.JMenuBar;
import charvax.swing.JMenuItem;
import charvax.swing.JOptionPane;
import charvax.swing.JPanel;


public class Tutorial extends JFrame implements ActionListener {
    
    private ColorScheme     savedColors;
    private ActionListener exitActionListener;

    public Tutorial() {
        super("Charva Demo, (c) R Pitman, 2002-2006, (c) charva-lanterna, 2015");
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JMenuBar menubar = new JMenuBar();
        JMenu jMenuFile = new JMenu("File");
        jMenuFile.setMnemonic('F');

//        JMenuItem jMenuItemFileChooser = new JMenuItem("JFileChooser", 'F');
//        jMenuItemFileChooser.addActionListener(this);
//        jMenuFile.add(jMenuItemFileChooser);
//
//        JMenuItem jMenuItemCustomFileChooser = new JMenuItem("custom FileChooser", 'c');
//        jMenuItemCustomFileChooser.addActionListener(this);
//        jMenuFile.add(jMenuItemCustomFileChooser);
//
//        jMenuFile.addSeparator();

        JMenuItem jMenuItemFileWarnColors = new JCheckBoxMenuItem("Use warning colors");
        jMenuItemFileWarnColors.setMnemonic('w');
        jMenuItemFileWarnColors.addActionListener(this);
        jMenuFile.add(jMenuItemFileWarnColors);
        
        jMenuFile.addSeparator();
        
        JMenuItem jMenuItemFileExit = new JMenuItem("Exit");
        jMenuItemFileExit.setMnemonic('x');
        jMenuItemFileExit.addActionListener(this);
        jMenuFile.add(jMenuItemFileExit);

        JMenu jMenuLayout = new JMenu("Layouts");
        jMenuLayout.setMnemonic('L');

        JMenuItem jMenuItemLayoutMisc = new JMenuItem("Miscellaneous Layouts");
        jMenuItemLayoutMisc.setMnemonic('M');
        jMenuItemLayoutMisc.addActionListener(this);
        jMenuLayout.add(jMenuItemLayoutMisc);

        JMenuItem jMenuItemLayoutColor = new JMenuItem("Layouts in Color");
        jMenuItemLayoutColor.setMnemonic('C');
        jMenuItemLayoutColor.addActionListener(this);
        jMenuLayout.add(jMenuItemLayoutColor);

        JMenuItem jMenuItemLayoutGBL = new JMenuItem("GridBagLayout");
        jMenuItemLayoutGBL.setMnemonic('G');
        jMenuItemLayoutGBL.addActionListener(this);
        jMenuLayout.add(jMenuItemLayoutGBL);

        JMenu jMenuContainers = new JMenu("Containers");
        jMenuContainers.setMnemonic('C');

        JMenuItem jMenuItemContainerJTabbedPane = new JMenuItem("JTabbedPane");
        jMenuItemContainerJTabbedPane.setMnemonic('T');
        jMenuItemContainerJTabbedPane.addActionListener(this);
        jMenuContainers.add(jMenuItemContainerJTabbedPane);
        
        jMenuContainers.addSeparator();

        JMenu jMenuItemContainerJOptionPane = new JMenu("JOptionPane");
        jMenuItemContainerJOptionPane.setMnemonic('O');
        jMenuContainers.add(jMenuItemContainerJOptionPane);

        JMenuItem jMenuItemShowMessageDialog =
                new JMenuItem("showMessageDialog");
        jMenuItemShowMessageDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowMessageDialog);

        JMenuItem jMenuItemShowConfirmDialog =
                new JMenuItem("showConfirmDialog");
        jMenuItemShowConfirmDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowConfirmDialog);

        JMenuItem jMenuItemShowInputDialog =
                new JMenuItem("showInputDialog");
        jMenuItemShowInputDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowInputDialog);

        JMenuItem jMenuItemShowCustomInputDialog =
                new JMenuItem("show Custom InputDialog");
        jMenuItemShowCustomInputDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowCustomInputDialog);

        JMenu jMenuWidgets = new JMenu("Widgets");
        jMenuWidgets.setMnemonic('W');

        JMenuItem jMenuItemWidgetText = new JMenuItem("Text components");
        jMenuItemWidgetText.setMnemonic('T');
        jMenuItemWidgetText.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetText);

        JMenuItem jMenuItemWidgetSelection = new JMenuItem("Selection components");
        jMenuItemWidgetSelection.setMnemonic('S');
        jMenuItemWidgetSelection.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetSelection);

        JMenuItem jMenuItemWidgetButtons = new JMenuItem("Buttons");
        jMenuItemWidgetButtons.setMnemonic('B');
        jMenuItemWidgetButtons.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetButtons);

        JMenuItem jMenuItemWidgetJTable = new JMenuItem("JTable");
        jMenuItemWidgetJTable.setMnemonic('J');
        jMenuItemWidgetJTable.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetJTable);

        JMenu jMenuEvents = new JMenu("Events");
        jMenuEvents.setMnemonic('E');

        JMenuItem jMenuItemKeyEvents = new JMenuItem("KeyEvents");
        jMenuItemKeyEvents.setMnemonic('K');
        jMenuItemKeyEvents.addActionListener(this);
        jMenuEvents.add(jMenuItemKeyEvents);

        JMenuItem jMenuItemFocusEvents = new JMenuItem("FocusEvents");
        jMenuItemFocusEvents.setMnemonic('F');
        jMenuItemFocusEvents.addActionListener(this);
        jMenuEvents.add(jMenuItemFocusEvents);

        JMenu jMenuThreads = new JMenu("Threads");
        jMenuThreads.setMnemonic('T');

        JMenuItem jMenuItemProgressBar = new JMenuItem("JProgressBar");
        jMenuItemProgressBar.setMnemonic('P');
        jMenuItemProgressBar.addActionListener(this);
        jMenuThreads.add(jMenuItemProgressBar);

        menubar.add(jMenuFile);
        menubar.add(jMenuLayout);
        menubar.add(jMenuContainers);
        menubar.add(jMenuWidgets);
        menubar.add(jMenuEvents);
        menubar.add(jMenuThreads);

        setJMenuBar(menubar);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        // character graphics generated here:
        // http://patorjk.com/software/taag/#p=display&h=1&v=1&f=Standard&t=charva-%0Alanterna
        labelPanel.add(new JLabel("       _                                       "));
        labelPanel.add(new JLabel("  ___ | |__    __ _  _ __ __   __ __ _         "));
        labelPanel.add(new JLabel(" / __|| '_ \\  / _` || '__|\\ \\ / // _` | _____  "));
        labelPanel.add(new JLabel("| (__ | | | || (_| || |    \\ V /| (_| ||_____| "));
        labelPanel.add(new JLabel(" \\___||_| |_| \\__,_||_|     \\_/  \\__,_|        "));
        labelPanel.add(new JLabel(" _                _                            "));
        labelPanel.add(new JLabel("| |  __ _  _ __  | |_  ___  _ __  _ __    __ _ "));
        labelPanel.add(new JLabel("| | / _` || '_ \\ | __|/ _ \\| '__|| '_ \\  / _` |"));
        labelPanel.add(new JLabel("| || (_| || | | || |_|  __/| |   | | | || (_| |"));
        labelPanel.add(new JLabel("|_| \\__,_||_| |_| \\__|\\___||_|   |_| |_| \\__,_|"));
        labelPanel.add(new JLabel(""));
        labelPanel.add(new JLabel("Use LEFT and RIGHT cursor keys to select a menu."));
        labelPanel.add(new JLabel("Use ENTER to invoke a menu or menu-item."));
        labelPanel.add(new JLabel("(You can also use the " +
                "underlined \"mnemonic key\" to invoke a menu.)"));
        labelPanel.add(new JLabel("Use BACKSPACE or ESC to dismiss a menu."));
        contentPane.add(labelPanel, BorderLayout.SOUTH);

        setLocation(0, 0);
        setSize(80, 24);
        validate();
    }

    public void setExitActionListener(final ActionListener exitActionListener) {
        this.exitActionListener = exitActionListener;
    }

    public void actionPerformed(ActionEvent ae) {
        String    actionCommand = ae.getActionCommand();
        Component source        = (Component)ae.getSource();

        if (actionCommand.equals("Use warning colors")) {
            if (((JMenuItem)source).isSelected()) {
                Toolkit.setNormalColors(savedColors);
                setColors(savedColors);
                savedColors = null;
            } else {
                savedColors = Toolkit.getNormalColors();
                Toolkit.setNormalColors(Toolkit.getWarningColors());
                setColors(Toolkit.getWarningColors());
            }
            repaint();
        } else if (actionCommand.equals("Exit")) {
            if (savedColors != null) {
                Toolkit.setNormalColors(savedColors);
                savedColors = null;
            }
            //System.gc();    // so that HPROF reports only live objects.
            if (exitActionListener != null) {
                exitActionListener.actionPerformed(ae);
            } else {
                hide();
            }
//        } else if (actionCommand.equals("JFileChooser")) {
//            testFileChooser();
//        } else if (actionCommand.equals("custom FileChooser")) {
//	    JFileChooser.CANCEL_LABEL = "Cancel (F4)";
//	    JFileChooser.CANCEL_ACCELERATOR = KeyEvent.VK_F4;
//	    (new JFileChooser()).show();
//            JOptionPane.showMessageDialog(this, "This test has been (temporarily) disabled",
//                    "Information", JOptionPane.PLAIN_MESSAGE);
        } else if (actionCommand.equals("Miscellaneous Layouts")) {
            JDialog dlg = new LayoutTest(this);
            dlg.show();
        } else if (actionCommand.equals("Layouts in Color")) {
            JDialog dlg = new ColorLayoutTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("GridBagLayout")) {
            JDialog dlg = new GridBagLayoutTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("JTabbedPane")) {
            JDialog dlg = new JTabbedPaneTest(this);
            dlg.show();
        } else if (actionCommand.equals("showMessageDialog")) {
            JOptionPane.showMessageDialog(this, 
                    "This is an example of a Message Dialog " 
                    + "with a single message string",
                    "This is the title", JOptionPane.PLAIN_MESSAGE);
        } else if (actionCommand.equals("showConfirmDialog")) {
            showConfirmDialog();
        } else if (actionCommand.equals("showInputDialog")) {
            showInputDialog();
        } else if (actionCommand.equals("show Custom InputDialog")) {
            showCustomInputDialog(this);
        } else if (actionCommand.equals("Text components")) {
            TextWidgetTest dlg = new TextWidgetTest(this);
            dlg.show();
        } else if (actionCommand.equals("Selection components")) {
            SelectionTest dlg = new SelectionTest(this);
            dlg.show();
        } else if (actionCommand.equals("Buttons")) {
            (new ButtonTest(this)).show();
        } else if (actionCommand.equals("JTable")) {
            JTableTest dlg = new JTableTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("KeyEvents")) {
            KeyEventTest dlg = new KeyEventTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("FocusEvents")) {
            FocusEventTest dlg = new FocusEventTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("JProgressBar")) {
            ProgressBarTest dlg = new ProgressBarTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else {
            JOptionPane.showMessageDialog(this, 
                    "Menu item \"" + actionCommand 
                    + "\" not implemented yet",
                    "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Demonstrate the JFileChooser.
     */
/*    private void testFileChooser() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("A File Chooser");
        chooser.setForeground(Color.white);
        chooser.setBackground(Color.blue);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

//  Uncomment this section of code to apply a FileFilter that masks out all
//  files whose names do not end with ".java".
//	// Construct an anonymous inner class that extends the abstract
//	// FileFilter class.
//	charvax.swing.filechooser.FileFilter filter = 
//		new charvax.swing.filechooser.FileFilter() {
//	    public boolean accept(File file_) {
//		String pathname = file_.getAbsolutePath();
//		return (pathname.endsWith(".java"));
//	    }
//	};
//	chooser.setFileFilter(filter);

        if (chooser.showDialog(this, "Open File") ==
                JFileChooser.APPROVE_OPTION) {

            String msgs[] = {"The selected file was:",
                    chooser.getSelectedFile().getAbsolutePath()};
            JOptionPane.showMessageDialog(this, msgs,
                    "Results of JFileChooser", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The CANCEL button was selected",
                    "Results of JFileChooser", JOptionPane.PLAIN_MESSAGE);
        }
    }*/

    /**
     * Demonstrate the JOptionPane.showConfirmDialog() method.
     */
    private void showConfirmDialog() {
        String[] messages = {
                "This is an example of a Confirm Dialog",
                "that displays an array of Strings"};

        int option = JOptionPane.showConfirmDialog(this, messages, "Select an Option",
                JOptionPane.YES_NO_CANCEL_OPTION);
        String result;
        if (option == JOptionPane.YES_OPTION)
            result = "User selected YES option";
        else if (option == JOptionPane.NO_OPTION)
            result = "User selected NO option";
        else
            result = "User selected Cancel option";
        JOptionPane.showMessageDialog(this, result,
                "Result of showConfirmDialog",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Demonstrate the JOptionPane.showInputDialog() method.
     */
    private void showInputDialog() {
        String[] messages = {
                "This is an example of an Input Dialog",
                "that displays an array of Strings"};
        String result = JOptionPane.showInputDialog(this, messages, "Input a value",
                JOptionPane.QUESTION_MESSAGE);
        String msg;
        if (result == null)
            msg = "User selected Cancel option";
        else
            msg = "User entered \"" + result + "\"";
        JOptionPane.showMessageDialog(this, msg,
                "Result of showInputDialog",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Demonstrate how to customize the JOptionPane.
     */
    private void showCustomInputDialog(Window parent_) {
        String[] results = {"", ""};
        String selected_option = null;

        String[] message = {
                "This shows how to create and use",
                "a JOptionPane directly, without using",
                "the convenience methods"};
        JOptionPane pane = new JOptionPane(message);

        // Make the dialog display a JTextField for user input.
        pane.setWantsInput(true);

        // Set the initial input value displayed to the user.
        pane.setInitialSelectionValue("default input value");

        // Provide customized button labels.
        String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
        pane.setOptions(options);

        // Make "Option 2" the default button.
        pane.setInitialValue("Option 2");

        JDialog dialog = pane.createDialog(parent_, "Custom JOptionPane");
        dialog.show();
        Object selectedValue = pane.getValue();
        //System.out.println("Selected value is " + selectedValue);
        results[0] = "The input value is \"" +
                pane.getInputValue() + "\"";

        // If there is NOT an array of option buttons:
        // (In this case, there is).
        if (pane.getOptions() == null) {
            int option = ((Integer) selectedValue).intValue();
            switch (option) {
//                case JOptionPane.YES_OPTION:
//                    selected_option = "YES";
//                    break;
                case JOptionPane.OK_OPTION:
                    selected_option = "OK";
                    break;
                case JOptionPane.NO_OPTION:
                    selected_option = "NO";
                    break;
                case JOptionPane.CANCEL_OPTION:
                    selected_option = "CANCEL";
                    break;
            }
        } else {
            // If there IS an array of option buttons:
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(selectedValue)) {
                    selected_option = options[i];
                    break;
                }
            }        // end for
        }
        results[1] = "The selected option is \"" + selected_option + "\"";

        /* Change the (static) labels and accelerators in the
         * JOptionPane. Note that the buttons stay customized
         * for future invocations of the JOptionPane methods,
         * until they are customized again.
        JOptionPane.OK_ACCELERATOR = KeyEvent.VK_F5;
        JOptionPane.YES_ACCELERATOR = KeyEvent.VK_F6;
        JOptionPane.NO_ACCELERATOR = KeyEvent.VK_F7;
        JOptionPane.CANCEL_ACCELERATOR = KeyEvent.VK_F8;
         */

        JOptionPane.showMessageDialog(this, results,
                "Result of Customized JOptionPane",
                JOptionPane.PLAIN_MESSAGE);
    }

}

