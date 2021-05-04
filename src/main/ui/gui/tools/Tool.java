package ui.gui.tools;

import ui.gui.WorkoutListGUI;

import javax.swing.*;

// Abstract Tool class
// CITE: some code inspired DrawingPlayer
public abstract class Tool {
    protected JButton button;
    protected WorkoutListGUI workoutListGUI;
    private boolean active;

    // EFFECTS: constructs tools
    public Tool(WorkoutListGUI workoutListGUI, JComponent parent) {
        this.workoutListGUI = workoutListGUI;
        createFields(parent);
        active = false;
        addListener();
    }

    // EFFECTS: creates the appropriate field for tool
    protected abstract void createFields(JComponent parent);

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
