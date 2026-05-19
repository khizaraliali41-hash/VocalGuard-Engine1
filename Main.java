import java.util.Scanner;

/**
 * Main Execution Entry Point
 * Implements biometrically secured boot sequence and command loop.
 * Managed by: Ali, Mohib, Hamid
 */
public class Main {
    public static void main(String[] args) {
        // Initialization of Core Services
        Scanner scanner = new Scanner(System.in);
        CommandProcessor processor = new CommandProcessor();
        AuthenticationService authService = new AuthenticationService();
        
        // Mock Database: Creating Ali's Profile (Name, MinFreq, MaxFreq)
        // In a real scenario, this data comes from your registration module.
        UserProfile aliProfile = new UserProfile("Khizar Ali Jamshed", 110.0, 150.0);

        System.out.println("[BOOT] Initializing VocalGuard Engine...");
        
        // --- SIMULATING VOICE AUTHENTICATION ---
        // Let's assume the mic captured a frequency of 125.5 Hz
        double capturedFrequency = 125.5; 

        if (authService.authenticate(capturedFrequency, aliProfile)) {
            System.out.println("[SYSTEM] Status: ALL MODULES OPERATIONAL");
            System.out.println("\nTUDO: Standby mode active. Waiting for input, Ali.");

            // Continuous Listening Loop (Only starts if Auth is successful)
            while (true) {
                System.out.print("\n>>> ");
                String userInput = scanner.nextLine();

                if (isTerminationCommand(userInput)) {
                    System.out.println("[SHUTDOWN] Terminating session. Safe travels, Ali.");
                    break;
                }

                // Process input through NLU layer
                processor.process(userInput);
            }
        } else {
            System.err.println("[CRITICAL] Identity Verification Failed. Access Denied.");
        }

        scanner.close();
    }

    private static boolean isTerminationCommand(String input) {
        String cmd = input.toLowerCase().trim();
        return cmd.equals("exit") || 
               cmd.equals("shutdown") || 
               cmd.equals("band ho jao") || 
               cmd.equals("khuda hafiz");
    }
}