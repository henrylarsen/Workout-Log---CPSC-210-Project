package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Exercise;
import model.ListOfWorkouts;
import model.Workout;
import org.json.*;

// Represents a reader that reads ListOfWorkouts from JSON data stored in file
// CITATION: All methods from class are directly taken from, or based on JsonSerializationDemo JsonReader class
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads listOfWorkouts from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfWorkouts read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfWorkouts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listOfWorkouts from JSON object and returns it
    private ListOfWorkouts parseListOfWorkouts(JSONObject jsonObject) {
        ListOfWorkouts low = new ListOfWorkouts();
        addWorkouts(low, jsonObject);
        return low;
    }

    // MODIFIES: low
    // EFFECTS: parses workouts from JSON object and adds them to listOfWorkouts
    private void addWorkouts(ListOfWorkouts low, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List of workouts");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            addWorkout(low, nextWorkout);
        }
    }

    // MODIFIES: low
    // EFFECTS: parses Workout from JSON object and adds it to listOfWorkouts
    private void addWorkout(ListOfWorkouts low, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Workout workout = new Workout(name);
        addExercises(workout, jsonObject);
        low.addWorkout(workout);
    }

    // MODIFIES: w
    // EFFECTS: parses Exercises from JSON object and adds it to Workout
    private void addExercises(Workout w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("exercises");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(w, nextExercise);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses Exercise from JSON object and adds it to Workout
    private void addExercise(Workout w, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");
        int time = jsonObject.getInt("time");
        Exercise exercise = new Exercise(name, sets, reps, weight, time);
        w.addExercise(exercise);
    }
}
