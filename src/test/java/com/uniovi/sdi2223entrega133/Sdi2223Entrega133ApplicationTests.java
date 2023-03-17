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
    //static String Geckodriver = "C:\\Lab\\PL-SDI-Sesión5-material\\geckodriver-v0.30.0-win64.exe";
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
    @Test
    @Order(1)
    public void PR01() {
        //Vamos al formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        //Rellenamos el formulario.
        PO_Signup.fillForm(driver, "user16@email.com", "Frodo", "Bolson", "user16", "user16");
        //Comprobamos que entramos en la sección privada y nos nuestra el texto a buscar
        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());
    }

    @Test
    @Order(2)
    public void PR02() {
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        PO_Signup.fillForm(driver, "", "", "", "17777", "17777");
        List<WebElement> result = PO_Signup.checkElementByKey(driver, "Error.empty",
                PO_Properties.getSPANISH() );
        String checkText = PO_HomeView.getP().getString("Error.empty",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }

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
        List<WebElement> offerList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(10, offerList.size());
    }

    // ---------------- Tests apartado 8 ----------------

    /*
        Ir a la lista de ofertas, borrar la primera oferta de la lista, comprobar que la lista se actualiza y
        que la oferta desaparece.
     */
    @Test
    @Order(18)
    public void PR18() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user09@email.com", "user09");

        // Se obtiene el título de la primera oferta
        List<WebElement> elements = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        String checkText = elements.get(0).getText().split(" ")[0];

        // Se da de baja la primera oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Dar de baja')]");
        elements.get(0).click();

        // Se comprueba que ya no aparezca
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, checkText, PO_View.getTimeout());
    }

    /*
        Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar que la lista se actualiza y
        que la oferta desaparece.
     */
    @Test
    @Order(19)
    public void PR19() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user09@email.com", "user09");

        // Se obtiene el título de la primera oferta
        List<WebElement> elements = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());

        int lastPos = elements.size() - 1;
        String checkText = elements.get(lastPos).getText().split(" ")[0];

        // Se da de baja la primera oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Dar de baja')]");
        elements.get(lastPos).click();

        // Se comprueba que ya no aparezca
        SeleniumUtils.waitTextIsNotPresentOnPage(driver, checkText, PO_View.getTimeout());
    }

    // ---------------- Tests apartado 9 ----------------

    /*
        Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que
        corresponde con el listado de las ofertas existentes en el sistema
     */
    @Test
    @Order(20)
    public void PR20() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

        // Si se loguea como usuario estándar correctamente, se accede a la vista de las ofertas propias
        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        // Se pincha en ver ofertas y se comprueba que se accede
        elements.get(0).click();
        PO_OffersView.checkOffersPage(driver, PO_Properties.getSPANISH());

        // Se hace una búsqueda con el texto en blanco
        PO_OffersView.searchOffers(driver, "");

        // Se comprueba que aparece el texto de la página de búsqueda de ofertas y que salen 5 ofertas
        PO_OffersView.checkOffersPage(driver, PO_Properties.getSPANISH());
        
        List<WebElement> offerList = SeleniumUtils.waitLoadElementsBy(driver, "free", "//tbody/tr",
                PO_View.getTimeout());
        Assertions.assertEquals(5, offerList.size());
    }

    /*
        Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se
        muestra la página que corresponde, con la lista de ofertas vacía.
     */
    @Test
    @Order(21)
    public void PR21() {
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

        // Si se loguea como usuario estándar correctamente, se accede a la vista de las ofertas propias
        PO_UserOffersView.checkUserOffersPage(driver, PO_Properties.getSPANISH());

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        // Se pincha en ver ofertas y se comprueba que se accede
        elements.get(0).click();
        PO_OffersView.checkOffersPage(driver, PO_Properties.getSPANISH());

        // Se hace una búsqueda con el texto en blanco
        PO_OffersView.searchOffers(driver, "Oferta inexistente");

        // Se comprueba que aparece el texto de la página de búsqueda de ofertas y el mensaje de que no hay ofertas
        PO_OffersView.checkOffersPage(driver, PO_Properties.getSPANISH());

        PO_UserOffersView.checkElementByKey(driver, "offer.empty",
                PO_Properties.getSPANISH());
    }

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
        PO_OffersView.searchOffers(driver, "Albornoz");
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
        PO_OffersView.searchOffers(driver, "Mesita");
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
        PO_OffersView.searchOffers(driver, "Mesa");
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
        PO_OffersView.searchOffers(driver, "Cortacesped");
        // Intentamos comprar la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Ahora buscamos por 'pescar' (podemos comprarla)
        PO_OffersView.searchOffers(driver, "pescar");
        // Compramos la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Volvemos a la lista de ofertas
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Volver al catálogo')]");
        elements.get(0).click();
        // Buscamos por 'libro' (también podemos comprarla)
        PO_OffersView.searchOffers(driver, "libro");
        // Compramos la oferta
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Comprar')]");
        elements.get(0).click();
        // Comprobamos que aparecen las ofertas que hemos comprado
        PO_View.checkElementBy(driver, "text", "Libro");
        PO_View.checkElementBy(driver, "text", "Caña de pescar");
    }

    // ---------------- Tests apartado 12 ---------------

    /*
         Sobre una búsqueda determinada de ofertas (a elección de desarrollador), enviar un mensaje
        a una oferta concreta. Se abriría dicha conversación por primera vez. Comprobar que el mensaje aparece
        en la conversación.
     */
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
        PO_OffersView.searchOffers(driver, "pescar");
        //Accedemos a la conversación
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Conversación')]");
        elements.get(0).click();
        //Ponemos un mensaje
        PO_ConversationView.fillMessageAddForm(driver, "Hola");
        // Comprobamos que aparece el mensaje
        PO_View.checkElementBy(driver, "text", "Hola");
    }

    /*
        Enviar un mensaje a una conversación ya existente accediendo desde el botón/enlace
        “Conversación”. Comprobar que el mensaje aparece en la conversación.
     */
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
        PO_OffersView.searchOffers(driver, "pescar");
        //Accedemos a la conversación
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Conversación')]");
        elements.get(0).click();
        // Comprobamos que aparece el mensaje anterior
        PO_View.checkElementBy(driver, "text", "Hola");
        //Ponemos un mensaje
        PO_ConversationView.fillMessageAddForm(driver, "Buenas");
        // Comprobamos que aparece el mensaje anterior
        PO_View.checkElementBy(driver, "text", "Buenas");
    }

    // ---------------- Tests apartado 13 ---------------

    /*
        Mostrar el listado de conversaciones ya abiertas. Comprobar que el listado contiene la
        cantidad correcta de conversaciones.
     */
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
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(text(), 'Conversación')]");
        elements.get(0).click();
        // Comprobamos que aparece los mensajes anteriores
        PO_View.checkElementBy(driver, "text", "Hola");
        PO_View.checkElementBy(driver, "text", "Buenas");
        //Ponemos otro mensaje
        PO_ConversationView.fillMessageAddForm(driver, "Adios");
        // Comprobamos que aparece el mensaje anterior
        PO_View.checkElementBy(driver, "text", "Adios");
    }

    // ---------------- Tests apartado 14 ---------------
    /*
        Visualizar al menos cuatro páginas en español/inglés/español (comprobando que algunas de
        las etiquetas cambian al idioma correspondiente). Ejemplo, Página principal/Opciones Principales de
        Usuario/Listado de Usuarios.
     */
    @Test
    @Order(29)
    public void PR29() {
        // Se comprueban los textos del formulario de registro
        PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
        // Se comprueban en español
        List<WebElement> result = PO_UserOffersView.checkElementByKey(driver, "signup.header",
                PO_Properties.getSPANISH());
        String checkText = PO_HomeView.getP().getString("signup.header",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "signup.lastname",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("signup.lastname",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "signup.name",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("signup.name",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        // Se cambia el idioma al inglés y se vuelven a comprobar
        PO_NavView.changeLanguage(driver, "btnEnglish");

        result = PO_UserOffersView.checkElementByKey(driver, "signup.message",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("signup.message",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "signup.header",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("signup.header",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "signup.name",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("signup.name",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        // Se cambia el idioma al español y se vuelven a comprobar
        PO_NavView.changeLanguage(driver, "btnSpanish");

        result = PO_UserOffersView.checkElementByKey(driver, "signup.message",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("signup.message",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "signup.header",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("signup.header",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "signup.name",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("signup.name",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        // Se comprueban los textos del formulario de login
        PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
        // Se comprueban en español
//        result = PO_UserOffersView.checkElementByKey(driver, "login.message",
//                PO_Properties.getSPANISH());
//        checkText = PO_HomeView.getP().getString("login.message",
//                PO_Properties.getSPANISH());
//        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "login.header",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("login.header",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "login.mail",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("login.mail",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        // FIXME: Comprobar contraseña en vez de titulo

        // Se cambia el idioma al inglés y se vuelven a comprobar
        PO_NavView.changeLanguage(driver, "btnEnglish");

//        result = PO_UserOffersView.checkElementByKey(driver, "login.message",
//                PO_Properties.getENGLISH());
//        checkText = PO_HomeView.getP().getString("login.message",
//                PO_Properties.getENGLISH());
//        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "login.header",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("login.header",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "login.mail",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("login.mail",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        // Se cambia el idioma al español y se vuelven a comprobar
        PO_NavView.changeLanguage(driver, "btnSpanish");

//        result = PO_UserOffersView.checkElementByKey(driver, "login.message",
//                PO_Properties.getSPANISH());
//        checkText = PO_HomeView.getP().getString("login.message",
//                PO_Properties.getSPANISH());
//        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "login.header",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("login.header",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "login.mail",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("login.mail",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        PO_LoginView.fillLoginForm(driver, "user01@email.com", "user01");

        // Se comprueba la vista de ofertas propias
        result = PO_UserOffersView.checkElementByKey(driver, "offer.user-title",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.user-title",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.user-description",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.user-description",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.detail",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.detail",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnEnglish");
        result = PO_UserOffersView.checkElementByKey(driver, "offer.user-title",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("offer.user-title",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.user-description",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("offer.user-description",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.detail",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("offer.detail",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnSpanish");
        result = PO_UserOffersView.checkElementByKey(driver, "offer.user-title",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.user-title",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.user-description",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.user-description",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.detail",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.detail",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        List<WebElement> elements = PO_View.checkElementBy(driver, "free", "//li[contains(@id, 'offer-menu')]/a");
        elements.get(0).click();
        elements = PO_View.checkElementBy(driver, "free", "//a[contains(@href, 'offer/list')]");
        elements.get(0).click();

        // Se comprueba la vista de todas las ofertas

        result = PO_UserOffersView.checkElementByKey(driver, "offer.list-title",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.list-title",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.list-description",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.list-description",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.sell",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.sell",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnEnglish");
        result = PO_UserOffersView.checkElementByKey(driver, "offer.list-title",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("offer.list-title",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.list-description",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("offer.list-description",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.sell",
                PO_Properties.getENGLISH());
        checkText = PO_HomeView.getP().getString("offer.sell",
                PO_Properties.getENGLISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        PO_NavView.changeLanguage(driver, "btnSpanish");
        result = PO_UserOffersView.checkElementByKey(driver, "offer.list-title",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.list-title",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.list-description",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.list-description",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());

        result = PO_UserOffersView.checkElementByKey(driver, "offer.sell",
                PO_Properties.getSPANISH());
        checkText = PO_HomeView.getP().getString("offer.sell",
                PO_Properties.getSPANISH());
        Assertions.assertEquals(checkText , result.get(0).getText());
    }
    // ---------------- Tests apartado 15 ---------------

}
