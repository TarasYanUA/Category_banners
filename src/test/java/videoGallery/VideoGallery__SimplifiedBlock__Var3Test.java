package videoGallery;

import admin.BannersManagementPage;
import admin.Block_Video;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import TestRunner.TestRunner;
import org.testng.annotations.Test;
import storefront.CategoryPage;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/*
* Устанавливаем модуль "Видео галерея"
* Добавляем несколько видео товару 'Apple - iPhone 5c'
* Создаём блок "Видео обзоры"

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
    * Позиция -         6

Настройки блока "Видео обзоры":
* Количество колонок в списке - 3
* Отобразить ссылку на товар -  да
* Отобразить заголовок видео -  нет
* Отобразить описание видео -   нет
* Количество видео -            5
*/

public class VideoGallery__SimplifiedBlock__Var3Test extends TestRunner implements AddVideo {
    @Test(priority = 1)
    public void setConfiguration_VideoGallery__SimplifiedBlock__Var3Test() {
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_VideoGallery, "ab__video_gallery", "form[name=ab_install_form_54312]");
        addVideo();
        csCart.createBlockIfNotExists("ab__vg_videos", "Видео обзоры");

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var2();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if (!$x("//a[text()='VideoGallery__SimplifiedBlock__Var3Test']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            //Нижних двух строк не должно быть, но из-за ошибки https://abteam.planfix.com/task/54635 нужно было дописывать
            bannersManagementPage.field_Name.setValue("VideoGallery__MainBlockSeo__Var2Test");
            bannersManagementPage.button_Save.click();
        }
        bannersManagementPage.field_Name.setValue("VideoGallery__SimplifiedBlock__Var3Test");
        bannersManagementPage.addCategoryToBanner();
        bannersManagementPage.set_BlockForBanner("Видео обзоры", "AB: Упрощенный блок", "fill--gray", "grid");
        bannersManagementPage.set_BlockForBanner("Видео обзоры", "AB: Упрощенный блок", "fill--gray", "without options");
        bannersManagementPage.set_BlockForBanner("Видео обзоры", "AB: Упрощенный блок", "fill--gray", "compact");
        bannersManagementPage.field_Position.setValue("6");
        bannersManagementPage.button_Save.click();

        // Работаем с настройками блока "Видео обзоры"
        bannersManagementPage.setting_BlockSettings.scrollIntoCenter().click();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        Block_Video blockVideo = new Block_Video();
        blockVideo.setSettingsForVideoBlock_Var3();
        bannersManagementPage.button_Save.click();
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_VideoGallery__SimplifiedBlock__Var3Test")
    public void check_VideoGallery__SimplifiedBlock__Var3Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "2400 VideoGallery__SimplifiedBlock__Var3Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2402 VideoGallery__SimplifiedBlock__Var3Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2404 VideoGallery__SimplifiedBlock__Var3Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "2406 VideoGallery__SimplifiedBlock__Var3Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2408 VideoGallery__SimplifiedBlock__Var3Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2410 VideoGallery__SimplifiedBlock__Var3Test - Grid (RTL)");
    }
}