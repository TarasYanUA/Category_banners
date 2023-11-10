import admin.BannersManagementPage;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;

public class BannerType_Image_Var1Test extends TestRunner {
    @Test(priority = 1)
    public void setConfiguration_BannerType_Image_Var1Test() {
        CsCart csCart = new CsCart();
        csCart.navigateToPage_DownloadedAddons();
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        bannersManagementPage.selectLanguageRU();
        if(!bannersManagementPage.status_Disabled.isEmpty()){   //если присутствует статус "Выкл.", то включаем баннер
            for(int i = 0; i <= bannersManagementPage.status_Disabled.size(); i++){
                bannersManagementPage.status_Disabled.get(i).shouldBe(Condition.enabled).click();
                $x("//div[contains(@class, 'dropleft open')]//a[@title='Вкл.']").click();
            }
        }

        //Работаем с первым баннером
        $("a[href$='category_banner_id=1']").click();
        bannersManagementPage.clickAndType_field_Name("AutoBanner type Image 01");
        bannersManagementPage.button_ServerGrid.click();
        Selenide.sleep(1500);
        bannersManagementPage.selectPictureForBanner("category_banners_list_image-1.png");
    }
}
