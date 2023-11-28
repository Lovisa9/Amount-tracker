package model;

import java.util.LinkedList;

// represent a list of amountTracker
public class AmountTrackerList {
    private double balance;
    private double incomeAmount;
    private double expenseAmount;
    private double initialAmount;
    private String totalAmountList;
    private LinkedList<AmountTracker> amountList;
    private double amount;

    //EFFECTS: create new empty amount list
    public AmountTrackerList() {
        amountList = new LinkedList<>();
    }

    //REQUIRE: initial >= 0
    //MODIFIES: this
    //EFFECTS: set the initial amount for the tracker
    public void setInitialAmount(double initial) {
        initialAmount = initial;
        balance = initial;
    }

    //REQUIRES: amount > 0
    //MODIFIES: this, amountList
    //EFFECTS: if enter income categories type, add the amount to total balance
    // and total income amount, and add into total amount list; if enter expense
    // categories type, subtract the amount from current balance, add the amount
    // to total expense amount,and finally add into total amount list.
    public boolean addAmount(AmountTracker at) {
        double amount = at.getAmount();
        String category = at.getCategory();
        amountList.addLast(at);

        if (!(at.isExpense(category))) {
            this.amount += amount;
            balance += amount;
            incomeAmount += amount;
            printIncome(at);
            return true;
        } else {
            if (balance >= amount) {
                balance -= amount;
                expenseAmount += amount;
                this.amount -= amount;
                printExpense(at);
                return true;
            } else {
                return false;
            }
        }

    }

    //REQUIRE: list is not empty
    //EFFECTS: print the income amount into the total amount list
    public void printIncome(AmountTracker am) {
        String last = totalAmountList;

        if (amountList.size() == 1) {
            totalAmountList = am.isExpenseS(am.getCategory()) + " " + am.getAmount();
        } else {
            for (AmountTracker is : amountList) {
                totalAmountList = last + "\n" + is.isExpenseS(is.getCategory()) + " " + is.getAmount();
            }
        }
    }


    //REQUIRE: list is not empty
    //EFFECTS: print the expense amount into the total amount list
    public void printExpense(AmountTracker am) {
        String last = totalAmountList;

        if (amountList.size() == 1) {
            totalAmountList = am.isExpenseS(am.getCategory()) + " -" + am.getAmount();
        } else {
            for (AmountTracker at : amountList) {
                totalAmountList = last + "\n" + at.isExpenseS(at.getCategory()) + " -" + at.getAmount();
            }
        }
    }

    //EFFECTS: print the total amount list
    public String printTotalList() {
        return totalAmountList;
    }

    //EFFECTS: return true if the list is empty, false otherwise
    public Boolean isEmpty() {
        return amountList.isEmpty();
    }

    //getters
    public double getIncomeAmount() {
        return incomeAmount;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public double getTotalAmount() {
        return balance;
    }

}
