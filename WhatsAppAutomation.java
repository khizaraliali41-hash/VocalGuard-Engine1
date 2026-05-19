import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

/**
 * WhatsAppAutomation Class
 * Automates browser actions using Selenium WebDriver.
 * Managed by: Ali & Mohib
 */
public class WhatsAppAutomation {

    private WebDriver driver;

    public void sendMessage(String phoneNumber, String message) {
        try {
            // 1. Chrome Setup
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
            
            // Professional Touch: User profile save karega taake bar bar QR scan na karna pare
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir=C:/Users/YourUser/AppData/Local/Google/Chrome/User Data/Default");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            // 2. Direct API Link (The Professional Way)
            // Format: https://web.whatsapp.com/send?phone=923001234567&text=Hello
            String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
            String finalUrl = "https://web.whatsapp.com/send?phone=" + phoneNumber + "&text=" + encodedMessage;

            System.out.println("[LOG] Launching WhatsApp Web for: " + phoneNumber);
            driver.get(finalUrl);

            // 3. Waiting & Sending
            // TUDO: Wait for the 'Send' button to appear
            Thread.sleep(10000); // 10 seconds for initial load
            
            WebElement sendButton = driver.findElement(By.xpath("//span[@data-icon='send']"));
            sendButton.click();

            System.out.println("[SUCCESS] Message delivered to destination.");
            
            // Optional: Close browser after sending
            // driver.quit();

        } catch (Exception e) {
            System.err.println("[ERROR] Automation Interrupted: " + e.getMessage());
        }
    }
}