import admin.BannersManagementPage;
import admin.Block_Blog;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import storefront.CategoryPage;
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
* Позиция -         6

Настройки блока "Блог":
* Шаблон "Блог: текстовые ссылки"
* Заполнение "Блог: текстовые ссылки"
* Макс. число элементов - 5
*/

public class BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test extends TestRunner implements Set_BlockForBanner {
    @Test
    public void setConfiguration_BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        if($x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").exists()){   //если баннер2 "Вкл.", то отключаем его
            $x("//a[@id='sw_select_2_wrap'][contains(text(), 'Вкл.')]").click();
            $x("//div[contains(@class, 'dropleft open')]//a[@title='Выкл.']").click();
        }
        if(!$x("//a[text()='BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test");
            set_BlockForBanner_Grid("Блог", "--", "");
            set_BlockForBanner_WithoutOptions("Блог", "--", "");
            set_BlockForBanner_Compact("Блог", "--", "");
            bannersManagementPage.clickAndType_field_Position("6");
            bannersManagementPage.button_Save.click();

            bannersManagementPage.selectLanguageRTL();
            bannersManagementPage.clickAndType_field_Name("BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test");
            set_BlockForBanner_Grid("Blog", "--", "");
            set_BlockForBanner_WithoutOptions("Blog", "--", "");
            set_BlockForBanner_Compact("Blog", "--", "");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.set_Blog_TextLinks();
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test")
    public void check_BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner_TextLinks("400 BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test - Grid");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_TextLinks("402 BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test - WithoutOptions");
        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_TextLinks("404 BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test - CompactList");

        shiftToRTLLanguage();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_TextLinks("406 BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_TextLinks("408 BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_TextLinks("410 BannerType_Block__Blog_TextLinks_NoWrapper__Var1Test - Grid (RTL)");
    }
}