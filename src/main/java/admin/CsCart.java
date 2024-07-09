package admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import storefront.CategoryPage;
import static com.codeborne.selenide.Selenide.*;

public class CsCart implements CheckMenuToBeActive {
    public CsCart(){super();}

    SelenideElement cookiesOnStorefront = $(".cm-btn-success");
    

    //Меню "Товары"
    SelenideElement menu_Products = $("a[href$='dispatch=products.manage'].main-menu-1__link");
    SelenideElement section_Categories = $(By.id("products_categories"));
    SelenideElement categoryElectronics = $(".multiple-table-row a[href$='category_id=166']");
    SelenideElement gearwheelOfCategory = $(".dropdown-icon--tools");
    SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");

    public CategoryPage navigateToCategoryPage (int tabNumber) {
        checkMenuToBeActive("dispatch=products.manage", menu_Products);
        section_Categories.click();
        categoryElectronics.click();
        Selenide.sleep(2000);
        gearwheelOfCategory.click();
        button_Preview.click();
        switchTo().window(tabNumber);
        cookiesOnStorefront.click();
        return new CategoryPage();
    }


    //Меню "Модули -- Скачанные модули"
    SelenideElement menu_Addons = $x("//span[text()='Модули']");
    SelenideElement menu_DownloadedAddons = $(By.id("addons_downloaded_add_ons"));
    SelenideElement themeSectionsOnPage_DownloadedAddons = $x("//tr[@id='addon_abt__unitheme2']//button[@class='btn dropdown-toggle']");
    SelenideElement section_colorSchemeSettings = $("div[class='btn-group dropleft open'] a[href$='abt__ut2.less_settings']");
    SelenideElement gearwheel_CategoryBanners = $("#addon_ab__category_banners").$(".nowrap.inline-block-basic");
    SelenideElement section_BannersManagement = $("#addon_ab__category_banners a[href$='ab__category_banners.manage']");
    SelenideElement gearwheel_AddonsManager = $("#addon_ab__addons_manager").$(".nowrap.inline-block-basic");
    SelenideElement section_ListOfAvailableAddons = $("#addon_ab__addons_manager a[href$='ab__am.addons']");
    public SelenideElement gearwheel_VideoGallery = $("tr#addon_ab__video_gallery button.btn.dropdown-toggle");
    SelenideElement addonsManagerField_Search = $("#ab__am_search");


    public void navigateTo_DownloadedAddonsPage() {
        checkMenuToBeActive("dispatch=addons.manage", menu_Addons);
        menu_DownloadedAddons.click();
    }

    public ColorschemeSettings navigateToPage_ColorSchemeSettings(){
        navigateTo_DownloadedAddonsPage();
        themeSectionsOnPage_DownloadedAddons.click();
        section_colorSchemeSettings.click();
        return new ColorschemeSettings();
    }

    public BannersManagementPage navigateToPage_BannersManagement(){
        navigateTo_DownloadedAddonsPage();
        gearwheel_CategoryBanners.click();
        section_BannersManagement.click();
        return new BannersManagementPage();
    }

    public void installAddonAtAddonsManager(SelenideElement addonMenu, String addonCode, String installButton){
        navigateTo_DownloadedAddonsPage();
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


    //Меню "Веб-сайт -- Темы -- Макеты"
    SelenideElement menu_Website = $("a[href$='dispatch=themes.manage'].main-menu-1__link");
    SelenideElement section_Themes = $("#website_themes");
    SelenideElement section_Layouts = $(".nav__actions-bar a[href$='block_manager.manage']");
    SelenideElement section_Blocks = $("#elm_menu_design_layouts_manage_blocks");

    public void addBlock_VideoGallery(){
        checkMenuToBeActive("dispatch=themes.manage", menu_Website);
        section_Themes.click();
        section_Layouts.click();
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