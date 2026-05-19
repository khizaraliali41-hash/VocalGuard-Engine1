import java.io.*;

public class VoiceRegistrationManager {
    private VoiceCaptureService capture = new VoiceCaptureService();
    private FFTService fft = new FFTService();
    private final String PROFILE_FILE = "voice_profile.dat";

    public void startRegistration(String name) {
        System.out.println("\n[REGISTRATION] Ali, please say 'TUDO' for calibration...");
        capture.startListening();
        
        // Let's capture for a moment
        byte[] data = capture.captureAudio();
        double freq = fft.calculateFrequency(data);
        
        capture.stopListening();

        // Saving Ali's frequency to a file (Persistence)
        saveProfile(name, freq);
        System.out.println("[SUCCESS] Profile created! Your baseline: " + String.format("%.2f", freq) + " Hz");
    }

    private void saveProfile(String name, double freq) {
        try (PrintWriter out = new PrintWriter(new FileWriter(PROFILE_FILE))) {
            out.println(name + "=" + freq);
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to save voice profile.");
        }
    }
}