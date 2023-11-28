package ui;

import model.Event;
import model.EventLog;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new TrackerGUI();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

        // Method gets from:
        // https://stackoverflow.com/questions/20725313/how-to-capture-when-java-application-is-quit
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                printLog(EventLog.getInstance());
            }
        });

    }

    // MODIFIES: this
    // EFFECTS: print the log with its description and the date
    // when doing the event
    public static void printLog(EventLog el) {
        for (Event e : el) {
            System.out.println(e.toString() + "\n");
        }
    }
}
