package admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.Utils;

import static com.codeborne.selenide.Selenide.*;

public class BannersManagementPage {
    public BannersManagementPage() {
        super();
    }

    public ElementsCollection status_Disabled = $$x("//a[@id][contains(text(), 'Выкл.')]");
    public SelenideElement field_Name = $("#elm_category_banner");
    public SelenideElement setting_BlockSettings = $(".cm-dialog-opener.action-properties.bm-action-properties");
    SelenideElement button_AddCategories = $("a[id^='opener_picker_categories_']");
    SelenideElement category_Electronics = $("#input_cat_166");
    SelenideElement button_SaveCategories = $(".buttons-container-picker .cm-dialog-closer");

    //Настройки для Вид списка "Сетка"
    public SelenideElement typeImage_Grid = $("input#image_products_multicolumns[value='I']");
    public SelenideElement typeBlock_Grid = $("#block_products_multicolumns");
    public SelenideElement button_Server_Grid = $x("(//a[contains(@id, 'server_')])[1]");
    public SelenideElement button_SelectBlock_Grid = $("#opener_select_block_products_multicolumns");
    public SelenideElement setting_Full_width = $("#elm_full_width_block_products_multicolumns");
    public SelenideElement setting_Wrapper_Grid = $("#products_multicolumns_wrapper");
    public SelenideElement setting_CssClass_Grid = $("#elm_category_banner_products_multicolumns_user_class");


    //Настройки для Вид списка "Список без опций"
    public SelenideElement typeImage_WithoutOptions = $("input#image_products_without_options[value='I']");
    public SelenideElement typeBlock_WithoutOptions = $("#block_products_without_options");
    public SelenideElement button_Server_WithoutOptions = $x("(//a[contains(@id, 'server_')])[2]");
    public SelenideElement button_SelectBlock_WithoutOptions = $("#opener_select_block_products_without_options");
    public SelenideElement setting_Wrapper_WithoutOptions = $("#products_without_options_wrapper");
    public SelenideElement setting_CssClass_WithoutOptions = $("#elm_category_banner_products_without_options_user_class");


    //Настройки для Вид списка "Компактный список"
    public SelenideElement typeImage_Compact = $("input#image_short_list[value='I']");
    public SelenideElement typeBlock_Compact = $("#block_short_list");
    public SelenideElement button_Server_Compact = $x("(//a[contains(@id, 'server_')])[3]");
    public SelenideElement button_SelectBlock_Compact = $("#opener_select_block_short_list");
    public SelenideElement setting_Wrapper_Compact = $("#short_list_wrapper");
    public SelenideElement setting_CssClass_Compact = $("#elm_category_banner_short_list_user_class");
    public SelenideElement button_Save = $(".cm-submit.btn-primary ");
    SelenideElement folder_PublicFiles = $("span[title='Пользовательские файлы']");
    SelenideElement folder_CategoryBanners = $("span[title='Пользовательские файлы/category_banner']");
    SelenideElement folder_8 = $("span[title='Пользовательские файлы/category_banner/8']");
    public SelenideElement field_Position = $("#elm_position");


    public void selectPictureForBanner(String picName) {
        Selenide.sleep(1000);
        SelenideElement banner = $x("//tbody//span[contains(text(), '" + picName + "')]");

        if (banner.exists()) {
            banner.doubleClick();
        } else {
            folder_PublicFiles.click();
            folder_CategoryBanners.click();
            folder_8.click();
            if ($("div[title='В виде списка']").exists())
                $("div[title='В виде списка']").click();
            banner.click();
            banner.doubleClick();
        }
    }

