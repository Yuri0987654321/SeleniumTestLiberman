package newtours.demoaut;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TestNewtoursDemoaut {

    @Test
    public void composition() throws InterruptedException {

        flightFinder();
        selectFlight();
        bookAflight();
        flightConfirmation();

    }
    @Test
    protected void test1Open() {
        Configuration.timeout = 1000;
        open("http://newtours.demoaut.com/");
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
        } catch (AWTException e) {
        }
    }
    @Test
    protected void test2Login() {
        $(By.name("userName")).setValue("tutorial");
        $(By.name("password")).setValue("tutorial").pressEnter();
    }

    protected void flightFinder() {
        Assert.assertTrue("Название не соответсвует «FLIGHT FINDER»", $(By.xpath(("//img[@src=\"/images/masts/mast_flightfinder.gif\"]"))).exists());
//Блок Flight Details
        $(By.name("tripType")).selectRadio("oneway");
        $(By.name("passCount")).selectOption(1);
        $(By.name("fromPort")).selectOption("Paris");
        $(By.name("fromMonth")).selectOption("November");
        $(By.name("fromDay")).selectOption("20");
        $(By.name("toPort")).selectOption("Seattle");
        $(By.name("toMonth")).selectOption("December");
        $(By.name("toDay")).selectOption("19");
//Блок Preferences
        $(By.name("servClass")).selectRadio("Business");
        $(By.name("airline")).selectOption(3);
        $(By.name("findFlights")).click();
    }


    protected void selectFlight() {
        Assert.assertTrue("Название не соответсвует «SELECT FLIGHT»", $(By.xpath("//img[@src=\"/images/masts/mast_selectflight.gif\"]")).exists());
//Блок DEPART
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
//Блок RETURN
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
//Блок Paris to Seattle
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
//Блок Seattle to Paris
        try {
            $(By.tagName("tbody")).shouldHave(text("Seattle to Paris"));
        } catch (Error e) {
            System.out.println("Название не соответсвует ожидаемому «Seattle to Paris»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("12/19/2015"));
        } catch (Error e) {
            System.out.println("Дата рейса «Seattle to Paris» не соответсвует дате «12/19/2015»");
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
//Блок Passengers
        $(By.name("passFirst0")).setValue("Ivanov");
        $(By.name("passLast0")).setValue("Ivan");
        $(By.name("pass.0.meal")).selectOption(3);
        $(By.name("passFirst1")).setValue("Ivanova");
        $(By.name("passLast1")).setValue("Irina");
        $(By.name("pass.1.meal")).selectOption(1);
//Блок Credit Card
        $(By.name("creditCard")).selectOption(2);
        $(By.name("creditnumber")).setValue("5479540454132487");
        $(By.name("cc_exp_dt_mn")).selectOption(05);
        $(By.name("cc_exp_dt_yr")).selectOptionContainingText("2009");
        $(By.name("cc_frst_name")).setValue("Ivan");
        $(By.name("cc_mid_name")).setValue("Ivanovich");
        $(By.name("cc_last_name")).setValue("Ivanov");
//Блок BillingAddress
        $(By.name("billAddress1")).setValue("1085 BorregasAve.");
        $(By.name("billCity")).setValue("Albuquerque");
        $(By.name("billState")).setValue("NewMexico");
        $(By.name("billZip")).setValue("94089");
        $(By.name("billCountry")).selectOptionContainingText("UNITED STATES");
//Блок DeliveryAddress
        $$(By.name("ticketLess")).get(1).click();
        $(By.name("delAddress1")).setValue("1225 BorregasAve.");
        $(By.name("delCity")).setValue("Boston");
        $(By.name("delState")).setValue("Massachusetts");
        $(By.name("delZip")).setValue("91089");
        $(By.name("delCountry")).selectOptionContainingText("UNITED STATES");
        $(By.name("buyFlights")).click();
    }

    void flightConfirmation() throws InterruptedException {
        String departingSum = null;
        String returningSum = null;
        String totalTaxes = null;
        String totalSum = null;

        Assert.assertTrue("Название не соответсвует «SELECT FLIGHT»", $(By.xpath("//img[@src=\"/images/masts/mast_confirmation.gif\"]")).exists());
//Блок Departing
        try {
            $$(By.className("frame_header_info")).get(2).shouldHave(Condition.matchesText(".*Paris to Seattle.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Информация в блоке Departing не соответствует ожидаемой «Paris to Seattle»");
        }
        try {
            $$(By.className("frame_header_info")).get(2).shouldHave(Condition.matchesText(".*11/20/2015.*"));
        } catch (Error e) {
            //      e.printStackTrace();
            System.out.println("Информация в блоке Departing не соответствует ожидаемой «11/20/2015»");
        }
        try {
            $$(By.className("frame_header_info")).get(2).shouldHave(Condition.matchesText(".*Unified Airlines 363.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Информация в блоке Departing не соответствует ожидаемой «Unified Airlines 363»");
        }
        try {
            String text = $$(By.className("frame_header_info")).get(2).getText();
            text = text.replaceAll("\n", " ");
            Matcher matcher = Pattern.compile(".*\\$(\\d*)\\s.*").matcher(text);
            if (matcher.matches()) {
                departingSum = matcher.group(1);
            }
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Информация в блоке Departing не соответствует ожидаемой «Unified Airlines 363»");
        }
//Блок Returning
        try {
            $$(By.className("frame_header_info")).get(4).shouldHave(Condition.matchesText(".*Seattle to Paris.*"));
        } catch (Error e) {
            //  e.printStackTrace();
            System.out.println("Информация в блоке Returning не соответствует ожидаемой «Seattle to Paris»");
        }
        try {
            $$(By.className("frame_header_info")).get(4).shouldHave(Condition.matchesText(".*12/19/2015.*"));
        } catch (Error e) {
            //    e.printStackTrace();
            System.out.println("Информация в блоке Returning не соответствует ожидаемой «12/19/2015»");
        }
        try {
            $$(By.className("frame_header_info")).get(4).shouldHave(Condition.matchesText(".*Blue Skies Airlines 631.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Информация в блоке Returning не соответствует ожидаемой «Blue Skies Airlines 631»");
        }
        try {
            String text = $$(By.className("frame_header_info")).get(4).getText();
            text = text.replaceAll("\n", " ");
            Matcher matcher = Pattern.compile(".*\\$(\\d*)\\s.*").matcher(text);
            if (matcher.matches()) {
                returningSum = matcher.group(1);
            }
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Информация в блоке Departing не соответствует ожидаемой «Unified Airlines 363»");
        }
//Блок Passengers
        int countPassangers = 2;
        try {
            $$(By.className("data_left")).get(1).shouldHave(Condition.matchesText(".*" + countPassangers + " passengers.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Информация в блоке Passengers не соответствует ожидаемой «2 passengers»");
        }
//Блок Billed To
        try {
            $$(By.tagName("tbody")).get(0).shouldHave(Condition.matchesText(".*Ivan Ivanovich Ivanov.*"));
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Billed To не соответствует ожидаемой «Ivan Ivanov Ivanovich»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*1085 BorregasAve.*"));
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Billed To не соответствует ожидаемой «1085 BorregasAve»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*Albuquerque, NewMexico, 94089.*"));
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Billed To не соответствует ожидаемой «Albuquerque, NewMexico, 94089»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*AX 0.*"));
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Billed To не соответствует ожидаемой «AX 0»");
        }
//Блок Delivery Address
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*1225 BorregasAve.*"));
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Delivery Address не соответствует ожидаемой «1225 BorregasAve.»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*Boston, Massachusetts, 91089.*"));
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Delivery Address не соответствует ожидаемой «Boston, Massachusetts , 91089»");
        }

//Блок Total Price (сравнение с суммой данных с FLIGHT CONFIRMATION)
        try {
            //$(Selectors.byText(".*Total.*Taxes.*")).getText();
            //$$(Selectors.byText("")).find(Condition.matchesText(".*Total.*"))
            //.*Total Taxes\:\s*\$(\d*).*
            String text = $$(Selectors.byXpath(".//tr//td")).find(Condition.matchesText(".*Total.*")).getText();
            text = text.replaceAll("\n", " ");
            Matcher matcher = Pattern.compile(".*Total Taxes\\:\\s*\\$(\\d*).*").matcher(text);
            if (matcher.matches()) {
                totalTaxes = matcher.group(1);
            }
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Delivery Address не соответствует ожидаемой «Boston, Massachusetts , 91089»");
        }
        try {
            String text = $$(Selectors.byXpath(".//tr//td")).find(Condition.matchesText(".*Total.*")).getText();
            text = text.replaceAll("\n", " ");
            Matcher matcher = Pattern.compile(".*Total Price \\(including taxes\\)\\:\\s*\\$(\\d*).*").matcher(text);
            if (matcher.matches()) {
                totalSum = matcher.group(1);
            }
        } catch (Error e) {
            //e.printStackTrace();
            System.out.println("Информация в блоке Delivery Address не соответствует ожидаемой «Boston, Massachusetts , 91089»");
        }

        System.out.println(" departingSum=" + departingSum + " returningSum=" + returningSum + " countPassangers="
                + countPassangers + " totalTaxes=" + totalTaxes + " totalSum=" + totalSum + " ");

        BigDecimal bdDepartingSum = new BigDecimal(departingSum);
        BigDecimal bdReturningSum = new BigDecimal(returningSum);
        BigDecimal bdCountPassangers = new BigDecimal(countPassangers);
        BigDecimal bdTotalTaxes = new BigDecimal(totalTaxes);
        BigDecimal bdTotalSum = new BigDecimal(totalSum);
        BigDecimal bdCountedTotalSum = bdDepartingSum.add(bdReturningSum).multiply(bdCountPassangers).add(bdTotalTaxes);
        Assert.assertEquals("Not equals total sum", bdCountedTotalSum, bdTotalSum);
        $(By.xpath("//a[@href=\"mercurywelcome.php\"]")).click();
        Thread.sleep(2000);
    }
}



