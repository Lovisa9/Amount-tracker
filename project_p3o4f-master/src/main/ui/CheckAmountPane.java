package ui;

import javax.swing.*;
import java.awt.*;

import static ui.TrackerGUI.*;

/*
 * Represents the check amount frame with a related image,
 * check the total, income, and expense amount in trackerRoom
 */
public class CheckAmountPane extends JFrame {
    private JPanel panel;

    // EFFECTS: create and display the frame
    public CheckAmountPane() {
        super("Check amount");
        panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        checkAmount();
    }

    // MODIFIES: this
    // EFFECTS: check the total, income, and expense amount in trackerRoom
    public void checkAmount() {
        double totalAmount;
        double income;
        double expense;
        String checklist = "";
        JLabel check = new JLabel();

        if (!TrackerGUI.isLoad()) {
            totalAmount = amountList.getTotalAmount();
            income = amountList.getIncomeAmount();
            expense = amountList.getExpenseAmount();
        } else {
            totalAmount = trackerRoom.getB();
            income = trackerRoom.getIncome();
            expense = trackerRoom.getExpense();
        }
        checklist += "<html><body>Your total amount is: " + totalAmount + ".<br>" + "<body><html>";
        checklist += "<html><body>Your total income amount is: " + income + ".<br>" + "<body><html>";
        checklist += "<html><body>Your total expense amount is: " + expense + ".<br>" + "<body><html>";
        check.setText(checklist);

        addIcon();
        check.setFont(new Font("Monospaced", Font.PLAIN, 13));

        panel.add(check);
    }

    //EFFECTS: add an icon into the frame
    private void addIcon() {
        Container c = this.getContentPane();
        ImageIcon startIcon = trackerGUI.createImageIcon("image/clipboard.png", "Check Icon");
        startIcon.setImage(startIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel icon3 = new JLabel("", startIcon, JLabel.CENTER);
        c.add(icon3);
        c.setBackground(Color.white);
    }
}

