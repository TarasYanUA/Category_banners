package storefront;

import com.codeborne.selenide.SelenideElement;
import org.jspecify.annotations.Nullable;
import utils.Utils;

import static com.codeborne.selenide.Selenide.*;

public class CategoryPage {
    public CategoryPage() {
        super();
    }

    public SelenideElement productListView_Grid = $(".ut2-icon-products-multicolumns");
    public SelenideElement productListView_ListWithoutOptions = $(".ut2-icon-products-without-options");
    public SelenideElement productListView_CompactList = $(".ut2-icon-short-list");


    public void scrollToAndScreenBanner(@Nullable SelenideElement banner, String screenshotName) {
        Utils.waitForSpinnerDisappear();

        if (banner == null)
            banner = $(".category-banner-block");

        Utils.conditionForBannerDisplay(banner);

        banner.scrollIntoCenter();
        screenshot(screenshotName);
    }
}