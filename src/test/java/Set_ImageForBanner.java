import admin.BannersManagementPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public interface Set_ImageForBanner extends ScrollToAndScreenBanner{
    default void set_ImageForBanner(){
        BannersManagementPage bannersManagementPage = new BannersManagementPage();
        if(!$x("//a[text()='Autobanner of Image type 01']").exists()) {
            bannersManagementPage.selectLanguageRTL();
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("Autobanner of Image type 01");
            bannersManagementPage.typeImage_Grid.click();
            bannersManagementPage.button_ServerGrid.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_Grid);
            bannersManagementPage.typeImage_WithoutOptions.click();
            bannersManagementPage.button_ServerWithoutOptions.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_WithoutOptions);
            bannersManagementPage.typeImage_Compact.click();
            bannersManagementPage.button_ServerCompact.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_CompactList);
            bannersManagementPage.clickAndType_field_Position("2");
            bannersManagementPage.button_Save.click();

            bannersManagementPage.selectLanguageRU();
            bannersManagementPage.clickAndType_field_Name("Autobanner of Image type 01");
            bannersManagementPage.typeImage_Grid.click();
            bannersManagementPage.button_ServerGrid.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_Grid);
            bannersManagementPage.typeImage_WithoutOptions.click();
            bannersManagementPage.button_ServerWithoutOptions.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_WithoutOptions);
            bannersManagementPage.typeImage_Compact.click();
            bannersManagementPage.button_ServerCompact.click();
            bannersManagementPage.selectPictureForBanner(firstBannerName_CompactList);
            bannersManagementPage.button_Save.click();
        }
    }
}
