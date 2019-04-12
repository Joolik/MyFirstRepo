package ru.levelup.z.y.gitstart.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SeleniumWD {

    @Test
    public void goTo() {
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        WebDriver driver = new ChromeDriver(options);

        // Максимальный размер окна браузера
        driver.manage().window().maximize();

        // Open test site by URL
        driver.get("http://khda91.fvds.ru/mantisbt/");

        // Assert browser title
        assertThat(driver.getTitle(), equalTo("MantisBT"));
        System.out.println("driver.getTitle() : " + driver.getTitle());

        // Login admin/admin
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-success")).click();
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn-success")).click();

        // Assert username in the right-top side of screen
        assertThat(driver.findElement(By.cssSelector(".user-info")).getText(), equalToIgnoringCase("admin"));
        System.out.println(driver.findElement(By.cssSelector(".user-info")).getText());

        // Assert left-side menu
        List<WebElement> menuLeftSide = driver.findElements(By.xpath("//div[@id='sidebar']//a"));
        for (WebElement we : menuLeftSide) {
            System.out.println(we.getText());
        }
        assertThat(menuLeftSide, not(empty()));

        // Click Manage
        for (WebElement we : menuLeftSide) {
            if (we.getText().trim().equals("Manage")) {
                we.click();
                break;
            }
        }

        // Click Manage Projects
        driver.findElement(By.xpath("//ul[contains(@class,'nav-tabs')]//a[text()='Manage Projects']")).click();

        // Check "Create new project" button
        WebElement btnNewProject = driver.findElement(By.xpath("//button[text()='Create New Project']"));
        assertThat(btnNewProject, notNullValue());
        // Click "Create new project" button
        if (btnNewProject.isEnabled()) {
            btnNewProject.click();
        }

        // Check fields on the "Add project" view

        List<WebElement> fieldsAddProject = driver.findElements(By.xpath("//div[@class='widget-body']//table//tr"));

        for (WebElement we : fieldsAddProject) {
            System.out.println(we.getText());
            System.out.println(we.getTagName());
        }

        // Fill project information

        // Click "Add project" button



        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Logout
        driver.findElement(By.className("user-info")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();

        // Close browser
        driver.close();
        driver.quit();
    }
}
