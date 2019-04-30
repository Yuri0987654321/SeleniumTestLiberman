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
    private String departingSum = null;
    private String returningSum = null;
    private final static int countPassangers = 2;

    @Test
    public void testNewtoursDemoaut_1() {
        openWebsite();
        login();
        flightFinder();
        flightFinder_FlightDetails();
        flightFinder_Preferences();
        SelectFlight();
        selectFlight_Depart();
        selectFlight_Return();
        bookAflight_Summary();
        bookAflight_Passengers();
        bookAflight_CreditCard();
        bookAflight_BillingAddress();
        bookAflight_DeliveryAddress();
        flightConfirmation();
        flightConfirmation_Departing();
        flightConfirmation_Returning();
        flightConfirmation_Passengers();
        flightConfirmation_BilledTo();
        flightConfirmation_DeliveryAddress();
        flightConfirmation_TotalPrice();
    }

    private void openWebsite() {
        Configuration.timeout = 1000;
        open("http://newtours.demoaut.com/");
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_F11);
        } catch (AWTException e) {
        }
    }

    private void login() {
        $(By.name("userName")).setValue("tutorial");
        $(By.name("password")).setValue("tutorial").pressEnter();
    }

    private void flightFinder() {
        Assert.assertTrue("Title is not match «FLIGHT FINDER»", $(By.xpath(("//img[@src=\"/images/masts/mast_flightfinder.gif\"]"))).exists());
    }

    private void flightFinder_FlightDetails() {
        $(By.name("tripType")).selectRadio("oneway");
        $(By.name("passCount")).selectOption(1);
        $(By.name("fromPort")).selectOption("Paris");
        $(By.name("fromMonth")).selectOption("November");
        $(By.name("fromDay")).selectOption("20");
        $(By.name("toPort")).selectOption("Seattle");
        $(By.name("toMonth")).selectOption("December");
        $(By.name("toDay")).selectOption("19");
    }

    private void flightFinder_Preferences() {
        $(By.name("servClass")).selectRadio("Business");
        $(By.name("airline")).selectOption(3);
        $(By.name("findFlights")).click();
    }

    private void SelectFlight() {
        Assert.assertTrue("Title is not match expected «SELECT FLIGHT»", $(By.xpath("//img[@src=\"/images/masts/mast_selectflight.gif\"]")).exists());
    }

    private void selectFlight_Depart() {
        try {
            $(By.tagName("tbody")).shouldHave(text("Paris to Seattle"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Text in block «DEPART» is not match expected «Paris to Seattle»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("11/20/2015"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Date in block «DEPART» is not match expected «11/20/2015»");
        }
        $(By.name("outFlight")).selectRadio("Unified Airlines$363$281$11:24");
    }

    private void selectFlight_Return() {
        try {
            $(By.tagName("tbody")).shouldHave(text("Seattle to Paris"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Text in block «RETURN» is not match expected «Seattle to Paris»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("12/19/2015"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Date in block «RETURN» is not match «12/19/2015»");
        }
        $(By.name("inFlight")).selectRadio("Blue Skies Airlines$631$273$14:30");
        $(By.name("reserveFlights")).click();
    }

    private void bookAflight_Summary() {
        try {
            $(By.className("frame_header_info")).shouldHave(text("Paris to Seattle"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Name does not match expected «Paris to Seattle»");
        }
        try {
            $(By.className("frame_header_info")).shouldHave(text("11/20/2015"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Date flight «Paris to Seattle» is not «11/20/2015»");
        }
        try {
            $(By.className("data_left")).shouldHave(text("Unified Airlines 363"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Name does not match expected «Unified Airlines 363»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("Business"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Class does not match expected «Business»");
        }
        try {
            $(By.className("data_center")).shouldHave(text("281"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Price does not match expected «281»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("Seattle to Paris"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Name does not match expected «Seattle to Paris»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("12/19/2015"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Date flight «Seattle to Paris» is not «12/19/2015»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("Blue Skies Airlines 631"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Name does not match expected «Blue Skies Airlines 631»");
        }
        try {
            $(By.className("data_center_mono")).shouldHave(text("Business"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Class does not match expected «Business»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("273"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Price does not match expected «273»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("2"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("sum of passengers dose not 2");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("$91"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Price taxes does not match expected «$91»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(text("$1199"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Final sum dose not match expected $1199");
        }
    }

    private void bookAflight_Passengers() {
        $(By.name("passFirst0")).setValue("Ivanov");
        $(By.name("passLast0")).setValue("Ivan");
        $(By.name("pass.0.meal")).selectOption(3);
        $(By.name("passFirst1")).setValue("Ivanova");
        $(By.name("passLast1")).setValue("Irina");
        $(By.name("pass.1.meal")).selectOption(1);
    }

    private void bookAflight_CreditCard() {
        $(By.name("creditCard")).selectOption(2);
        $(By.name("creditnumber")).setValue("5479540454132487");
        $(By.name("cc_exp_dt_mn")).selectOption(05);
        $(By.name("cc_exp_dt_yr")).selectOptionContainingText("2009");
        $(By.name("cc_frst_name")).setValue("Ivan");
        $(By.name("cc_mid_name")).setValue("Ivanovich");
        $(By.name("cc_last_name")).setValue("Ivanov");
    }

    private void bookAflight_BillingAddress() {
        $(By.name("billAddress1")).setValue("1085 BorregasAve.");
        $(By.name("billCity")).setValue("Albuquerque");
        $(By.name("billState")).setValue("NewMexico");
        $(By.name("billZip")).setValue("94089");
        $(By.name("billCountry")).selectOptionContainingText("UNITED STATES");
    }

    private void bookAflight_DeliveryAddress() {
        $$(By.name("ticketLess")).get(1).click();
        $(By.name("delAddress1")).setValue("1225 BorregasAve.");
        $(By.name("delCity")).setValue("Boston");
        $(By.name("delState")).setValue("Massachusetts");
        $(By.name("delZip")).setValue("91089");
        $(By.name("delCountry")).selectOptionContainingText("UNITED STATES");
        $(By.name("buyFlights")).click();
    }

    private void flightConfirmation() {
        Assert.assertTrue("Name dose not match expected «FLIGHT CONFIRMATION»", $(By.xpath("//img[@src=\"/images/masts/mast_confirmation.gif\"]")).exists());
    }

    private void flightConfirmation_Departing() {
        try {
            $$(By.className("frame_header_info")).get(2).shouldHave(Condition.matchesText(".*Paris to Seattle.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Departing block does not match expected «Paris to Seattle»");
        }
        try {
            $$(By.className("frame_header_info")).get(2).shouldHave(Condition.matchesText(".*11/20/2015.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Departing block does not match expected «11/20/2015»");
        }
        try {
            $$(By.className("frame_header_info")).get(2).shouldHave(Condition.matchesText(".*Unified Airlines 363.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Departing block does not match expected «Unified Airlines 363»");
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
            System.out.println("Information in the Departing block does not match expected «Unified Airlines 363»");
        }
    }

    private void flightConfirmation_Returning() {
        try {
            $$(By.className("frame_header_info")).get(4).shouldHave(Condition.matchesText(".*Seattle to Paris.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Returning block does not match expected «Seattle to Paris»");
        }
        try {
            $$(By.className("frame_header_info")).get(4).shouldHave(Condition.matchesText(".*12/19/2015.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Returning block does not match expected «12/19/2015»");
        }
        try {
            $$(By.className("frame_header_info")).get(4).shouldHave(Condition.matchesText(".*Blue Skies Airlines 631.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Returning block does not match expected «Blue Skies Airlines 631»");
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
            System.out.println("Information in the Departing block does not match expected «Unified Airlines 363»");
        }
    }

    private void flightConfirmation_Passengers() {
        try {
            $$(By.className("data_left")).get(1).shouldHave(Condition.matchesText(".*" + countPassangers + " passengers.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Passengers block does not match expected «2 passengers»");
        }
    }

    private void flightConfirmation_BilledTo() {
        try {
            $$(By.tagName("tbody")).get(0).shouldHave(Condition.matchesText(".*Ivan Ivanovich Ivanov.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Billed To block does not match expected «Ivan Ivanov Ivanovich»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*1085 BorregasAve.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Billed To block does not match expected «1085 BorregasAve»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*Albuquerque, NewMexico, 94089.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Billed To block does not match expected «Albuquerque, NewMexico, 94089»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*AX 0.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Billed To block does not match expected «AX 0»");
        }
    }

    private void flightConfirmation_DeliveryAddress() {
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*1225 BorregasAve.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Delivery Address block does not match expected «1225 BorregasAve.»");
        }
        try {
            $(By.tagName("tbody")).shouldHave(Condition.matchesText(".*Boston, Massachusetts, 91089.*"));
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Delivery Address block does not match expected «Boston, Massachusetts , 91089»");
        }
    }

    private void flightConfirmation_TotalPrice() { //сравнение FLIGHT CONFIRMATION с суммой данных
        String totalTaxes = null;
        String totalSum = null;
        try {
            String text = $$(Selectors.byXpath(".//tr//td")).find(Condition.matchesText(".*Total.*")).getText();
            text = text.replaceAll("\n", " ");
            Matcher matcher = Pattern.compile(".*Total Taxes\\:\\s*\\$(\\d*).*").matcher(text);
            if (matcher.matches()) {
                totalTaxes = matcher.group(1);
            }
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Delivery Address block does not match expected «Boston, Massachusetts , 91089»");
        }
        try {
            String text = $$(Selectors.byXpath(".//tr//td")).find(Condition.matchesText(".*Total.*")).getText();
            text = text.replaceAll("\n", " ");
            Matcher matcher = Pattern.compile(".*Total Price \\(including taxes\\)\\:\\s*\\$(\\d*).*").matcher(text);
            if (matcher.matches()) {
                totalSum = matcher.group(1);
            }
        } catch (Error e) {
            e.printStackTrace();
            System.out.println("Information in the Delivery Address block does not match expected «Boston, Massachusetts , 91089»");
        }

        BigDecimal bdDepartingSum = new BigDecimal(departingSum);
        BigDecimal bdReturningSum = new BigDecimal(returningSum);
        BigDecimal bdCountPassangers = new BigDecimal(countPassangers);
        BigDecimal bdTotalTaxes = new BigDecimal(totalTaxes);
        BigDecimal bdTotalSum = new BigDecimal(totalSum);
        BigDecimal bdCountedTotalSum = bdDepartingSum.add(bdReturningSum).multiply(bdCountPassangers).add(bdTotalTaxes);
        Assert.assertEquals("Not equals total sum", bdCountedTotalSum, bdTotalSum);
        $(By.xpath("//a[@href=\"mercurywelcome.php\"]")).click();
    }
}



