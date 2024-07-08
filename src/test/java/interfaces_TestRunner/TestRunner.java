package interfaces_TestRunner;/*
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
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class TestRunner {
    public static final String BASIC_URL = "https://trs.test.abt.team/4172ultru_category_banners/admin.php?dispatch=addons.manage";

    @BeforeMethod
    public void openBrowser() {
        Configuration.browser = "chrome";
        open(BASIC_URL);
        Configuration.holdBrowserOpen = false; //не закрываем браузер пока ведём разработку
        Configuration.screenshots = true; //делаем скриншоты при падении
        WebDriverRunner.getWebDriver().manage().window().maximize(); //окно браузера на весь экран
        $(".btn.btn-primary").click();
        $("#bp_off_bottom_panel").click();
    }

    @AfterMethod
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    public void selectLanguage_RTL() {
        $("a[id*='_wrap_language_']").scrollTo().click();
        $(".ty-select-block__list-item a[data-ca-name='ar']").click();
    }
}