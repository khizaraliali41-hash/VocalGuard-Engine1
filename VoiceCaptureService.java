import javax.sound.sampled.*;

/**
 * VoiceCaptureService Class
 * Handles real time audio input from the system microphone.
 * Managed by: Ali (Lead Engineer)
 */
public class VoiceCaptureService {

    // Standard Audio Format for Voice Recognition
    private AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
    private TargetDataLine microphone;

    /**
     * Initializes and starts the microphone line.
     */
    public void startListening() {
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);
            microphone.start();
            System.out.println("[SYSTEM]: Microphone is now active...");
        } catch (LineUnavailableException e) {
            System.err.println("[EXCEPTION]: Microphone hardware is busy or not found.");
        }
    }

    /**
     * Captures a small buffer of audio data.
     * @return byte array containing raw audio signal.
     */
    public byte[] captureAudio() {
        byte[] data = new byte[1024];
        int bytesRead = microphone.read(data, 0, data.length);
        return (bytesRead > 0) ? data : null;
    }

    public void stopListening() {
        if (microphone != null) {
            microphone.stop();
            microphone.close();
        }
    }
}
