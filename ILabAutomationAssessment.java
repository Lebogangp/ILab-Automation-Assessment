
package ilab.automation.assessment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Lebogang
 */
public class ILabAutomationAssessment
{
    /**
     * @param args the command line arguments
     */
    public static WebDriver driver = null, driverFx = null;
    public static WebElement element;
    public static WebDriverWait wait;
        
    public static void main(String[] args)
    { 
        // setting up the driver for the browser
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lebogang\\Documents\\Selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        //driverFx = new FirefoxDriver();
                
        //navigating to the url and maximizing the window
        String url = "http://www.ilabquality.com";
        driver.get(url);
        
        //driverFx.get(url);
        
        driver.manage().window().maximize();
        System.out.println("[INFO] Successfully  navigated to URL");
        
        //After browser has loaded, this clicks on the Careers link/tab
        if(!clickElementByXpath("//ul[@id='menu-primary-right-menu']//a[text()='CAREERS']")){
            System.out.println("unable to click careers link");
        }
        System.out.println("[INFO] Successfully clicked on Careers");
        //locating  the South Africa element 
        if(!findElementByXpath("//div//a[text()='South Africa']")){
            System.out.println("[ERROR] Failed to find element, South Africa");
        }
        
        //clicking the South Africa element
        if(!clickElementByXpath("//div//a[text()='South Africa']")){
            System.out.println("[] Failed to click South Africa");
        }
        System.out.println("[INFO] Successfully clicked South Africa");
        
        //locate the first job displayed in the list
        if(!findElementByXpath("//div[@class='wpjb-grid-col wpjb-col-40 wpjb-col-title']//span[@class='wpjb-line-major']")){
            System.out.println("[ERROR] Failed to locate the first job");
        }
        System.out.println("[INFO] First job on list found");
        
        //click the first job
        if(!clickElementByXpath("//div[@class='wpjb-grid-col wpjb-col-40 wpjb-col-title']//span[@class='wpjb-line-major']")){
            System.out.println("[ERROR] Failed to click the first job");
        }
        System.out.println("[INFO] Successfully clicked the first job on the list");
        
        //scrolling down to the apply button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        
        //locate the apply button 
        if(!findElementByXpath("//div//a[contains(text(),'Apply')]")){
            System.out.println("[ERROR] Failed to find the Apply button");
        }
        System.out.println("[INFO] Located the Apply button");
        
        //Click the Apply button
        if(!clickElementByXpath("//div//a[contains(text(),'Apply')]")){
            System.out.println("[ERROR] Failed to click the apply button");
        }
        System.out.println("[INFO] Successfully found the Apply button ");
        
        //scrolling down to view the application form
        js.executeScript("window.scrollBy(0,500)");
        //inserting first name
        if(!insertElementByXpath("//input[@id='applicant_name']", "Lebogang")){
            System.out.println("[ERROR] Failed to insert first name");
        }
        System.out.println("[INFO] First name inserted");
        
        //insert email address
        if(!insertElementByXpath("//input[@id='email']", "automationAssessment@iLABQuality.com")){
            System.out.println("[ERROR] Failed to insert email address");
        }
        System.out.println("[INFO] Email address inserted");
        
        //inserting cell number
        if(!insertElementByXpath("//input[@id='phone']", cellNumber())){
            System.out.println("[ERROR] Failed to insert cell number"); 
        }
        System.out.println("[INFO] Cellphone number inserted ");
        
        //inserting text message
        if(!insertElementByXpath("//textarea[@id='message']", "ILab seems like a great company to work for")){
            System.out.println("[ERROR] Failed to insert comment");
        }
        System.out.println("[INFO] Comments added successfully");
                
        //click the send application button
        if(!clickElementByXpath("//input[@value='Send Application']")){
            System.out.println("[ERROR] Failed to click the Send Application button");
        }
        System.out.println("[INFO] Send Application clicked successfully");
        
        //scrolling down to view the error message
        js.executeScript("window.scrollBy(0,500)");
        
        //locating the error message on the form
        if(!findElementByXpath("//ul[@class='wpjb-errors']//li")){
            System.out.println("[ERROR] Failed to find the error message on the form");
        }
        System.out.println("[INFO] Error message located successfully");
                
        //Extracting the error message displayed
        String message = driver.findElement(By.xpath("//ul[@class='wpjb-errors']//li")).getText();
        System.out.println("[INFO] Error message displayed successfully");
        System.out.println("[INFO] "+ message);
        
        //closes the browser after the test has completed
        driver.close();
        System.out.println("[INFO] Browser closed successfully");
    }
    
    public static String cellNumber(){
        Random randomNumbers = new Random();
        String cell;
        int num0 = randomNumbers.nextInt(10);
        int num1 = randomNumbers.nextInt(10);
        int num2 = randomNumbers.nextInt(10);
        int num3 = randomNumbers.nextInt(10);
        int num4 = randomNumbers.nextInt(10);
        int num5 = randomNumbers.nextInt(10);
        int num6 = randomNumbers.nextInt(10);
        
        cell="083"+num0+num1+num2+num3+num4+num5+num6;
        //System.out.println(cell);
        return cell;
    }

    
public static boolean findElementByXpath(String xpath){
        element = driver.findElement(By.xpath(xpath));
        wait = new WebDriverWait(driver, 15);
        return true;
    }
    public static boolean clickElementByXpath(String xpath){
        element = driver.findElement(By.xpath(xpath));
        wait = new WebDriverWait(driver, 15);
        element.click();
        return true;
    }
    public static boolean insertElementByXpath(String xpath, String text){
        element = driver.findElement(By.xpath(xpath));
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.clear();
        element.sendKeys(text);
        return true;
    }   
       
}
