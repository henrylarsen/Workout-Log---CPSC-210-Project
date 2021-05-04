package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for Workout
class WorkoutTest {
    private Workout testWorkout;

    @BeforeEach
    void runBefore() {
        testWorkout = new Workout("Workout A");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWorkout.getLength());
        assertEquals(0, testWorkout.getTotalTime());
        assertEquals("Workout A", testWorkout.getName());
    }

    @Test
    void testAddWorkout() {
        Exercise ex = new Exercise("A", 1, 2,12, 1);
        testWorkout.addExercise(ex);
        assertEquals(1, testWorkout.getLength());
    }

    @Test
    void testRemoveWorkoutOne() {
        Exercise ex = new Exercise("A", 1, 2,12, 1);
        testWorkout.addExercise(ex);
        assertEquals(1, testWorkout.getLength());
        testWorkout.removeExercise("A");
        assertEquals(0, testWorkout.getLength());
    }

    @Test
    void testRemoveWorkoutMultiple() {
        Exercise ex1 = new Exercise("A", 1, 2,12, 1);
        Exercise ex2 = new Exercise("B", 2, 2,12, 1);
        Exercise ex3 = new Exercise("A", 5, 2,12, 1);
        testWorkout.addExercise(ex1);
        testWorkout.addExercise(ex2);
        testWorkout.addExercise(ex3);
        assertEquals(3, testWorkout.getLength());
        testWorkout.removeExercise("A");
        assertEquals(1,testWorkout.getLength());
    }

    @Test
    void testRemoveWorkoutNone() {
        Exercise ex1 = new Exercise("A", 1, 2,12, 1);
        Exercise ex2 = new Exercise("B", 2, 2,12, 1);
        Exercise ex3 = new Exercise("A", 5, 2,12, 1);
        testWorkout.addExercise(ex1);
        testWorkout.addExercise(ex2);
        testWorkout.addExercise(ex3);
        assertEquals(3, testWorkout.getLength());
        testWorkout.removeExercise("C");
        assertEquals(3,testWorkout.getLength());
    }

    @Test
    void testAddTime() {
        testWorkout.addTime(30);
        assertEquals(30, testWorkout.getTotalTime());
    }

    @Test
    void testRemoveTime() {
        testWorkout.addTime(30);
        testWorkout.removeTime(10);
        assertEquals(20, testWorkout.getTotalTime());
        testWorkout.removeTime(30);
        assertEquals(0, testWorkout.getTotalTime());
    }

    @Test
    void testSetTime() {
        testWorkout.addTime(30);
        testWorkout.setTime(50);
        assertEquals(50, testWorkout.getTotalTime());
    }

    @Test
    void testSetName() {
        testWorkout.setName("Arms");
        assertEquals("Arms",testWorkout.getName());
    }

    @Test
    void testGetExercises() {
        Exercise ex1 = new Exercise("A", 1, 2,12, 1);
        Exercise ex2 = new Exercise("B", 2, 2,12, 1);
        testWorkout.addExercise(ex1);
        testWorkout.addExercise(ex2);
        assertEquals(2, testWorkout.getExercises().size());
        assertEquals("A", testWorkout.getExercises().get(0).getName());
    }

}