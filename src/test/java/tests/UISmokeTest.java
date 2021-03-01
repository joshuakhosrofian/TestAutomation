package tests;

import base.UITestBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BoatListingPage;
import pages.HomePage;
import pages.FlightListingPage;


@Listeners(utility.TestListener.class)
public class UISmokeTest extends UITestBase {

    @Test
    public void verifyThatUserCanBookFlight(){
        HomePage home = new HomePage();
        LOG("Going to the home page");
        home.open();
        LOG("User is choosing flight");
        home.chooseFlight();
        IMG("Screenshot");
    }

    @Test
    public void verifyFlightListingsFromIADtoHND(){
        HomePage home = new HomePage();
        FlightListingPage flightListings = new FlightListingPage();
        LOG("Going to the home page");
        home.open();
        LOG("User is choosing FLIGHTS");
        home.chooseFlight();
        LOG("User is choosing option for flight FROM");
        home.chooseFlightFrom();
        LOG("User is choosing option for flight TO");
        home.chooseFlightTo();
        LOG("User is choosing flight departure date (DEPART)");
        home.chooseFlightDepartDate();
        LOG("User clicks SEARCH button");
        home.searchFlight();
        LOG("User goes to Flight Listing Page");
        flightListings.showFlightListing();
        IMG("Screenshot");
    }

    @Test
    public void  verifyBoookingOptionsForBoat(){
        HomePage home = new HomePage();
        BoatListingPage bookingOption = new BoatListingPage();
        LOG("Going to the home page");
        home.open();
        LOG("User is choosing BOATS");
        home.chooseBoat();
        LOG("User is choosing option for boat DESTINATION");
        home.chooseBoatDestination();
        LOG("User is choosing option for boat TYPE");
        home.chooseBoatType();
        LOG("User is choosing boat departure DATE");
        home.chooseBoatDate();
        LOG("User clicks SEARCH button");
        home.searchForBoat();
        LOG("User goes to Boat Listing Page");
        bookingOption.showBoatBookingOption();
        IMG("Screenshot");
    }

}