    private void createAutobanner() {
        String bannerName_Grid = "category_banners_main_image-1";
        String bannerName_WithoutOptions = "category_banners_list_image-1";
        String bannerName_CompactList = "category_banners_short_list_pair-1";

        clickBannerLinkIfExists();
        field_Name.setValue("Autobanner of Image type");

        typeImage_Grid.click();
        button_Server_Grid.click();
        selectPictureForBanner(bannerName_Grid);

        typeImage_WithoutOptions.click();
        button_Server_WithoutOptions.click();
        selectPictureForBanner(bannerName_WithoutOptions);

        typeImage_Compact.click();
        button_Server_Compact.click();
        selectPictureForBanner(bannerName_CompactList);
    }

    public void set_ImageForBanner() {
        if (!status_Disabled.isEmpty()) {   //если присутствует статус "Выкл.", то включаем баннеры
            for (int i = 0; i <= status_Disabled.size(); i++) {
                status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }

        if (!$x("//a[text()='Autobanner of Image type']").exists()) {
            Utils.shiftLanguage("ru");
            createAutobanner();
            field_Position.setValue("2");
            button_Save.click();
            $(".ab__am-menu").click();
            $(".dropdown-menu a[href$='dispatch=ab__category_banners.manage']").click();

            Utils.shiftLanguage("ar");
            createAutobanner();
            button_Save.click();
        }
    }


    private void selectAppropriateBlock(String value, String blockTitle) {
        SelenideElement block = $("#content_" + value + " strong[title='" + blockTitle + "']");
        Utils.waitForSpinnerDisappear();
        $(".ui-dialog-title").shouldBe(Condition.enabled);

        ElementsCollection selects = $$("div.tabs--enable-fill select");
        selects.last().selectOptionByValue(value);
        sleep(500);

        if (block.is(Condition.exist)) {
            block.click();
        } else {
                selects.last().selectOptionContainingText("Непривязанные блоки");
            sleep(500);

            ElementsCollection blocks = $$("div[id*='content_theme_layout_blocks_'] strong[title='" + blockTitle + "']");
            blocks.last().click();
        }
        Utils.waitForSpinnerDisappear();
    }

    public void set_BlockForBanner(String blockTitle, String wrapper, String cssClass, String template) {
        switch (template.toLowerCase()) {
            case "grid":
                typeBlock_Grid.click();
                button_SelectBlock_Grid.click();
                selectAppropriateBlock("theme_layout_blocks_abt__unitheme2-5products_multicolumns", blockTitle);
                if (!setting_Full_width.isSelected())
                    setting_Full_width.click();
                setting_Wrapper_Grid.selectOption(wrapper);
                setting_CssClass_Grid.setValue(cssClass);
                break;

            case "without options":
                typeBlock_WithoutOptions.click();
                button_SelectBlock_WithoutOptions.click();
                selectAppropriateBlock("theme_layout_blocks_abt__unitheme2-5products_without_options", blockTitle);
                setting_Wrapper_WithoutOptions.selectOption(wrapper);
                setting_CssClass_WithoutOptions.setValue(cssClass);
                break;

            case "compact":
                typeBlock_Compact.click();
                button_SelectBlock_Compact.click();
                selectAppropriateBlock("theme_layout_blocks_abt__unitheme2-5short_list", blockTitle);
                setting_Wrapper_Compact.selectOption(wrapper);
                setting_CssClass_Compact.setValue(cssClass);
                break;

            default:
                throw new IllegalArgumentException("Unsupported page type: " + template);
        }
    }

    public void addCategoryToBanner() {
        button_AddCategories.click();
        Utils.waitForSpinnerDisappear();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        if ($x("//span[text()='Магазин: CS-Cart']").exists())
            $x("//span[text()='Магазин: CS-Cart']/..//span[contains(@class, 'icon-caret-right')]").click();
        if (!category_Electronics.isSelected())
            category_Electronics.click();
        button_SaveCategories.click();
        $(".ui-dialog-title").shouldBe(Condition.disappear);
    }

    public void clickBannerLinkIfExists() {
        SelenideElement bannerLink = $("a[href$='category_banner_id=1']");
        (bannerLink.exists() ? bannerLink : $(".nav__actions-adv-buttons .cs-icon.cs-icon--type-plus")).click();
    }
}