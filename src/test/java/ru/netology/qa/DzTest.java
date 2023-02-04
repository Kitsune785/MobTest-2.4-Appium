package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DzTest {

        private AndroidDriver driver;

        @BeforeEach
        public void setUp() throws MalformedURLException {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("appium:deviceName", "Some name");
            desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
            desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
            desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
            desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
            desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

            URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub/");

            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        }

        @Test
        public void testEmptyText() {
            TestScreen screen = new TestScreen(driver);
            String textBefore = screen.textChanged.getText();
            screen.input.sendKeys("    ");
            screen.buttonChange.click();
            String textAfter = screen.textChanged.getText();
            Assertions.assertEquals(textBefore, textAfter);
        }

        @Test
        public void testNewActivity() {
            TestScreen screen = new TestScreen(driver);
            screen.input.sendKeys("Netology");
            screen.buttonActivity.click();
            String textAfter = screen.activityText.getText();
            Assertions.assertEquals("Netology", textAfter);
            driver.navigate().back();
        }

        @AfterEach
        public void tearDown() {
            driver.quit();
        }
}