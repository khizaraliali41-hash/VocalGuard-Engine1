/**
 * UserProfile Class (Standard SE Model)
 * Stores authorized user details and includes self-verification logic.
 */
public class UserProfile {
    private String userName;
    private double minFrequency; 
    private double maxFrequency; 

    public UserProfile(String userName, double minFreq, double maxFreq) {
        this.userName = userName;
        this.minFrequency = minFreq;
        this.maxFrequency = maxFreq;
    }

    /**
     * Logic to verify if the voice frequency belongs to this profile.
     * @param frequency The live frequency detected from the microphone.
     * @return boolean Result of authentication.
     */
    public boolean isMatch(double frequency) {
        // Checking if frequency is within the registered range
        return (frequency >= minFrequency && frequency <= maxFrequency);
    }

    // Getters
    public String getUserName() { return userName; }
}