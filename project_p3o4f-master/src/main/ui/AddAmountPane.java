package ui;

import model.AmountTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.TrackerGUI.*;

/*
 * Represents an addAmount frame with their actions, add the amount
 * into the trackerRoom
 */
public class AddAmountPane extends JFrame {
    private JPanel panel;
    private JButton back;
    private JButton incomeButton;
    private JButton expenseButton;
    private double entryAmount;
    protected static boolean isExecuted = false;
    protected static AddAmountPane addAmountPane;

    // EFFECTS: create and display the addAmount frame
    public AddAmountPane() {
        super("Add Amount");
        addAmountPane = this;

        this.setFont(new Font("Serif", Font.PLAIN, 30));
        panel = new JPanel();
        back = new JButton("Back");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAutoRequestFocus(false);

        if (!isExecuted && !isLoad()) {
            initialAction();
            isExecuted = true;
        }
        addAmountButton(this.getContentPane());
    }

    // EFFECTS: add the addAmount button with their images and actions,
    // also add the backButton into the frame
    public void addAmountButton(Container c) {
        ImageIcon icon1 = trackerGUI.createImageIcon("image/profits.png", "Print Icon");
        icon1.setImage(icon1.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        ImageIcon icon2 = trackerGUI.createImageIcon("image/expenses.png", "Print Icon");
        icon2.setImage(icon2.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));

        incomeButton = new JButton("INCOME");
        expenseButton = new JButton("EXPENSE");
        incomeButton.setIcon(icon1);
        expenseButton.setIcon(icon2);
        c.setLayout(new GridBagLayout());

        GridBagConstraints g1 = TrackerGUI.getGridBagConstraints(0.0, 0, 1);
        g1.weighty = 0.3;

        GridBagConstraints g2 = TrackerGUI.getGridBagConstraints(0.1, 0, 2);
        g2.weighty = 0.3;

        GridBagConstraints g3 = TrackerGUI.getGridBagConstraints(0.0, 6, 5);
        g3.gridheight = 1;
        g3.weighty = 0;
        g3.ipady = 10;

        c.add(incomeButton, g1);
        c.add(expenseButton, g2);

        incomeAction();
        expenseAction();
        backButton();
        c.add(back, g3);
    }

    // MODIFIES: this
    // EFFECTS: The action to be taken when user want to enter initial amount
    public void initialAction() {
        JFrame frame = new JFrame("Enter initial amount:");
        JPanel panel = new JPanel();
        JTextField initial = new JTextField();
        initial.setColumns(18);
        panel.add(initial);
        confirmButtonAction(panel, initial, null, "");

        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setLocation(180, 340);
        frame.pack();
        frame.setSize(300, 100);
        frame.setVisible(true);
    }

