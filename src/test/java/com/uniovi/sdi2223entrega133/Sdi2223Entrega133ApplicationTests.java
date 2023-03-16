package com.uniovi.sdi2223entrega133;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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

    // ---------------- Tests apartado 1 ----------------

    // ---------------- Tests apartado 2 ----------------

    // ---------------- Tests apartado 3 ----------------

    // ---------------- Tests apartado 4 ----------------

    // ---------------- Tests apartado 5 ----------------

    // ---------------- Tests apartado 6 ----------------

    /*
        Ir al formulario de alta de oferta, rellenarla con datos válidos y pulsar el botón Enviar.
        Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
     */
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

    /*
        Ir al formulario de alta de oferta, rellenarla con datos inválidos (precio negativo) y pulsar el
        botón Enviar. Comprobar que se muestra el mensaje de campo inválido.
     */
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

    // ---------------- Tests apartado 7 ----------------

    /*
        Mostrar el listado de ofertas para dicho usuario y comprobar que se muestran todas los que
        existen para este usuario.
     */
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

    // ---------------- Tests apartado 8 ----------------

    // ---------------- Tests apartado 9 ----------------

    // ---------------- Tests apartado 10 ---------------

    /*
        Sobre una búsqueda determinada (a elección del desarrollador), comprar una oferta que deja
        un saldo positivo en el contador del comprador. Comprobar que el contador se actualiza correctamente
        en la vista del comprador.
     */
    @Test
    @Order(22)
    public void PR22() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");
        // Clicamos el menú de ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista global de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();
        // Buscamos ofertas por 'Albornoz' (cuesta 20€)
        WebElement buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("Albornoz");
        driver.findElement(By.className("btn")).click();
        // Comprobamos que el usuario tiene 100€
        PO_View.checkElementBy(driver, "text", "100.0€");
        // Compramos la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Comprobamos que el saldo se ha reducido
        PO_View.checkElementBy(driver, "text", "80.0€");
    }

    /*
        Sobre una búsqueda determinada (a elección del desarrollador), comprar una oferta que deja
        un saldo 0 en el contador del comprador. Comprobar que el contador se actualiza correctamente en la
        vista del comprador.
     */
    @Test
    @Order(23)
    public void PR23() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user02@email.com", "user02");
        // Clicamos el menú de ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista global de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();
        // Buscamos ofertas por 'Mesita' (cuesta 100€)
        WebElement buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("Mesita");
        driver.findElement(By.className("btn")).click();
        // Comprobamos que el usuario tiene 100€
        PO_View.checkElementBy(driver, "text", "100.0€");
        // Compramos la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Comprobamos que el saldo se ha reducido
        PO_View.checkElementBy(driver, "text", "0.0€");
    }

    /*
        Sobre una búsqueda determinada (a elección del desarrollador), intentar comprar una oferta
        que esté por encima de saldo disponible del comprador. Y comprobar que se muestra el mensaje de
        saldo no suficiente.
     */
    @Test
    @Order(24)
    public void PR24() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user03@email.com", "user03");
        // Clicamos el menú de ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista global de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();
        // Buscamos ofertas por 'Mesa' (cuesta 120€)
        WebElement buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("Mesa");
        driver.findElement(By.className("btn")).click();
        // Comprobamos que el usuario tiene 100€
        PO_View.checkElementBy(driver, "text", "100.0€");
        // Intentamos comprar la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Comprobamos que se muestra el error
        PO_View.checkElementBy(driver, "text", "El usuario no dispone de dinero suficiente para comprar la oferta");
    }

    // ---------------- Tests apartado 11 ---------------

    /*
        Ir a la opción de ofertas compradas del usuario y mostrar la lista. Comprobar que aparecen
        las ofertas que deben aparecer.
     */
    @Test
    @Order(25)
    public void PR25() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user05@email.com", "user05");
        // Clicamos el menú de ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista global de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();
        // Primero buscamos ofertas por 'Cortacesped' (no podemos comprarla)
        WebElement buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("Cortacesped");
        driver.findElement(By.className("btn")).click();
        // Intentamos comprar la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Ahora buscamos por 'pescar' (podemos comprarla)
        buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("pescar");
        driver.findElement(By.className("btn")).click();
        // Compramos la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Volvemos a la lista de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Volver al catálogo')]");
        elements.get(0).click();
        // Buscamos por 'libro' (también podemos comprarla)
        buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("libro");
        driver.findElement(By.className("btn")).click();
        // Compramos la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Comprobamos que aparecen las ofertas que hemos comprado
        PO_View.checkElementBy(driver, "text", "Libro");
        PO_View.checkElementBy(driver, "text", "Caña de pescar");
    }

    // ---------------- Tests apartado 12 ---------------

    @Test
    @Order(26)
    public void PR26() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user05@email.com", "user05");
        // Clicamos el menú de ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista global de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();
        // Ahora buscamos por 'pescar' (podemos conversar)
        WebElement buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("pescar");
        driver.findElement(By.className("btn")).click();
        //Accedemos a la conversación
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'conversation/69/user05@email')]");
        elements.get(0).click();
        //Ponemos un mensaje
        PO_ConversationView.fillMessageAddForm(driver, "Hola");
        // Comprobamos que aparece el mensaje
        PO_View.checkElementBy(driver, "text", "Hola");
        //Hacemos logout
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    @Test
    @Order(27)
    public void PR27() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user05@email.com", "user05");
        // Clicamos el menú de ofertas
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista global de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();
        // Ahora buscamos por 'pescar' (podemos conversar)
        WebElement buscador = driver.findElement(By.name("searchText"));
        buscador.click();
        buscador.clear();
        buscador.sendKeys("pescar");
        driver.findElement(By.className("btn")).click();
        //Accedemos a la conversación
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'conversation/69/user05@email')]");
        elements.get(0).click();
        // Comprobamos que aparece el mensaje anterior
        PO_View.checkElementBy(driver, "text", "Hola");
        //Ponemos un mensaje
        PO_ConversationView.fillMessageAddForm(driver, "Buenas");
        // Comprobamos que aparece el mensaje anterior
        PO_View.checkElementBy(driver, "text", "Buenas");
        //Hacemos logout
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    // ---------------- Tests apartado 13 ---------------

    @Test
    @Order(28)
    public void PR28() {
        // Iniciamos sesión
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user05@email.com", "user05");
        // Clicamos el menú de conversaciones
        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'conversation-menu')]/a");
        elements.get(0).click();
        // Y vamos a la lista de conversaciones activas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'conversation/list')]");
        elements.get(0).click();
        //Accedemos a la conversación (Solo deberia estar la que usamos en los 2 anteriores tests)
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'conversation/69/user05@email')]");
        elements.get(0).click();
        // Comprobamos que aparece los mensajes anteriores
        PO_View.checkElementBy(driver, "text", "Hola");
        PO_View.checkElementBy(driver, "text", "Buenas");
        //Ponemos otro mensaje
        PO_ConversationView.fillMessageAddForm(driver, "Adios");
        // Comprobamos que aparece el mensaje anterior
        PO_View.checkElementBy(driver, "text", "Adios");
        //Hacemos logout
        PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
    }

    // ---------------- Tests apartado 14 ---------------

    // ---------------- Tests apartado 15 ---------------

}
