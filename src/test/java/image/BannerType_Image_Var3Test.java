package image;

import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import TestRunner.TestRunner;
import org.testng.annotations.Test;
import storefront.AssertsPage;
import storefront.CategoryPage;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;

/*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Придать небольшую округлость
    - Скруглить углы блоков, окон, баннеров -   да
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Рамка с внешними отступами
    - Добавить фон/маску для изображений товара -   да

Настройки баннера:
* Тип контента -    Изображение
* Позиция -         2
*/

public class BannerType_Image_Var3Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var3Test() {
        CsCart csCart = new CsCart();
        BannersManagementPage bannersManagementPage = new BannersManagementPage();

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var3();

        //Работаем с баннерами
        csCart.navigateToPage_BannersManagement();
        bannersManagementPage.set_ImageForBanner();
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var3Test")
    public void check_BannerType_Image_Var3Test(){
        CsCart csCart = new CsCart();
        AssertsPage assertsPage = new AssertsPage();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);

        assertsPage.assertBannerExists(assertsPage.firstBannerForGrid);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForGrid),
                "300 BannerType_Image_Var3Test - first banner, Grid");

        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForGrid),
                "302 BannerType_Image_Var3Test - second banner, Grid");
        assertsPage.assertBannerExists(assertsPage.secondBannerForGrid);

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        assertsPage.assertBannerExists(assertsPage.firstBannerForListWithoutOptions);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForListWithoutOptions),
                "304 BannerType_Image_Var3Test - first banner, ListWithoutOptions");

        assertsPage.assertBannerExists(assertsPage.secondBannerForListWithoutOptions);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForListWithoutOptions),
                "306 BannerType_Image_Var3Test - second banner, ListWithoutOptions");

        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        assertsPage.assertBannerExists(assertsPage.firstBannerForCompactList);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForCompactList),
                "308 BannerType_Image_Var3Test - first banner, CompactList");

        assertsPage.assertBannerExists(assertsPage.secondBannerForCompactList);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForCompactList),
                "310 BannerType_Image_Var3Test - second banner, CompactList");

        //Язык RTL
        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForCompactList),
                "312 BannerType_Image_Var3Test - first banner, CompactList (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForCompactList),
                "314 BannerType_Image_Var3Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForListWithoutOptions),
                "316 BannerType_Image_Var3Test - first banner, ListWithoutOptions (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForListWithoutOptions),
                "318 BannerType_Image_Var3Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForGrid),
                "320 BannerType_Image_Var3Test - first banner, Grid (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForGrid),
                "322 BannerType_Image_Var3Test - second banner, Grid (RTL)");
    }
}