package videoGallery;

import admin.CheckMenuToBeActive;
import org.openqa.selenium.By;
import utils.Utils;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public interface AddVideo extends CheckMenuToBeActive {
    default void addVideo() {
        //Переходим на страницу редактирования товара
        checkMenuToBeActive("dispatch=products.manage", $("a[href$='dispatch=products.manage'].main-menu-1__link"));
        $(By.id("products_products")).click();
        Utils.closeNotifications();
        $x("//td[@class='product-name-column wrap-word']//a[contains(text(), 'Apple - iPhone 5c')]").click();
        $("#ab__video_gallery").click();

        if ($$("tr[id*='ab__vg_video_extra']").size() == 1) {
            //Добавляем видео с YouTube
            $x("//input[@name='product_data[ab__vg_videos][1][pos]']").setValue("10");
            $(By.name("product_data[ab__vg_videos][1][title]")).setValue("Музыка, успокаивает нервную систему и радует душу");
            $("#ab__vg__video_path__1").setValue("ayjzgSwtzbY");

            //Добавляем видео с Vimeo
            $("#box_add_ab__vg_video .btn-add").click();
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[pos]')])[2]").setValue("20");
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[title]')])[2]")
                    .setValue("Disney Channel/ABC Oscar");
            $x("(//select[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[type]')])[2]").selectOption("Vimeo");
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[video_path]')])[2]").setValue("154625007");

            //Добавляем видео с YouTube
            $x("(//tbody[contains(@id, 'box_add_ab__vg_video_')]//a[@class='btn btn-add'])[1]").click();
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[pos]')])[3]").setValue("30");
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[title]')])[3]")
                    .setValue("AUROSONIC - 30 VOCAL TRANCE HITS");
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[video_path]')])[3]").setValue("ABDHiwJXTag");

            //Добавляем видео с YouTube
            $x("(//tbody[contains(@id, 'box_add_ab__vg_video_')]//a[@class='btn btn-add'])[2]").click();
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[pos]')])[4]").setValue("40");
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[title]')])[4]").setValue("TREE OF LIFE - Beautiful Inspirational Orchestral Music Mix");
            $x("(//input[contains(@name, 'product_data[ab__vg_videos]')][contains(@name, '[video_path]')])[4]").setValue("SA7uXNeVRjs");
            $(".cm-product-save-buttons").scrollIntoCenter().click();
        }
    }
}