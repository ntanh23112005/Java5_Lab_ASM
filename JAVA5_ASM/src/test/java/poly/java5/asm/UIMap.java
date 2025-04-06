package poly.java5.asm;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UIMap {
    Properties properties;

    public UIMap(String filePath){
        try {
            FileInputStream Locator = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(Locator);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getData(String ElementName) throws Exception{
        //Read value using the logical name as Key
        return properties.getProperty(ElementName);
    }

    public By getLocator(String ElementName) throws Exception{
        //Read value using the logical name as Key
        String locator = properties.getProperty(ElementName);

        //Split the value which contains locator type and locator value
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];

        //return a instance of By call based on type of locator
        if (locatorType.equalsIgnoreCase("id"))
            return By.id(locatorValue);
        else if (locatorType.equalsIgnoreCase("name"))
            return By.name(locatorValue);
        else if ( (locatorType.equalsIgnoreCase("classname")) || (locatorType.equalsIgnoreCase("class")) )
            return By.className(locatorValue);
        else if ( (locatorType.equalsIgnoreCase("tagname")) || (locatorType.equalsIgnoreCase("tag")) )
            return By.tagName(locatorValue);
        else if ( (locatorType.equalsIgnoreCase("linktext")) || (locatorType.equalsIgnoreCase("link")) )
            return By.linkText(locatorValue);
        else if (locatorType.equalsIgnoreCase("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if ( (locatorType.equalsIgnoreCase("cssselector")) || (locatorType.equalsIgnoreCase("css")) )
            return By.cssSelector(locatorValue);
        else if (locatorType.equalsIgnoreCase("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Locator type " + locatorType + " Not defined !");
    }
}
