package poly.java5.asm;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderTest {
    WebDriver driver;
    WebDriver driverLogout;

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

    //order successfully
    @Test(priority = 1)
    public void makeOrderSuccessfully() throws Exception{
        int rowIndex = 57; //i58, j58

        driver.get("http://localhost:8080/account/login");

        // state 1: log in to home
        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.correct"));
        password.sendKeys(dataFile.getData("login.password.correct"));
        loginBtn.click();

        // state 2: add product
        List<WebElement> addProductBtn = driver.findElements(uiMap.getLocator("addProductBtn"));
        if (!addProductBtn.isEmpty()) {
            WebElement firstAddProductBtn = addProductBtn.get(0);

            // Scroll tới nút mua đầu tiên
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstAddProductBtn);
            Thread.sleep(500);
            firstAddProductBtn.click();
            Thread.sleep(200);
            WebElement toastClose = driver.findElement(uiMap.getLocator("toastClose"));
            toastClose.click();
        } else {
            throw new Exception("Not find element'");
        }
        // enter to cart
        WebElement cartBtn = driver.findElement(uiMap.getLocator("cartBtn"));
        cartBtn.click();


        //state 3: make order
        WebElement makeOrderBtn = driver.findElement(uiMap.getLocator("makeOrderBtn"));
        makeOrderBtn.click();
        Thread.sleep(1000);

        WebElement addressShipping = driver.findElement(uiMap.getLocator("shippingAddress"));
        addressShipping.clear();
        addressShipping.sendKeys(dataFile.getData("order.shippingAddress"));

        WebElement makeOrderBtnSubmit = driver.findElement(uiMap.getLocator("makeOrderBtnSubmit"));
        makeOrderBtnSubmit.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("loggedInSuccess")
            ));

            actualResult = resultElement.getText().trim();

            String expected = dataFile.getData("order.success").trim();

//            System.out.println("Actual : " + actualResult);
//            System.out.println("Expected : " + expected);

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            actualResult = "Some thing is wrong !";
            status = "FAIL";
//            e.printStackTrace();
        }

        writeExcel(rowIndex, actualResult, status);
    }

    //test with empty cart
    @Test(priority = 2)
    public void orderFailWithEmptyCart() throws Exception{
        int rowIndex = 61; //i62, j62

        driver.get("http://localhost:8080/account/login");

        // state 1: log in to home
        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.correct"));
        password.sendKeys(dataFile.getData("login.password.correct"));
        loginBtn.click();

        // state 2: enter to cart
        WebElement toastClose = driver.findElement(uiMap.getLocator("toastClose"));
        toastClose.click(); // close notification

        WebElement cartBtn = driver.findElement(uiMap.getLocator("cartBtn"));
        cartBtn.click();


        //state 3: make order
        WebElement makeOrderBtn = driver.findElement(uiMap.getLocator("makeOrderBtn"));
        makeOrderBtn.click();
        Thread.sleep(1000);

        WebElement addressShipping = driver.findElement(uiMap.getLocator("shippingAddress"));
        addressShipping.clear();
        addressShipping.sendKeys(dataFile.getData("order.shippingAddress"));

        WebElement makeOrderBtnSubmit = driver.findElement(uiMap.getLocator("makeOrderBtnSubmit"));
        makeOrderBtnSubmit.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("success")
            ));

            actualResult = resultElement.getText().trim();

            String expected = dataFile.getData("order.null").trim();

            System.out.println("Actual : " + actualResult);
            System.out.println("Expected : " + expected);

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            actualResult = "Some thing is wrong !";
            status = "FAIL";
//            e.printStackTrace();
        }

        writeExcel(rowIndex, actualResult, status);
    }


    //order with null shipping address
    @Test(priority = 3)
    public void orderFailWithNullShippingAddress() throws Exception{
        int rowIndex = 64; //i65, j65

        driver.get("http://localhost:8080/account/login");

        // state 1: log in to home
        WebElement username = driver.findElement(uiMap.getLocator("username"));
        WebElement password = driver.findElement(uiMap.getLocator("password"));
        WebElement loginBtn = driver.findElement(uiMap.getLocator("submit"));

        username.clear();
        password.clear();

        username.sendKeys(dataFile.getData("login.username.correct"));
        password.sendKeys(dataFile.getData("login.password.correct"));
        loginBtn.click();

        // state 2: add product
        List<WebElement> addProductBtn = driver.findElements(uiMap.getLocator("addProductBtn"));
        if (!addProductBtn.isEmpty()) {
            WebElement firstAddProductBtn = addProductBtn.get(0);

            // Scroll tới nút mua đầu tiên
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", firstAddProductBtn);
            Thread.sleep(500);
            firstAddProductBtn.click();
            Thread.sleep(200);
            WebElement toastClose = driver.findElement(uiMap.getLocator("toastClose"));
            toastClose.click();
        } else {
            throw new Exception("Not find element'");
        }
        // enter to cart
        WebElement cartBtn = driver.findElement(uiMap.getLocator("cartBtn"));
        cartBtn.click();


        //state 3: make order
        WebElement makeOrderBtn = driver.findElement(uiMap.getLocator("makeOrderBtn"));
        makeOrderBtn.click();
        Thread.sleep(1000);

        WebElement addressShipping = driver.findElement(uiMap.getLocator("shippingAddress"));
        addressShipping.clear();
        addressShipping.sendKeys(dataFile.getData("order.shippingAddress.null"));

        WebElement makeOrderBtnSubmit = driver.findElement(uiMap.getLocator("makeOrderBtnSubmit"));
        makeOrderBtnSubmit.click();

        String actualResult, status;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try{
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    uiMap.getLocator("success")
            ));

            actualResult = resultElement.getText().trim();

            String expected = dataFile.getData("order.fail.shippingAddress.null").trim();

            System.out.println("Actual : " + actualResult);
            System.out.println("Expected : " + expected);

            status = actualResult.equalsIgnoreCase(expected) ? "PASS" : "FAIL";

        } catch (Exception e) {
            actualResult = "Some thing is wrong !";
            status = "FAIL";
//            e.printStackTrace();
        }

        writeExcel(rowIndex, actualResult, status);
    }


    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }

        if (driverLogout != null) {
            driverLogout.quit();
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
