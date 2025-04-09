package poly.java6.lab6.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SaveTestNGResultToExcel {
    WebDriver driver;
    public UIMap uiMap;
    public UIMap dataFile;
    public String workingDir;

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    Map<String, Object[]> TestNGResults;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("TestNG Results");
        TestNGResults = new HashMap<>();

        uiMap = new UIMap("src/main/resources/locator.properties");
        dataFile = new UIMap("src/main/resources/data.properties");
    }

    @Test(description = "Opens the TestNG Demo Website for Login Test")
    public void lauchWebsite(){
        try {
            driver.get("https://practicetestautomation.com/practice-test-login/");
            TestNGResults.put("2", new Object[]{
                    1d, "Navigate to demo website", "Site gets opened successfully", "Pass"
            });
        }catch (Exception e){
            TestNGResults.put("2", new Object[]{
                    1d, "Navigate to demo website", "Site failed to open", "Failed"
            });
            e.printStackTrace();
        }
    }

    @Test(description = "Fill to Login Details")
    public void fillLoginDetails() throws Exception{
        try {
            WebElement username = driver.findElement(uiMap.getLocator("username"));
            username.sendKeys(dataFile.getData("student"));

            WebElement password = driver.findElement(uiMap.getLocator("password"));
            password.sendKeys(dataFile.getData("Password123"));

            TestNGResults.put("3", new Object[]{
                    2d, "Fill Login Form Data (username, password)", "Login details are filled successfully", "Pass"
            });
        } catch (Exception e) {
            TestNGResults.put("3", new Object[]{
                    2d, "Fill Login Form Data (username, password)", "Login form filling failed", "Fail"
            });
        }
    }

    @Test(description = "Perform Login")
    public void doLogin() throws Exception{
        try {
            WebElement loginButton = driver.findElement(uiMap.getLocator("submit"));
            loginButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(uiMap.getLocator("post-title")));
            Assert.assertEquals(username.getText(),"Logged In Successfully");

            TestNGResults.put("4", new Object[]{
                    3d, "Click Login and Verify welcome message", "Login Successfully", "Pass"
            });
        } catch (Exception e) {
            TestNGResults.put("4", new Object[]{
                    3d, "Click Login and Verify welcome message", "Login Failed", "Fail"
            });
        }
    }

    @AfterClass
    public void tearDown(){
        Set<String> keySet = TestNGResults.keySet();
        int rownum = 0;
        for (String key: keySet){
            Row row = sheet.createRow(rownum ++);
            Object[] objArr = TestNGResults.get(key);
            int cellnum = 0;
            for (Object obj: objArr){
                Cell cell = row.createCell(cellnum ++ );
                if(obj instanceof Date)
                    cell.setCellValue((Date) obj);
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try(FileOutputStream out = new FileOutputStream(("SaveTestNGResultsToExcel.xls"))){
            workbook.write(out);
            System.out.println("Successfully saved Selenium Webdriver TestNG results to Excel File !!");
        }catch (IOException e){
            e.printStackTrace();
        }

        driver.quit();
    }
}
