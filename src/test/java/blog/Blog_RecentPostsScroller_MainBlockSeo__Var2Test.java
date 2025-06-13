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
    - Скруглить углы для элементов интерфейса - Не использовать
    - Скруглить углы блоков, окон, баннеров -   нет
    - Отображать заголовки заглавными буквами - да

 Вкладка "Списки товаров":
    - Тип обрамления товара в сетке -           Без рамки
    - Добавить фон/маску для изображений товара -   нет

Настройки баннера:
* Тип контента -    Блок
* Блок -            Блог
* На всю ширину -   да
* Оболочка -        AB: Основной блок (SEO)
* Пользовательский CSS-класс - fill--color
* Позиция -         6

Настройки блока "Блог":
* Шаблон "АВ: Блог: Последние посты"
* Заполнение "Блог: прокрутка последних постов"
*/

public class Blog_RecentPostsScroller_MainBlockSeo__Var2Test extends TestRunner {
    @Test
    public void setConfiguration_Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateToPage_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var2();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateToPage_BannersManagement();
        Utils.switchOffSecondBanner();
        if(!$x("//a[text()='Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test']").exists()) {
            $("a[href$='category_banner_id=1']").click();
            bannersManagementPage.field_Name.setValue("Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test");
            bannersManagementPage.set_BlockForBanner("Блог", "AB: Основной блок (SEO)", "fill--color", "grid");
            bannersManagementPage.set_BlockForBanner("Блог", "AB: Основной блок (SEO)", "fill--color", "without options");
            bannersManagementPage.set_BlockForBanner("Блог", "AB: Основной блок (SEO)", "fill--color", "compact");
            bannersManagementPage.field_Position.setValue("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.setBlogSettings("прокрутка последних постов", "");
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test")
    public void check_Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateToCategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "1300 Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1302 Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1304 Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "1306 Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1308 Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1310 Blog_ABBlogRecentPosts_RecentPostsScroller_MainBlockSeo__Var2Test - Grid (RTL)");
    }
}