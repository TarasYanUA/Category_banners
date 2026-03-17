package blog;

import admin.BannersManagementPage;
import admin.Block_Blog;
import admin.ColorschemeSettings;
import admin.CsCart;
import com.codeborne.selenide.Condition;
import testRunner.TestRunner;
import enums.BlockTitle;
import org.testng.annotations.Test;
import storefront.CategoryPage;
import utils.Utils;

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

public class Blog_RecentPostsScroller_SecondaryFramedBlock__Var3Test extends TestRunner {
    @Test
    public void setConfiguration_Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateTo_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var3();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateTo_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if(!$x("//a[text()='Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            bannersManagementPage.field_Name.setValue("Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test");
            bannersManagementPage.set_BlockForBanner(BlockTitle.BLOG.value(),
                    "AB: Второстепенный блок с обрамлением",
                    "fill--gray",
                    "grid");
            bannersManagementPage.set_BlockForBanner(BlockTitle.BLOG.value(),
                    "AB: Второстепенный блок с обрамлением",
                    "fill--gray",
                    "without options");
            bannersManagementPage.set_BlockForBanner(BlockTitle.BLOG.value(),
                    "AB: Второстепенный блок с обрамлением",
                    "fill--gray",
                    "compact");
            bannersManagementPage.field_Position.setValue("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.setBlogSettings("прокрутка последних постов", "");
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test")
    public void check_Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateTo_CategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "1600 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1602 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1604 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "1606 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1608 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "1610 Blog_ABBlogRecentPosts_RecentPostsScroller_SecondaryFramedBlock__Var3Test - Grid (RTL)");
    }
}