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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import poly.java5.dao.UserDAO;
import poly.java5.service.UserService;
import poly.java5.service.impl.UserServiceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegisterTest {

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
        sheet = workbook.getSheetAt(0); //first sheet in excel

        uiMap = new UIMap("src/main/resources/locator.properties");
        dataFile = new UIMap("src/main/resources/data.properties");
    }

    //test with all correct information
    @Test(priority = 1)
    public void registerSuccess() throws Exception {
        int rowIndex = 21; //I22, J22

        String emailTest = "test" + System.currentTimeMillis() + "@gmail.com";

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.correct"));
        email.sendKeys(emailTest);
        mobile.sendKeys(dataFile.getData("register.mobile.correct"));
        password.sendKeys(dataFile.getData("register.password.correct"));
        confirmPassword.sendKeys(dataFile.getData("register.confirmPassword.correct"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerSuccess")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.success").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";


//            System.out.println("Actual: " + actualResult);
//            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
        }

        writeExcel(rowIndex, actualResult, status);
    }

    // test with email exits and correct other information
    @Test(priority = 2)
    public void registerFailWithEmailExits() throws Exception {
        int rowIndex = 24; //I25, J25

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.correct"));
        email.sendKeys(dataFile.getData("register.email.exits"));
        mobile.sendKeys(dataFile.getData("register.mobile.correct"));
        password.sendKeys(dataFile.getData("register.password.correct"));
        confirmPassword.sendKeys(dataFile.getData("register.confirmPassword.correct"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerError")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.email.errorExist").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

            System.out.println("Actual: " + actualResult);
            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
        }

        writeExcel(rowIndex, actualResult, status);

    }

    // test with wrong format mobile
    @Test(priority = 3)
    public void registerFailWithWrongFormatMobile() throws Exception {
        int rowIndex = 27; //I28, J28

        String emailTest = "test" + System.currentTimeMillis() + "@gmail.com";

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.correct"));
        email.sendKeys(emailTest);
        mobile.sendKeys(dataFile.getData("register.mobile.wrongFormat"));
        password.sendKeys(dataFile.getData("register.password.correct"));
        confirmPassword.sendKeys(dataFile.getData("register.confirmPassword.correct"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerError")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.mobile.error").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

//            System.out.println("Actual: " + actualResult);
//            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
        }

        writeExcel(rowIndex, actualResult, status);
    }

    // test with password < 6 character
    @Test(priority = 4)
    public void registerFailWithShortPassword() throws Exception {
        int rowIndex = 30; //I31, J31

        String emailTest = "test" + System.currentTimeMillis() + "@gmail.com";

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.correct"));
        email.sendKeys(emailTest);
        mobile.sendKeys(dataFile.getData("register.mobile.correct"));
        password.sendKeys(dataFile.getData("register.password.errorShort"));
        confirmPassword.sendKeys(dataFile.getData("register.password.errorShort"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerError")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.password.error").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

//            System.out.println("Actual: " + actualResult);
//            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
        }

        writeExcel(rowIndex, actualResult, status);

    }

    // test with confirmPassword not same password
    @Test(priority = 5)
    public void registerFailWithWrongConfirmPassword() throws Exception {
        int rowIndex = 33; //I34, J34

        String emailTest = "test" + System.currentTimeMillis() + "@gmail.com";

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.correct"));
        email.sendKeys(emailTest);
        mobile.sendKeys(dataFile.getData("register.mobile.correct"));
        password.sendKeys(dataFile.getData("register.password.correct"));
        confirmPassword.sendKeys(dataFile.getData("register.confirmPassword.inCorrect"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerError")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.confirmPassword.error").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

            System.out.println("Actual: " + actualResult);
            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
        }

        writeExcel(rowIndex, actualResult, status);

    }

    //test with FullName has number
    @Test(priority = 6)
    public void registerFailWithWrongFullName() throws Exception {
        int rowIndex = 36; //I37, J37

        String emailTest = "test" + System.currentTimeMillis() + "@gmail.com";

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.wrongFormat"));
        email.sendKeys(emailTest);
        mobile.sendKeys(dataFile.getData("register.mobile.correct"));
        password.sendKeys(dataFile.getData("register.password.correct"));
        confirmPassword.sendKeys(dataFile.getData("register.confirmPassword.correct"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerError")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.fullName.error").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";


            System.out.println("Actual: " + actualResult);
            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
        }

        writeExcel(rowIndex, actualResult, status);
    }


    //test with empty information
    @Test(priority = 7)
    public void registerFailWithEmptyInformation() throws Exception {
        int rowIndex = 39; //I40, J40

        driver.get("http://localhost:8080/account/register");
        WebElement fullName = driver.findElement(uiMap.getLocator("fullName"));
        WebElement email = driver.findElement(uiMap.getLocator("email"));
        WebElement mobile = driver.findElement(uiMap.getLocator("mobile"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement confirmPassword = driver.findElement(uiMap.getLocator("confirmPassword"));

        WebElement registerBtn = driver.findElement(uiMap.getLocator("submit"));

        fullName.clear();
        email.clear();
        mobile.clear();
        password.clear();
        confirmPassword.clear();

        fullName.sendKeys(dataFile.getData("register.fullName.empty"));
        email.sendKeys(dataFile.getData("register.email.empty"));
        mobile.sendKeys(dataFile.getData("register.mobile.empty"));
        password.sendKeys(dataFile.getData("register.password.empty"));
        confirmPassword.sendKeys(dataFile.getData("register.confirmPassword.empty"));

        registerBtn.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("registerError")
            ));

            actualResult = resultElement.getText().trim();
            String expected = dataFile.getData("register.fail.empty").trim();

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";


            System.out.println("Actual: " + actualResult);
            System.out.println("Expected: " + expected);
        } catch (Exception e) {
            actualResult = ("Register fail (Element not found)");
            status = "FAIL";
            throw new Exception(e);
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
