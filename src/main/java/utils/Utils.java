package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Utils {
    public static void waitForSpinnerDisappear() {
        $("div#ajax_loading_box[style=\"display: block;\"]").shouldBe(Condition.disappear, Duration.ofSeconds(10));
        sleep(1000);
    }

    public static void closeNotifications() {
        ElementsCollection notifications;
        do {
            notifications = $$(".cm-notification-content.alert-success .cm-notification-close");
            for (SelenideElement notification : notifications) {
                notification.click();
            }
        } while (!notifications.isEmpty());
    }

    public static void shiftLanguage(String ruEnAr){
        closeNotifications();
        $("a[id$=_wrap_content]").click();
        $(".content-wrap a[href$='descr_sl=" + ruEnAr + "']").click();
    }

    public static void switchOffSecondAndNextBanners() {
        ElementsCollection banners = $$x("//a[starts-with(@id, 'sw_select_') and contains(text(), 'Вкл.')]");
        for (SelenideElement banner : banners) {
            String id = banner.getAttribute("id");
            if (id != null) {
                try {
                    int number = Integer.parseInt(id.replace("sw_select_", "").replace("_wrap", ""));
                    if (number >= 2) {
                        banner.click();
                        $x("//div[contains(@class, 'dropleft open')]//a[@title='Выкл.']").click();
                    }
                } catch (NumberFormatException ignored) {
                    // Пропускаем, если id не содержит корректный номер
                }
            }
        }
    }

    public static void setCheckbox(SelenideElement checkbox, boolean shouldBeChecked) {
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.scrollIntoCenter().click();
        }
    }

    //Из-за ошибки https://abteam.planfix.com/task/43074 пришлось делать условия на присутствие баннеров на странице категории и обновлять страницу, если баннер отсутствует
    public static void conditionForBannerDisplay(SelenideElement banner){
        if(!banner.exists()){
            Selenide.refresh();
            Selenide.sleep(2000);
        }
    }
}