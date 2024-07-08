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
    - Скруглить углы для элементов интерфейса - Не использовать
    - Скруглить углы блоков, окон, баннеров -   нет
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Без рамки
    - Добавить фон/маску для изображений товара -   нет

Настройки баннера:
    * Тип контента -    Блок
    * Блок -            Видео товаров
    * На всю ширину -   да
    * Оболочка -        AB: Упрощенный блок
    * Пользовательский CSS-класс - fill--gray
    * Позиция -         6

Настройки блока "Видео товаров":
* Количество колонок в списке - 3
* Отобразить ссылку на товар -  да
* Отобразить заголовок видео -  нет
* Отобразить описание видео -   нет
* Количество видео -            5
*/

public class BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test extends TestRunner implements AddVideo, Set_BlockForBanner, SwitchOffSecondBanner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test(){
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_VideoGallery, "ab__video_gallery", "form[name=ab_install_form_54312]");
        addVideo();
        csCart.addBlock_VideoGallery();

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var2();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        switchOffSecondBanner();
        if(!$x("//a[text()='BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test']").exists()) {
            $("a[href$='category_banner_id=3']").click();
            bannersManagementPage.clickAndType_field_Name("BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test");
            set_BlockForBanner_Grid("Видео товаров", "AB: Упрощенный блок", "fill--gray");
            set_BlockForBanner_WithoutOptions("Видео товаров", "AB: Упрощенный блок", "fill--gray");
            set_BlockForBanner_Compact("Видео товаров", "AB: Упрощенный блок", "fill--gray");
            bannersManagementPage.clickAndType_field_Position("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Видео товаров"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Video blockVideo = new Block_Video();
            blockVideo.setSettingsForVideoBlock_Var3();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test")
    public void check_BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2400 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - Grid");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2402 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - WithoutOptions");
        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2404 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - CompactList");

        selectLanguage_RTL();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2406 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2408 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_VideoGallery("2410 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - Grid (RTL)");
    }
}