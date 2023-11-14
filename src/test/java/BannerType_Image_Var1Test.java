import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import storefront.AssertsPage;
import storefront.CategoryPage;
import static com.codeborne.selenide.Selenide.*;

/*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Полностью скруглить
    - Скруглить углы блоков, окон, баннеров -   да
    - Отображать заголовки заглавными буквами - нет

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Рамка без внешних отступов
    - Добавить фон/маску для изображений товара -   нет

Настройки баннера:
* Тип контента -    Изображение
* Позиция -         2
*/

public class BannerType_Image_Var1Test extends TestRunner implements BannersImageType {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var1Test() {
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.fieldOfActiveColorscheme.click();
        colorschemeSettings.activateColorscheme.click();
        Selenide.sleep(2000);
        colorschemeSettings.setting_RoundCornersForElements.selectOptionByValue("full");
        if(!colorschemeSettings.setting_RoundCornersOfBlocks.isSelected()){
            colorschemeSettings.setting_RoundCornersOfBlocks.click();   }
        if(colorschemeSettings.setting_DisplayHeadersInCapitalLetters.isSelected()){
            colorschemeSettings.setting_DisplayHeadersInCapitalLetters.click(); }
        colorschemeSettings.tab_ProductLists.click();
        colorschemeSettings.setting_FrameType.selectOptionByValue("solid_without_margins");
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

    @Test(priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var1Test")
    public void check_BannerType_Image_Var1Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        SoftAssert softAssert = new SoftAssert();
        AssertsPage assertsPage = new AssertsPage();
        scrollAndScreenBanner(firstBannerName_Grid, "100 BannerType_Image_Var1Test - first banner, Grid");
        softAssert.assertTrue($(".ut2-gl__banner img").getAttribute("src").contains(firstBannerName_Grid),
                "There is no first banner of Image type for Grid!");
        scrollAndScreenBanner(secondBannerName_Grid, "102 BannerType_Image_Var1Test - second banner, Grid");
        softAssert.assertTrue(assertsPage.secondBannerForGrid.getAttribute("src").contains(secondBannerName_Grid),
                "There is no second banner of Image type for Grid!");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollAndScreenBanner(firstBannerName_WithoutOptions, "104 BannerType_Image_Var1Test - first banner, ListWithoutOptions");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(firstBannerName_WithoutOptions),
                "There is no first banner of Image type for ListWithoutOptions!");
        scrollAndScreenBanner(secondBannerName_WithoutOptions, "106 BannerType_Image_Var1Test - second banner, ListWithoutOptions");
        softAssert.assertTrue(assertsPage.secondBannerForListWithoutOptions.getAttribute("src").contains(secondBannerName_WithoutOptions),
                "There is no second banner of Image type for ListWithoutOptions!");

        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        scrollAndScreenBanner(firstBannerName_CompactList, "108 BannerType_Image_Var1Test - first banner, CompactList");
        softAssert.assertTrue($(".category-banner img").getAttribute("src").contains(firstBannerName_CompactList),
                "There is no first banner of Image type for CompactList!");
        scrollAndScreenBanner(secondBannerName_CompactList, "110 BannerType_Image_Var1Test - second banner, CompactList");
        softAssert.assertTrue(assertsPage.secondBannerForCompactList.getAttribute("src").contains(secondBannerName_CompactList),
                "There is no second banner of Image type for CompactList!");

        //Язык RTL
        shiftToRTLLanguage();
        Selenide.sleep(2000);
        scrollAndScreenBanner(firstBannerName_CompactList, "112 BannerType_Image_Var1Test - first banner, CompactList (RTL)");
        scrollAndScreenBanner(secondBannerName_CompactList, "114 BannerType_Image_Var1Test - second banner, CompactList (RTL)");

        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        scrollAndScreenBanner(firstBannerName_WithoutOptions, "116 BannerType_Image_Var1Test - first banner, ListWithoutOptions (RTL)");
        scrollAndScreenBanner(secondBannerName_WithoutOptions, "118 BannerType_Image_Var1Test - second banner, ListWithoutOptions (RTL)");

        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        scrollAndScreenBanner(firstBannerName_Grid, "120 BannerType_Image_Var1Test - first banner, Grid (RTL)");
        scrollAndScreenBanner(secondBannerName_Grid, "122 BannerType_Image_Var1Test - second banner, Grid (RTL)");
        softAssert.assertAll(); //Есть ошибка видимости баннеров https://abteam.planfix.com/task/43074
    }
}