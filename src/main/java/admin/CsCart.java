package admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import storefront.CategoryPage;
import static com.codeborne.selenide.Selenide.*;

public class CsCart {
    public CsCart(){super();}

    SelenideElement menu_Products = $x("//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']");
    SelenideElement section_Categories = $("a[href$='dispatch=categories.manage']");
    SelenideElement categoryElectronics = $(".multiple-table-row a[href$='category_id=166']");
    SelenideElement gearwheelOfCategory = $(".dropdown-icon--tools");
    SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");
    SelenideElement cookiesOnStorefront = $(".cm-btn-success");
    public CategoryPage navigateToCategoryPage (int tabNumber){
        menu_Products.hover();
        section_Categories.click();
        categoryElectronics.click();
        Selenide.sleep(2000);
        gearwheelOfCategory.click();
        button_Preview.click();
        switchTo().window(tabNumber);
        cookiesOnStorefront.click();
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

    public BannersManagementPage navigateToPage_BannersManagement(){
        menu_Addons.hover();
        section_DownloadedAddons.click();
        gearwheel_CategoryBanners.click();
        section_BannersManagement.click();
        return new BannersManagementPage();
    }

    SelenideElement gearwheel_AddonsManager = $("#addon_ab__addons_manager").$(".nowrap.inline-block-basic");
    SelenideElement section_ListOfAvailableAddons = $("#addon_ab__addons_manager a[href$='ab__am.addons']");
    public SelenideElement gearwheel_VideoGallery = $("tr#addon_ab__video_gallery button.btn.dropdown-toggle");
    SelenideElement addonsManagerField_Search = $("#ab__am_search");
    public void installAddonAtAddonsManager(SelenideElement addonMenu, String addonCode, String installButton){
        menu_Addons.hover();
        section_DownloadedAddons.click();
        if(!$(addonMenu).exists()) {
            gearwheel_AddonsManager.click();
            section_ListOfAvailableAddons.click();
            addonsManagerField_Search.click();
            addonsManagerField_Search.sendKeys(addonCode);
            addonsManagerField_Search.sendKeys(Keys.ENTER);
            $(installButton).click();
            Alert alert = Selenide.webdriver().driver().switchTo().alert();
            alert.accept();
            Selenide.sleep(11000);
            $(menu_Addons).shouldBe(Condition.enabled);
        }
    }

    SelenideElement menu_Design = $("#elm_menu_design");
    SelenideElement section_Layouts = $("#elm_menu_design_layouts");
    SelenideElement section_Blocks = $("#elm_menu_design_layouts_manage_blocks");

    public void addBlock_VideoGallery(){
        menu_Design.hover();
        section_Layouts.hover();
        section_Blocks.click();
        $("#elm_type").selectOptionByValue("ab__vg_videos");
        $(".advanced-search-field__search").click();
        if($x("//p[text()='Здесь пока ничего нет']").exists()){
            $(".cs-icon.icon-plus").click();
            $(".ui-dialog-title").shouldBe(Condition.exist);
            $("strong[title='AB: Видео товаров']").click();
            $("input[name='block_data[description][name]']").shouldBe(Condition.enabled).click();
            $("input[name='block_data[description][name]']").sendKeys("Видео товаров");
            $("input[name='dispatch[block_manager.update_block]']").click();
        }
    }
}