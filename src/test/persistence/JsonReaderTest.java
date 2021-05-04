package persistence;

import model.Exercise;
import model.ListOfWorkouts;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// tests for JsonReader
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfWorkouts low = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfWorkouts() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfWorkouts");
        try {
            ListOfWorkouts low = reader.read();
            assertEquals(0, low.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfWorkouts");
        try {
            ListOfWorkouts low = reader.read();
            List<Workout> workouts = low.getWorkouts();
            assertEquals(2, workouts.size());
            checkWorkout("Chest", 2 , workouts.get(0));
            List<Exercise> exercises1 = workouts.get(0).getExercises();
            checkExercise("bench press", 4, 8, 165, 0, exercises1.get(0));
            checkExercise("push-ups", 5, 0, 25, 45, exercises1.get(1));

            List<Exercise> exercises2 = workouts.get(1).getExercises();
            checkWorkout("Legs", 3, workouts.get(1));
            checkExercise("back squat", 4, 10, 185, 0, exercises2.get(0));
            checkExercise("dead-lift", 3, 8, 205, 0, exercises2.get(1));
            checkExercise("lunges", 4, 15, 50, 0, exercises2.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}