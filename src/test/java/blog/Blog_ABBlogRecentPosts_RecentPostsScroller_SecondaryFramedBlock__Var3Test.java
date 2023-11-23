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
    - Скруглить углы для элементов интерфейса - Придать небольшую округлость
    - Скруглить углы блоков, окон, баннеров -   да
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Рамка с внешними отступами
    - Добавить фон/маску для изображений товара -   да

Настройки баннера:
* Тип контента -    Блок
* Блок -            Блог
* На всю ширину -   да
* Оболочка -        AB: Второстепенный блок с обрамлением
* Пользовательский CSS-класс - fill--gray
* Позиция -         6

Настройки блока "Блог":
* Шаблон "АВ: Блог: Последние посты"
* Заполнение "Блог: прокрутка последних постов"
*/

public class Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test extends TestRunner implements Set_BlockForBanner, SwitchOffSecondBanner {
    @Test
    public void setConfiguration_Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var3();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        switchOffSecondBanner();
        if(!$x("//a[text()='Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.clickAndType_field_Name("Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test");
            set_BlockForBanner_Grid("Блог", "AB: Второстепенный блок с обрамлением", "fill--gray");
            set_BlockForBanner_WithoutOptions("Блог", "AB: Второстепенный блок с обрамлением", "fill--gray");
            set_BlockForBanner_Compact("Блог", "AB: Второстепенный блок с обрамлением", "fill--gray");
            bannersManagementPage.clickAndType_field_Position("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.set_Blog_ABBlogRecentPosts("blog.recent_posts_scroller");
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test")
    public void check_Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("1600 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - Grid");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("1602 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - WithoutOptions");
        categoryPage.productListView_CompactList.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("1604 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - CompactList");

        shiftToRTLLanguage();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("1606 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("1608 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.hover().click();
        Selenide.sleep(2000);
        categoryPage.scrollToAndScreenBanner_ABBlogRecentPosts("1610 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - Grid (RTL)");
    }
}