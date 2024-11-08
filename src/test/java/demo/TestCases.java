
package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        //WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");
        //options.setExperimentalOption("debuggerAddress","127.0.0.1:9222");

       System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException
    {
        System.out.println("Testcase01:Verify the URL of the Google Calendar home page");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        Thread.sleep(3000);
        WebElement nameTextbox=driver.findElement(By.xpath("//div/input[@type='text']"));
        Wrappers.sendKeysAdvance(nameTextbox, "Crio Learner");
        WebElement textboxArea=driver.findElement(By.xpath( "//div/textarea[@class='KHxj8b tL9Q4c']"));
        long epoch=System.currentTimeMillis()/1000;
        Wrappers.sendKeysAdvance(textboxArea, "I want to be the best QA Engineer! "+epoch);

    
        List<WebElement> radioExperience=driver.findElements(By.xpath("//div[@class='nWQGrd zwllIb']//span[@class='aDTYNe snByac OvPDhc OIC90c']"));

        Wrappers.clickRadioOption(radioExperience, "0 - 2");

        List<WebElement> LangCheckbox=driver.findElements(By.xpath("//div[@class='eBFwI']//span[@class='aDTYNe snByac n5vBHf OIC90c']"));
        Wrappers.clickRadioOption(LangCheckbox, "Java");
        //Wrappers.clickRadioOption(LangCheckbox, "Selenium");

        WebElement choose=driver.findElement(By.xpath("(//span[text()='Choose'])[1]"));
        choose.click();
        List<WebElement> salutation=driver.findElements(By.xpath("//div[@jsname='wQNmvb']//span[@class='vRMGwf oJeWuf']"));
        Wrappers.clickRadioOption(salutation, "Mr");

       //WebElement sal=driver.findElement(By.xpath("(//div[@data-value='Mr'])[1]"));
       //sal.click();

       WebElement date=driver.findElement(By.xpath("//input[@type='date']"));

       LocalDate currentDate= LocalDate.now();
       LocalDate currentDateMinus7= currentDate.minusDays(7);
       

       String dateString=String.valueOf(currentDateMinus7);
       System.out.println(dateString);
       date.sendKeys(dateString);

       Thread.sleep(2000);

       WebElement timeHH=driver.findElement(By.xpath("//input[@aria-label='Hour']"));
       WebElement timeMM=driver.findElement(By.xpath("//input[@aria-label='Minute']"));

       timeHH.sendKeys("07");
       timeMM.sendKeys("30");

       WebElement submitButton=driver.findElement(By.xpath("//span[text()='Submit']"));
       submitButton.click();
       Thread.sleep(3000);


       WebElement successMsg=driver.findElement(By.xpath("//div[@class='vHW8K']"));
       WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='vHW8K']")));
      
        System.out.println(successMsg.getText());


    

       Assert.assertEquals(successMsg.getText(), "Thanks for your response, Automation Wizard!");



       

       

        

    }

   
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}