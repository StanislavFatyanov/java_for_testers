package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class HelperBase {
    protected final ApplicationManager manager;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public HelperBase(ApplicationManager manager) {

        this.manager = manager;
        this.driver = manager.driver;
        this.wait = manager.wait;
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).clear();
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        //manager.driver.findElement(locator).click();
    }
}
