import java.awt.Desktop;
import java.net.URI;
import java.io.IOException;

/**
 * AutomationHandler Class
 * Handles low-level OS operations and web integration.
 * Developed by: Mohib & Ali
 */
public class AutomationHandler {

    /**
     * Opens a specific URL in the system's default web browser.
     */
    public void openBrowser(String url) {
        try {
            // Check if Desktop is supported by the OS
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url)); // Action: Open URL
            } else {
                System.out.println("[ERROR] Desktop browsing is not supported on this system.");
            }
        } catch (Exception e) {
            System.err.println("[EXCEPTION] Error while opening browser: " + e.getMessage());
        }
    }

    /**
     * Launches a local Windows application like notepad.exe.
     */
    public void launchApp(String appName) {
        try {
            // Executing system-level commands via Runtime
            Runtime.getRuntime().exec(appName); 
        } catch (IOException e) {
            System.err.println("[EXCEPTION] Failed to launch authentication app: " + appName);
        }
    }

    /**
     * WhatsApp Automation Module (Selenium Placeholder).
     */
    public void sendWhatsAppMessage(String contactName, String message) {
        // Log to indicate development status
        System.out.println("[LOG] WhatsApp module for " + contactName + " is currently in development...");
    }
}