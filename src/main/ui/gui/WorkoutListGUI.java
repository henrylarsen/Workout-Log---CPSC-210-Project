package ui.gui;

import model.Exercise;
import model.ListOfWorkouts;
import model.Workout;
import ui.gui.tools.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

// Class for the GUI
public class WorkoutListGUI extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 400;

    public ListOfWorkouts listOfWorkouts;

    // Main areas
    public JPanel textPanel;
    public JPanel toolPanel;
    public JTextArea textArea;
    public JLabel name;

    // Tools
    public List<Tool> tools;
    public CreateWorkoutTool createWorkoutTool;
    public AddExerciseTool addExerciseTool;
    public ViewRoutineTool viewRoutineTool;
    public SaveTool saveTool;
    public LoadTool loadTool;
    public ViewWorkoutsTool viewWorkoutsTool;
    public AudioVisualTool audioVisualTool;

    // Font for text
    public Font textAreaFont;

    // EFFECTS: initiates all of the fields and graphics for GUI
    public WorkoutListGUI() {
        super("Workout List");
        initializeFields();
        initializeGraphics();
    }

    // EFFECTS: initializes the tools and listOfWorkouts
    private void initializeFields() {
        tools = new ArrayList<>();
        listOfWorkouts = new ListOfWorkouts();
    }

    // MODIFIES: this
    // EFFECTS: initializes graphics for GUI, including tools
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeToolPanel();
        initializeTextPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("WorkoutListGUI");
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates all tools and adds them to toolPanel, creates graphics
    private void initializeToolPanel() {
        toolPanel = new JPanel();
        toolPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        toolPanel.setLayout(new GridLayout(0,1));
        toolPanel.setSize(new Dimension(0, 0));
        add(toolPanel, BorderLayout.EAST);

        JPanel createWorkoutPanel = new JPanel();
        makeCreateWorkoutPanel(createWorkoutPanel);
        toolPanel.add(createWorkoutPanel);

        JPanel addExercisePanel = new JPanel();
        makeAddExercisePanel(addExercisePanel);
        toolPanel.add(addExercisePanel);

        JPanel viewPanel = new JPanel();
        makeViewPanel(viewPanel);
        toolPanel.add(viewPanel);

        JPanel saveLoadPanel = new JPanel();
        makeSaveLoadPanel(saveLoadPanel);
        toolPanel.add(saveLoadPanel);

        JPanel audioVisualPanel = new JPanel();
        makeAudioVisualPanel(audioVisualPanel);
        toolPanel.add(audioVisualPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates panel for CreateWorkoutTool
    private void makeCreateWorkoutPanel(JPanel createWorkoutPanel) {
        createWorkoutPanel.setLayout(new GridLayout());
        createWorkoutPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
                "Create a workout:"), BorderFactory.createEmptyBorder(10,10,10,10)));

        createWorkoutTool = new CreateWorkoutTool(this, createWorkoutPanel);
        tools.add(createWorkoutTool);
    }

    // MODIFIES: this
    // EFFECTS: creates panel for addExerciseTool
    private void makeAddExercisePanel(JPanel addExercisePanel) {
        addExercisePanel.setLayout(new GridLayout(4, 4));
        addExercisePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
                "Create an Exercise:"), BorderFactory.createEmptyBorder(10,10,10,10)));

        addExerciseTool = new AddExerciseTool(this, addExercisePanel);
        tools.add(addExerciseTool);
    }

    // MODIFIES: this
    // EFFECTS: creates panel for ViewTool
    private void makeViewPanel(JPanel viewPanel) {
        viewPanel.setLayout(new GridLayout(2, 0));
        viewPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
                "Change View:"), BorderFactory.createEmptyBorder(10,10,10,10)));

        viewRoutineTool = new ViewRoutineTool(this, viewPanel);
        tools.add(viewRoutineTool);

        viewWorkoutsTool = new ViewWorkoutsTool(this, viewPanel);
        tools.add(viewWorkoutsTool);
    }

    // MODIFIES: this
    // EFFECTS: creates shared panel for SaveTool and LoadTool
    private void makeSaveLoadPanel(JPanel saveLoadPanel) {
        saveLoadPanel.setLayout(new GridLayout(2, 0));
        saveLoadPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
                "Save or load file:"), BorderFactory.createEmptyBorder(10,10,10,10)));

        loadTool = new LoadTool(this, saveLoadPanel);
        tools.add(loadTool);

        saveTool = new SaveTool(this, saveLoadPanel);
        tools.add(loadTool);
    }

    // MODIFIES: this
    // EFFECTS: creates panel for AudioVisualTool
    private void makeAudioVisualPanel(JPanel audioVisualPanel) {
        audioVisualPanel.setLayout(new GridLayout());
        audioVisualPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(
                "Click button if you've completed a workout!"),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        audioVisualTool = new AudioVisualTool(this, audioVisualPanel);
        tools.add(audioVisualTool);
    }

    // MODIFIES: this
    // EFFECTS: initializes the text panel used for display
    private void initializeTextPanel() {
        textPanel = new JPanel();

        textPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        textPanel.setLayout(new GridLayout(1,0));
        textPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));

        add(textPanel, BorderLayout.WEST);
        textPanel.setBackground(new Color(0xC299EA));

        textAreaFont = new Font("Times New Roman", Font.PLAIN, 20);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(textAreaFont);
        textPanel.add(textArea);

        textArea.setText("Welcome to the your workout room!");

    }

    // MODIFIES: listOfWorkouts, this
    // EFFECTS: adds workout to listOfWorkouts, sets display to all workouts
    public void addWorkout(String name) {
        Workout w = new Workout(name);
        listOfWorkouts.addWorkout(w);
        setAllWorkoutsDisplay();
    }

    // MODIFIES: this, textArea
    // EFFECTS: sets the textPanel to display a list of all of the workouts
    public void setAllWorkoutsDisplay() {
        if (listOfWorkouts.getLength() == 0) {
            textArea.setText("You have no workouts.");
        }
        textArea.setText("Your list of workouts: \n\n");
        for (int i = 0; i < listOfWorkouts.getLength(); i++) {
            String s = textArea.getText();
            textArea.setText(s + (i + 1) + ") " + listOfWorkouts.getWorkouts().get(i).getName() + "\r\n");
        }
    }

    // REQUIRES: Strings for sets, reps, weight, and time are natural numbers.
    //           String for number is Natural number that <= highest workout number
    // MODIFIES: listOfWorkouts, this
    // EFFECTS: adds an exercise to selected workout with given fields.
    //          sets the textArea to display the routine of workout that exercise was added to
    public void addExercise(String name, String sets, String reps, String weight, String time, String number) {
        int number1 = parseInt(number);
        Workout w1 = listOfWorkouts.getWorkouts().get(number1 - 1);
        w1.addExercise(new Exercise(name, parseInt(sets), parseInt(reps), parseInt(weight), parseInt(time)));
        viewRoutine(number1);
    }

    // EFFECTS: returns listOfWorkouts
    public ListOfWorkouts getListOfWorkouts() {
        return listOfWorkouts;
    }

    // REQUIRES: number <= the total amount of workouts
    // MODIFIES: this
    // EFFECTS: sets display to view the workout of given number
    public void viewRoutine(int number) {
        int num = number - 1;
        Workout w1 = listOfWorkouts.getWorkouts().get(num);
        if (w1.getLength() == 0) {
            textArea.setText("There are no exercises in this workout.");
        } else {
            textArea.setText(number + ") " + w1.getName() + ":\n\n");
            for (int i = 0; i < w1.getLength(); i++) {
                String s = textArea.getText();

                String workoutNumber = ((i + 1) + ". ");
                String name = (w1.getExercises().get(i).getName() + "\n");
                String sets = (" --- Sets: " + (w1.getExercises().get(i).getSets()) + "\n");
                String reps = (" --- Reps: " + (w1.getExercises().get(i).getReps()) + "\n");
                String weight = (" --- Weight: " + (w1.getExercises().get(i).getWeight()) + "\n");
                String time = (" --- Time: " + (w1.getExercises().get(i).getTime()) + "\n");

                textArea.setText(s + workoutNumber + name + sets + reps + weight + time + "\n");
            }
        }
    }

    // MODIFIES: listOfWorkouts, this
    // EFFECTS: sets listOfWorkouts to given low
    public void setWorkouts(ListOfWorkouts low) {
        listOfWorkouts = low;
    }

    // MODIFIES: textArea, this
    // EFFECTS: sets the textArea to given string
    public void setText(String s) {
        textArea.setText(s);
    }
}
