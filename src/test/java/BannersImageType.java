import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;

public interface BannersImageType {
    String firstBannerName_Grid = "category_banners_main_image-1";
    String firstBannerName_WithoutOptions = "category_banners_list_image-1";
    String firstBannerName_CompactList = "category_banners_short_list_pair-1";

    String secondBannerName_Grid = "category_banners_main_image-2";
    String secondBannerName_WithoutOptions = "category_banners_list_image-2";
    String secondBannerName_CompactList = "category_banners_short_list_pair-2";


//Из-за ошибки https://abteam.planfix.com/task/43074 пришлось делать условия на присутствие баннеров на странице категории и обновлять страницу, если баннер отсутствует
    default void scrollAndScreenBanner(String bannerName, String screenshotName) {
        if(!$("img[src*='" + bannerName + "']").exists()){
            Selenide.refresh();
            Selenide.sleep(2000);
        }
        $("img[src*='" + bannerName + "']").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}");
        //$("img[src*='" + bannerName + "']").scrollIntoView(false);
        screenshot(screenshotName);
    }
}
