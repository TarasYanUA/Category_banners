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

public class BannerType_Image_Var1Test extends TestRunner {
    String bannerNameFor_Grid = "category_banners_main_image-1_78ep-0t.jpg";
    String bannerNameFor_WithoutOptions = "category_banners_list_image-1_0f1y-d8.png";
    String bannerNameFor_CompactList = "category_banners_short_list_pair-1_35vv-7m.png";

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
        bannersManagementPage.selectLanguageRU();
        if(!bannersManagementPage.status_Disabled.isEmpty()){   //если присутствует статус "Выкл.", то включаем баннер
            for(int i = 0; i <= bannersManagementPage.status_Disabled.size(); i++){
                bannersManagementPage.status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }

        //Работаем с первым баннером
        $("a[href$='category_banner_id=1']").click();
        bannersManagementPage.clickAndType_field_Name("AutoBanner type Image 01");
        bannersManagementPage.typeImage_Grid.click();
        bannersManagementPage.button_ServerGrid.click();
        bannersManagementPage.selectPictureForBanner(bannerNameFor_Grid);
        bannersManagementPage.typeImage_WithoutOptions.click();
        bannersManagementPage.button_ServerWithoutOptions.click();
        bannersManagementPage.selectPictureForBanner(bannerNameFor_WithoutOptions);
        bannersManagementPage.typeImage_Compact.click();
        bannersManagementPage.button_ServerCompact.click();
        bannersManagementPage.selectPictureForBanner(bannerNameFor_CompactList);
        bannersManagementPage.clickAndType_field_Position("2");
        bannersManagementPage.button_Save.click();
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Image_Var1Test")
    public void check_BannerType_Image_Var1Test(){
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        SoftAssert softAssert = new SoftAssert();
        AssertsPage assertsPage = new AssertsPage();
        softAssert.assertTrue($("img[src$='" + bannerNameFor_Grid + "']").exists(),
                "There is no first banner type Image for Grid!");
        softAssert.assertTrue(assertsPage.secondBannerForGrid.exists(),
                "There is no second banner type Image for Grid!");
        $("img[src$='" + bannerNameFor_Grid + "']").scrollIntoView(false);
        screenshot("100 BannerType_Image_Var1Test - first banner, Grid");
        assertsPage.secondBannerForGrid.scrollIntoView(false);
        screenshot("102 BannerType_Image_Var1Test - second banner, Grid");
    }
}