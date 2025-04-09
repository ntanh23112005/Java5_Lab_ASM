package poly.java6.lab6.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestLogin2 {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @Test
    public void testLogin() throws InterruptedException {
        Thread.sleep(4000);
        WebElement username = driver.findElement(By.cssSelector("input[name='username']"));
        WebElement password = driver.findElement(By.cssSelector("input[name='password']"));

        Thread.sleep(4000);
        username.sendKeys("student");
        password.sendKeys("Password123");

        Thread.sleep(4000);
        driver.findElement(By.cssSelector("button[id='submit']")).click();

        Thread.sleep(4000);
        String result = driver.findElement(By.cssSelector("h1[class = 'post-title']")).getText();
        String expect = "Logged In Successfully";

        Thread.sleep(4000);
        Assert.assertEquals(result, expect);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
