import admin.BannersManagementPage;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;

public interface Set_BlockForBanner {
    BannersManagementPage bannersManagementPage = new BannersManagementPage();

    default void set_BlockForBanner_Grid(String bannerName, String wrapperText, String cssClass){
        bannersManagementPage.typeBlock_Grid.click();
        bannersManagementPage.button_SelectBlock_Grid.click();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        $("strong[title='" + bannerName + "']").click();
        if(!bannersManagementPage.setting_Full_width.isSelected()){
            bannersManagementPage.setting_Full_width.click();
        }
        bannersManagementPage.setting_Wrapper_Grid.selectOptionContainingText(wrapperText);
        bannersManagementPage.clickAndTypeSetting_CssClass_Grid(cssClass);
    }

}
