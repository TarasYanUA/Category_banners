package storefront;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AssertsPage {
    public AssertsPage(){super();}

    public SelenideElement secondBannerForGrid = $("img[src$='category_banners_main_image-2_x7ch-l7.jpg']");

}
