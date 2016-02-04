package testJauntProject;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class myTestClass{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://potus-ui.herokuapp.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void test1() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Presidents by year")).click();
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2014");
    String mypresendent = driver.findElement(By.xpath("html/body/div[1]/ul/li/a/strong")).getText();  
    Assert.assertEquals("2014 President Name is Barack Obama ","Barack Obama", mypresendent);
   // System.out.println(mypresendent);
    String myPresenparty = driver.findElement(By.xpath("/html/body/div/ul/li/span")).getText();
    Assert.assertEquals(" Barack Obama  Party ","Democrat", myPresenparty);
    
  }

  @Test
  public void test2() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Find Presidents by name")).click();
    driver.findElement(By.id("q")).clear();
    driver.findElement(By.id("q")).sendKeys("Dwight D. Eisenhower");
    driver.findElement(By.id("search-btn")).click();
    driver.findElement(By.xpath("html/body/div[1]/ul/li/a/strong")).getText();
    Assert.assertEquals("Pass","Dwight D. Eisenhower", driver.findElement(By.xpath("html/body/div[1]/ul/li/a/strong")).getText());
  }
  
  @Test
  public void testUntitled() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Presidential parties")).click();
    Assert.assertEquals("Chart ID is chart","chart", driver.findElement(By.xpath("html/body/div/div/div[2]/canvas")).getAttribute("id"));
  }
  @After
  public void tearDown() throws Exception {
    driver.quit();   
  }

  

  
}
