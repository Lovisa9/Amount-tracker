package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a TrackerRoom which have AmountTrackers
public class TrackerRoom extends AmountTrackerList implements Writable {
    private String name;
    private List<AmountTracker> ams;
    private double balance;
    private double initialAmount;
    private double incomeAmount;
    private double expenseAmount;
    private double amount;

    // EFFECTS: constructs TrackerRoom which contains a name and empty list of AmountTracker
    public TrackerRoom(String name) {
        this.name = name;
        ams = new ArrayList<>();
    }

    //REQUIRE: initial >= 0
    //MODIFIES: this
    //EFFECTS: set the initial amount for the tracker
    public void setInitial(double initial) {
        initialAmount = initial;
        balance = amount + initial;
    }

    //MODIFIES: this
    //EFFECTS: add AmountTrackers to the TrackerRoom
    public void addAmountTrackers(AmountTracker ams) {
        this.ams.add(ams);
        if (!(ams.isExpense(ams.isExpenseS(ams.getCategory())))) {
            this.amount += ams.getAmount();
            balance += ams.getAmount();
            incomeAmount += ams.getAmount();
            EventLog.getInstance().logEvent(new Event("Add income amount "
                    + ams.getCategory() + " " + ams.getAmount() + " to total list of amount!"));
        } else {
            if (balance >= ams.getAmount()) {
                balance -= ams.getAmount();
                expenseAmount += ams.getAmount();
                this.amount -= ams.getAmount();
                EventLog.getInstance().logEvent(new Event("Add expense amount "
                        + ams.getCategory() + " -" + ams.getAmount() + " to total list of amount!"));
            }
        }
    }

    //EFFECTS: returns unmodifiable list of AmountTrackers in this TrackerRoom
    public List<AmountTracker> getAmountTrackerList() {
        EventLog.getInstance().logEvent(new Event("Total amount list was printed!"));
        return Collections.unmodifiableList(ams);
    }

    //EFFECTS: returns number of AmountTracker in this trackerRoom
    public int getNumListInRoom() {
        return ams.size();
    }

    //getters
    public String getName() {
        return name;
    }

    public double getB() {
        return balance;
    }

    public double getIncome() {
        return incomeAmount;
    }

    public double getInitial() {
        return initialAmount;
    }

    public double getExpense() {
        return expenseAmount;
    }

    //EFFECTS: return this as a json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("AmountTrackerList", listToJson());
        json.put("InitialAmount", getInitial());
        return json;
    }

    //EFFECTS: return a JSON array of AmountTrackers in the TrackerRoom
    private JSONArray listToJson() {
        JSONArray amountTrackers = new JSONArray();
        for (AmountTracker t : ams) {
            amountTrackers.put(t.toJson());
        }
        return amountTrackers;
    }
}
