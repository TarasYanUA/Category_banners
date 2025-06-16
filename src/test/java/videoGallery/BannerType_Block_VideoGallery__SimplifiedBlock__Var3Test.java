package videoGallery;

import admin.BannersManagementPage;
import admin.Block_Video;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import TestRunner.TestRunner;
import com.codeborne.selenide.SelenideElement;
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

public class BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test extends TestRunner implements AddVideo {
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
        Utils.switchOffSecondAndNextBanners();
        if(!$x("//a[text()='BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test']").exists()) {
            SelenideElement bannerLink = $("a[href$='category_banner_id=3']");
            if (bannerLink.exists())
                bannerLink.click();
            else {   //Должна быть только первая строка, но из-за ошибки https://abteam.planfix.com/task/54635 нужно было дописывать
                $(".nav__actions-adv-buttons .cs-icon.cs-icon--type-plus").click();
                bannersManagementPage.field_Name.setValue("BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test");
                bannersManagementPage.button_Save.click();
            }

            bannersManagementPage.field_Name.setValue("BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test");
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
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test")
    public void check_BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "2400 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2402 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2404 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "2406 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2408 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "2410 BannerType_Block_VideoGallery__SimplifiedBlock__Var3Test - Grid (RTL)");
    }
}