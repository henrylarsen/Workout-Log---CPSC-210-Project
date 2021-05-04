package ui.gui.tools;

import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tool that adds exercises to a workout
public class AddExerciseTool extends Tool {
    private JTextField textFieldName;
    private JTextField textFieldSets;
    private JTextField textFieldReps;
    private JTextField textFieldWeight;
    private JTextField textFieldTime;
    private JTextField textFieldNumber;
    private JLabel name;
    private JLabel sets;
    private JLabel reps;
    private JLabel weight;
    private JLabel time;
    private JLabel number;


    // EFFECTS: constructor for addExerciseTool
    public AddExerciseTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
    }

    // EFFECTS: creates the buttons and text field for exercise inputs
    public void setLabelsAndText(JComponent parent) {

        name = new JLabel("Name: ");
        parent.add(name);
        textFieldName = new JTextField(0);
        parent.add(textFieldName);

        sets = new JLabel("Number of sets: ");
        parent.add(sets);
        textFieldSets = new JTextField(0);
        parent.add(textFieldSets);

        reps = new JLabel("Number of reps: ");
        parent.add(reps);
        textFieldReps = new JTextField(0);
        parent.add(textFieldReps);

        weight = new JLabel("Weight: ");
        parent.add(weight);
        textFieldWeight = new JTextField(0);
        parent.add(textFieldWeight);

        time = new JLabel("Time: ");
        parent.add(time);
        textFieldTime = new JTextField(0);
        parent.add(textFieldTime);

        workoutNumberField(parent);

    }

    // EFFECTS: creates button and text field to select workout
    public void workoutNumberField(JComponent parent) {

        number = new JLabel("Workout number:");
        parent.add(number);
        textFieldNumber = new JTextField(0);
        parent.add(textFieldNumber);
    }

    @Override
    // EFFECTS: creates the appropriate field for tool
    protected void createFields(JComponent parent) {
        setLabelsAndText(parent);

        button = new JButton("Add Exercise");
        button.setEnabled(true);
        addToParent(parent);
    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener((new AddExerciseToolClickHandler()));
    }

    // class for the click handler
    private class AddExerciseToolClickHandler implements ActionListener {

        @Override
        // EFFECTS: implements actions
        public void actionPerformed(ActionEvent e) {
            String name = textFieldName.getText();
            String sets = textFieldSets.getText();
            String reps = textFieldReps.getText();
            String weight = textFieldWeight.getText();
            String time = textFieldTime.getText();
            String number = textFieldNumber.getText();
            workoutListGUI.addExercise(name, sets, reps, weight, time, number);
            textFieldName.setText(null);
            textFieldSets.setText(null);
            textFieldReps.setText(null);
            textFieldWeight.setText(null);
            textFieldTime.setText(null);
            textFieldNumber.setText(null);

        }
    }
}
