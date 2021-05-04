package persistence;

import model.Exercise;
import model.ListOfWorkouts;
import model.Workout;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// tests for JsonWriter
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfWorkouts low = new ListOfWorkouts();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfWorkouts() {
        try {
            ListOfWorkouts low = new ListOfWorkouts();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfWorkouts.json");
            writer.open();
            writer.write(low);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfWorkouts.json");
            low = reader.read();
            assertEquals(0, low.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfWorkouts low = new ListOfWorkouts();
            Workout w1 = new Workout("Chest");
            w1.addExercise(new Exercise("bench press", 4, 8, 165, 0));
            w1.addExercise(new Exercise("push-ups", 5, 0, 25, 45));
            Workout w2 = new Workout("Legs");
            w2.addExercise(new Exercise("back squat", 4, 10, 185, 0));
            w2.addExercise(new Exercise("dead-lift", 3, 8, 205, 0));
            w2.addExercise(new Exercise("lunges", 4, 15, 50, 0));
            low.addWorkout(w1);
            low.addWorkout(w2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(low);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            low = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }

}
