package ui;

import model.AmountTracker;
import model.AmountTrackerList;
import model.TrackerRoom;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Represents a Tracker application
public class Tracker extends AmountTrackerList {
    private static final String JSON_STORE = "./data/TrackerRoom.json";
    private TrackerRoom trackerRoom;
    private JsonWriter writer;
    private JsonReader reader;
    private AmountTrackerList amountList;
    double entryAmount;
    Scanner scanner;
    boolean isContinuous = true;
    private boolean isExecuted = false;
    private double initial;
    private boolean isLoad = false;
    private List<AmountTracker> amList;

    //EFFECTS: create a TrackerRoom and start the application
    public Tracker() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        amountList = new AmountTrackerList();
        trackerRoom = new TrackerRoom("Tracker");
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
        amList = new ArrayList<>();
        run();
    }

    //EFFECTS: check if quit the application, if so enter key, quit otherwise
    public void run() {
        String entry;

        while (isContinuous) {
            showMenu();
            entry = scanner.next();
            entry = entry.toLowerCase();
            if (entry.equals("quit")) {
                isContinuous = false;
            } else {
                entryKey(entry);
            }
        }
        System.out.println("\nThank you for using AmountTracker!");
    }

    //EFFECTS: fit the entry into its process
    public void entryKey(String entry) {
        if (entry.equals("add")) {
            addAmountTrackerIntoRoom();
        } else if (entry.equals("print")) {
            printTotalAmountList();
        } else if (entry.equals("check")) {
            checkAmount();
        } else if (entry.equals("save")) {
            saveTrackerRoom();
        } else if (entry.equals("load")) {
            loadTrackerRoom();
        } else {
            System.out.println("Your entry is invalid... Check again!!");
        }
    }

    //EFFECTS: shows the menu of options
    public void showMenu() {
        System.out.println("\nselect from:");
        System.out.println("add <- add amount");
        System.out.println("print <- print the total amount list");
        System.out.println("check <- check total income and expense amount");
        System.out.println("save <- save the TrackerRoom to a file");
        System.out.println("load <- load the TrackerRoom from a file");
        System.out.println("quit <- quit application");
    }

    //EFFECTS: add the amountTracker into TrackerRoom, and user can decide whether
    // set initial amount or not
    public void addAmountTrackerIntoRoom() {
        enterInitial();
        System.out.println("To add a new amount, type INCOME or EXPENSE: ");
        String entry = scanner.next();
        if (entry.equals("INCOME")) {
            incomeCondition();
        } else if (entry.equals("EXPENSE")) {
            expenseCondition();
        } else {
            System.out.println("Your entry is invalid... Check again!!");
        }
    }

    //MODIFIES: this
    //EFFECTS: enter the initial amount
    public void enterInitial() {
        String entry1;
        if (!isExecuted) {
            System.out.println("Do you want to add (first run the application) or change an initial amount?");
            System.out.println("enter 'yes' if you do so, otherwise enter 'no':");
            entry1 = scanner.next();
            while (!(entry1.equals("yes") || entry1.equals("no"))) {
                System.out.println("Your entry is invalid... Check again!!");
                entry1 = scanner.next();
            }
            if (entry1.equals("yes")) {
                System.out.println("Enter your initial amount: ");
                initial = scanner.nextDouble();
                while (initial < 0) {
                    System.out.println("Initial amount should be greater than 0!! Try again:");
                    initial = scanner.nextDouble();
                }
                amountList.setInitialAmount(initial);
                trackerRoom.setInitial(initial);
            } else if (entry1.equals("no")) {
                amountList.setInitialAmount(trackerRoom.getInitial());
            }
            isExecuted = true;
        }
    }

    //MODIFIES: this
    //EFFECTS: the income condition when add amount
    public void incomeCondition() {
        System.out.println("Categories: \n SALARY \n SCHOLARSHIP \n OTHERS_INCOME");
        System.out.println("Add a category for the income amount:");
        String category = scanner.next();
        while (AmountTracker.isExpenseS(category) == null) {
            System.out.println("Your entry is invalid... Check again!!");
            category = scanner.next();
        }

        System.out.println("Add the income amount:");
        entryAmount = scanner.nextDouble();

        AmountTracker am = new AmountTracker(AmountTracker.isExpenseS(category), entryAmount);
        amountList.addAmount(am);
        trackerRoom.addAmountTrackers(am);
    }

    //MODIFIES: this
    //EFFECTS: the expense condition when add amount
    public void expenseCondition() {
        System.out.println("Categories: \n FOOD \n SHOPPING \n ENTERTAINMENT \n RENT \n OTHERS_EXPENSE");
        System.out.println("Add a category of the expense amount:");
        String category = scanner.next();
        while (AmountTracker.isExpenseS(category) == "") {
            System.out.println("Your entry is invalid... Check again!!");
            category = scanner.next();
        }

        System.out.println("Add the expense amount: ");
        entryAmount = scanner.nextDouble();

        if (!isLoad()) {
            while (amountList.getTotalAmount() < entryAmount) {
                System.out.println("Insufficient balance on account... Try again:");
                entryAmount = scanner.nextDouble();
            }
        } else {
            while (trackerRoom.getB() < entryAmount) {
                System.out.println("Insufficient balance on account... Try again:");
                entryAmount = scanner.nextDouble();
            }
        }
        AmountTracker am = new AmountTracker(AmountTracker.isExpenseS(category), entryAmount);
        amountList.addAmount(am);
        trackerRoom.addAmountTrackers(am);
    }

    //MODIFIES: this
    //EFFECTS: check the total, income, and expense amount in trackerRoom
    public void checkAmount() {
        double totalAmount;
        double income;
        double expense;

        if (!isLoad()) {
            totalAmount = amountList.getTotalAmount();
            income = amountList.getIncomeAmount();
            expense = amountList.getExpenseAmount();
        } else {
            totalAmount = trackerRoom.getB();
            income = trackerRoom.getIncome();
            expense = trackerRoom.getExpense();
        }
        System.out.println("Your total amount is: " + totalAmount + ".");
        System.out.println("Your total income amount is " + income + ".");
        System.out.println("Your total expense amount is " + expense + ".");
    }

    //MODIFIERS: this
    //EFFECTS: print the total amount list in trackerRoom
    public void printTotalAmountList() {
        amList = trackerRoom.getAmountTrackerList();
        String category;
        System.out.println("Initial Amount: " + trackerRoom.getInitial());
        for (AmountTracker am : amList) {
            category = am.getCategory();
            if (!am.isExpense(AmountTracker.isExpenseS(category))) {
                System.out.println(am.getCategory() + " " + am.getAmount());

            } else {
                System.out.println(am.getCategory() + " -" + am.getAmount());
            }
        }
    }

    //EFFECTS: save the trackerRoom to file
    public void saveTrackerRoom() {
        try {
            writer.open();
            writer.write(trackerRoom);
            writer.close();
            System.out.println("Saved" + trackerRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: load the trackerRoom from file
    public void loadTrackerRoom() {
        try {
            trackerRoom = reader.read();
            isLoad = true;
            amountList = new AmountTrackerList();
            System.out.println("Loaded " + trackerRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: check if the user load the trackerRoom from file
    public boolean isLoad() {
        return isLoad;
    }
}

