package videoGallery;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public interface AddVideo{
    default void addVideo() {
        //Переходим на страницу редактирования товара
        $("a[href='#products']").hover();
        $x("//span[text()='Товары']").click();
        if ($(".cm-notification-close").exists()) {
            $(".cm-notification-close").click();
        }
        $x("//td[@class='product-name-column wrap-word']//a[contains(text(), 'Apple - iPhone 5c')]").click();
        $("#ab__video_gallery").click();

        if ($$("tr[id*='ab__vg_video_extra']").size() == 1) {
            //Добавляем видео с YouTube
            $x("//input[@name='product_data[ab__vg_videos][1][pos]']").click();
            $x("//input[@name='product_data[ab__vg_videos][1][pos]']").setValue("10");
            $(By.name("product_data[ab__vg_videos][1][title]")).click();
            $(By.name("product_data[ab__vg_videos][1][title]")).setValue("Музыка, успокаивает нервную систему и радует душу");
            $("#ab__vg__video_path__1").click();
            $("#ab__vg__video_path__1").setValue("ayjzgSwtzbY");

            //Добавляем видео с Vimeo
            $("#add_ab__vg_video").click();
            $(By.name("product_data[ab__vg_videos][2][pos]")).click();
            $(By.name("product_data[ab__vg_videos][2][pos]")).setValue("20");
            $(By.name("product_data[ab__vg_videos][2][title]")).click();
            $(By.name("product_data[ab__vg_videos][2][title]")).setValue("Disney Channel/ABC Oscar");
            $(By.name("product_data[ab__vg_videos][2][type]")).click();
            $(By.name("product_data[ab__vg_videos][2][type]")).selectOption("Vimeo");
            $("#ab__vg__video_path__1_1").click();
            $("#ab__vg__video_path__1_1").setValue("154625007");

            //Добавляем видео с YouTube
            $("#add_ab__vg_video_1").click();
            $x("//input[@name='product_data[ab__vg_videos][4][pos]']").click();
            $x("//input[@name='product_data[ab__vg_videos][4][pos]']").setValue("30");
            $(By.name("product_data[ab__vg_videos][4][title]")).click();
            $(By.name("product_data[ab__vg_videos][4][title]")).setValue("AUROSONIC - 30 VOCAL TRANCE HITS");
            $("#ab__vg__video_path__1_1_2").click();
            $("#ab__vg__video_path__1_1_2").setValue("ABDHiwJXTag");

            //Добавляем видео с YouTube
            $("#add_ab__vg_video_1_2").click();
            $x("//input[@name='product_data[ab__vg_videos][7][pos]']").click();
            $x("//input[@name='product_data[ab__vg_videos][7][pos]']").setValue("40");
            $(By.name("product_data[ab__vg_videos][7][title]")).click();
            $(By.name("product_data[ab__vg_videos][7][title]")).setValue("TREE OF LIFE - Beautiful Inspirational Orchestral Music Mix");
            $("#ab__vg__video_path__1_1_2_3").click();
            $("#ab__vg__video_path__1_1_2_3").setValue("SA7uXNeVRjs");
            $(".cm-product-save-buttons").hover().click();
        }
    }
}