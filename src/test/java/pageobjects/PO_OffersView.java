package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.SeleniumUtils;

public class PO_OffersView extends PO_NavView {
    static public void checkOffersPage(WebDriver driver, int language) {
        //Esperamos a que se cargue el saludo de bienvenida en Español
        SeleniumUtils.waitLoadElementsBy(driver, "text", p.getString("offer.list-title", language),
                getTimeout());
    }

    static public void searchOffers(WebDriver driver, String searchText) {
        WebElement title = driver.findElement(By.name("searchText"));
        title.click();
        title.clear();
        title.sendKeys(searchText);
        //Pulsar el boton de Alta.
        By boton = By.id("searchButton");
        driver.findElement(boton).click();
    }
}
