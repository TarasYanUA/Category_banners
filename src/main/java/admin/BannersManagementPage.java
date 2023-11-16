package admin;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class BannersManagementPage {
    public BannersManagementPage(){super();}
    SelenideElement button_Language = $("a[id$=_wrap_content]");
    SelenideElement languageRu = $(".content-wrap a[href$='descr_sl=ru']");
    SelenideElement languageRTL = $(".content-wrap a[href$='descr_sl=ar']");
    public void selectLanguageRU(){
        button_Language.click();
        languageRu.click();
    }
    public void selectLanguageRTL(){
        button_Language.click();
        languageRTL.click();
    }
    public ElementsCollection status_Disabled = $$x("//a[@id][contains(text(), 'Выкл.')]");
    public SelenideElement field_Name = $("#elm_category_banner");
    public void clickAndType_field_Name(String name){
        field_Name.click();
        field_Name.clear();
        field_Name.sendKeys(name);
    }

    //Настройки для Вид списка "Сетка"
    public SelenideElement typeImage_Grid = $("input#image_products_multicolumns[value='I']");
    public SelenideElement typeBlock_Grid = $("#block_products_multicolumns");
    public SelenideElement button_Server_Grid = $x("(//a[contains(@id, 'server_')])[1]");
    public SelenideElement button_SelectBlock_Grid = $("#opener_select_block_products_multicolumns");
    public SelenideElement setting_Full_width = $("#elm_full_width_block_products_multicolumns");
    public SelenideElement setting_Wrapper_Grid = $("#products_multicolumns_wrapper");
    SelenideElement setting_CssClass_Grid = $("#elm_category_banner_products_multicolumns_user_class");
    public void clickAndTypeSetting_CssClass_Grid(String value){
        setting_CssClass_Grid.click();
        setting_CssClass_Grid.clear();
        setting_CssClass_Grid.setValue(value);
    }


    //Настройки для Вид списка "Список без опций"
    public SelenideElement typeImage_WithoutOptions = $("input#image_products_without_options[value='I']");
    public SelenideElement typeBlock_WithoutOptions = $("#block_products_without_options");
    public SelenideElement button_Server_WithoutOptions = $x("(//a[contains(@id, 'server_')])[2]");
    public SelenideElement button_SelectBlock_WithoutOptions = $("#opener_select_block_products_without_options");
    public SelenideElement setting_Wrapper_WithoutOptions = $("#products_without_options_wrapper");
    SelenideElement setting_CssClass_WithoutOptions = $("#elm_category_banner_products_without_options_user_class");
    public void clickAndTypeSetting_CssClass_WithoutOptions(String value){
        setting_CssClass_WithoutOptions.click();
        setting_CssClass_WithoutOptions.clear();
        setting_CssClass_WithoutOptions.setValue(value);
    }


    //Настройки для Вид списка "Компактный список"
    public SelenideElement typeImage_Compact = $("input#image_short_list[value='I']");
    public SelenideElement typeBlock_Compact = $("#block_short_list");
    public SelenideElement button_Server_Compact = $x("(//a[contains(@id, 'server_')])[3]");
    public SelenideElement button_SelectBlock_Compact = $("#opener_select_block_short_list");
    public SelenideElement setting_Wrapper_Compact = $("#short_list_wrapper");
    public SelenideElement setting_CssClass_Compact = $("#elm_category_banner_short_list_user_class");
    public void clickAndTypeSetting_CssClass_Compact(String value){
        setting_CssClass_Compact.click();
        setting_CssClass_Compact.clear();
        setting_CssClass_Compact.setValue(value);
    }


    SelenideElement field_Position = $("#elm_position");
    public void clickAndType_field_Position(String position){
        field_Position.click();
        field_Position.clear();
        field_Position.sendKeys(position);
    }
    public SelenideElement button_Save = $(".cm-submit.btn-primary ");

    SelenideElement folder_PublicFiles = $("span[title='Пользовательские файлы']");
    SelenideElement folder_CategoryBanners = $("span[title='Пользовательские файлы/category_banner']");
    SelenideElement folder_8 = $("span[title='Пользовательские файлы/category_banner/8']");
    public void selectPictureForBanner(String picName){
        Selenide.sleep(1000);
        if($x("//tbody//span[contains(text(), '" + picName + "')]").exists()){
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
}