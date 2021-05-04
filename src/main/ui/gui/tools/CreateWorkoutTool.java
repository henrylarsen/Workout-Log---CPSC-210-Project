package ui.gui.tools;

import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tool to create a new workout
public class CreateWorkoutTool extends Tool {
    private JLabel name;
    private JTextField textField;

    // EFFECTS: constructs Tool
    public CreateWorkoutTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
    }

    // EFFECTS: creates the appropriate field for tool
    @Override
    protected void createFields(JComponent parent) {
        name = new JLabel("Name: ");
        parent.add(name);

        textField = new JTextField(0);
        parent.add(textField);


        button = new JButton("Create workout");
        button.setEnabled(true);
        addToParent(parent);

    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener((new CreateWorkoutToolClickHandler()));
    }

    // class for the click handler
    private class CreateWorkoutToolClickHandler implements ActionListener {

        @Override
        // EFFECTS: implements actions
        public void actionPerformed(ActionEvent e) {
            String name = textField.getText();
            workoutListGUI.addWorkout(name);
            textField.setText(null);

        }
    }
}
