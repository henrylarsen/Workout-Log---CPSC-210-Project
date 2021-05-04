package ui.gui.tools;

import model.ListOfWorkouts;
import persistence.JsonReader;
import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Tool to load workouts from file
public class LoadTool extends Tool {
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";
    private WorkoutListGUI workoutListGUI;

    // EFFECTS: constructs Tool
    public LoadTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
        this.workoutListGUI = workoutListGUI;
    }

    @Override
    // EFFECTS: creates the appropriate field for tool
    protected void createFields(JComponent parent) {
        button = new JButton("Load Workouts from File");
        button.setEnabled(true);
        addToParent(parent);
        jsonReader = new JsonReader(JSON_STORE);
    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener((new LoadToolClickHandler()));
    }

    // class for the click handler
    private class LoadToolClickHandler implements ActionListener {

        @Override
        // EFFECTS: implements actions
        public void actionPerformed(ActionEvent e) {
            try {
                ListOfWorkouts listOfWorkouts = jsonReader.read();
                workoutListGUI.setWorkouts(listOfWorkouts);
                workoutListGUI.setText("Loaded your workouts from " + JSON_STORE);

            } catch (IOException f) {
                workoutListGUI.setText("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}


