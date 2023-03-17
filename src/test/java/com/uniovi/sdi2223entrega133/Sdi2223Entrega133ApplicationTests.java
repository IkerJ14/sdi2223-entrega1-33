package com.uniovi.sdi2223entrega133;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import pageobjects.*;
import util.SeleniumUtils;

import java.util.List;

@SpringBootTest
class Sdi2223Entrega133ApplicationTests {

    static String PathFirefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    //static String Geckodriver = "C:\\Path\\geckodriver-v0.30.0-win64.exe";
    static String Geckodriver = "C:\\Users\\ikerj\\Desktop\\PL-SDI-Sesion5-material\\geckodriver-v0.30.0-win64.exe";
    //static String PathFirefox = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
    //static String Geckodriver = "/Users/USUARIO/selenium/geckodriver-v0.30.0-macos";
    // Común a Windows y a MACOSX
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
    @Order(17)
    public void PR17() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

        // Si se loguea como usuario estándar correctamente, se accede a la vista de las ofertas propias
        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());

        // Se deben mostrar las 10 ofertas propias existentes
        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(10, markList.size());
    }

    @Test
    @Order(33)
    public void PR33() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

        // Si se loguea como usuario admin correctamente, se accede a la vista de los usuarios
        PO_LogsView.clickLogOp(driver, "logDropdown", "id", "logDropdown");

        PO_LogsView.clickLogOp(driver, "logListOp", "id", "logListOp");

        PO_LogsView.clickLogOp(driver, "btn btn-danger", "class", "btn btn-danger");

        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody",
                PO_View.getTimeout());

        Assertions.assertEquals(0, markList.get(0).findElements(By.className("filaLog")).size());
    }

    @Test
    @Order(34)
    public void PR34() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "adn@email.com", "admin");

        PO_LoginView.fillLoginForm(driver, "admin@email.com", "admin");

        // Si se loguea como usuario admin correctamente, se accede a la vista de los usuarios
        PO_LogsView.clickLogOp(driver, "logDropdown", "id", "logDropdown");

        PO_LogsView.clickLogOp(driver, "logListOp", "id", "logListOp");

        List<WebElement> markList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        System.out.println(markList.get(0).findElements(By.xpath("//*[@id=\"tableLogs\"]/table/tbody/tr")).get(3).getText());
        Assertions.assertTrue( markList.get(0).findElements(By.xpath("//*[@id=\"tableLogs\"]/table/tbody/tr")).get(1).getText().contains("GET"));
        Assertions.assertTrue( markList.get(0).findElements(By.xpath("//*[@id=\"tableLogs\"]/table/tbody/tr")).get(2).getText().contains("LOGIN-EX El usuario admin@email.com"));
        Assertions.assertTrue( markList.get(0).findElements(By.xpath("//*[@id=\"tableLogs\"]/table/tbody/tr")).get(3).getText().contains("LOGIN_ERR"));
    }
}
