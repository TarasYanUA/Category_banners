package interfaces_TestRunner;

import admin.BannersManagementPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;

public interface Set_BlockForBanner {
    BannersManagementPage bannersManagementPage = new BannersManagementPage();

    default void set_BlockForBanner_Grid(String blockName, String wrapperText, String cssClass){
        bannersManagementPage.typeBlock_Grid.click();
        bannersManagementPage.button_SelectBlock_Grid.click();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        $("#content_user_existing_blocks_products_multicolumns strong[title='" + blockName + "']").click();
        Selenide.sleep(1000);
        if(!bannersManagementPage.setting_Full_width.isSelected()){
            bannersManagementPage.setting_Full_width.click();
        }
        bannersManagementPage.setting_Wrapper_Grid.selectOption(wrapperText);
        bannersManagementPage.clickAndTypeSetting_CssClass_Grid(cssClass);
    }

    default void set_BlockForBanner_WithoutOptions(String blockName, String wrapperText, String cssClass){
        bannersManagementPage.typeBlock_WithoutOptions.click();
        bannersManagementPage.button_SelectBlock_WithoutOptions.click();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        $("#content_user_existing_blocks_products_without_options strong[title='" + blockName + "']").click();
        Selenide.sleep(1000);
        bannersManagementPage.setting_Wrapper_WithoutOptions.selectOption(wrapperText);
        bannersManagementPage.clickAndTypeSetting_CssClass_WithoutOptions(cssClass);
    }

    default void set_BlockForBanner_Compact(String blockName, String wrapperText, String cssClass){
        bannersManagementPage.typeBlock_Compact.click();
        bannersManagementPage.button_SelectBlock_Compact.click();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        $("#content_user_existing_blocks_short_list strong[title='" + blockName + "']").click();
        Selenide.sleep(1000);
        bannersManagementPage.setting_Wrapper_Compact.selectOption(wrapperText);
        bannersManagementPage.clickAndTypeSetting_CssClass_Compact(cssClass);
    }
}