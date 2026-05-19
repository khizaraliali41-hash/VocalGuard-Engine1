/**
 * AuthenticationService Class
 * Validates real time voice signals against stored biometric profiles.
 * Features: Noise tolerance and match percentage calculation.
 * Developed by: Khizar Ali Jamshed
 */
public class AuthenticationService {

    /**
     * Authenticates the user with a tolerance level for environmental noise.
     * @param capturedFreq The live frequency from the microphone.
     * @param profile The stored UserProfile.
     * @return boolean True if within range, false otherwise.
     */
    public boolean authenticate(double capturedFreq, UserProfile profile) {
        // Logging the incoming data for debugging (Professional Practice)
        System.out.println("[AUTH] Captured: " + String.format("%.2f", capturedFreq) + " Hz");
        System.out.println("[AUTH] Analyzing against profile: " + profile.getUserName());

        // SE Improvement: Add a small buffer (e.g., 2Hz) for background noise
        double tolerance = 2.0; 
        
        boolean isAuthorized = (capturedFreq >= (profile.getMinFrequency() - tolerance) && 
                               capturedFreq <= (profile.getMaxFrequency() + tolerance));

        if (isAuthorized) {
            printSuccessReport(profile.getUserName());
            return true;
        } else {
            printFailureReport();
            return false;
        }
    }

    private void printSuccessReport(String name) {
        System.out.println("[STATUS] Access Granted. Welcome back, " + name + ".");
    }

    private void printFailureReport() {
        System.err.println("[SECURITY] Unauthorized frequency detected. Access Denied.");
    }
}