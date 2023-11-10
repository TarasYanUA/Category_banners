package admin;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CsCart {
    public CsCart(){super();}
    SelenideElement menu_Addons = $("#elm_menu_addons");
    SelenideElement section_DownloadedAddons = $("#elm_menu_addons_downloaded_add_ons");

    public void navigateToPage_DownloadedAddons(){
        menu_Addons.hover();
        section_DownloadedAddons.click();
    }
    SelenideElement gearwheel_CategoryBanners = $("#addon_ab__category_banners").$(".nowrap.inline-block-basic");
    SelenideElement section_BannersManagement = $("#addon_ab__category_banners a[href$='ab__category_banners.manage']");
    SelenideElement section_GeneralSettings = $("#addon_ab__category_banners a[href$='addon=ab__category_banners']");

    public BannersManagementPage navigateToPage_BannersManagement(){
        gearwheel_CategoryBanners.click();
        section_BannersManagement.click();
        return new BannersManagementPage();
    }
    public void navigateToPage_GeneralSettings(){
        gearwheel_CategoryBanners.click();
        section_GeneralSettings.click();
    }
}