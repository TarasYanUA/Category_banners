package storefront;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CategoryPage {
    public CategoryPage(){super();}

    public SelenideElement productListView_Grid = $(".ty-icon.ty-icon-products-multicolumns");
    public SelenideElement productListView_ListWithoutOptions = $(".ty-icon.ty-icon-products-without-options");
    public SelenideElement productListView_CompactList = $("div[class='ut2-sorting-wrap'] span[class='ty-icon ty-icon-short-list']");
}
