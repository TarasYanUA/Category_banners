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
* Оболочка -        AB: Второстепенный блок
* Пользовательский CSS-класс - fill--color
* Позиция -         6

Настройки блока "Блог":
* Шаблон "Блог: текстовые ссылки"
* Заполнение "Блог: текстовые ссылки"
* Макс. число элементов - 5
*/

public class Blog_TextLinks_SecondaryBlock__Var2Test extends TestRunner {
    @Test
    public void setConfiguration_BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test(){
        CsCart csCart = new CsCart();
        //Работаем с настройками цветосхемы
        ColorschemeSettings colorschemeSettings = csCart.navigateTo_ColorSchemeSettings();
        colorschemeSettings.set_ColorschemeSettings_Var2();

        //Работаем с баннерами
        BannersManagementPage bannersManagementPage = csCart.navigateTo_BannersManagement();
        Utils.switchOffSecondAndNextBanners();
        if(!$x("//a[text()='BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test']").exists()) {
            bannersManagementPage.clickBannerLinkIfExists();
            bannersManagementPage.field_Name.setValue("BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test");
            bannersManagementPage.addCategoryToBanner();
            bannersManagementPage.set_BlockForBanner(BlockTitle.BLOG.value(),
                    "AB: Второстепенный блок",
                    "fill--color",
                    "grid");
            bannersManagementPage.set_BlockForBanner(BlockTitle.BLOG.value(),
                    "AB: Второстепенный блок",
                    "fill--color",
                    "without options");
            bannersManagementPage.set_BlockForBanner(BlockTitle.BLOG.value(),
                    "AB: Второстепенный блок",
                    "fill--color",
                    "compact");
            bannersManagementPage.field_Position.setValue("6");
            bannersManagementPage.button_Save.click();

            //Работаем с настройками блока "Блог"
            bannersManagementPage.setting_BlockSettings.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            Block_Blog blockBlog = new Block_Blog();
            blockBlog.setBlogSettings("текстовые ссылки", "6");
        }
    }

    @Test (priority = 2, dependsOnMethods = "setConfiguration_BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test")
    public void check_BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test() {
        CsCart csCart = new CsCart();
        CategoryPage categoryPage = csCart.navigateTo_CategoryPage(1);
        categoryPage.scrollToAndScreenBanner(null, "900 BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test - Grid");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "902 BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test - WithoutOptions");
        categoryPage.productListView_CompactList.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "904 BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test - CompactList");

        selectLanguage_RTL();
        categoryPage.scrollToAndScreenBanner(null, "906 BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test - CompactList (RTL)");
        categoryPage.productListView_ListWithoutOptions.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "908 BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test - WithoutOptions (RTL)");
        categoryPage.productListView_Grid.scrollIntoCenter().click();
        Utils.waitForSpinnerDisappear();
        categoryPage.scrollToAndScreenBanner(null, "910 BannerType_Block__Blog_TextLinks_SecondaryBlock__Var2Test - Grid (RTL)");
    }
}