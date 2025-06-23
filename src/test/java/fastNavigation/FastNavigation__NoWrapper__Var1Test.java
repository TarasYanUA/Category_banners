package fastNavigation;

import TestRunner.TestRunner;
import admin.BannersManagementPage;
import admin.Block_FastNavigation;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import storefront.CategoryPage;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FastNavigation__NoWrapper__Var1Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_FastNavigation__NoWrapper__Var1Test() {
        String blockTitle = "Быстрая навигация";
        CsCart csCart = new CsCart();
        csCart.installAddonAtAddonsManager(csCart.gearwheel_FastNavigation, "ab__fast_navigation", "form[name=ab_install_form_54311]");
        csCart.createBlockIfNotExists("ab__fast_navigation", blockTitle, "Fast navigation");
        
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();
        
        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if(!$x("//a[text()='FastNavigation__NoWrapper__Var1']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            bannersManagementPage.field_Name.setValue("FastNavigation__NoWrapper__Var1");
            bannersManagementPage.addCategoryToBanner();
            bannersManagementPage.set_BlockForBanner(blockTitle, "--", "", "grid");
            bannersManagementPage.set_BlockForBanner(blockTitle, "--", "", "without options");
            bannersManagementPage.set_BlockForBanner(blockTitle, "--", "", "compact");
            bannersManagementPage.field_Position.setValue("4-6");

            // Работаем с настройками блока "Быстрая навигация"
            bannersManagementPage.setting_BlockSettings.scrollIntoCenter().click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_FastNavigation blockFastNavigation = new Block_FastNavigation();
            blockFastNavigation.setSettingsForBlock_FastNavigation("Одноуровневая навигация", "Скроллер", "4");
            bannersManagementPage.button_Save.click();
        }
    }

    @Test(priority = 2, dependsOnMethods = "setConfiguration_FastNavigation__NoWrapper__Var1Test")
    public void check_FastNavigation__NoWrapper__Var1Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "3100 FastNavigation__NoWrapper__Var1 - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3102 FastNavigation__NoWrapper__Var1 - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3104 FastNavigation__NoWrapper__Var1 - CompactList");
        
        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "3106 FastNavigation__NoWrapper__Var1 - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3108 FastNavigation__NoWrapper__Var1 - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "3110 FastNavigation__NoWrapper__Var1 - Grid (RTL)");
    }
}