    // EFFECTS: The action when user clicked the incomeButton
    public void incomeAction() {
        incomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new IncomeAction();
                frame.setVisible(true);
                setVisible(false);
            }
        });
    }

    /*
     * Represents the action to be taken when user want to enter income amount
     */
    private class IncomeAction extends JFrame {
        public IncomeAction() {
            super("INCOME");
            income();
        }

        // EFFECTS: add the buttons for the income frame with their actions
        public void income() {
            JPanel panel = new JPanel();
            JButton salary = new JButton("SALARY");
            JButton scholarship = new JButton("SCHOLARSHIP");
            JButton otherIncome = new JButton("OTHERS_INCOME");

            panel.add(salary);
            panel.add(scholarship);
            panel.add(otherIncome);

            salary.addActionListener(new IncomeAmountEntryAction(salary.getText()));
            scholarship.addActionListener(new IncomeAmountEntryAction(scholarship.getText()));
            otherIncome.addActionListener(new IncomeAmountEntryAction(otherIncome.getText()));

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(panel);
            this.pack();
            this.setSize(400, 300);
            this.setLocationRelativeTo(null);
            this.setLayout(new GridLayout(4, 0));

            backButton();
            panel.add(back);
            panel.setVisible(true);
            setVisible(false);
        }
    }

    //EFFECTS: the action when user clicked the expenseButton
    public void expenseAction() {
        expenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new ExpenseAction();
                frame.setVisible(true);
                setVisible(false);
            }
        });
    }

    /*
     * Represents the action when user want to add the expense amount
     */
    private class ExpenseAction extends JFrame {
        public ExpenseAction() {
            super("EXPENSE");
            expense();
        }

        // EFFECTS: add the buttons for the expense frame with their actions
        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        public void expense() {
            JPanel panel = new JPanel();
            JButton shopping = new JButton("SHOPPING");
            JButton food = new JButton("FOOD");
            JButton otherExpense = new JButton("OTHERS_EXPENSE");
            JButton entertainment = new JButton("ENTERTAINMENT");
            JButton rent = new JButton("RENT");

            panel.add(shopping);
            panel.add(food);
            panel.add(otherExpense);
            panel.add(entertainment);
            panel.add(rent);

            shopping.addActionListener(new ExpenseAmountEntryAction(shopping.getText()));
            food.addActionListener(new ExpenseAmountEntryAction(food.getText()));
            otherExpense.addActionListener(new ExpenseAmountEntryAction(otherExpense.getText()));
            entertainment.addActionListener(new ExpenseAmountEntryAction(entertainment.getText()));
            rent.addActionListener(new ExpenseAmountEntryAction(rent.getText()));

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(panel);
            this.pack();
            this.setSize(400, 300);
            this.setLocationRelativeTo(null);
            this.setLayout(new GridLayout(7, 0));

            backButton();
            panel.add(back);
            panel.setVisible(true);
            setVisible(false);
        }
    }

    /*
     * Represents adding the income amount frame with its action
     */
    private class IncomeAmountEntryAction extends JFrame implements ActionListener {
        private String category;

        //EFFECTS: give a name for the income amount entry frame
        public IncomeAmountEntryAction(String category) {
            super("Enter Amount:");
            this.category = category;
            amountEntry();
        }

        //EFFECTS: the action when clicked the incomeButton
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new IncomeAmountEntryAction(category);
            frame.setVisible(true);
        }

        //EFFECTS: create the income frame with a JTextField that allowed
        // to enter an amount
        public void amountEntry() {
            JPanel panel = new JPanel();
            JTextField text = new JTextField();
            text.setColumns(19);
            panel.add(text);

            confirmButtonAction(panel, text, incomeButton, category);

            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setContentPane(panel);
            this.pack();
            this.setSize(300, 100);
            this.setLocationRelativeTo(null);
        }
    }

    /*
     * Represents adding the expense amount frame with its action
     */
    private class ExpenseAmountEntryAction extends JFrame implements ActionListener {
        private String category;

        //EFFECTS: give a name for the expense amount entry frame
        public ExpenseAmountEntryAction(String category) {
            super("Enter Amount:");
            this.category = category;
            amountEntry();
        }

        //EFFECTS: the action when clicked the expenseButton
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new ExpenseAmountEntryAction(category);
            frame.setVisible(true);
            setVisible(false);
        }

        //EFFECTS: create the expense frame with a JTextField that allowed
        // to enter an amount
        public void amountEntry() {
            JFrame frame = new JFrame();

            JPanel panel = new JPanel();
            frame.setContentPane(panel);
            JTextField text = new JTextField();
            text.setColumns(19);
            panel.add(text);

            confirmButtonAction(panel, text, expenseButton, category);

            this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            this.setContentPane(panel);
            this.pack();
            this.setSize(300, 100);
            this.setLocationRelativeTo(null);
        }
    }

    // EFFECTS: add the confirm button with its action
    public void confirmButtonAction(JPanel panel, JTextField text, JButton button, String category) {
        JFrame frame = new JFrame();
        frame.setContentPane(panel);
        frame.pack();

        JButton confirmButton = new JButton("OK");
        JLabel invalid = new JLabel("Invalid entry! Check again");
        invalid.setForeground(Color.red);
        invalid.setVisible(false);

        JLabel insufficient = new JLabel("Insufficient balance on account... Try again:");
        insufficient.setForeground(Color.blue);
        insufficient.setVisible(false);

        panel.add(invalid);
        panel.add(insufficient);
        panel.add(confirmButton);
        confirmAction(text, button, category, frame, confirmButton, invalid, insufficient);
    }

    // MODIFIES: this
    // EFFECTS: the action when the user clicked OK button (while entering an amount),
    // check for entry valid or not.
    private void confirmAction(JTextField text, JButton button, String category, JFrame frame, JButton confirmButton,
                               JLabel invalid, JLabel insufficient) {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double en = Double.parseDouble(text.getText());
                    text.setText("");
                    text.requestFocus();
                    entryAmount = en;
                    if (button == null) {
                        enterInitial(en);
                    } else if (button.equals(incomeButton) || button.equals(expenseButton)) {
                        AmountTracker am = new AmountTracker(AmountTracker.isExpenseS(category), entryAmount);
                        if (!am.isExpense(category)) {
                            incomeCondition(category, entryAmount);
                        } else {
                            expenseCondition(am, insufficient);
                        }
                    }
                } catch (NumberFormatException n) {
                    invalid.setVisible(true);
                }
            }
        });
    }

    //REQUIRES: entryAmount >= 0
    //MODIFIES: this
    //EFFECTS: enter the initial amount
    public void enterInitial(double entryAmount) {
        amountList.setInitialAmount(entryAmount);
        trackerRoom.setInitial(entryAmount);
        popUpFrame();
    }

    //REQUIRES: entryAmount >= 0
    //MODIFIES: this
    //EFFECTS: the income condition when add amount
    public void incomeCondition(String category, double entryAmount) {
        AmountTracker am = new AmountTracker(AmountTracker.isExpenseS(category), entryAmount);
        amountList.addAmount(am);
        trackerRoom.addAmountTrackers(am);
        popUpFrame();
    }

    //REQUIRES: entryAmount >= 0
    //MODIFIES: this
    //EFFECTS: the expense condition when add amount
    private void expenseCondition(AmountTracker am, JLabel insufficient) {
        if (!TrackerGUI.isLoad()) {
            if (amountList.getTotalAmount() < entryAmount) {
                insufficient.setVisible(true);
            } else {
                amountList.addAmount(am);
                trackerRoom.addAmountTrackers(am);
                popUpFrame();
            }
        } else {
            if (trackerRoom.getB() < entryAmount) {
                insufficient.setVisible(true);
            } else {
                amountList.addAmount(am);
                trackerRoom.addAmountTrackers(am);
                popUpFrame();
            }
        }
    }

    //EFFECTS: the popUp frame displays when the amount is added successfully
    private void popUpFrame() {
        JOptionPane.showMessageDialog(null,
                "Amount added successfully!!",
                "",
                JOptionPane.QUESTION_MESSAGE);
    }

    //EFFECTS: the action when clicked backButton,
    // return to the main frame for this application
    public void backButton() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainGUI.setVisible(true);
            }
        });
    }
}

