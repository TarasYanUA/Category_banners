package admin;

import com.codeborne.selenide.Selenide;
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


    /*
Настройки цветосхемы:
        Вкладка "Общие":
        - Скруглить углы для элементов интерфейса - Полностью скруглить
        - Скруглить углы блоков, окон, баннеров -   да
        - Отображать заголовки заглавными буквами - нет

        Вкладка "Списки товаров":
        - Тип обрамления товара в сетке -           Рамка без внешних отступов
        - Добавить фон/маску для изображений товара -   нет
*/
    public void set_ColorschemeSettings_Var1() {
        fieldOfActiveColorscheme.click();
        activateColorscheme.click();
        Selenide.sleep(2000);
        setting_RoundCornersForElements.selectOptionByValue("full");
        if (!setting_RoundCornersOfBlocks.isSelected()) {
            setting_RoundCornersOfBlocks.click();
        }
        if (setting_DisplayHeadersInCapitalLetters.isSelected()) {
            setting_DisplayHeadersInCapitalLetters.click();
        }
        tab_ProductLists.click();
        setting_FrameType.selectOptionByValue("solid_without_margins");
        if (setting_MaskForProductImages.isSelected()) {
            setting_MaskForProductImages.click();
        }
        button_Save.click();
    }

    /*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Не использовать
    - Скруглить углы блоков, окон, баннеров -   нет
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Без рамки
    - Добавить фон/маску для изображений товара -   нет
*/
    public void set_ColorschemeSettings_Var2() {
        fieldOfActiveColorscheme.click();
        activateColorscheme.click();
        Selenide.sleep(2000);
        setting_RoundCornersForElements.selectOptionByValue("do_not_use");
        if(setting_RoundCornersOfBlocks.isSelected()){
            setting_RoundCornersOfBlocks.click();   }
        if(!setting_DisplayHeadersInCapitalLetters.isSelected()){
            setting_DisplayHeadersInCapitalLetters.click(); }
        tab_ProductLists.click();
        setting_FrameType.selectOptionByValue("none");
        if(setting_MaskForProductImages.isSelected()){
            setting_MaskForProductImages.click();   }
        button_Save.click();
    }

    /*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Придать небольшую округлость
    - Скруглить углы блоков, окон, баннеров -   да
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Рамка с внешними отступами
    - Добавить фон/маску для изображений товара -   да
*/
    public void set_ColorschemeSettings_Var3() {
        fieldOfActiveColorscheme.click();
        activateColorscheme.click();
        Selenide.sleep(2000);
        setting_RoundCornersForElements.selectOptionByValue("little");
        if(!setting_RoundCornersOfBlocks.isSelected()){
            setting_RoundCornersOfBlocks.click();   }
        if(!setting_DisplayHeadersInCapitalLetters.isSelected()){
            setting_DisplayHeadersInCapitalLetters.click(); }
        tab_ProductLists.click();
        setting_FrameType.selectOptionByValue("solid_with_margins");
        if(!setting_MaskForProductImages.isSelected()){
            setting_MaskForProductImages.click();   }
        button_Save.click();
    }
}