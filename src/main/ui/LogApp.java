package ui;

import model.Exercise;
import model.ListOfWorkouts;
import model.Workout;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// The log application that keeps track of workouts and exercises
public class LogApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private ListOfWorkouts listOfWorkouts;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the log application
    public LogApp() {
        runLog();
    }

    // cite: code modelled off of TellerApp from CPSC 210 library
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLog() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");

    }

    // cite: code modelled off of TellerApp from CPSC 210 library
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "v":
                viewWorkouts();
                break;
            case "c":
                createNewWorkout();
                break;
            case "a":
                addExercise();
                break;
            case "g":
                viewRoutine();
                break;
            case "s":
                saveListOfWorkouts();
                break;
            case "l":
                loadListOfWorkouts();
                break;
            default:
                System.out.println("Selection not valid... ");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes workoutList
    private void init() {
        listOfWorkouts = new ListOfWorkouts();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // cite: code modelled off of TellerApp from CPSC 210 library
    // EFFECTS: displays the list of workouts to user
    private void displayMenu() {
        System.out.println("\nMain Menu - Select From:");
        System.out.println("\t v -> View Workouts");
        System.out.println("\t c -> Create New Workout");
        System.out.println("\t a -> Add Exercise to Workout");
        System.out.println("\t g -> Get Specific Workout");
        System.out.println("\t s -> Save Workout List to File");
        System.out.println("\t l -> Load Workout List from File");
        System.out.println("\t q -> Quit");
    }

    // EFFECTS: displays numbered list of workout names, or if none, "You have no workouts"
    private void viewWorkouts() {
        if (listOfWorkouts.getLength() == 0) {
            System.out.println("You have no workouts.");
        }
        for (int i = 0; i < listOfWorkouts.getLength(); i++) {
            System.out.println((i + 1) + " -> " + listOfWorkouts.getWorkouts().get(i).getName());

        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new workout and adds it to listOfWorkouts
    private void createNewWorkout() {
        System.out.println("Name of Workout: ");
        input.nextLine();
        String name = input.nextLine();
        Workout newWorkout = new Workout(name);
        listOfWorkouts.addWorkout(newWorkout);
    }

    // MODIFIES: this
    // EFFECTS: creates new exercise with given parameters and adds it to the selected workout
    private void addExercise() {
        if (listOfWorkouts.getLength() == 0) {
            System.out.println("You have no workouts.");
        } else {
            System.out.println("Select Workout Number:");
            int num = input.nextInt();
            Workout w1 = listOfWorkouts.getWorkouts().get(num - 1);
            System.out.println("Selected: " + w1.getName());
            System.out.println("Name of Exercise:");
            input.nextLine();
            String name = input.nextLine();
            System.out.println("Number of Sets:");
            int sets = input.nextInt();
            System.out.println("Number of Repetitions:");
            int reps = input.nextInt();
            System.out.println("Weight Used (lbs):");
            int weight = input.nextInt();
            System.out.println("Time to complete (seconds):");
            int time = input.nextInt();
            Exercise e1 = new Exercise(name, sets, reps, weight, time);
            w1.addExercise(e1);
            System.out.println("Exercise has been added.");
        }
    }

    // EFFECTS: prints out all exercises and exercise info in a workout
    private void viewRoutine() {
        System.out.println("What workout would you like to view? (Enter valid number): ");
        int num = input.nextInt() - 1;
        Workout w1 = listOfWorkouts.getWorkouts().get(num);
        if (w1.getLength() == 0) {
            System.out.println("There are no exercises in this workout.");
        } else {
            for (int i = 0; i < w1.getLength(); i++) {
                System.out.println((i + 1) + ")");
                System.out.println(" --- Name: " + w1.getExercises().get(i).getName());
                System.out.println(" --- Sets: " + (w1.getExercises().get(i).getSets()));
                System.out.println(" --- Reps: " + (w1.getExercises().get(i).getReps()));
                System.out.println(" --- Weight: " + (w1.getExercises().get(i).getWeight()));
                System.out.println(" --- Time: " + (w1.getExercises().get(i).getTime()));
            }
        }
    }

    private void saveListOfWorkouts() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfWorkouts);
            jsonWriter.close();
            System.out.println("Your workouts have been saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadListOfWorkouts() {
        try {
            listOfWorkouts = jsonReader.read();
            System.out.println("Loaded your workouts from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
