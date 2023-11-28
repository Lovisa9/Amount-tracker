package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Methods taken from JSONReader class in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads TrackerRoom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // The method taken from JSONReader class in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads TrackerRoom from file and returns it, throws IOException
    // if an error occurs reading data from file
    public TrackerRoom read() throws IOException {
        String jsonFile = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonFile);
        return parseTrackerRoom(jsonObject);
    }

    // The method taken from JSONReader class in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // The method taken from JSONReader class in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: parses TrackerRoom from JSON object and returns it
    private TrackerRoom parseTrackerRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        TrackerRoom tr = new TrackerRoom(name);
        addAmountTrackers(tr, jsonObject);
        return tr;
    }

    // The method taken from JSONReader class in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: tr
    // EFFECTS: parses AmountTrackers from JSON object and adds them to TrackerRoom
    private void addAmountTrackers(TrackerRoom tr, JSONObject jsonObject) {
        JSONArray amountTrackers = jsonObject.getJSONArray("AmountTrackerList");
        double initial = jsonObject.getDouble("InitialAmount");
        tr.setInitial(initial);
        for (Object json : amountTrackers) {
            JSONObject next = (JSONObject) json;
            addAmountTracker(tr, next);
        }
    }

    // The method taken from JSONReader class in:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: tr
    // EFFECTS: parses AmountTracker from JSON object and adds it to TrackerRoom
    private void addAmountTracker(TrackerRoom tr, JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        double amount = jsonObject.getDouble("amount");

        AmountTrackerList amountTrackers = new AmountTrackerList();
        AmountTracker am = new AmountTracker(category, amount);
        amountTrackers.addAmount(am);

        tr.addAmountTrackers(am);
    }
}
