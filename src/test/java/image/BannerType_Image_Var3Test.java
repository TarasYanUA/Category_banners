package image;

import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Selenide;
import interfaces_TestRunner.TestRunner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import storefront.AssertsPage;
import storefront.CategoryPage;
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

public class BannerType_Image_Var3Test extends TestRunner implements Set_ImageForBanner, ScrollToAndScreenBanner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var3Test() {
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var3();

        //Работаем с баннерами
        csCart.navigateToPage_BannersManagement();
        set_ImageForBanner();
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var3Test")
    public void check_BannerType_Image_Var3Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        SoftAssert softAssert = new SoftAssert();
        AssertsPage assertsPage = new AssertsPage();
        scrollToAndScreenBanner(ScrollToAndScreenBanner.firstBannerName_Grid, "300 BannerType_Image_Var3Test - first banner, Grid");
        softAssert.assertTrue($(".ut2-gl__banner img").getAttribute("src").contains(ScrollToAndScreenBanner.firstBannerName_Grid),
                "There is no first banner of Image type for Grid!");
        scrollToAndScreenBanner(ScrollToAndScreenBanner.secondBannerName_Grid, "302 BannerType_Image_Var3Test - second banner, Grid");
        softAssert.assertTrue(assertsPage.secondBannerForGrid.getAttribute("src").contains(ScrollToAndScreenBanner.secondBannerName_Grid),
                "There is no second banner of Image type for Grid!");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(ScrollToAndScreenBanner.firstBannerName_WithoutOptions, "304 BannerType_Image_Var3Test - first banner, ListWithoutOptions");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(ScrollToAndScreenBanner.firstBannerName_WithoutOptions),
                "There is no first banner of Image type for ListWithoutOptions!");
        scrollToAndScreenBanner(ScrollToAndScreenBanner.secondBannerName_WithoutOptions, "306 BannerType_Image_Var3Test - second banner, ListWithoutOptions");
        softAssert.assertTrue(assertsPage.secondBannerForListWithoutOptions.getAttribute("src").contains(ScrollToAndScreenBanner.secondBannerName_WithoutOptions),
                "There is no second banner of Image type for ListWithoutOptions!");

        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(ScrollToAndScreenBanner.firstBannerName_CompactList, "308 BannerType_Image_Var3Test - first banner, CompactList");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(ScrollToAndScreenBanner.firstBannerName_CompactList),
                "There is no first banner of Image type for CompactList!");
        scrollToAndScreenBanner(ScrollToAndScreenBanner.secondBannerName_CompactList, "310 BannerType_Image_Var3Test - second banner, CompactList");
        softAssert.assertTrue(assertsPage.secondBannerForCompactList.getAttribute("src").contains(ScrollToAndScreenBanner.secondBannerName_CompactList),
                "There is no second banner of Image type for CompactList!");

        //Язык RTL
        shiftToRTLLanguage();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(ScrollToAndScreenBanner.firstBannerName_CompactList, "312 BannerType_Image_Var3Test - first banner, CompactList (RTL)");
        scrollToAndScreenBanner(ScrollToAndScreenBanner.secondBannerName_CompactList, "314 BannerType_Image_Var3Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(ScrollToAndScreenBanner.firstBannerName_WithoutOptions, "316 BannerType_Image_Var3Test - first banner, ListWithoutOptions (RTL)");
        scrollToAndScreenBanner(ScrollToAndScreenBanner.secondBannerName_WithoutOptions, "318 BannerType_Image_Var3Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(ScrollToAndScreenBanner.firstBannerName_Grid, "320 BannerType_Image_Var3Test - first banner, Grid (RTL)");
        scrollToAndScreenBanner(ScrollToAndScreenBanner.secondBannerName_Grid, "322 BannerType_Image_Var3Test - second banner, Grid (RTL)");
        softAssert.assertAll(); //Есть ошибка видимости баннеров https://abteam.planfix.com/task/43074
    }
}