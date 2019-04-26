package newtours.demoaut;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestNewtoursDemoaut {

    @Test
    public void composition() throws InterruptedException {
        open("http://newtours.demoaut.com/");
        login();
        flightFinder();
        selectFlight();
        bookAflight();
    }

    protected void login() {
        $(By.name("userName")).setValue("tutorial");
        $(By.name("password")).setValue("tutorial").pressEnter();
    }

    protected void flightFinder() {
        Assert.assertTrue("Название не соответствует «FLIGHT FINDER»", $(By.xpath(("//img[@src=\"/images/masts/mast_flightfinder.gif\"]"))).exists());
        $(By.name("tripType")).selectRadio("oneway");
        $(By.name("passCount")).selectOption(1);
        $(By.name("fromPort")).selectOption(4);
        $(By.name("fromMonth")).selectOption(10);
        $(By.name("fromDay")).selectOption(19);
        $(By.name("toPort")).selectOption(7);
        $(By.name("toMonth")).selectOption(11);
        $(By.name("toDay")).selectOption(18);
        $(By.name("servClass")).selectRadio("Business");
        $(By.name("airline")).selectOption(3);
        $(By.name("findFlights")).click();
    }


    protected void selectFlight() {
        Assert.assertTrue("Название не соответствует «SELECT FLIGHT»", $(By.xpath("//img[@src=\"/images/masts/mast_selectflight.gif\"]")).exists());
        try {
            $(By.tagName("tbody")).shouldHave(text("Paris to Seattle"));
        } catch (Error e) {
            System.out.println("В блоке «DEPART» назавние не соотвтетсвует назавнию «Paris to Seattle»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("11/20/2015"));
        } catch (Error e) {
            System.out.println("В блоке «DEPART» дата не соотвтетсвует «11/20/2015»");
        }

        $(By.name("outFlight")).selectRadio("Unified Airlines$363$281$11:24");

        try {
            $(By.tagName("tbody")).shouldHave(text("Seattle to Paris"));
        } catch (Error e) {
            System.out.println("В блоке «RETURN» назавние не соотвтетсвует назавнию «Seattle to Paris»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("12/19/2015"));
        } catch (Error e) {
            System.out.println("В блоке «RETURN» дата не соотвтетсвует «12/19/2015»");
        }

        $(By.name("inFlight")).selectRadio("Blue Skies Airlines$631$273$14:30");
        $(By.name("reserveFlights")).click();
    }
    protected void bookAflight(){

    }

//        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver73.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://newtours.demoaut.com/");
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("userName")));
//        driver.findElement(By.name("userName")).sendKeys("tutorial");
//        driver.findElement(By.name("password")).sendKeys("tutorial");
//        driver.findElement(By.name("login")).click();
//        wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@src=\"/images/masts/mast_flightfinder.gif\"]")));


    // driver.findElement(By.name("oneway")).click();
    //Thread.sleep(1000);
    //driver.quit();
}



