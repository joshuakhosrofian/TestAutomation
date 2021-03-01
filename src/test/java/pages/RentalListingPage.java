package pages;

import base.UserActions;
import org.openqa.selenium.By;

public class RentalListingPage extends UserActions {
    private By rentalListing = css(".align-items-end.d-flex.fe");

    public void showRentalListings(){
        highlight(rentalListing);
    }

}
