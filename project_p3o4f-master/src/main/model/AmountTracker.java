package model;

import org.json.JSONObject;
import persistence.Writable;

// represent the amountTracker with amount (income or expense) and their categories
public class AmountTracker implements Writable {
    private String category;
    private double amount;

    //EFFECTS: Construct an amountTrack with category and amount
    public AmountTracker(String c, double amount) {
        category = c;
        this.amount = amount;
    }

    //REQUIRES: the string should be one of "FOOD", "SHOPPING",
    // "ENTERTAINMENT", "RENT", "OTHERS_EXPENSE", "SALARY", "SCHOLARSHIP",
    // "OTHERS_INCOME"
    //EFFECTS: return true if the entry is one of "FOOD", "SHOPPING",
    // "ENTERTAINMENT", "OTHERS_INCOME", otherwise return false
    public boolean isExpense(String c) {
        return c == "FOOD" || c == "SHOPPING" || c == "ENTERTAINMENT" || c == "RENT" || c == "OTHERS_EXPENSE";
    }


    //EFFECTS: return String c, if the string is not one of "FOOD", "SHOPPING",
    // "ENTERTAINMENT", "RENT", "OTHERS_EXPENSE", "SALARY", "SCHOLARSHIP",
    // "OTHERS_INCOME" the REQUIRES, return "".
    public static String isExpenseS(String c) {
        if ("FOOD".equals(c)) {
            return "FOOD";
        } else if ("SHOPPING".equals(c)) {
            return "SHOPPING";
        } else if ("ENTERTAINMENT".equals(c)) {
            return "ENTERTAINMENT";
        } else if ("RENT".equals(c)) {
            return "RENT";
        } else if ("OTHERS_EXPENSE".equals(c)) {
            return "OTHERS_EXPENSE";
        } else if ("SALARY".equals(c)) {
            return "SALARY";
        } else if ("SCHOLARSHIP".equals(c)) {
            return "SCHOLARSHIP";
        } else if ("OTHERS_INCOME".equals(c)) {
            return "OTHERS_INCOME";
        }
        return "";
    }

    //getters
    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    //EFFECTS: return this as a json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("amount", amount);
        return json;
    }

}
