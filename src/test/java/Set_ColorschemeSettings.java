import admin.ColorschemeSettings;

public interface Set_ColorschemeSettings {
    ColorschemeSettings colorschemeSettings = new ColorschemeSettings();

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
    default void set_ColorschemeSettings_Var1() {
        colorschemeSettings.setting_RoundCornersForElements.selectOptionByValue("full");
        if (!colorschemeSettings.setting_RoundCornersOfBlocks.isSelected()) {
            colorschemeSettings.setting_RoundCornersOfBlocks.click();
        }
        if (colorschemeSettings.setting_DisplayHeadersInCapitalLetters.isSelected()) {
            colorschemeSettings.setting_DisplayHeadersInCapitalLetters.click();
        }
        colorschemeSettings.tab_ProductLists.click();
        colorschemeSettings.setting_FrameType.selectOptionByValue("solid_without_margins");
        if (colorschemeSettings.setting_MaskForProductImages.isSelected()) {
            colorschemeSettings.setting_MaskForProductImages.click();
        }
        colorschemeSettings.button_Save.click();
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
    default void set_ColorschemeSettings_Var2() {
        colorschemeSettings.setting_RoundCornersForElements.selectOptionByValue("do_not_use");
        if(colorschemeSettings.setting_RoundCornersOfBlocks.isSelected()){
            colorschemeSettings.setting_RoundCornersOfBlocks.click();   }
        if(!colorschemeSettings.setting_DisplayHeadersInCapitalLetters.isSelected()){
            colorschemeSettings.setting_DisplayHeadersInCapitalLetters.click(); }
        colorschemeSettings.tab_ProductLists.click();
        colorschemeSettings.setting_FrameType.selectOptionByValue("none");
        if(colorschemeSettings.setting_MaskForProductImages.isSelected()){
            colorschemeSettings.setting_MaskForProductImages.click();   }
        colorschemeSettings.button_Save.click();
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
    default void set_ColorschemeSettings_Var3() {
        colorschemeSettings.setting_RoundCornersForElements.selectOptionByValue("little");
        if(!colorschemeSettings.setting_RoundCornersOfBlocks.isSelected()){
            colorschemeSettings.setting_RoundCornersOfBlocks.click();   }
        if(!colorschemeSettings.setting_DisplayHeadersInCapitalLetters.isSelected()){
            colorschemeSettings.setting_DisplayHeadersInCapitalLetters.click(); }
        colorschemeSettings.tab_ProductLists.click();
        colorschemeSettings.setting_FrameType.selectOptionByValue("solid_with_margins");
        if(!colorschemeSettings.setting_MaskForProductImages.isSelected()){
            colorschemeSettings.setting_MaskForProductImages.click();   }
        colorschemeSettings.button_Save.click();
    }
}