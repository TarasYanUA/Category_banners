package interfaces_TestRunner;

import static com.codeborne.selenide.Selenide.$x;

public interface SwitchOffSecondBanner {
    default void switchOffSecondBanner(){
    if($x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").exists()) {   //если баннер2 "Вкл.", то отключаем его
        $x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").click();
        $x("//div[contains(@class, 'dropleft open')]//a[@title='Выкл.']").click();
        }
    }
}
