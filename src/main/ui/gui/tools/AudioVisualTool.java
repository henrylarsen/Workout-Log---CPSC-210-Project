package ui.gui.tools;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import ui.gui.WorkoutListGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// Creates the tool used for the audio/visual component
public class AudioVisualTool extends Tool {
    public static final String JPG = "./data/photos/arnold.jpg";
    public static final String WAV = "./data/audio/spongebob.wav";
    private JFrame frame;
    private ImageIcon icon;
    private JLabel label;

    private AudioPlayer audioPlayer;
    private AudioStream audioStream;
    private InputStream inputStream;

    // EFFECTS: constructs Tool
    public AudioVisualTool(WorkoutListGUI workoutListGUI, JComponent parent) {
        super(workoutListGUI, parent);
    }

    @Override
    // EFFECTS: creates the appropriate field for tool
    protected void createFields(JComponent parent) {
        button = new JButton("CLICK ME!");
        button.setEnabled(true);
        addToParent(parent);
    }

    @Override
    // EFFECTS: creates listener
    protected void addListener() {
        button.addActionListener((new AudioVisualToolClickHandler()));
    }

    // class for the click handler
    private class AudioVisualToolClickHandler implements ActionListener {

        @Override
        // EFFECTS: displays JPG and plays WAV
        public void actionPerformed(ActionEvent e) {
            image();
            music();
        }

        // EFFECTS: displays the image selected
        private void image() {
            frame = new JFrame();
            frame.setMinimumSize(new Dimension(400, 400));
            icon = new ImageIcon(JPG);
            label = new JLabel(icon);
            frame.add(label);
            frame.pack();
            frame.setVisible(true);
        }

        // EFFECTS: plays the selected audio file
        public void music() {
            audioPlayer = AudioPlayer.player;
            ContinuousAudioDataStream loop = null;

            try {
                inputStream = new FileInputStream(WAV);
                audioStream = new AudioStream(inputStream);
                AudioPlayer.player.start(audioStream);

            } catch (IOException error) {
                System.out.print(error.toString());
            }
            audioPlayer.start(loop);
        }


    }
}
