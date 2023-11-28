package persistence;

import model.AmountTracker;
import model.TrackerRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Represent tests for JsonReader, test reading data from file
class JsonReaderTest extends JsonTest {

    //EFFECTS: test reading the file does not exist, IOException should
    // be thrown
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TrackerRoom tr = reader.read();
            fail("IOException was not thrown!");
        } catch (IOException e) {
            // expected
        }
    }

    //EFFECTS: test reading the empty TrackerRoom, caught the IOException if it was thrown
    @Test
    void testReaderEmptyTrackerRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTrackerRoom.json");
        try {
            TrackerRoom tr = reader.read();
            tr.setInitial(0);
            assertEquals(0, tr.getInitial());
            assertEquals("Tracker", tr.getName());
            assertEquals(0, tr.getNumListInRoom());
            assertEquals(0, tr.getB());
        } catch (IOException e) {
            fail("Fail to read file");
        }
    }

    //EFFECTS: test reading the general TrackerRoom (at least two AmountTrackers in TrackerRoom),
    // caught the IOException if it was thrown
    @Test
    void testReaderGeneralTrackerRoom() {
        //EFFECTS: test reading one AmountTracker in TrackerRoom, caught the IOException if it was thrown
        JsonReader reader = new JsonReader("./data/testReaderTrackerRoomOneList.json");
        try {
            TrackerRoom tr = reader.read();
            tr.setInitial(1000);
            assertEquals(1000, tr.getInitial());
            tr.setInitialAmount(1000);
            assertEquals("Tracker", tr.getName());
            List<AmountTracker> trackerList = tr.getAmountTrackerList();
            assertEquals(1, trackerList.size());
            check("FOOD", 100, trackerList.get(0));
            assertEquals(0, tr.getIncome());
            assertEquals(100, tr.getExpense());
        } catch (IOException e) {
            fail("Fail to read file");
        }

        //EFFECTS: test reading two AmountTrackers in TrackerRoom, caught the IOException if it was thrown
        JsonReader reader1 = new JsonReader("./data/testReaderTrackerRoomTwoList.json");
        try {
            TrackerRoom tr1 = reader1.read();
            tr1.setInitial(1000);
            assertEquals(1000, tr1.getInitial());
            tr1.setInitialAmount(1000);
            assertEquals("Tracker", tr1.getName());
            List<AmountTracker> amountTrackerLists = tr1.getAmountTrackerList();
            assertEquals(2, tr1.getNumListInRoom());
            check("SALARY", 1000, amountTrackerLists.get(0));
            check("ENTERTAINMENT", 150, amountTrackerLists.get(1));
            assertEquals(1000, tr1.getIncome());
            assertEquals(150, tr1.getExpense());
        } catch (IOException e) {
            fail("Fail to read file");
        }
    }
}