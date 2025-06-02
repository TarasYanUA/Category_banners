package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class Utils {
    public static void waitForSpinnerDisappear() {
        $("div#ajax_loading_box[style=\"display: block;\"]").shouldBe(Condition.disappear, Duration.ofSeconds(10));
        sleep(1000);
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