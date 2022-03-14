package com.lasalle.automation.vueling.reservation.service;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;

public class ResultPage extends PageObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(how = How.CLASS_NAME, using = "travelInfo_headerTitle")
    private WebElementFacade destination;

    @FindBy(how = How.ID, using = "availabilityTable0")
    private WebElementFacade availabilityTable;

    public String getDestination() {
        return destination.getText();
    }

    public boolean isThereAvailabilityTable() {
        return availabilityTable.isPresent();
    }
}
