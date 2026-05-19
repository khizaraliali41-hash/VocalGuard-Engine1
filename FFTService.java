import org.jtransforms.fft.DoubleFFT_1D;

/**
 * FFTService Class
 * Converts Time-Domain (Audio Bytes) to Frequency-Domain (Hz).
 * Managed by: Khizar Ali Jamshed
 */
public class FFTService {

    /**
     * Calculates the dominant frequency from audio data.
     * @param audioData Raw bytes from the microphone.
     * @return double Frequency in Hz.
     */
    public double calculateFrequency(byte[] audioData) {
        int n = audioData.length;
        double[] fftData = new double[n * 2];

        // 1. Convert bytes to double for math processing
        for (int i = 0; i < n; i++) {
            fftData[i] = audioData[i];
        }

        // 2. Perform FFT
        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        fft.realForward(fftData);

        // 3. Find the peak magnitude (The loudest frequency)
        double maxMag = -1;
        int peakIdx = -1;
        for (int i = 0; i < n / 2; i++) {
            double re = fftData[2 * i];
            double im = fftData[2 * i + 1];
            double mag = Math.sqrt(re * re + im * im);
            if (mag > maxMag) {
                maxMag = mag;
                peakIdx = i;
            }
        }

        // 4. Convert index to Hz (Sample Rate = 16000)
        return (double) peakIdx * 16000 / n;
    }
}
