package pages;

import base.UserActions;
import org.openqa.selenium.By;

public class HomePage extends UserActions {

    // Flight Elements
    private By flightButton = css(".flights.text-center");
    private By flightFrom = css("div#s2id_location_from");
    private By flightTo = css("div#s2id_location_to");
    private By flightDepartDate = css("input#FlightsDateStart");
    private By searchFlightButton = css("div#flights > .menu-horizontal-content.tab-inner > .form-search-main-01 > form[role='search'] .btn.btn-block.btn-primary");

    // Boat Elements
    private By boatButton = css(".boats.text-center");
    private By boatDestination = css("div#select2-drop  .select2-input");
    private By boatType = css("div#boattype_chosen  span");
    private By boatDate = css("[class] [aria-labelledby='home-tab']:nth-of-type(3) [placeholder='dd\\/mm\\/yyyy']");
    private By searchBoatButton = css("div#boats > .ftab-inner.menu-horizontal-content  form[role='search'] .btn.btn-block.btn-primary");

    // Rental Elements
    private By rentalsButton = css(".rentals.text-center");
    private By rentalDestination = css("div#select2-drop  .select2-input");
    private By rentalType = css("div#rentaltype_chosen  span");
    private By rentalDate = css(".col-lg-4.col-sm-12.col-xs-12 div#airDatepickerRange-hotel input#DateTours");
    private By searchRentalsButton = css("div#rentals > .ftab-inner.menu-horizontal-content  form[role='search'] .btn.btn-block.btn-primary");

    public void open(){
        gotoSite("https://www.phptravels.net/");
    }

    // Flight Methods

    public void chooseFlight(){
        click(flightButton);
    }

    public void chooseFlightFrom(){
        click(flightFrom);
    }

    public void chooseFlightTo(){
        click(flightTo);
    }

    public void chooseFlightDepartDate(){
        click(flightDepartDate);
    }

    public void searchFlight(){
        click(searchFlightButton);
    }

    // Boat Methods
    public void chooseBoat(){
        click(boatButton);
    }

    public void chooseBoatDestination(){
        click(boatDestination);
    }

    public void chooseBoatType(){
        click(boatType);
    }

    public void chooseBoatDate(){
        click(boatDate);
    }

    public void searchForBoat(){
        click(searchBoatButton);
    }

    //Rental Methods
    public void chooseRentalsButton(){
        click(rentalsButton);
    }

    public void chooseRentalDestination(){
        click(rentalDestination);
    }

    public void chooseRentalType(){
        click(rentalType);
    }

    public void chooseRentalDate(){
        click(rentalDate);
    }

    public void searchForRentals(){
        click(searchRentalsButton);
    }
}
