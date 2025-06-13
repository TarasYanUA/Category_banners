package TestRunner;/*
Модуль "Баннеры для категорий" v3.0.0 + тема Юни2(UltRu) v4.18.1d. Можно установить весь пакет темы Юни2.
Работает в браузерах Chrome и Edge!

Запустить тесты можно:
 1) Через файл TestNG.xml
 2) Через Surefire отчёт: перейти в "Терминал" и ввести "mvn clean test". После этого в папке "target -> surefire-reports"
 открыть файл "index.html" с помощью браузера (правая кнопка мыши -> Open in -> Browser).
*/

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import storefront.CollectAssertMessages;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class TestRunner {
    public static final String BASIC_URL = "https://trs.test.abt.team/4183ultru/admin.php?dispatch=addons.manage";
    private SoftAssert softAssert;

    @BeforeMethod
    public void openBrowser() {
        Configuration.browser = "chrome";
        open(BASIC_URL);
        Configuration.screenshots = true; //делаем скриншоты при падении
        Configuration.savePageSource = false; //не создавать html файлы при создании скриншотов
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран

        softAssert = new SoftAssert();
        CollectAssertMessages.setSoftAssertions(softAssert);

        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }

    @AfterMethod
    public void closeBrowser() {
        softAssert = CollectAssertMessages.getSoftAssertions();
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("\nОшибки в asserts:");
            System.out.println(e.getMessage());
        }

        sleep(2000);
        Selenide.closeWebDriver();
    }

    public void selectLanguage_RTL() {
        $("a[id*='_wrap_language_']").scrollTo().click();
        $(".ty-select-block__list-item a[data-ca-name='ar']").click();
        Selenide.sleep(3000);
    }
}