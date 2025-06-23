package admin;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Block_FastNavigation {
    public Block_FastNavigation() {super();}

    SelenideElement button_BlockSettings = $("a[id*='sw_case_settings_']");
    SelenideElement setting_DisplayType = $("select[name='block_data[properties][ab__fn_display_type]']");
    SelenideElement numberOfColumns_Desktop = $("input[name=\"block_data[properties][ab__fn_number_of_columns_desktop]\"]");
    SelenideElement numberOfColumns_Notebook = $("input[name=\"block_data[properties][ab__fn_number_of_columns_desktop_small]\"]");
    SelenideElement button_Save = $("input[name='dispatch[block_manager.update_block]']");


    public void setSettingsForBlock_FastNavigation(String template, String displayType, String columns) {
        $("select[name='block_data[properties][template]']").selectOptionContainingText(template);
        sleep(1500);
        button_BlockSettings.click();
        setting_DisplayType.selectOptionContainingText(displayType);
        numberOfColumns_Desktop.setValue(columns);
        numberOfColumns_Notebook.setValue(columns);
        button_Save.click();
    }
}
