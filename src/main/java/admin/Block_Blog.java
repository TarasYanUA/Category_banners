package admin;

import com.codeborne.selenide.SelenideElement;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;

public class Block_Blog {
    public Block_Blog(){super();}

    SelenideElement template = $("select[id*='blog_template']");
    SelenideElement tab_Content = $("li[id*='block_contents']");
    SelenideElement limit = $("input[id*='items_properties_items_limit']");
    SelenideElement lastDays = $("input[id*='content_items_properties_items_last_days']");
    SelenideElement button_Save = $("input[name='dispatch[block_manager.update_block]']");


    public void setBlogSettings(String pattern, String limitNumber) {
        switch (pattern.trim()) {
            case "текстовые ссылки":
                template.selectOptionByValue("addons/blog/blocks/text_links.tpl");
                Utils.waitForSpinnerDisappear();
                tab_Content.click();
                limit.setValue(limitNumber);
                break;

            case "прокрутка последних постов":
                template.selectOptionByValue("addons/blog/blocks/recent_posts_scroller.tpl");
                Utils.waitForSpinnerDisappear();
                break;

            case "АВ Последние посты":
                template.selectOptionByValue("addons/blog/blocks/abt_ut2_recent_posts.tpl");
                Utils.waitForSpinnerDisappear();
                tab_Content.click();
                limit.setValue(limitNumber);
                lastDays.setValue("1000");
                break;

            default:
                throw new IllegalArgumentException("Неизвестный шаблон: " + pattern);
        }

        button_Save.click();
    }
}