package poly.java6.lab6.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin1 {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    public void testLogin() throws InterruptedException {
        Thread.sleep(4000);
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        Thread.sleep(4000);
        username.sendKeys("student");
        password.sendKeys("Password123");

        Thread.sleep(4000);
        driver.findElement(By.id("submit")).click();

        Thread.sleep(4000);
        String result = driver.findElement(By.className("post-title")).getText();
        String expect = "Logged In Successfully";

        Thread.sleep(4000);
        Assert.assertEquals(result, expect);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
