package image;

import admin.BannersManagementPage;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public interface Set_ImageForBanner extends ScrollToAndScreenBanner{
    default void set_ImageForBanner(){
        BannersManagementPage bannersManagementPage = new BannersManagementPage();
        if(!bannersManagementPage.status_Disabled.isEmpty()){   //если присутствует статус "Выкл.", то включаем баннер
            for(int i = 0; i <= bannersManagementPage.status_Disabled.size(); i++){
                bannersManagementPage.status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }
        if(!$x("//a[text()='Autobanner of Image type']").exists()) {
            bannersManagementPage.selectLanguageRTL();
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("Autobanner of Image type");
            bannersManagementPage.typeImage_Grid.click();
            bannersManagementPage.button_Server_Grid.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_Grid);
            bannersManagementPage.typeImage_WithoutOptions.click();
            bannersManagementPage.button_Server_WithoutOptions.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_WithoutOptions);
            bannersManagementPage.typeImage_Compact.click();
            bannersManagementPage.button_Server_Compact.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_CompactList);
            bannersManagementPage.clickAndType_field_Position("2");
            bannersManagementPage.button_Save.click();

            bannersManagementPage.selectLanguageRU();
            bannersManagementPage.clickAndType_field_Name("Autobanner of Image type");
            bannersManagementPage.typeImage_Grid.click();
            bannersManagementPage.button_Server_Grid.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_Grid);
            bannersManagementPage.typeImage_WithoutOptions.click();
            bannersManagementPage.button_Server_WithoutOptions.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_WithoutOptions);
            bannersManagementPage.typeImage_Compact.click();
            bannersManagementPage.button_Server_Compact.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_CompactList);
            bannersManagementPage.button_Save.click();
        }
    }
}