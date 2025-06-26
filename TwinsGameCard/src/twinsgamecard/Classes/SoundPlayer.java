package twinsgamecard.Classes;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException; // Added import
import java.io.File; // For file system paths
import java.net.URL; // If sounds were to be loaded from URLs

public class SoundPlayer {

    // Placeholder paths - these need to be replaced with actual .wav file paths
    // For example, if you have a 'sounds' directory in your project root:
    // private static final String FLIP_SOUND_PATH = "sounds/flip.wav";
    private static final String FLIP_SOUND_PATH = "PLACEHOLDER_FLIP_SOUND.wav";
    private static final String MATCH_SOUND_PATH = "PLACEHOLDER_MATCH_SOUND.wav";
    private static final String MISMATCH_SOUND_PATH = "PLACEHOLDER_MISMATCH_SOUND.wav";
    private static final String WIN_SOUND_PATH = "PLACEHOLDER_WIN_SOUND.wav";

    private static void playSound(String soundFilePath) {
        try {
            // Attempt to load from file system first
            File soundFile = new File(soundFilePath);
            if (!soundFile.exists()) {
                System.err.println("Sound file not found: " + soundFilePath + ". Sound playback skipped.");
                // Optionally, try to load as a resource from classpath if packaged within JAR
                // URL soundURL = SoundPlayer.class.getResource(soundFilePath);
                // if (soundURL == null) {
                //     System.err.println("Sound resource not found in classpath: " + soundFilePath);
                //     return;
                // }
                // AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
                return; // Exit if file not found
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            // If you want to ensure the sound plays before program continues (for short sounds),
            // you might add a listener for STOP event or a short sleep, but usually not needed for game SFX.
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file: " + soundFilePath + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error playing sound " + soundFilePath + ": " + e.getMessage());
            // e.printStackTrace(); // For more detailed debugging
        }
    }

    public static void playFlipSound() {
        playSound(FLIP_SOUND_PATH);
    }

    public static void playMatchSound() {
        playSound(MATCH_SOUND_PATH);
    }

    public static void playMismatchSound() {
        playSound(MISMATCH_SOUND_PATH);
    }

    public static void playGameWinSound() {
        playSound(WIN_SOUND_PATH);
    }
}
