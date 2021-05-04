package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests for Exercise
public class ExerciseTest {
    private Exercise testExercise;

    @BeforeEach
    void runBefore() {
        testExercise = new Exercise("Bench Press", 4, 8,
                200, -1);
    }

    @Test
    void testConstructor() {
        assertEquals("Bench Press", testExercise.getName());
        assertEquals(4, testExercise.getSets());
        assertEquals(8, testExercise.getReps());
        assertEquals(200, testExercise.getWeight());
        assertEquals(0, testExercise.getTime());
    }

    @Test
    void testChangeName() {
        testExercise.changeName("Back Squat");
        assertEquals("Back Squat", testExercise.getName());
    }

    @Test
    void testChangeSets() {
        testExercise.changeSets(3);
        assertEquals(3, testExercise.getSets());
    }

    @Test
    void testChangeReps() {
        testExercise.changeReps(12);
        assertEquals(12, testExercise.getReps());
    }

    @Test
    void testChangeTime() {
        testExercise.changeTime(45);
        assertEquals(45, testExercise.getTime());
    }

    @Test
    void testChangeWeight() {
        testExercise.changeWeight(215);
        assertEquals(215, testExercise.getWeight());
    }
}
