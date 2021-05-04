package ui.gui.tools;

import model.ListOfWorkouts;
import persistence.JsonWriter;
import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Tool to save workout to file
public class SaveTool extends Tool {
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;

    // EFFECTS: constructs Tool
    public SaveTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
    }

    @Override
    // EFFECTS: creates the appropriate field for tool
    protected void createFields(JComponent parent) {
        button = new JButton("Save Workouts to File");
        button.setEnabled(true);
        addToParent(parent);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener((new SaveToolClickHandler()));
    }

    // class for the click handler
    private class SaveToolClickHandler implements ActionListener {
        ListOfWorkouts listOfWorkouts = workoutListGUI.getListOfWorkouts();

        @Override
        // EFFECTS: implements actions
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(listOfWorkouts);
                jsonWriter.close();
                workoutListGUI.setText("Your workouts have been saved to " + JSON_STORE);
            } catch (FileNotFoundException f) {
                workoutListGUI.setText("Unable to write to file: " + JSON_STORE);
            }
        }
    }
}

