package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_ConversationView extends PO_NavView {
    static public void fillMessageAddForm(WebDriver driver, String messagep) {
        WebElement title = driver.findElement(By.name("textMessage"));
        title.click();
        title.clear();
        title.sendKeys(messagep);
        //Pulsar el boton de Alta.
        By boton = By.className("btn");
        driver.findElement(boton).click();
    }
}