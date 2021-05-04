package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a list of workouts
public class ListOfWorkouts implements Writable {
    private ArrayList<Workout> workoutList;

    // EFFECTS: constructs and empty list of workouts
    public ListOfWorkouts() {
        workoutList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds workout w to workoutList
    public void addWorkout(Workout w) {
        workoutList.add(w);
    }

    // EFFECTS: returns size of workoutList
    public int getLength() {
        return workoutList.size();
    }

    // MODIFIES: this
    // EFFECT: removes workout with name s from workoutList
    public void removeWorkout(String s) {
        workoutList.removeIf(w -> (s.equals(w.getName())));
    }

    // EFFECTS: returns workoutList
    public ArrayList<Workout> getWorkouts() {
        return workoutList;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("List of workouts", workoutsToJson());
        return json;
    }

    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Workout w : workoutList) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}
