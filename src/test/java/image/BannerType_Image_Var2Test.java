package image;

import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Selenide;
import interfaces_TestRunner.TestRunner;
import org.testng.annotations.Test;
import storefront.AssertsPage;
import storefront.CategoryPage;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;

/*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Не использовать
    - Скруглить углы блоков, окон, баннеров -   нет
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Без рамки
    - Добавить фон/маску для изображений товара -   нет

Настройки баннера:
* Тип контента -    Изображение
* Позиция -         2
*/

public class BannerType_Image_Var2Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var2Test() {
        CsCart csCart = new CsCart();
        BannersManagementPage bannersManagementPage = new BannersManagementPage();

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var2();

        //Работаем с баннерами
        csCart.navigateToPage_BannersManagement();
        bannersManagementPage.set_ImageForBanner();
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var2Test")
    public void check_BannerType_Image_Var2Test(){
        CsCart csCart = new CsCart();
        AssertsPage assertsPage = new AssertsPage();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);

        assertsPage.assertElementExists(assertsPage.firstBannerForGrid);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForGrid), "200 BannerType_Image_Var2Test - first banner, Grid");

        assertsPage.assertElementExists(assertsPage.secondBannerForGrid);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForGrid), "202 BannerType_Image_Var2Test - second banner, Grid");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        assertsPage.assertElementExists(assertsPage.firstBannerForListWithoutOptions);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForListWithoutOptions), "204 BannerType_Image_Var2Test - first banner, ListWithoutOptions");

        assertsPage.assertElementExists(assertsPage.secondBannerForListWithoutOptions);
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForListWithoutOptions), "206 BannerType_Image_Var2Test - second banner, ListWithoutOptions");

        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        assertsPage.assertElementExists(assertsPage.firstBannerForCompactList);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForCompactList), "208 BannerType_Image_Var2Test - first banner, CompactList");

        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForCompactList), "210 BannerType_Image_Var2Test - second banner, CompactList");
        assertsPage.assertElementExists(assertsPage.secondBannerForCompactList);

        //Язык RTL
        selectLanguage_RTL();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForCompactList), "212 BannerType_Image_Var2Test - first banner, CompactList (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForCompactList), "214 BannerType_Image_Var2Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForListWithoutOptions), "216 BannerType_Image_Var2Test - first banner, ListWithoutOptions (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForListWithoutOptions), "218 BannerType_Image_Var2Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner($(assertsPage.firstBannerForGrid), "220 BannerType_Image_Var2Test - first banner, Grid (RTL)");
        categoryPage.scrollToAndScreenBanner($(assertsPage.secondBannerForGrid), "222 BannerType_Image_Var2Test - second banner, Grid (RTL)");
    }
}