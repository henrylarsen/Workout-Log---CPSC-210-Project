package ui.gui.tools;

import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

// Tool to view a workouts routine
public class ViewRoutineTool extends Tool {
    private JLabel name;
    private JTextField textField;

    // EFFECTS: constructs Tool
    public ViewRoutineTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
    }

    @Override
    // EFFECTS: creates the appropriate field for tool
    protected void createFields(JComponent parent) {

        name = new JLabel("View a specific workout: ");
        parent.add(name);

        textField = new JTextField(0);
        parent.add(textField);

        button = new JButton("View Specific Workout");
        button.setEnabled(true);
        addToParent(parent);

    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener((new ViewRoutineToolClickHandler()));
    }

    // class for the click handler
    private class ViewRoutineToolClickHandler implements ActionListener {

        @Override
        // EFFECTS: implements actions
        public void actionPerformed(ActionEvent e) {
            int number = parseInt(textField.getText());
            workoutListGUI.viewRoutine(number);
            textField.setText(null);

        }
    }
}
