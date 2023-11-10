package admin;

import com.codeborne.selenide.ElementsCollection;
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
        field_Name.sendKeys(name);
    }
    public SelenideElement typeImage_Grid = $("input#image_products_multicolumns[value='I']");
    public SelenideElement typeImage_WithoutOptions = $("input#image_products_without_options[value='I']");
    public SelenideElement typeImage_Compact = $("input#image_short_list[value='I']");
    public SelenideElement typeBlock_Grid = $("input#image_products_multicolumns[value='B']");
    public SelenideElement typeBlock_WithoutOptions = $("input#image_products_without_options[value='B']");
    public SelenideElement typeBlock_Compact = $("input#image_short_list[value='B']");
    public SelenideElement button_ServerGrid = $x("(//a[contains(@id, 'server_')])[1]");
    public SelenideElement button_ServerWithoutOptions = $x("(//a[contains(@id, 'server_')])[2]");
    public SelenideElement button_ServerCompact = $x("(//a[contains(@id, 'server_')])[3]");
    public SelenideElement field_Position = $("#elm_position");
    public SelenideElement button_Save = $(".cm-submit.btn-primary ");

    SelenideElement folder_PublicFiles = $("span[title='Пользовательские файлы']");
    SelenideElement folder_CategoryBanners = $("span[title='Пользовательские файлы/category_banner']");
    SelenideElement folder_8 = $("span[title='Пользовательские файлы/category_banner/8']");
    public void selectPictureForBanner(String picName){
        folder_PublicFiles.click();
        folder_CategoryBanners.click();
        folder_8.click();
        if($("div[title='В виде списка']").exists()){
            $("div[title='В виде списка']").click();
        }
        $x("//tbody//span[text()='" + picName + "']").doubleClick();
    }
}
