package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a workout that is comprised of exercises, has a name, and has a total time
public class Workout implements Writable {
    private ArrayList<Exercise> exerciseList;      // list of exercises in workout
    private double totalTime;                      // approximate time to complete workout (minutes)
    private String name;                           // name of workout, names cannot be used twice

    // EFFECTS: constructs an empty workout with no time
    public Workout(String s) {
        exerciseList = new ArrayList<>();
        totalTime = 0;
        name = s;

    }

    // MODIFIES: this
    // EFFECTS: adds an exercise to the workout (at the back)
    public void addExercise(Exercise e) {
        exerciseList.add(e);
    }

    // REQUIRES: s is non-zero in length
    // MODIFIES: this
    // EFFECTS: removes all workouts with s as name, if none have s as name, do nothing
    public void removeExercise(String s) {
        exerciseList.removeIf(e -> (s.equals(e.getName())));
    }

    // REQUIRES: time is >= 0
    // EFFECTS: add time to totalTime
    public void addTime(double time) {
        totalTime = totalTime + time;
    }

    // REQUIRES: time is >= 0
    // EFFECTS: set totalTime
    public void setTime(double time) {
        totalTime = time;
    }

    // REQUIRES: time is >= 0
    // EFFECTS: subtracts time from totalTime, if difference is < 0, set totalTime to 0
    public void removeTime(double time) {
        if (totalTime - time < 0) {
            totalTime = 0;
        } else {
            totalTime = totalTime - time;
        }
    }

    // EFFECTS: returns totalTime
    public double getTotalTime() {
        return totalTime;
    }

    // EFFECTS: returns length of exerciseList
    public int getLength() {
        return exerciseList.size();
    }

    // MODIFIES: this
    // EFFECTS: changes name to s
    public void setName(String s) {
        name = s;
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns exerciseList
    public ArrayList<Exercise> getExercises() {
        return exerciseList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("exercises", exercisesToJson());
        return json;
    }

    private JSONArray exercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exerciseList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
