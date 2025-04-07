package poly.java5.asm;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;
    UIMap uiMap;
    UIMap dataFile;

    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String excelPath;

    @BeforeClass
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //load excel exists

        excelPath = Paths.get("Exel_lab.xlsx").toAbsolutePath().toString();
        FileInputStream fis = new FileInputStream(excelPath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0); // first sheet in excel

        uiMap = new UIMap("src/main/resources/locator.properties");
        dataFile = new UIMap("src/main/resources/data.properties");

    }

    // test with correct username and email
    @Test(priority = 1)
    public void loginSuccess() throws Exception{

        int rowIndex = 9; //I10, J10

        driver.get("http://localhost:8080/account/login");

        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.correct"));
        password.sendKeys(dataFile.getData("login.password.correct"));
        loginBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("loggedInSuccess")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("login.message.correct").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

//            System.out.println("Actual: " + actualResult);
//            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Login fail (Element not found)");
            status = "FAIL";
        }

            writeExcel(rowIndex, actualResult, status);
//            System.out.println("Excel path: " + excelPath);
//            System.out.println("Username: " + dataFile.getData("login.username.correct"));
//            System.out.println("Password: " + dataFile.getData("login.password.correct"));
    }

    //test with incorrect username and valid password
    @Test(priority = 2)
    public void logInFailWithWrongUsernameAndCorrectPassword() throws Exception{
        int rowIndex = 12; //i13, j13

        driver.get("http://localhost:8080/account/login");

        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.incorrect"));
        password.sendKeys(dataFile.getData("login.password.correct"));
        loginBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("loggedInFail")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("login.message.incorrect").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

//            System.out.println("Actual: " + actualResult);
//            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Login fail (Element not found)");
            status = "FAIL";
        }

        writeExcel(rowIndex, actualResult, status);
//            System.out.println("Excel path: " + excelPath);
//            System.out.println("Username: " + dataFile.getData("login.username.incorrect"));
//            System.out.println("Password: " + dataFile.getData("login.password.correct"));
    }

    //test with correct username and incorrect password
    @Test(priority = 3)
    public void logInFailWithCorrectUsernameAndWrongPassword() throws Exception{
        int rowIndex = 15; //i16, j16

        driver.get("http://localhost:8080/account/login");

        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.correct"));
        password.sendKeys(dataFile.getData("login.password.incorrect"));
        loginBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("loggedInFail")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("login.message.incorrect").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            actualResult = ("Login fail (Element not found)");
            status = "FAIL";
        }

        writeExcel(rowIndex, actualResult, status);
    }

    //test with correct username and incorrect password
    @Test(priority = 4)
    public void logInFailWithWrongUsernameAndWrongPassword() throws Exception{
        int rowIndex = 18; //i19, j19

        driver.get("http://localhost:8080/account/login");

        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.incorrect"));
        password.sendKeys(dataFile.getData("login.password.incorrect"));
        loginBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("loggedInFail")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("login.message.incorrect").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            actualResult = ("Login fail (Element not found)");
            status = "FAIL";
        }

        writeExcel(rowIndex, actualResult, status);
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }

        // Write the Excel file if it's not saved yet (just in case)
        if (workbook != null) {
            try (FileOutputStream fos = new FileOutputStream(excelPath)) {
                workbook.write(fos);
            }
        }

        // Close the workbook (release resources)
        if (workbook != null) {
            workbook.close();
        }

        System.out.println("Test execution completed and resources cleaned up.");
    }



    // write to excel, column I, J function
    public void writeExcel(int rowIndex, String result, String status) throws Exception {
        Row row = sheet.getRow(rowIndex);
        if (row == null) row = sheet.createRow(rowIndex);

        Cell resultCell = row.getCell(8); // I column (index 8)
        if (resultCell == null) resultCell = row.createCell(8);
        resultCell.setCellValue(result);

        Cell statusCell = row.getCell(9); // J column (index 9)
        if (statusCell == null) statusCell = row.createCell(9);
        statusCell.setCellValue(status);

        // Save the updated file
        FileOutputStream fos = new FileOutputStream(excelPath);
        workbook.write(fos);
        fos.close();
    }

}
