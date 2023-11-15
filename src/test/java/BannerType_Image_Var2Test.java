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

public class BannerType_Image_Var2Test extends TestRunner implements ScrollToAndScreenBanner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var2Test() {
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.fieldOfActiveColorscheme.click();
        colorschemeSettings.activateColorscheme.click();
        Selenide.sleep(2000);

        colorschemeSettings.setting_RoundCornersForElements.selectOptionByValue("do_not_use");
        if(colorschemeSettings.setting_RoundCornersOfBlocks.isSelected()){
            colorschemeSettings.setting_RoundCornersOfBlocks.click();   }
        if(!colorschemeSettings.setting_DisplayHeadersInCapitalLetters.isSelected()){
            colorschemeSettings.setting_DisplayHeadersInCapitalLetters.click(); }
        colorschemeSettings.tab_ProductLists.click();
        colorschemeSettings.setting_FrameType.selectOptionByValue("none");
        if(colorschemeSettings.setting_MaskForProductImages.isSelected()){
            colorschemeSettings.setting_MaskForProductImages.click();   }
        colorschemeSettings.button_Save.click();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        if(!bannersManagementPage.status_Disabled.isEmpty()){   //если присутствует статус "Выкл.", то включаем баннер
            for(int i = 0; i <= bannersManagementPage.status_Disabled.size(); i++){
                bannersManagementPage.status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }
        //Работаем с первым баннером
        if(!$x("//a[text()='Autobanner of Image type 01']").exists()) {
            bannersManagementPage.selectLanguageRTL();
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("Autobanner of Image type 01");
            bannersManagementPage.typeImage_Grid.click();
            bannersManagementPage.button_ServerGrid.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_Grid);
            bannersManagementPage.typeImage_WithoutOptions.click();
            bannersManagementPage.button_ServerWithoutOptions.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_WithoutOptions);
            bannersManagementPage.typeImage_Compact.click();
            bannersManagementPage.button_ServerCompact.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_CompactList);
            bannersManagementPage.clickAndType_field_Position("2");
            bannersManagementPage.button_Save.click();

            bannersManagementPage.selectLanguageRU();
            bannersManagementPage.clickAndType_field_Name("Autobanner of Image type 01");
            bannersManagementPage.typeImage_Grid.click();
            bannersManagementPage.button_ServerGrid.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_Grid);
            bannersManagementPage.typeImage_WithoutOptions.click();
            bannersManagementPage.button_ServerWithoutOptions.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_WithoutOptions);
            bannersManagementPage.typeImage_Compact.click();
            bannersManagementPage.button_ServerCompact.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_CompactList);
            bannersManagementPage.button_Save.click();
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var2Test")
    public void check_BannerType_Image_Var2Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        SoftAssert softAssert = new SoftAssert();
        AssertsPage assertsPage = new AssertsPage();
        scrollToAndScreenBanner(firstBannerName_Grid, "200 BannerType_Image_Var2Test - first banner, Grid");
        softAssert.assertTrue($(".ut2-gl__banner img").getAttribute("src").contains(firstBannerName_Grid),
                "There is no first banner of Image type for Grid!");
        scrollToAndScreenBanner(secondBannerName_Grid, "202 BannerType_Image_Var2Test - second banner, Grid");
        softAssert.assertTrue(assertsPage.secondBannerForGrid.getAttribute("src").contains(secondBannerName_Grid),
                "There is no second banner of Image type for Grid!");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_WithoutOptions, "204 BannerType_Image_Var2Test - first banner, ListWithoutOptions");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(firstBannerName_WithoutOptions),
                "There is no first banner of Image type for ListWithoutOptions!");
        scrollToAndScreenBanner(secondBannerName_WithoutOptions, "206 BannerType_Image_Var2Test - second banner, ListWithoutOptions");
        softAssert.assertTrue(assertsPage.secondBannerForListWithoutOptions.getAttribute("src").contains(secondBannerName_WithoutOptions),
                "There is no second banner of Image type for ListWithoutOptions!");

        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_CompactList, "208 BannerType_Image_Var2Test - first banner, CompactList");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(firstBannerName_CompactList),
                "There is no first banner of Image type for CompactList!");
        scrollToAndScreenBanner(secondBannerName_CompactList, "210 BannerType_Image_Var2Test - second banner, CompactList");
        softAssert.assertTrue(assertsPage.secondBannerForCompactList.getAttribute("src").contains(secondBannerName_CompactList),
                "There is no second banner of Image type for CompactList!");

        //Язык RTL
        shiftToRTLLanguage();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_CompactList, "212 BannerType_Image_Var2Test - first banner, CompactList (RTL)");
        scrollToAndScreenBanner(secondBannerName_CompactList, "214 BannerType_Image_Var2Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_WithoutOptions, "216 BannerType_Image_Var2Test - first banner, ListWithoutOptions (RTL)");
        scrollToAndScreenBanner(secondBannerName_WithoutOptions, "218 BannerType_Image_Var2Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        scrollToAndScreenBanner(firstBannerName_Grid, "220 BannerType_Image_Var2Test - first banner, Grid (RTL)");
        scrollToAndScreenBanner(secondBannerName_Grid, "222 BannerType_Image_Var2Test - second banner, Grid (RTL)");
        softAssert.assertAll(); //Есть ошибка видимости баннеров https://abteam.planfix.com/task/43074
    }
}