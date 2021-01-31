
package charva.showcase;

import charva.awt.BorderLayout;
import charva.awt.Container;
import charva.awt.FlowLayout;
import charva.awt.event.ActionEvent;
import charva.awt.event.ActionListener;
import charva.awt.event.KeyEvent;
import charva.awt.event.KeyListener;
import charvax.swing.BoxLayout;
import charvax.swing.ButtonGroup;
import charvax.swing.JButton;
import charvax.swing.JDialog;
import charvax.swing.JFrame;
import charvax.swing.JLabel;
import charvax.swing.JList;
import charvax.swing.JPanel;
import charvax.swing.JRadioButton;
import charvax.swing.JTabbedPane;
import charvax.swing.border.EmptyBorder;
import charvax.swing.border.TitledBorder;


/**
 * This class demonstrates how to use the JTabbedPane.
 */
class JTabbedPaneTest extends JDialog implements ActionListener, KeyListener {

    JTabbedPaneTest(JFrame owner_) {
        super(owner_, "JTabbedPane Test");

        Container contentPane = getContentPane();

        JPanel toppan = new JPanel();
        toppan.setBorder(new EmptyBorder(1, 1, 1, 1));
        toppan.add(new JLabel(
                "Press the F5, F6 and F7 keys to switch between panes"));

        JPanel centerpan = new JPanel();
        centerpan.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 1));

        _tabpane = new JTabbedPane();
        _tabpane.addTab("General (F5)", 'g', new GeneralPane());
        _tabpane.addTab("Device Manager (F6)", 'd', new DevicePane());
        _tabpane.addTab("Performance (F7)", 'p', new PerformancePane());
        addKeyListener(this);
        centerpan.add(_tabpane);

        _okButton = new JButton("OK");
        _okButton.setMnemonic('o');
        _okButton.addActionListener(this);

        contentPane.add(toppan, BorderLayout.NORTH);
        contentPane.add(centerpan, BorderLayout.CENTER);
        contentPane.add(_okButton, BorderLayout.SOUTH);

        pack();
    }

    public void actionPerformed(ActionEvent e_) {
        hide();
    }

    /**
     * This method implements the KeyListener interface and handles the
     * interactive selection of tabs.
     */
    public void keyPressed(KeyEvent evt_) {
        int key = evt_.getKeyCode();
        if (key == KeyEvent.VK_F5)
            _tabpane.setSelectedIndex(0);
        else if (key == KeyEvent.VK_F6)
            _tabpane.setSelectedIndex(1);
        else if (key == KeyEvent.VK_F7)
            _tabpane.setSelectedIndex(2);
    }

    public void keyTyped(KeyEvent evt_) {
    }

    public void keyReleased(KeyEvent evt_) {
    }

    class GeneralPane extends JPanel {
        public GeneralPane() {
            setLayout(new BorderLayout());

            JPanel northpan = new JPanel();
            northpan.setLayout(new BoxLayout(northpan, BoxLayout.Y_AXIS));
            northpan.setBorder(new TitledBorder("System"));
            northpan.add(new JLabel("Red Hat Linux 9.0"));

            JPanel centerpan = new JPanel();
            centerpan.setLayout(new BoxLayout(centerpan, BoxLayout.Y_AXIS));
            centerpan.setBorder(new TitledBorder("Registered to"));
            centerpan.add(new JLabel("Rob Pitman"));
            centerpan.add(new JLabel("8 Pickwood Road"));
            centerpan.add(new JLabel("Centurion, South Africa"));

            JPanel southpan = new JPanel();
            southpan.setLayout(new BoxLayout(southpan, BoxLayout.Y_AXIS));
            southpan.setBorder(new TitledBorder("Computer"));
            southpan.add(new JLabel("GenuineIntel"));
            southpan.add(new JLabel("x86 Family 15 Model 1 Stepping 2"));
            southpan.add(new JLabel("256 MB RAM"));

            add(northpan, BorderLayout.NORTH);
            add(centerpan, BorderLayout.CENTER);
            add(southpan, BorderLayout.SOUTH);
            pack();
        }
    }

    class DevicePane extends JPanel {
        public DevicePane() {
            setLayout(new BorderLayout());

            JPanel northpan = new JPanel();
            JRadioButton button1 = new JRadioButton("View devices by type");
            JRadioButton button2 = new JRadioButton("View devices by connection");
            ButtonGroup buttons = new ButtonGroup();
            buttons.add(button1);
            buttons.add(button2);
            button1.setSelected(true);
            northpan.add(button1);
            northpan.add(button2);

            JPanel centerpan = new JPanel();
            String[] devices = {"Computer", "CD-ROM", "Disk drives",
                    "Display adapters", "Floppy disk controllers",
                    "Imaging devices", "Keyboard", "Modem",
                    "Monitors", "Mouse"};
            JList deviceList = new JList(devices);
            deviceList.setBorder(new TitledBorder("Devices"));
            centerpan.add(deviceList);

            JPanel southpan = new JPanel();
            southpan.add(new JButton("Properties"));
            southpan.add(new JButton("Refresh"));
            southpan.add(new JButton("Remove"));
            southpan.add(new JButton("Print..."));

            add(northpan, BorderLayout.NORTH);
            add(centerpan, BorderLayout.CENTER);
            add(southpan, BorderLayout.SOUTH);
            pack();
        }
    }

    class PerformancePane extends JPanel {
        public PerformancePane() {
            setLayout(new BorderLayout());

            JPanel centerpan = new JPanel();
            centerpan.setBorder(new TitledBorder("Performance Status"));
            centerpan.setLayout(new BoxLayout(centerpan, BoxLayout.Y_AXIS));
            centerpan.add(new JLabel("Memory:           256.0 MB of RAM"));
            centerpan.add(new JLabel("System Resources: 50% free"));
            centerpan.add(new JLabel("File System:      32 bit"));
            centerpan.add(new JLabel("Virtual Memory:   32 bit"));
            centerpan.add(new JLabel("Disk Compression: Not Installed"));
            centerpan.add(new JLabel("Your system is configured for optimum performance"));

            JPanel southpan = new JPanel();
            southpan.setBorder(new TitledBorder("Advanced Settings"));
            southpan.add(new JButton("File System..."));
            southpan.add(new JButton("Graphics..."));
            southpan.add(new JButton("Virtual Memory..."));

            add(centerpan, BorderLayout.CENTER);
            add(southpan, BorderLayout.SOUTH);
            pack();
        }
    }

    private JButton _okButton;
    private JTabbedPane _tabpane;
}

