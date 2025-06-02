package image;

import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Selenide;
import interfaces_TestRunner.TestRunner;
import utils.Utils;
import org.testng.annotations.Test;
import storefront.AssertsPage;
import storefront.CategoryPage;

import static com.codeborne.selenide.Selenide.$;

/*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Полностью скруглить
    - Скруглить углы блоков, окон, баннеров -   да
    - Отображать заголовки заглавными буквами - нет

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Рамка без внешних отступов
    - Добавить фон/маску для изображений товара - нет

Настройки баннера:
* Тип контента -    Изображение
* Позиция -         2
*/

public class BannerType_Image_Var1Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var1Test() {
        CsCart csCart = new CsCart();
        BannersManagementPage bannersManagementPage = new BannersManagementPage();

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        csCart.navigateToPage_BannersManagement();
        bannersManagementPage.set_ImageForBanner();
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var1Test")
    public void check_BannerType_Image_Var1Test(){
        CsCart csCart = new CsCart();
        AssertsPage assertsPage = new AssertsPage();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);

        assertsPage.assertElementExists(assertsPage.firstBannerForGrid);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForGrid), "100 BannerType_Image_Var1Test - first banner, Grid");

        assertsPage.assertElementExists(assertsPage.secondBannerForGrid);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForGrid), "102 BannerType_Image_Var1Test - second banner, Grid");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        assertsPage.assertElementExists(assertsPage.firstBannerForListWithoutOptions);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForListWithoutOptions), "104 BannerType_Image_Var1Test - first banner, ListWithoutOptions");

        assertsPage.assertElementExists(assertsPage.secondBannerForListWithoutOptions);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForListWithoutOptions), "106 BannerType_Image_Var1Test - second banner, ListWithoutOptions");

        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        assertsPage.assertElementExists(assertsPage.firstBannerForCompactList);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForCompactList), "108 BannerType_Image_Var1Test - first banner, CompactList");

        assertsPage.assertElementExists(assertsPage.secondBannerForCompactList);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForCompactList), "110 BannerType_Image_Var1Test - second banner, CompactList");

        //Язык RTL
        selectLanguage_RTL();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForCompactList), "112 BannerType_Image_Var1Test - first banner, CompactList (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForCompactList), "114 BannerType_Image_Var1Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForListWithoutOptions), "116 BannerType_Image_Var1Test - first banner, ListWithoutOptions (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForListWithoutOptions), "118 BannerType_Image_Var1Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForGrid), "120 BannerType_Image_Var1Test - first banner, Grid (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForGrid), "122 BannerType_Image_Var1Test - second banner, Grid (RTL)");
    }
}