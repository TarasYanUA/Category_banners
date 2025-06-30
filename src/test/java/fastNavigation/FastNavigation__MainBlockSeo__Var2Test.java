package fastNavigation;

import TestRunner.TestRunner;
import admin.BannersManagementPage;
import admin.Block_FastNavigation;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import storefront.CategoryPage;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/*
* Устанавливаем модуль "Быстрая навигация"
* Создаём блок "Быстрая навигация"

Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Не использовать
    - Скруглить углы блоков, окон, баннеров -   нет
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Без рамки
    - Добавить фон/маску для изображений товара -   нет

Настройки баннера:
    * Тип контента -    Блок
    * Блок -            Быстрая навигация
    * На всю ширину -   да
    * Оболочка -        AB: Основной блок (SEO)
    * Пользовательский CSS-класс - fill--color
    * Позиция -         4-6

Настройки блока "Быстрая навигация":
    * Шаблон -              Одноуровневая навигация
    * Тип отображения -     Сетка
    * Количество колонок в списке -  5
*/

public class FastNavigation__MainBlockSeo__Var2Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_MainBlockSeo__Var2Test() {
        String blockTitle = "Быстрая навигация";
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_FastNavigation, "ab__fast_navigation", "form[name=ab_install_form_54311]");
        csCart.createBlockIfNotExists("ab__fast_navigation", blockTitle, "Fast navigation");

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var2();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if (!$x("//a[text()='FastNavigation__MainBlockSeo__Var2Test']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            bannersManagementPage.field_Name.setValue("FastNavigation__MainBlockSeo__Var2Test");
            bannersManagementPage.addCategoryToBanner();
            bannersManagementPage.set_BlockForBanner(blockTitle, "AB: Основной блок (SEO)", "fill--color", "grid");
            bannersManagementPage.set_BlockForBanner(blockTitle, "AB: Основной блок (SEO)", "fill--color", "without options");
            bannersManagementPage.set_BlockForBanner(blockTitle, "AB: Основной блок (SEO)", "fill--color", "compact");
            bannersManagementPage.field_Position.setValue("4-6");

            // Работаем с настройками блока "Быстрая навигация"
            bannersManagementPage.setting_BlockSettings.scrollIntoCenter().click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_FastNavigation blockFastNavigation = new Block_FastNavigation();
            blockFastNavigation.setSettingsForBlock_FastNavigation("Одноуровневая навигация", "Сетка", "5");
            bannersManagementPage.button_Save.click();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_MainBlockSeo__Var2Test")
    public void check_FastNavigation__MainBlockSeo__Var2Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "3200 FastNavigation__MainBlockSeo__Var2Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3202 FastNavigation__MainBlockSeo__Var2Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3204 FastNavigation__MainBlockSeo__Var2Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "3206 FastNavigation__MainBlockSeo__Var2Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3208 FastNavigation__MainBlockSeo__Var2Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3210 FastNavigation__MainBlockSeo__Var2Test - Grid (RTL)");
    }
}