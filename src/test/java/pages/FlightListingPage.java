package pages;

import base.UserActions;
import org.openqa.selenium.By;

public class FlightListingPage extends UserActions {

    private By flightListing = css(".post-heading.text-muted");

    public void showFlightListing(){
        highlight(flightListing);
    }
}
