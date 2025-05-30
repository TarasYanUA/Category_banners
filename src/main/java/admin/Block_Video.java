package admin;

import com.codeborne.selenide.SelenideElement;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;

public class Block_Video {
    public Block_Video() {
        super();
    }

    SelenideElement button_Settings = $("a[id^='sw_case_settings_']");
    SelenideElement setting_NumberOfColumns = $("select[id$='ab__vg_videos_properties_number_of_columns']");
    SelenideElement setting_DisplayLinkToProduct = $("input[id$='ab__vg_videos_properties_ab__vg_show_product_link']");
    SelenideElement setting_DisplayVideoTitle = $("input[id$='ab__vg_videos_properties_ab__vg_show_video_title']");
    SelenideElement setting_DisplayVideoDescription = $("input[id$='ab__vg_videos_properties_ab__vg_show_video_description']");
    SelenideElement tab_BlockSettings = $("li[id^='block_settings_']");
    SelenideElement setting_NumberOfVideos = $("input[id$='ab__vg_videos_properties_ab__vg_max_videos']");
    SelenideElement button_Save = $("input[name='dispatch[block_manager.update_block]']");

    /*
    Настройки блока "Видео товаров":
    * Количество колонок в списке - 4
    * Отобразить ссылку на товар -  да
    * Отобразить заголовок видео -  да
    * Отобразить описание видео -   да
    * Количество видео -            10
     */
    public void setSettingsForVideoBlock_Var1() {
        button_Settings.click();
        setting_NumberOfColumns.selectOptionByValue("4");
        Utils.setCheckbox(setting_DisplayLinkToProduct, true);
        Utils.setCheckbox(setting_DisplayVideoTitle, true);
        Utils.setCheckbox(setting_DisplayVideoDescription, true);
        tab_BlockSettings.click();
        setting_NumberOfVideos.setValue("10");
        button_Save.click();
    }

    /*
Настройки блока "Видео товаров":
* Количество колонок в списке - 3
* Отобразить ссылку на товар -  нет
* Отобразить заголовок видео -  нет
* Отобразить описание видео -   нет
* Количество видео -            3
 */
    public void setSettingsForVideoBlock_Var2() {
        button_Settings.click();
        setting_NumberOfColumns.selectOptionByValue("3");
        Utils.setCheckbox(setting_DisplayLinkToProduct, false);
        Utils.setCheckbox(setting_DisplayVideoTitle, false);
        Utils.setCheckbox(setting_DisplayVideoDescription, false);
        tab_BlockSettings.click();
        setting_NumberOfVideos.setValue("3");
        button_Save.click();
    }

    /*
Настройки блока "Видео товаров":
* Количество колонок в списке - 3
* Отобразить ссылку на товар -  да
* Отобразить заголовок видео -  нет
* Отобразить описание видео -   нет
* Количество видео -            5
*/
    public void setSettingsForVideoBlock_Var3() {
        button_Settings.click();
        setting_NumberOfColumns.selectOptionByValue("3");
        Utils.setCheckbox(setting_DisplayLinkToProduct, true);
        Utils.setCheckbox(setting_DisplayVideoTitle, false);
        Utils.setCheckbox(setting_DisplayVideoDescription, false);
        tab_BlockSettings.click();
        setting_NumberOfVideos.setValue("5");
        button_Save.click();
    }
}