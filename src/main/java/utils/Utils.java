package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Utils {
    public static String firstBannerName_Grid = "category_banners_main_image-1";
    public static String firstBannerName_WithoutOptions = "category_banners_list_image-1";
    public static String firstBannerName_CompactList = "category_banners_short_list_pair-1";
    public static String secondBannerName_Grid = "category_banners_main_image-2";
    public static String secondBannerName_WithoutOptions = "category_banners_list_image-2";
    public static String secondBannerName_CompactList = "category_banners_short_list_pair-2";

    public static void waitForSpinnerDisappear() {
        $("div#ajax_loading_box[style=\"display: block;\"]").shouldBe(Condition.disappear, Duration.ofSeconds(10));
        sleep(1000);
    }

    //Из-за ошибки https://abteam.planfix.com/task/43074 пришлось делать условия на присутствие баннеров на странице категории и обновлять страницу, если баннер отсутствует
    public static void scrollToAndScreenBanner(String bannerName, String screenshotName) {
        waitForSpinnerDisappear();
        if(!$("img[src*='" + bannerName + "']").exists()){
            Selenide.refresh();
            Selenide.sleep(2000);
        }
        $("img[src*='" + bannerName + "']").scrollIntoCenter();
        screenshot(screenshotName);
    }

    public static void shiftLanguage(String ruEnAr){
        ElementsCollection notifications;
        do {
            notifications = $$(".cm-notification-content.alert-success .cm-notification-close");
            for (SelenideElement notification : notifications) {
                notification.click();
            }
        } while (!notifications.isEmpty());
        $("a[id$=_wrap_content]").click();
        $(".content-wrap a[href$='descr_sl=" + ruEnAr + "']").click();
    }

    public static void switchOffSecondBanner(){
        if($x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").exists()) {   //если баннер2 "Вкл.", то отключаем его
            $x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").click();
            $x("//div[contains(@class, 'dropleft open')]//a[@title='Выкл.']").click();
        }
    }

    public static void setCheckbox(SelenideElement checkbox, boolean shouldBeChecked) {
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.scrollIntoCenter().click();
        }
    }
}