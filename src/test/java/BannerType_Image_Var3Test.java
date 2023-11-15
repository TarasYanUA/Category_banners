import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import storefront.AssertsPage;
import storefront.CategoryPage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

public class BannerType_Image_Var3Test extends TestRunner implements Set_ColorschemeSettings, Set_ImageForBanner, ScrollToAndScreenBanner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var3Test() {
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.fieldOfActiveColorscheme.click();
        colorschemeSettings.activateColorscheme.click();
        Selenide.sleep(2000);
        set_ColorschemeSettings_Var3();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        if(!bannersManagementPage.status_Disabled.isEmpty()){   //если присутствует статус "Выкл.", то включаем баннер
            for(int i = 0; i <= bannersManagementPage.status_Disabled.size(); i++){
                bannersManagementPage.status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }
        //Работаем с первым баннером
        set_ImageForBanner();
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var3Test")
    public void check_BannerType_Image_Var3Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        SoftAssert softAssert = new SoftAssert();
        AssertsPage assertsPage = new AssertsPage();
        scrollToAndScreenBanner(firstBannerName_Grid, "300 BannerType_Image_Var3Test - first banner, Grid");
        softAssert.assertTrue($(".ut2-gl__banner img").getAttribute("src").contains(firstBannerName_Grid),
                "There is no first banner of Image type for Grid!");
        scrollToAndScreenBanner(secondBannerName_Grid, "302 BannerType_Image_Var3Test - second banner, Grid");
        softAssert.assertTrue(assertsPage.secondBannerForGrid.getAttribute("src").contains(secondBannerName_Grid),
                "There is no second banner of Image type for Grid!");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_WithoutOptions, "304 BannerType_Image_Var3Test - first banner, ListWithoutOptions");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(firstBannerName_WithoutOptions),
                "There is no first banner of Image type for ListWithoutOptions!");
        scrollToAndScreenBanner(secondBannerName_WithoutOptions, "306 BannerType_Image_Var3Test - second banner, ListWithoutOptions");
        softAssert.assertTrue(assertsPage.secondBannerForListWithoutOptions.getAttribute("src").contains(secondBannerName_WithoutOptions),
                "There is no second banner of Image type for ListWithoutOptions!");

        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_CompactList, "308 BannerType_Image_Var3Test - first banner, CompactList");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(firstBannerName_CompactList),
                "There is no first banner of Image type for CompactList!");
        scrollToAndScreenBanner(secondBannerName_CompactList, "310 BannerType_Image_Var3Test - second banner, CompactList");
        softAssert.assertTrue(assertsPage.secondBannerForCompactList.getAttribute("src").contains(secondBannerName_CompactList),
                "There is no second banner of Image type for CompactList!");

        //Язык RTL
        shiftToRTLLanguage();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_CompactList, "312 BannerType_Image_Var3Test - first banner, CompactList (RTL)");
        scrollToAndScreenBanner(secondBannerName_CompactList, "314 BannerType_Image_Var3Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_WithoutOptions, "316 BannerType_Image_Var3Test - first banner, ListWithoutOptions (RTL)");
        scrollToAndScreenBanner(secondBannerName_WithoutOptions, "318 BannerType_Image_Var3Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_Grid, "320 BannerType_Image_Var3Test - first banner, Grid (RTL)");
        scrollToAndScreenBanner(secondBannerName_Grid, "322 BannerType_Image_Var3Test - second banner, Grid (RTL)");
        softAssert.assertAll(); //Есть ошибка видимости баннеров https://abteam.planfix.com/task/43074
    }
}