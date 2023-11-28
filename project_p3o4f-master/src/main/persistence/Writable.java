package persistence;

import netscape.javascript.JSObject;
import org.json.JSONObject;

// Interface taken from Writable class in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//EFFECTS: return the Json
public interface Writable {
    JSONObject toJson();
}
