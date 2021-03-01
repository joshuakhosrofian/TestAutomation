package pages;

import base.UserActions;
import org.openqa.selenium.By;

public class BoatListingPage extends UserActions {

    private By boatBookingOption = css(".align-items-end.d-flex.fe");

    public void showBoatBookingOption(){
        highlight(boatBookingOption);
    }
}
