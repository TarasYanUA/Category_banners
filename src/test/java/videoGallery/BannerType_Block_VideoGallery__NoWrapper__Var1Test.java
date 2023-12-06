package videoGallery;

import admin.BannersManagementPage;
import admin.Block_Video;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import interfaces_TestRunner.Set_BlockForBanner;
import interfaces_TestRunner.SwitchOffSecondBanner;
import interfaces_TestRunner.TestRunner;
import org.testng.annotations.Test;
import storefront.CategoryPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/*
* Устанавливаем модуль "Видео галерея"
* Добавляем несколько видео товару 'Apple - iPhone 5c'
* Создаём блок "Видео товаров"

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
    * Блок -            Видео товаров
    * На всю ширину -   да
    * Оболочка -        нет
    * Пользовательский CSS-класс - нет
    * Позиция -         6

Настройки блока "Видео товаров":
    * Количество колонок в списке - 4
    * Отобразить ссылку на товар -  да
    * Отобразить заголовок видео -  да
    * Отобразить описание видео -   да
    * Количество видео -            10
*/

public class BannerType_Block_VideoGallery__NoWrapper__Var1Test extends TestRunner implements AddVideo, Set_BlockForBanner, SwitchOffSecondBanner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Block_VideoGallery__NoWrapper__Var1Test(){
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_VideoGallery, "ab__video_gallery", "form[name=ab_install_form_54312]");
        addVideo();
        csCart.addBlock_VideoGallery();

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        switchOffSecondBanner();
        if(!$x("//a[text()='BannerType_Block_VideoGallery__NoWrapper__Var1Test']").exists()) {
            $("a[href$='category_banner_id=3']").click();
            bannersManagementPage.clickAndType_field_Name("BannerType_Block_VideoGallery__NoWrapper__Var1Test");
            set_BlockForBanner_Grid("Видео товаров", "--", "");
            set_BlockForBanner_WithoutOptions("Видео товаров", "--", "");
            set_BlockForBanner_Compact("Видео товаров", "--", "");
            bannersManagementPage.clickAndType_field_Position("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Видео товаров"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Video blockVideo = new Block_Video();
            blockVideo.setSettingsForVideoBlock_Var1();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_BannerType_Block_VideoGallery__NoWrapper__Var1Test")
    public void check_BannerType_Block_VideoGallery_Var1Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2200 BannerType_Block_VideoGallery__NoWrapper__Var1Test - Grid");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2202 BannerType_Block_VideoGallery__NoWrapper__Var1Test - WithoutOptions");
        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2204 BannerType_Block_VideoGallery__NoWrapper__Var1Test - CompactList");

        shiftToRTLLanguage();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2206 BannerType_Block_VideoGallery__NoWrapper__Var1Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2208 BannerType_Block_VideoGallery__NoWrapper__Var1Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2210 BannerType_Block_VideoGallery__NoWrapper__Var1Test - Grid (RTL)");
    }
}