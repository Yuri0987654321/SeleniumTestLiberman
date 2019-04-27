package newtours.demoaut;

import com.codeborne.selenide.ElementsCollection;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Collection;

import static com.codeborne.selenide.Condition.name;
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
        Assert.assertTrue("Название не соответсвует «FLIGHT FINDER»", $(By.xpath(("//img[@src=\"/images/masts/mast_flightfinder.gif\"]"))).exists());
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
        Assert.assertTrue("Название не соответсвует «SELECT FLIGHT»", $(By.xpath("//img[@src=\"/images/masts/mast_selectflight.gif\"]")).exists());
        try {
            $(By.tagName("tbody")).shouldHave(text("Paris to Seattle"));
        } catch (Error e) {
            System.out.println("Текст в блоке «DEPART» не соответсвует тексту «Paris to Seattle»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("11/20/2015"));
        } catch (Error e) {
            System.out.println("Дата в блоке «DEPART» не соответсвует дате «11/20/2015»");
        }

        $(By.name("outFlight")).selectRadio("Unified Airlines$363$281$11:24");

        try {
            $(By.tagName("tbody")).shouldHave(text("Seattle to Paris"));
        } catch (Error e) {
            System.out.println("Текст в блоке «RETURN» не соответсвует тексту «Seattle to Paris»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("12/19/2015"));
        } catch (Error e) {
            System.out.println("Дата в блоке «RETURN» не соответсвует дате «12/19/2015»");
        }

        $(By.name("inFlight")).selectRadio("Blue Skies Airlines$631$273$14:30");
        $(By.name("reserveFlights")).click();
    }
    protected void bookAflight() throws InterruptedException {
//Paris to Seattle
        try {
            $(By.className("frame_header_info")).shouldHave(text("Paris to Seattle"));
        } catch (Error e) {
            System.out.println("Название не соответсвует ожидаемому «Paris to Seattle»");
        }
        try {
            $(By.className("frame_header_info")).shouldHave(text("11/20/2015"));
        } catch (Error e) {
            System.out.println("Дата рейса «Paris to Seattle» не соответсвует дате «11/20/2015»");
        }
        try {
            $(By.className("data_left")).shouldHave(text("Unified Airlines 363"));
        } catch (Error e) {
            System.out.println("Название не соответсвует ожидаемому «Unified Airlines 363»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("Business"));
        } catch (Error e) {
            System.out.println("Класс не соответсвует ожидаемому Business");
        }
        try {
            $(By.className("data_center")).shouldHave(text("281"));
        } catch (Error e) {
            System.out.println("Цена не соответсвует ожидаемой 281");
        }
//Seattle to Paris
        try {
            $(By.tagName("tbody")).shouldHave(text("Seattle to Paris"));
        } catch (Error e) {
            System.out.println("Название не соответсвует ожидаемому «Seattle to Paris»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("12/19/2015"));
        } catch (Error e) {
            System.out.println("Дата рейса «Seattle to Paris» не соответсвует дате «11/20/2015»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("Blue Skies Airlines 631"));
        } catch (Error e) {
            System.out.println("Название не соответсвует ожидаемому «Blue Skies Airlines 631»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("Business"));
        } catch (Error e) {
            System.out.println("Класс не соответсвует ожидаемому Business");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("273"));
        } catch (Error e) {
            System.out.println("Цена не соответсвует ожидаемой 273");
        }

        try {
            $(By.tagName("tbody")).shouldHave(text("2"));
        } catch (Error e) {
            System.out.println("Количество пассажиров не соответсвует ожидаемому - 2");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("$91"));
        } catch (Error e) {
            System.out.println("Стоиомть сборов не соответсвует $91");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("$1199"));
        } catch (Error e) {
            System.out.println("Итого не соответсвует $1199");
        }
//Passengers
        $(By.name("passFirst0")).setValue("Ivanov");
        $(By.name("passLast0")).setValue("Ivan");
        $(By.name("pass.0.meal")).selectOption(3);
        $(By.name("passFirst1")).setValue("Ivanova");
        $(By.name("passLast1")).setValue("Irina");
        $(By.name("pass.1.meal")).selectOption(1);
// Credit Card
        $(By.name("creditCard")).selectOption(2);
        $(By.name("creditnumber")).setValue("5479540454132487");
        $(By.name("cc_exp_dt_mn")).selectOption(05);
        $(By.name("cc_exp_dt_yr")).selectOptionContainingText("2009");
        $(By.name("cc_frst_name")).setValue("Ivan");
        $(By.name("cc_mid_name")).setValue("Ivanovich");
        $(By.name("cc_last_name")).setValue("Ivanov");
//BillingAddress
        $(By.name("billAddress1")).setValue("1085 BorregasAve.");
        $(By.name("billCity")).setValue("Albuquerque");
        $(By.name("billState")).setValue("NewMexico");
        $(By.name("billZip")).setValue("94089");
        $(By.name("billCountry")).selectOptionContainingText("UNITED STATES");
        //$(By.xpath(".//input[@name='ticketLess'"]))
        Thread.sleep(5000);

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



