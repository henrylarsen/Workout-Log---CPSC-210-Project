package model;

import org.json.JSONObject;
import persistence.Writable;

// represents an exercise with a name; and the recommended number of sets, repetition, weight, and time (duration).
public class Exercise implements Writable {
    private String name;    // exercise name
    private int sets;       // number of sets
    private int reps;       // number of repetitions
    private int weight;     // weight for exercise
    private int duration;   // how long to do exercise in seconds

    /*
     * REQUIRES: name, reps, weight, is non-zero length, sets and duration is > 0.
     * EFFECTS: exerciseName is set to name; setsPerformed is set sets;
     * the repsPerformed is set to reps; weightUsed is set to weight;
     * if time >=0 then duration of exercise is set to time, otherwise time is 0.
     */

    public Exercise(String exerciseName, int setsPerformed, int repsPerformed, int weightUsed, int time) {
        name = exerciseName;
        sets = setsPerformed;
        reps = repsPerformed;
        weight = weightUsed;
        if (time >= 0) {
            duration = time;
        } else {
            duration = 0;
        }
    }

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: returns sets
    public int getSets() {
        return sets;
    }

    // EFFECTS: returns reps
    public int getReps() {
        return reps;
    }

    // EFFECTS: return weight
    public int getWeight() {
        return weight;
    }

    // EFFECT: return duration
    public int getTime() {
        return duration;
    }

    // MODIFIES: this
    // EFFECTS: changes name to s
    public void changeName(String s) {
        name = s;
    }

    // MODIFIES: this
    // EFFECTS: changes sets to i
    public void changeSets(int i) {
        sets = i;
    }

    // MODIFIES: this
    // EFFECTS: changes reps to i
    public void changeReps(int i) {
        reps = i;
    }

    // MODIFIES: this
    // EFFECTS: changes weight to i
    public void changeWeight(int i) {
        weight = i;
    }

    // MODIFIES: this
    // EFFECTS: changes time to i
    public void changeTime(int d) {
        duration = d;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("sets", sets);
        json.put("reps", reps);
        json.put("weight", weight);
        json.put("time", duration);
        return json;
    }
}
