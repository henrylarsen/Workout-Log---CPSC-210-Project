package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// tests for ListOfWorkouts
public class ListOfWorkoutsTest {
    private ListOfWorkouts testLOW;

    @BeforeEach
    void runBefore() {
        testLOW = new ListOfWorkouts();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testLOW.getLength());
    }

    @Test
    void testAddWorkout() {
        Workout wo1 = new Workout("Workout 1");
        testLOW.addWorkout(wo1);
        assertEquals(1, testLOW.getLength());
    }

    @Test
    void testRemoveWorkoutOne() {
        Workout wo1 = new Workout("Workout 1");
        testLOW.addWorkout(wo1);
        assertEquals(1, testLOW.getLength());
        testLOW.removeWorkout("Workout 1");
        assertEquals(0, testLOW.getLength());
    }

    @Test
    void testRemoveWorkoutNone() {
        Workout wo1 = new Workout("Workout 1");
        testLOW.addWorkout(wo1);
        assertEquals(1, testLOW.getLength());
        testLOW.removeWorkout("workout 1");
        assertEquals(1, testLOW.getLength());
    }

    @Test
    void testGetWorkouts() {
        Workout wo1 = new Workout("Workout 1");
        Workout wo2 = new Workout("Workout 2");
        testLOW.addWorkout(wo1);
        testLOW.addWorkout(wo2);
        assertEquals(2, testLOW.getWorkouts().size());
        assertEquals("Workout 2", testLOW.getWorkouts().get(1).getName());
    }
}
