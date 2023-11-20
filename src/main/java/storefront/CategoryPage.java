package storefront;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;

public class CategoryPage {
    public CategoryPage(){super();}

    public SelenideElement productListView_Grid = $(".ty-icon.ty-icon-products-multicolumns");
    public SelenideElement productListView_ListWithoutOptions = $(".ty-icon.ty-icon-products-without-options");
    public SelenideElement productListView_CompactList = $("div[class='ut2-sorting-wrap'] span[class='ty-icon ty-icon-short-list']");

    public void scrollToAndScreenBanner_TextLinks(String screenshotName){
        if($("#ajax_loading_box[style*='display:']").exists()){
            $("#ajax_loading_box[style='display: none;']").shouldBe(Condition.exist);
        }
        $(".ty-blog-text-links").scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}");
        screenshot(screenshotName);
    }
}