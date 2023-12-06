package admin;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Block_Video {
    public Block_Video(){super();}

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
    public void setSettingsForVideoBlock_Var1(){
        button_Settings.click();
        setting_NumberOfColumns.selectOptionByValue("4");
        if(!setting_DisplayLinkToProduct.isSelected()) {
            setting_DisplayLinkToProduct.click();
        }
        if(!setting_DisplayVideoTitle.isSelected()) {
            setting_DisplayVideoTitle.click();
        }
        if(!setting_DisplayVideoDescription.isSelected()) {
            setting_DisplayVideoDescription.click();
        }
        tab_BlockSettings.click();
        setting_NumberOfVideos.click();
        setting_NumberOfVideos.clear();
        setting_NumberOfVideos.sendKeys("10");
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
    public void setSettingsForVideoBlock_Var2(){
        button_Settings.click();
        setting_NumberOfColumns.selectOptionByValue("3");
        if(setting_DisplayLinkToProduct.isSelected()) {
            setting_DisplayLinkToProduct.click();
        }
        if(setting_DisplayVideoTitle.isSelected()) {
            setting_DisplayVideoTitle.click();
        }
        if(setting_DisplayVideoDescription.isSelected()) {
            setting_DisplayVideoDescription.click();
        }
        tab_BlockSettings.click();
        setting_NumberOfVideos.click();
        setting_NumberOfVideos.clear();
        setting_NumberOfVideos.sendKeys("3");
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
    public void setSettingsForVideoBlock_Var3(){
        button_Settings.click();
        setting_NumberOfColumns.selectOptionByValue("3");
        if(setting_DisplayLinkToProduct.isSelected()) {
            setting_DisplayLinkToProduct.click();
        }
        if(setting_DisplayVideoTitle.isSelected()) {
            setting_DisplayVideoTitle.click();
        }
        if(setting_DisplayVideoDescription.isSelected()) {
            setting_DisplayVideoDescription.click();
        }
        tab_BlockSettings.click();
        setting_NumberOfVideos.click();
        setting_NumberOfVideos.clear();
        setting_NumberOfVideos.sendKeys("5");
        button_Save.click();
    }
}
