package ePay_CRM.Reusable_Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollHandler {
	private WebDriver driver;
    private JavascriptExecutor js;

	CallListeners event=new CallListeners();

    public ScrollHandler(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // Scroll to the bottom of the page
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scroll to the top of the page
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    // Scroll to a specific element
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Scroll by a specific number of pixels
    public void scrollByPixels(int x, int y) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    // Scroll horizontally to the right
    public void scrollRight(int pixels) {
        js.executeScript("window.scrollBy(arguments[0], 0);", pixels);
    }

    // Scroll horizontally to the left
    public void scrollLeft(int pixels) {
        js.executeScript("window.scrollBy(arguments[0], 0);", -pixels);
    }
    
    // Scroll inside dropdown using JavaScriptExecutor
    public void scrollToDropdownOption(WebElement dropdownElement) {
        // Get current scroll position
        long scrollHeight = (long) js.executeScript("return arguments[0].scrollHeight", dropdownElement);
        
        for (int i = 0; i < scrollHeight; i += 80) { // Slow down by scrolling in small steps
            js.executeScript("arguments[0].scrollTop = arguments[1]", dropdownElement, i);
            
            // ✅ Capture screenshot after each small scroll movement
            event.printSnap("Scrolling inside dropdown  Position " + i);

            try {
                Thread.sleep(500); // Slow down scrolling
            } catch (InterruptedException e) {
                System.out.println("Error in sleep: " + e.getMessage());
            }
        }
    }
 
}
