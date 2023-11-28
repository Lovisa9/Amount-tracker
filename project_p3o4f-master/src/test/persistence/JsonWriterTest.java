package persistence;

import model.AmountTracker;
import model.TrackerRoom;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Represents a test for JsonWriter, check for writing data to a file, and use reader
//to get back, and check if it is what we wrote
public class JsonWriterTest extends JsonTest {

    //EFFECTS: test for write an invalid file, IOException should
    // be thrown
    @Test
    void testWriteInvalidFile() {
        try {
            TrackerRoom wr = new TrackerRoom("Tracker");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was not thrown!");
        } catch (IOException e){
            // expected
        }
    }

    //EFFECTS: test writing the empty TrackerRoom, caught the IOException if it was thrown
    @Test
    void testEmptyWriterTrackerRoom() {
        try {
            TrackerRoom tr = new TrackerRoom("Tracker");
            JsonWriter writer = new JsonWriter("./data/testEmptyWriterTrackerRoom.json");
            writer.open();
            writer.write(tr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyWriterTrackerRoom.json");
            tr = reader.read();
            assertEquals("Tracker", tr.getName());
            assertEquals(0, tr.getNumListInRoom());
        } catch (IOException e) {
            fail("Caught IOException which is not expected!");
        }

    }

    //EFFECTS: test writing the general TrackerRoom (contain at least two AmountTrackers in TrackerRoom),
    // caught the IOException if it was thrown
    @Test
    void testGeneralWriterTrackerRoom() {
        //EFFECTS: test writing one AmountTracker in TrackerRoom, caught the IOException if it was thrown
        try {
            TrackerRoom tr = new TrackerRoom("Tracker");
            AmountTracker am1 = new AmountTracker("SHOPPING", 200);
            AmountTracker am2 = new AmountTracker("SCHOLARSHIP", 1000);

            tr.addAmountTrackers(am1);
            tr.addAmountTrackers(am2);
            JsonWriter writer = new JsonWriter("./data/testGeneralWriterTrackerRoomTwoList.json");
            tr.setInitial(1000);
            writer.open();
            writer.write(tr);
            writer.close();

            //EFFECTS: test writing two AmountTracker in TrackerRoom, caught the IOException if it was thrown
            JsonReader reader = new JsonReader("./data/testGeneralWriterTrackerRoomTwoList.json");
            tr = reader.read();
            List<AmountTracker> trackerList = tr.getAmountTrackerList();
            assertEquals("Tracker", tr.getName());
            assertEquals(2, tr.getNumListInRoom());
            assertEquals(1000, tr.getInitial());
            tr.setInitialAmount(1000);
            check("SHOPPING", 200, trackerList.get(0));
            check("SCHOLARSHIP", 1000, trackerList.get(1));
        } catch (IOException e) {
            fail("Caught IOException which is not expected!");
        }
    }


}
