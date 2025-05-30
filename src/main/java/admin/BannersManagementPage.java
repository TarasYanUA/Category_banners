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
    public SelenideElement setting_BlockSettings = $("#ajax_update_block_products_multicolumns .cs-icon.icon-cog");


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
        if ($x("//tbody//span[contains(text(), '" + picName + "')]").exists()) {
            $x("//tbody//span[contains(text(), '" + picName + "')]").doubleClick();
        } else {
            folder_PublicFiles.click();
            folder_CategoryBanners.click();
            folder_8.click();
            if ($("div[title='В виде списка']").exists()) {
                $("div[title='В виде списка']").click();
            }
            $x("//tbody//span[contains(text(), '" + picName + "')]").click();
            $x("//tbody//span[contains(text(), '" + picName + "')]").doubleClick();
        }
    }

    public void set_ImageForBanner() {
        if (!status_Disabled.isEmpty()) {   //если присутствует статус "Выкл.", то включаем баннер
            for (int i = 0; i <= status_Disabled.size(); i++) {
                status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }
        if (!$x("//a[text()='Autobanner of Image type']").exists()) {
            Utils.shiftLanguage("ru");
            $("a[href$='category_banner_id=1']").click();
            field_Name.setValue("Autobanner of Image type");
            typeImage_Grid.click();
            button_Server_Grid.click();
            selectPictureForBanner(Utils.firstBannerName_Grid);
            typeImage_WithoutOptions.click();
            button_Server_WithoutOptions.click();
            selectPictureForBanner(Utils.firstBannerName_WithoutOptions);
            typeImage_Compact.click();
            button_Server_Compact.click();
            selectPictureForBanner(Utils.firstBannerName_CompactList);
            field_Position.setValue("2");
            button_Save.click();

            Utils.shiftLanguage("ar");
            field_Name.setValue("Autobanner of Image type");
            typeImage_Grid.click();
            button_Server_Grid.click();
            selectPictureForBanner(Utils.firstBannerName_Grid);
            typeImage_WithoutOptions.click();
            button_Server_WithoutOptions.click();
            selectPictureForBanner(Utils.firstBannerName_WithoutOptions);
            typeImage_Compact.click();
            button_Server_Compact.click();
            selectPictureForBanner(Utils.firstBannerName_CompactList);
            button_Save.click();
        }
    }
}