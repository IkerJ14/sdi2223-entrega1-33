package pageobjects;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import util.*;

import java.util.*;

public class PO_LogsView extends PO_NavView{

    public static void clickLogOp(WebDriver driver, String textOption, String criterio, String targetText) {
        //CLickamos en la opción de registro y esperamos a que se cargue el enlace de Registro.
        List<WebElement> elements = SeleniumUtils.waitLoadElementsBy(driver, criterio, textOption,
                getTimeout());
        //Tiene que haber un sólo elemento.
        Assertions.assertEquals(1, elements.size());
        //Ahora lo clickamos
        elements.get(0).click();
    }
}
