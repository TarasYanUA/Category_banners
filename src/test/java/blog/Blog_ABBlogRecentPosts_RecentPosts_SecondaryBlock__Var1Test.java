package blog;

import admin.BannersManagementPage;
import admin.Block_Blog;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import interfaces_TestRunner.Set_BlockForBanner;
import interfaces_TestRunner.SwitchOffSecondBanner;
import interfaces_TestRunner.TestRunner;
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
* Оболочка -        AB: Второстепенный блок
* Пользовательский CSS-класс - нет
* Позиция -         6

Настройки блока "Блог":
* Шаблон "АВ: Блог: Последние посты"
* Заполнение "Блог: прокрутка последних постов"
*/

public class Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test extends TestRunner implements Set_BlockForBanner, SwitchOffSecondBanner {
    @Test
    public void setConfiguration_Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        switchOffSecondBanner();
        if(!$x("//a[text()='Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test");
            set_BlockForBanner_Grid("Блог", "AB: Второстепенный блок", "");
            set_BlockForBanner_WithoutOptions("Блог", "AB: Второстепенный блок", "");
            set_BlockForBanner_Compact("Блог", "AB: Второстепенный блок", "");
            bannersManagementPage.clickAndType_field_Position("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.set_Blog_ABBlogRecentPosts("1");
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test")
    public void check_Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("2000 Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test - Grid");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("2002 Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test - WithoutOptions");
        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("2004 Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test - CompactList");

        selectLanguage_RTL();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("2006 Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("2008 Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("2010 Blog_ABBlogRecentPosts_RecentPosts_SecondaryBlock__Var1Test - Grid (RTL)");
    }
}