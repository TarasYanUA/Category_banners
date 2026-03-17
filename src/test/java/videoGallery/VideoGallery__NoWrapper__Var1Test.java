package videoGallery;

import admin.BannersManagementPage;
import admin.Block_Video;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import testRunner.TestRunner;
import enums.BlockTitle;
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
        - Скруглить углы для элементов интерфейса - Полностью скруглить
        - Скруглить углы блоков, окон, баннеров -   да
        - Отображать заголовки заглавными буквами - нет

        Вкладка "Списки товаров":
        - Тип обрамления товара в сетке -           Рамка без внешних отступов
        - Добавить фон/маску для изображений товара -   нет

Настройки баннера:
    * Тип контента -    Блок
    * Блок -            Видео обзоры
    * На всю ширину -   да
    * Оболочка -        нет
    * Пользовательский CSS-класс - нет
    * Позиция -         6

Настройки блока "Видео обзоры":
    * Количество колонок в списке - 4
    * Отобразить ссылку на товар -  да
    * Отобразить заголовок видео -  да
    * Отобразить описание видео -   да
    * Количество видео -            10
*/

public class VideoGallery__NoWrapper__Var1Test extends TestRunner implements AddVideo {
    @Test(priority = 1)
    public void setConfiguration_VideoGallery__NoWrapper__Var1Test() {
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_VideoGallery, "ab__video_gallery", "form[name=ab_install_form_54312]");
        addVideo();
        csCart.createBlockIfNotExists("ab__vg_videos", BlockTitle.VIDEO_GALLERY.value());

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateTo_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();
        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateTo_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if (!$x("//a[text()='VideoGallery__NoWrapper__Var1Test']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            bannersManagementPage.field_Name.setValue("VideoGallery__NoWrapper__Var1Test");
            bannersManagementPage.addCategoryToBanner();
            bannersManagementPage.set_BlockForBanner(BlockTitle.VIDEO_GALLERY.value(),
                    "--",
                    "",
                    "grid");
            bannersManagementPage.set_BlockForBanner(BlockTitle.VIDEO_GALLERY.value(),
                    "--",
                    "",
                    "without options");
            bannersManagementPage.set_BlockForBanner(BlockTitle.VIDEO_GALLERY.value(),
                    "--",
                    "",
                    "compact");
            bannersManagementPage.field_Position.setValue("6");

            // Работаем с настройками блока "Видео обзоры"
            bannersManagementPage.setting_BlockSettings.scrollIntoCenter().click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Video blockVideo = new Block_Video();
            blockVideo.setSettingsForVideoBlock_Var1();
            bannersManagementPage.button_Save.click();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_VideoGallery__NoWrapper__Var1Test")
    public void check_VideoGallery_Var1Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateTo_CategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "2200 VideoGallery__NoWrapper__Var1Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2202 VideoGallery__NoWrapper__Var1Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2204 VideoGallery__NoWrapper__Var1Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "2206 VideoGallery__NoWrapper__Var1Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2208 VideoGallery__NoWrapper__Var1Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2210 VideoGallery__NoWrapper__Var1Test - Grid (RTL)");
    }
}