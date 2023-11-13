package admin;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ColorschemeSettings {
    public ColorschemeSettings(){super();}

    public SelenideElement button_Save = $("a[data-ca-dispatch=\"dispatch[abt__ut2.update_settings]\"]");
    public SelenideElement fieldOfActiveColorscheme = $("a[id^='sw_select_'][id$='_wrap_currency']");
    public SelenideElement activateColorscheme = $x("//div[@class=\"language-wrap\"]//a[contains(text(),\"CS-Cart\")]");
    public SelenideElement setting_RoundCornersForElements = $(By.id("settings.abt__ut2.general.use_rounding"));
    public SelenideElement setting_RoundCornersOfBlocks = $(By.id("settings.abt__ut2.general.use_rounding_blocks"));
    public SelenideElement setting_DisplayHeadersInCapitalLetters = $(By.id("settings.abt__ut2.general.use_titles_uppercase"));
    public SelenideElement tab_ProductLists = $(".nav-tabs #product_list");
    public SelenideElement setting_FrameType = $(By.id("settings.abt__ut2.product_list.show_grid_border"));
    public SelenideElement setting_MaskForProductImages = $(By.id("settings.abt__ut2.product_list.mask_images_gallery"));

}