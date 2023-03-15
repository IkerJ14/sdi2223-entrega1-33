package com.uniovi.sdi2223entrega133;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import pageobjects.*;
import util.SeleniumUtils;

import java.util.List;

@SpringBootTest
class Sdi2223Entrega133ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Dev\\tools\\selenium\\geckodriver-v0.30.0-win64.exe";
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    //static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
//Común a Windows y a MACOSX
    static WebDriver driver = getDriver(PathFirefox, Geckodriver);
    static String URL = "http://localhost:8080";

    public static WebDriver getDriver(String PathFirefox, String Geckodriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckodriver);
        driver = new FirefoxDriver();
        return driver;
    }

    @BeforeEach
    public void setUp() {
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeAll
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterAll
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    @Order(15)
    public void PR15() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");


        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");
        // Se pincha en agregar oferta
        elements.get(0).click();
        String checkText = "Oferta nueva 1";
        PO_OfferAddView.fillOfferAddForm(driver, checkText, "Esto es la descripción de una oferta", "50");

        // Se comprueba que la nueva oferta aparece en la página
        elements = PO_View.checkElementBy(driver, "text", checkText);
        Assertions.assertEquals(checkText, elements.get(0).getText());

        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    @Test
    @Order(16)
    public void PR16() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");


        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/add')]");

        elements.get(0).click();
        // Se intenta añadir una oferta con precio negativo
        PO_OfferAddView.fillOfferAddForm(driver, "Oferta nueva 1", "Esto es la descripción de una oferta", "-50");

        // Se comprueba que aparece el mensaje de error correspondiente
        List<WebElement> result = PO_UserOffersView.checkElementByKey(driver, "Error.offer.price.negative",
                PO_Properties.getSPANISH());
        String checkText = PO_HomeView.getP().getString("Error.offer.price.negative",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText, result.get(0).getText());

        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    @Test
    @Order(17)
    public void PR17() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user02@email.com", "user02");

        // Si se loguea como usuario estándar correctamente, se accede a la vista de las ofertas propias
        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());

        // Se deben mostrar las 10 ofertas propias existentes
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(10, markList.size());

        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

}
