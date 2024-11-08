package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void sendKeysAdvance(WebElement e, String input) throws InterruptedException
     {
        e.click();
        e.sendKeys(input);
        Thread.sleep(3000);
     }

     public static void clickRadioOption(List<WebElement> e, String input) throws InterruptedException
     {
        
        for(WebElement el:e)
        {
            if(el.getText().contains(input))
            el.click();
            Thread.sleep(2000);

        }
        
     }
}
