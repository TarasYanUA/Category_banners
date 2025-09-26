package storefront;

import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class AssertsPage {
    public AssertsPage() {
        super();
    }

    SoftAssert softAssert = CollectAssertMessages.getSoftAssertions();

    public String firstBannerForGrid = "img[src*='category_banners_main_image-1']";
    public String firstBannerForListWithoutOptions = "img[src*='category_banners_list_image-1']";
    public String firstBannerForCompactList = "img[src*='category_banners_short_list_pair-1']";
    public String secondBannerForGrid = "img[src*='category_banners_main_image-2']";
    public String secondBannerForListWithoutOptions = "img[src*='category_banners_list_image-2']";
    public String secondBannerForCompactList = "img[src*='category_banners_short_list_pair-2']";


    public void assertBannerExists(String selector) {
        Map<String, String> messages = Map.of(
                firstBannerForGrid, "There is no first banner of Image type for Grid!",
                firstBannerForListWithoutOptions,"There is no first banner of Image type for ListWithoutOptions!",
                firstBannerForCompactList, "There is no first banner of Image type for CompactList!",
                secondBannerForGrid,"There is no second banner of Image type for Grid!",
                secondBannerForListWithoutOptions,"There is no second banner of Image type for ListWithoutOptions!",
                secondBannerForCompactList,"There is no second banner of Image type for CompactList!"
        );

        String message = messages.get(selector);
        if (message == null)
            throw new IllegalArgumentException("No assert found for selector: " + selector);

        softAssert.assertTrue($(selector).exists(), message
                + "\nБаннеры могут отсутствовать из-за ошибки #43074");
        //Есть ошибка видимости баннеров https://abteam.planfix.com/task/43074
    }
}