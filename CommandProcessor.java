import java.util.Set;

/**
 * CommandProcessor Class (V2.0 Intelligence Update)
 * Interprets user intent from bilingual speech.
 * Now supports Dynamic Voice to File Registration & WhatsApp Automation.
 * Managed by: Hamid & Ali
 */
public class CommandProcessor {

    private AutomationHandler automation = new AutomationHandler();
    private ContactRegistry registry = new ContactRegistry();
    private WhatsAppAutomation wa = new WhatsAppAutomation();

    public void process(String rawInput) {
        if (rawInput == null || rawInput.isEmpty()) return;

        String input = rawInput.toLowerCase().trim();

        // 1. DYNAMIC CONTACT REGISTRATION (New Feature)
        // Command: "save number of [name] as [number]"
        if (input.startsWith("save number of") || input.contains("save karo")) {
            handleSaveCommand(input);
        }

        // 2. WHATSAPP AUTOMATION (New Feature)
        // Command: "dayan ko message karo" or "message mohib"
        else if (input.contains("message") || input.contains("msg") || input.contains("karo")) {
            handleWhatsAppMessage(input);
        }

        // 3. WEB AUTOMATION (Existing)
        else if (isGeminiCommand(input)) {
            executeGeminiAction();
        } 
        else if (isYouTubeCommand(input)) {
            executeYouTubeAction(input);
        }

        // 4. PERSONAL PRODUCTIVITY (Existing)
        else if (isNotebookCommand(input)) {
            executeNotebookAction();
        }

        // 5. SYSTEM INFORMATION (Existing)
        else if (input.contains("time") || input.contains("waqt")) {
            System.out.println("TUDO: Current system time is " + java.time.LocalTime.now());
        }

        // 6. DEFAULT FALLBACK
        else {
            handleUnknown(input);
        }
    }

    // --- NEW LOGIC METHODS ---

    private void handleSaveCommand(String input) {
        try {
            // Logic to split name and number: "save number of Zayn as 0321..."
            String name = input.substring(input.indexOf("of") + 3, input.indexOf("as")).trim();
            String number = input.substring(input.indexOf("as") + 3).trim();

            registry.addNewContact(name, number);
            System.out.println("TUDO: Profile updated. " + name + " is now in our secure registry.");
        } catch (Exception e) {
            System.err.println("TUDO: Format error. Use: 'Save number of [Name] as [Number]'");
        }
    }

    private void handleWhatsAppMessage(String input) {
        boolean contactFound = false;
        Set<String> savedNames = registry.getAllNames();

        for (String name : savedNames) {
            if (input.contains(name)) {
                String number = registry.getNumber(name);
                System.out.println("TUDO: Contact " + name + " identified. Initiating WhatsApp...");
                
                // You can add logic here to extract the actual message from input
                String defaultMsg = "Asalam-o-Alaikum, this is TUDO (Ali's Voice Engine).";
                wa.sendMessage(number, defaultMsg);
                
                contactFound = true;
                break;
            }
        }

        if (!contactFound) {
            System.out.println("TUDO: This name is not registered. Should I save it first, Ali?");
        }
    }

    // --- LOGIC GATE METHODS (Existing) ---

    private boolean isGeminiCommand(String input) {
        return input.contains("gemini") || input.contains("gn kholo") || input.contains("open gn");
    }

    private boolean isYouTubeCommand(String input) {
        return input.contains("youtube") && (input.contains("chalao") || input.contains("play") || input.contains("search"));
    }

    private boolean isNotebookCommand(String input) {
        return input.contains("notebook") || input.contains("notepad") || input.contains("likhna shuru karo");
    }

    // --- EXECUTION DISPATCHERS ---

    private void executeGeminiAction() {
        System.out.println("[LOG] Connecting to Gemini AI Framework...");
        automation.openBrowser("https://gemini.google.com");
    }

    private void executeYouTubeAction(String input) {
        System.out.println("[LOG] Scanning YouTube for media content...");
        automation.openBrowser("https://www.youtube.com");
    }

    private void executeNotebookAction() {
        System.out.println("[LOG] Initializing local text editor...");
        automation.launchApp("notepad.exe");
    }

    private void handleUnknown(String input) {
        System.out.println("TUDO: Instruction '" + input + "' not recognized. Please clarify, Ali.");
    }
}