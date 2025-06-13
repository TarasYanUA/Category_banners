package blog;

import admin.BannersManagementPage;
import admin.Block_Blog;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import TestRunner.TestRunner;
import org.testng.annotations.Test;
import storefront.CategoryPage;
import utils.Utils;

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
* Оболочка -        AB: Упрощенный блок
* Пользовательский CSS-класс - нет
* Позиция -         6

Настройки блока "Блог":
* Шаблон "АВ: Блог: Последние посты"
* Заполнение "Блог: прокрутка последних постов"
*/

public class Blog_RecentPostsScroller_SimplifiedBlock__Var1Test extends TestRunner {
    @Test
    public void setConfiguration_BannerType_Block__Blog_ABBlogRecentPosts_SimplifiedBlock__Var1Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var1();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        Utils.switchOffSecondBanner();
        if(!$x("//a[text()='Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.field_Name.setValue("Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test");
            bannersManagementPage.set_BlockForBanner("Блог", "AB: Упрощенный блок", "", "grid");
            bannersManagementPage.set_BlockForBanner("Блог", "AB: Упрощенный блок", "", "without options");
            bannersManagementPage.set_BlockForBanner("Блог", "AB: Упрощенный блок", "", "compact");
            bannersManagementPage.field_Position.setValue("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.setBlogSettings("прокрутка последних постов", "");
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Block__Blog_ABBlogRecentPosts_SimplifiedBlock__Var1Test")
    public void check_BannerType_Block__Blog_ABBlogRecentPosts_SimplifiedBlock__Var1Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "1400 Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1402 Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1404 Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "1406 Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1408 Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1410 Blog_ABBlogRecentPosts_RecentPostsScroller_SimplifiedBlock__Var1Test - Grid (RTL)");
    }
}