package storefront;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AssertsPage {
    public AssertsPage(){super();}

    public SelenideElement secondBannerForGrid = $("img[src*='category_banners_main_image-2']");
    public SelenideElement secondBannerForListWithoutOptions = $("img[src*='category_banners_list_image-2']");
    public SelenideElement secondBannerForCompactList = $("img[src*='category_banners_short_list_pair-2']");
}