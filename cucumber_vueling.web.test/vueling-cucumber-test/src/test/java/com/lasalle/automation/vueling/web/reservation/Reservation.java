package com.lasalle.automation.vueling.web.reservation;

import com.lasalle.automation.vueling.reservation.domain.Finder;
import com.lasalle.automation.vueling.reservation.service.ResultPage;
import com.lasalle.automation.vueling.reservation.service.ServiceReservation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class Reservation {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static WebDriver driver;

    private ServiceReservation searchPage;
    private ResultPage resultPage;

    private List<Finder> searches;

    @Before
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\home\\s2o\\tmp\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        LOGGER.debug("driver started");
        driver.navigate().to("https://www.vueling.com/es");
        List<WebElement> buttonCookies = driver.findElements((By.cssSelector("button[id='ensCloseBanner']")));
        buttonCookies.get(0).click();
    }

    @After
    public static void afterClass() {
        driver.close();
        LOGGER.debug("driver closed");
    }
    
    @Given("^I'm main page$")
    public void iMMainPage() {
        // Write code here that turns the phrase above into concrete actions
        LOGGER.debug("I'm main page started");
        driver.get("https://www.vueling.com/es");
    }

    @When("^I try to find a fly$")
    public void iTryToFindAFly(List<Finder> searchDtoList) {
        LOGGER.debug("Destination: [{}]", searchDtoList.get(0).getDestination());
        searches = searchDtoList;
        searchPage.setDriver(driver);
        searchPage.searchFlight(searches.get(0));
    }

    @Then("^I get available flight$")
    public void iGetAvailableFlight() {
        resultPage.setDriver(driver);
        String actualDestination = resultPage.getDestination();
        assertThat(actualDestination).isEqualTo(searches.get(0).getDestination());
        Boolean isThereAvailabilityTable = resultPage.isThereAvailabilityTable();
        assertThat(isThereAvailabilityTable).isEqualTo(true);
    }
}
