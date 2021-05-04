package ui.gui.tools;

import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// tool used to view workouts
public class ViewWorkoutsTool extends Tool {

    // EFFECTS: constructs Tool
    public ViewWorkoutsTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
    }

    @Override
    // EFFECTS: creates the appropriate field for tool
    protected void createFields(JComponent parent) {
        button = new JButton("View all workouts:");
        button.setEnabled(true);
        addToParent(parent);

    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener(new ViewWorkoutToolClickHandler());
    }

    // class for the click handler
    private class ViewWorkoutToolClickHandler implements ActionListener {

        @Override
        // EFFECTS: implements actions
        public void actionPerformed(ActionEvent e) {
            workoutListGUI.setAllWorkoutsDisplay();

        }
    }
}
