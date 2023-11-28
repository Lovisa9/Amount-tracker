package persistence;

import model.AmountTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

//EFFECTS: check if the amountTracker in TrackerRoom is correct (add correct into TrackerRoom)
public class JsonTest {
    public void check(String category, double amount, AmountTracker amountTracker) {
        assertEquals(category, amountTracker.getCategory());
        assertEquals(amount, amountTracker.getAmount());
    }
}
