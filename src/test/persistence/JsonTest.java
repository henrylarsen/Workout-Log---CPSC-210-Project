package persistence;

import model.Exercise;
import model.Workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests to check workout and exercise parameters
// CITATION: methods and class based off of JsonTest class in JsonSerializationDemo
public class JsonTest {
    // checks if workouts name and length match given
    protected void checkWorkout(String name, int length, Workout workout) {
        assertEquals(name, workout.getName());
        assertEquals(length, workout.getLength());
    }

    // checks if exercise's fields match given
    protected void checkExercise(String name, int sets, int reps, int weight, int time, Exercise exercise) {
        assertEquals(name, exercise.getName());
        assertEquals(sets, exercise.getSets());
        assertEquals(reps, exercise.getReps());
        assertEquals(weight, exercise.getWeight());
        assertEquals(time, exercise.getTime());
    }
}
