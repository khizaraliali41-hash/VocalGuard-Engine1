import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set; // Added for CommandProcessor compatibility

/**
 * ContactRegistry Class (Updated)
 * Manages external data persistence for nicknames and phone numbers.
 * Managed by: Ali & Hamid
 */
public class ContactRegistry {
    private Map<String, String> contacts = new HashMap<>();
    private final String FILE_PATH = "contacts.txt"; 

    public ContactRegistry() {
        loadContactsFromFile();
    }

    private void loadContactsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile(); // Creates file if it doesn't exist
            } catch (IOException e) {
                System.err.println("[ERROR] System cannot initialize storage.");
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    contacts.put(parts[0].toLowerCase().trim(), parts[1].trim());
                }
            }
            System.out.println("[SYSTEM] " + contacts.size() + " contacts synchronized.");
        } catch (IOException e) {
            System.err.println("[ERROR] Data synchronization failed.");
        }
    }

    /**
     * NEW FEATURE: Returns all registered nicknames.
     * This allows the CommandProcessor to loop through names.
     */
    public Set<String> getAllNames() {
        return contacts.keySet();
    }

    public String getNumber(String nickname) {
        return contacts.getOrDefault(nickname.toLowerCase(), null);
    }

    public void addNewContact(String name, String number) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            
            bw.write(name.toLowerCase().trim() + "=" + number.trim());
            bw.newLine();
            contacts.put(name.toLowerCase().trim(), number.trim()); 
            System.out.println("[SUCCESS] Contact '" + name + "' written to database.");
            
        } catch (IOException e) {
            System.err.println("[ERROR] File write permission denied.");
        }
    }
}