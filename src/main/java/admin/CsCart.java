package admin;

import com.codeborne.selenide.SelenideElement;
import storefront.CategoryPage;
import static com.codeborne.selenide.Selenide.*;

public class CsCart {
    public CsCart(){super();}

    SelenideElement menu_Products = $x("//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']");
    SelenideElement section_Categories = $("a[href$='dispatch=categories.manage']");
    SelenideElement categoryElectronics = $(".multiple-table-row a[href$='category_id=166']");
    SelenideElement gearwheelOfCategory = $(".dropdown-icon--tools");
    SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");
    public CategoryPage navigateToCategoryPage (int tabNumber){
        menu_Products.hover();
        section_Categories.click();
        categoryElectronics.click();
        gearwheelOfCategory.click();
        button_Preview.click();
        switchTo().window(tabNumber);
        return new CategoryPage();
    }

    SelenideElement menu_Addons = $("#elm_menu_addons");
    SelenideElement section_DownloadedAddons = $("#elm_menu_addons_downloaded_add_ons");
    SelenideElement gearwheel_UniTheme = $x("//tr[@id='addon_abt__unitheme2']//button[@class='btn dropdown-toggle']");
    SelenideElement section_ColorSchemeSettings = $("div[class=\"btn-group dropleft open\"] a[href$='abt__ut2.less_settings']");
    public ColorschemeSettings navigateToPage_ColorSchemeSettings(){
        menu_Addons.hover();
        section_DownloadedAddons.click();
        gearwheel_UniTheme.click();
        section_ColorSchemeSettings.click();
        return new ColorschemeSettings();
    }
    SelenideElement gearwheel_CategoryBanners = $("#addon_ab__category_banners").$(".nowrap.inline-block-basic");
    SelenideElement section_BannersManagement = $("#addon_ab__category_banners a[href$='ab__category_banners.manage']");
    SelenideElement section_GeneralSettings = $("#addon_ab__category_banners a[href$='addon=ab__category_banners']");

    public BannersManagementPage navigateToPage_BannersManagement(){
        menu_Addons.hover();
        section_DownloadedAddons.click();
        gearwheel_CategoryBanners.click();
        section_BannersManagement.click();
        return new BannersManagementPage();
    }
    public void navigateToPage_GeneralSettings(){
        menu_Addons.hover();
        section_DownloadedAddons.click();
        gearwheel_CategoryBanners.click();
        section_GeneralSettings.click();
    }
}