package ui;

import model.AmountTracker;
import javax.swing.*;
import java.awt.*;

import static ui.TrackerGUI.trackerGUI;

/*
 * Represents the print amount frame with image,
 * show the total amount list in trackerRoom
 */
public class PrintListPane extends JFrame {

    // EFFECTS: create and display the frame (with the icon)
    public PrintListPane() {
        super("Print amount");
        Container c = this.getContentPane();
        c.setBackground(Color.WHITE);

        ImageIcon startIcon = trackerGUI.createImageIcon("image/bill.png", "Print Icon");
        startIcon.setImage(startIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel icon2 = new JLabel("", startIcon, JLabel.CENTER);
        add(icon2);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JLabel amountList = new JLabel();
        amountList.setText(printList());
        amountList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        c.setLayout(new GridLayout());
        c.add(amountList);
    }

    // MODIFIES: this
    // EFFECTS: print the total amount list in trackerRoom
    public String printList() {
        TrackerGUI.amList = TrackerGUI.trackerRoom.getAmountTrackerList();
        String category;
        String list = "<html><body> Initial Amount: " + TrackerGUI.trackerRoom.getInitial() + "<br>" + "<body><html>";

        for (AmountTracker am : TrackerGUI.amList) {
            category = am.getCategory();
            if (!am.isExpense(AmountTracker.isExpenseS(category))) {
                list += "<html><body> " + am.getCategory() + " " + am.getAmount() + "<br>" + "<body><html>";

            } else {
                list += "<html><body> " + am.getCategory() + " -" + am.getAmount() + "<br>" + "<body><html>";
            }
        }
        return list;
    }
}
