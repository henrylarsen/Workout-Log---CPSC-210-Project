package persistence;

import org.json.JSONObject;

// CITATION: Based on JsonSerializationDemo Writable Interface
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
