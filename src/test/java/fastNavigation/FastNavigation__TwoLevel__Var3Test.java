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
    * Блок -            Видео обзоры
    * На всю ширину -   да
    * Оболочка -        AB: Упрощенный блок
    * Пользовательский CSS-класс - fill--gray
    * Позиция -         4-6

Настройки блока "Быстрая навигация":
    * Шаблон -              Двухуровневая навигация
    * Количество колонок в списке -  4
*/

public class FastNavigation__TwoLevel__Var3Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_FastNavigation__TwoLevel__Var3Test() {
        String blockTitle = "Быстрая навигация";
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_FastNavigation, "ab__fast_navigation", "form[name=ab_install_form_54331]");
        csCart.createBlockIfNotExists("ab__fast_navigation", blockTitle, "Fast navigation");

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var3();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if (!$x("//a[text()='FastNavigation__TwoLevel__Var3Test']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            bannersManagementPage.field_Name.setValue("FastNavigation__TwoLevel__Var3Test");
            bannersManagementPage.addCategoryToBanner();
            bannersManagementPage.set_BlockForBanner(blockTitle, "AB: Упрощенный блок", "fill--gray", "grid");
            bannersManagementPage.set_BlockForBanner(blockTitle, "AB: Упрощенный блок", "fill--gray", "without options");
            bannersManagementPage.set_BlockForBanner(blockTitle, "AB: Упрощенный блок", "fill--gray", "compact");
            bannersManagementPage.field_Position.setValue("4-6");

            // Работаем с настройками блока "Быстрая навигация"
            bannersManagementPage.setting_BlockSettings.scrollIntoCenter().click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_FastNavigation blockFastNavigation = new Block_FastNavigation();
            blockFastNavigation.setSettingsForBlock_FastNavigation("Двухуровневая навигация", "", "4");
            bannersManagementPage.button_Save.click();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_FastNavigation__TwoLevel__Var3Test")
    public void check_FastNavigation__TwoLevel__Var3Test() {
        CsCart csCart = new CsCart();
        Block_FastNavigation blockFastNavigation = new Block_FastNavigation();

        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "3300 FastNavigation__TwoLevel__Var3Test - Grid");
        blockFastNavigation.clickCategoryInBlockAndScreen("3302 FastNavigation__TwoLevel__Var3Test, Second level - Grid");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3304 FastNavigation__TwoLevel__Var3Test - WithoutOptions");
        blockFastNavigation.clickCategoryInBlockAndScreen("3306 FastNavigation__TwoLevel__Var3Test, Second level - WithoutOptions");

        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3308 FastNavigation__TwoLevel__Var3Test - CompactList");
        blockFastNavigation.clickCategoryInBlockAndScreen("3310 FastNavigation__TwoLevel__Var3Test, Second level - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "3312 FastNavigation__TwoLevel__Var3Test - CompactList (RTL)");
        blockFastNavigation.clickCategoryInBlockAndScreen("3314 FastNavigation__TwoLevel__Var3Test, Second level - CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3316 FastNavigation__TwoLevel__Var3Test - WithoutOptions (RTL)");
        blockFastNavigation.clickCategoryInBlockAndScreen("3318 FastNavigation__TwoLevel__Var3Test, Second level - WithoutOptions (RTL)");

        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3320 FastNavigation__TwoLevel__Var3Test - Grid (RTL)");
        blockFastNavigation.clickCategoryInBlockAndScreen("3322 FastNavigation__TwoLevel__Var3Test, Second level - Grid (RTL)");
    }
}