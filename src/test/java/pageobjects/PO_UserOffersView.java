package pageobjects;

import org.openqa.selenium.WebDriver;
import util.SeleniumUtils;

public class PO_UserOffersView extends PO_NavView {
    static public void checkUserOffersPage(WebDriver driver, int language) {
        //Esperamos a que se cargue el saludo de bienvenida en Español
        SeleniumUtils.waitLoadElementsBy(driver, "text", p.getString("offer.user-title", language),
                getTimeout());
    }
}
