package videogallery;

import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import interfaces_TestRunner.Set_BlockForBanner;
import interfaces_TestRunner.SwitchOffSecondBanner;
import interfaces_TestRunner.TestRunner;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BannerType_Block_VideoGallery_Var1Test extends TestRunner implements AddVideo, Set_BlockForBanner, SwitchOffSecondBanner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Block_VideoGallery_Var1Test(){
        CsCart csCart = new CsCart();
        csCart.navigateToPage_ListOfAvailableAddons();


        addVideo();

        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        switchOffSecondBanner();
        if(!$x("//a[text()='BannerType_Block_VideoGallery_Var1Test']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("BannerType_Block_VideoGallery_Var1Test");
            set_BlockForBanner_Grid("Блог", "--", "");
            set_BlockForBanner_WithoutOptions("Блог", "--", "");
            set_BlockForBanner_Compact("Блог", "--", "");
            bannersManagementPage.clickAndType_field_Position("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Видео товаров"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
        }
    }
}