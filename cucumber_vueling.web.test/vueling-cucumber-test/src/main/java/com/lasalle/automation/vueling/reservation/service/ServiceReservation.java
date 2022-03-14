package com.lasalle.automation.vueling.reservation.service;

import com.lasalle.automation.vueling.reservation.domain.CalendarDTO;
import com.lasalle.automation.vueling.reservation.domain.Finder;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServiceReservation extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(how = How.CSS, using = ".searchbar-container .searchbar-main .searchbar-main_tab-content .searchbar-main_tab-content_pane .form-group>.form-input.origin .input-group input")
    private WebElementFacade origin;

    @FindBy(how = How.CSS, using = ".searchbar-container .searchbar-main .searchbar-main_tab-content .searchbar-main_tab-content_pane .form-group>.form-input.destination .input-group input")
    private WebElementFacade destination;

    @FindBy(how = How.ID, using = "btnSubmitHomeSearcher")
    private WebElementFacade btnSearch;

    @FindBy(how = How.CSS, using = "td:not(.ui-state-disabled)")
    private List<WebElementFacade> listOfAvailableDays;

    @FindBy(how = How.CLASS_NAME, using = "addReturnFlight")
    private WebElementFacade addReturnLink;

    @FindBy(how = How.CSS, using = "#passengers-popup > ul:nth-child(2) > li:nth-child(1) > div.passengers-popup_main_counter > span.icon.vy-icon-rounded-more")
    private WebElementFacade addPassengerButton;

    public void searchFlight(Finder search) {
        LOGGER.debug("searchFlight starts, reservation: [{}]", search);
        setOrigin(search.getOrigin());
        setDestination(search.getDestination());
        setDate(search.getOutboundFly());
        setPassengers(Integer.parseInt(search.getPassengers()));
        setReturnDate(search.getReturnFly());
        btnSearch.click();
    }

    public void setOrigin(String originCity) {
        origin.typeAndEnter(originCity);
    }

    public void setDestination(String destinationCity) {
        destination.typeAndEnter(destinationCity);
    }

    public void setDate(String dateKey){

        Date outboundDate = CalendarDTO.fromString(dateKey);

        int day = Integer.parseInt(new SimpleDateFormat("dd").format(outboundDate));
        int lastDayElementChecked = Integer.parseInt(listOfAvailableDays.get(0).getText());

        for(WebElementFacade dayElement : listOfAvailableDays){
            int dayNumber = Integer.parseInt(dayElement.getText());

            if(dayNumber >= day || dayNumber < lastDayElementChecked ) {
                dayElement.click();
                break;
            }

            lastDayElementChecked = dayNumber;
        }

    }

    public void setReturnDate(String dateKey) {

        if (dateKey.isBlank()){
            return;
        }

        addReturnLink.click();
        setDate(dateKey);
    }

    public void setPassengers(int numberOfPassengers) {

        for (int i = 2; i <= numberOfPassengers; i++) {
            addPassengerButton.click();
        }
    }

}
