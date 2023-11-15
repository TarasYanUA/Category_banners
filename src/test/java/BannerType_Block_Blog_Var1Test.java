import admin.BannersManagementPage;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/*
Настройки цветосхемы:
 Вкладка "Общие":
    - Скруглить углы для элементов интерфейса - Полностью скруглить
    - Скруглить углы блоков, окон, баннеров -   да
    - Отображать заголовки заглавными буквами - нет

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Рамка без внешних отступов
    - Добавить фон/маску для изображений товара - нет

Настройки баннера:
* Тип контента -    Блок
* Блок -            Блог
* На всю ширину -   да
* Оболочка -        нет
* Пользовательский CSS-класс - нет
* Позиция -         2
*/

public class BannerType_Block_Blog_Var1Test extends TestRunner implements Set_ColorschemeSettings, Set_BlockForBanner, ScrollToAndScreenBanner {
    @Test
    public void setConfiguration_BannerType_Block_Blog_Var1Test(){
        CsCart csCart = new CsCart();
//Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.fieldOfActiveColorscheme.click();
        colorschemeSettings.activateColorscheme.click();
        Selenide.sleep(2000);
        set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        if($x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").exists()){   //если баннер2 "Вкл.", то отключаем его
            $x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").click();
            $x("//div[contains(@class, 'dropleft open')]//a[@title='Выкл.']").click();
        }
        if(!$x("//a[text()='Autobanner for Blog']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("Autobanner for Blog");
            set_BlockForBanner_Grid("Блог", "--", "");
//ЗДЕСЬ ОСТАНОВИЛСЯ


            bannersManagementPage.typeBlock_WithoutOptions.click();
            bannersManagementPage.button_SelectBlock_WithoutOptions.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            $("strong[title='Блог']").click();
            bannersManagementPage.setting_Wrapper_WithoutOptions.selectOptionContainingText("--");
            bannersManagementPage.clickAndTypeSetting_CssClass_WithoutOptions("");

            bannersManagementPage.typeBlock_Compact.click();
            bannersManagementPage.button_SelectBlock_Compact.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            $("strong[title='Блог']").click();
            bannersManagementPage.setting_Wrapper_Compact.selectOptionContainingText("--");
            bannersManagementPage.clickAndTypeSetting_CssClass_Compact("");
            bannersManagementPage.clickAndType_field_Position("2");
            bannersManagementPage.button_Save.click();

            bannersManagementPage.selectLanguageRTL();
            bannersManagementPage.clickAndType_field_Name("Autobanner for Blog");
            bannersManagementPage.typeBlock_Grid.click();
            bannersManagementPage.button_SelectBlock_Grid.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            $("strong[title='Blog']").click();
            if(!bannersManagementPage.setting_Full_width.isSelected()){
                bannersManagementPage.setting_Full_width.click();
            }
            bannersManagementPage.setting_Wrapper_Grid.selectOptionContainingText("--");
            bannersManagementPage.clickAndTypeSetting_CssClass_Grid("");

            bannersManagementPage.typeBlock_WithoutOptions.click();
            bannersManagementPage.button_SelectBlock_WithoutOptions.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            $("strong[title='Blog']").click();
            bannersManagementPage.setting_Wrapper_WithoutOptions.selectOptionContainingText("--");
            bannersManagementPage.clickAndTypeSetting_CssClass_WithoutOptions("");

            bannersManagementPage.typeBlock_Compact.click();
            bannersManagementPage.button_SelectBlock_Compact.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            $("strong[title='Blog']").click();
            bannersManagementPage.setting_Wrapper_Compact.selectOptionContainingText("--");
            bannersManagementPage.clickAndTypeSetting_CssClass_Compact("");
            bannersManagementPage.clickAndType_field_Position("2");
            bannersManagementPage.button_Save.click();
        }
    }
}