package storefront;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.Utils;

import static com.codeborne.selenide.Selenide.*;

public class CategoryPage {
    public CategoryPage() {
        super();
    }

    public SelenideElement productListView_Grid = $(".ut2-icon-products-multicolumns");
    public SelenideElement productListView_ListWithoutOptions = $(".ut2-icon-products-without-options");
    public SelenideElement productListView_CompactList = $(".ut2-icon-short-list");


    public void scrollToAndScreenBanner_TextLinks(String screenshotName) {
        Utils.waitForSpinnerDisappear();
        $(".ty-blog-text-links").scrollIntoCenter();
        screenshot(screenshotName);
    }

    public void scrollToAndScreenBanner_ABBlogRecentPosts(String screenshotName) {
        Utils.waitForSpinnerDisappear();
        $(".category-banner-block").scrollIntoCenter();
        screenshot(screenshotName);
    }

    public void scrollToAndScreenBanner_VideoGallery(String screenshotName) {
        Utils.waitForSpinnerDisappear();
        if (!$(".category-banner-block").exists()) {
            Selenide.refresh();
            Selenide.sleep(2000);
        }
        if ($(".category-banner-block").exists()) {
            $(".category-banner-block").scrollIntoCenter();
            screenshot(screenshotName);
        }
    }
}