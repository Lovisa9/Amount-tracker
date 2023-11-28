package ui;

import model.AmountTracker;
import model.AmountTrackerList;
import model.TrackerRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Represent a GUI for the AmountTracker application, (the main frame)
 */
public class TrackerGUI extends JFrame {
    private static final String JSON_STORE = "./data/TrackerRoom.json";
    protected static TrackerRoom trackerRoom;
    private static JsonWriter writer;
    private static JsonReader reader;
    protected static AmountTrackerList amountList;
    private static boolean isLoad = false;
    protected static List<AmountTracker> amList;
    protected static TrackerGUI trackerGUI;
    protected static MainGUI mainGUI;

    // EFFECTS: create the main frame for the application,
    // and initialize the all values in TrackerRoom, AmountTracker,
    // AmountTrackerList
    public TrackerGUI() throws FileNotFoundException {
        super("AmountTracker");
        amountList = new AmountTrackerList();
        trackerRoom = new TrackerRoom("Tracker");
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
        amList = new ArrayList<>();
        trackerGUI = this;


        JFrame frame = new MainGUI();
        frame.setPreferredSize(new Dimension(500, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    /**
     * This method taken from:
     * https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Represents the main frame for the application, add buttons
     * into the main frame, and set the menuBar
     */
    public class MainGUI extends JFrame {

        // EFFECTS: create the main frame for the application
        public MainGUI() {
            super("AmountTracker");
            mainGUI = this;

            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setPreferredSize(new Dimension(500, 400));
            addOriginalButton();
            this.setJMenuBar(addMenu());
            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setResizable(true);
            Container c = this.getContentPane();
            c.setBackground(Color.WHITE); // set the frame background
        }

        // EFFECTS: create the buttons (main frame) for the application
        public void addOriginalButton() {
            setLayout(new GridLayout(4, 2));
            JButton add = new JButton("Add amount");
            JButton print = new JButton("Print amount");
            JButton check = new JButton("Check amount");

            buttonActions(add, print, check);

            add(add);
            add(print);
            add(check);
        }

        //EFFECTS: add the actions for add, print, check buttons
        private void buttonActions(JButton add, JButton print, JButton check) {
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new AddAmountPane();
                    frame.setVisible(true);
                    setVisible(false);
                }
            });
            print.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new PrintListPane();
                    frame.setVisible(true);
                }
            });

            check.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new CheckAmountPane();
                    frame.setVisible(true);
                }
            });
        }

        // EFFECTS: create the menu bar for the application with images,
        // add save and load menus with their action
        private JMenuBar addMenu() {
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menu.setMnemonic('F');

            ImageIcon icon1 = createImageIcon("image/loading.png", "");
            icon1.setImage(icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            ImageIcon icon2 = createImageIcon("image/floppy-disk.png", "");
            icon2.setImage(icon2.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            JMenuItem load = new JMenuItem("Load");
            JMenuItem save = new JMenuItem("Save");

            menuActions(load, save);

            load.setIcon(icon1);
            save.setIcon(icon2);
            menu.add(load);
            menu.add(save);
            menuBar.add(menu);
            return menuBar;
        }

        //EFFECTS: add the actions for load and save Menu
        private void menuActions(JMenuItem load, JMenuItem save) {
            load.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadTrackerRoom();
                }
            });
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveTrackerRoom();
                }
            });
        }

        //EFFECTS: save the trackerRoom to file, and show a
        // message that save successfully
        public void saveTrackerRoom() {
            try {
                writer.open();
                writer.write(trackerRoom);
                writer.close();

                JOptionPane.showMessageDialog(null,
                        "Saved" + trackerRoom.getName() + " to " + JSON_STORE,
                        "Save",
                        JOptionPane.QUESTION_MESSAGE);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to write to file: " + JSON_STORE,
                        "Save",
                        JOptionPane.QUESTION_MESSAGE);
            }
        }

        // MODIFIES: this
        // EFFECTS: load the trackerRoom from file, and show a
        // message that load successfully
        public void loadTrackerRoom() {
            try {
                trackerRoom = reader.read();
                isLoad = true;
                amountList = new AmountTrackerList();

                JOptionPane.showMessageDialog(null,
                        "Loaded " + trackerRoom.getName() + " from " + JSON_STORE,
                        "Load",
                        JOptionPane.QUESTION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Unable to read from file: " + JSON_STORE,
                        "Save",
                        JOptionPane.QUESTION_MESSAGE);
            }
        }
    }

    //EFFECTS: add the GridBagConstraints for GridBagLayout
    protected static GridBagConstraints getGridBagConstraints(double v, int x, int y) {
        GridBagConstraints g1 = new GridBagConstraints();
        g1.fill = GridBagConstraints.BOTH;
        g1.ipady = 40;
        g1.weightx = v;
        g1.gridwidth = 3;
        g1.gridx = x;
        g1.gridy = y;
        return g1;
    }

    //EFFECTS: return true if user load the data from file,
    // otherwise, return false
    public static boolean isLoad() {
        return isLoad;
    }
}
