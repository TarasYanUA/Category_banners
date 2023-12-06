package admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class Block_Blog {
    public Block_Blog(){super();}

    SelenideElement template = $("select[id*='blog_template']");
    SelenideElement tab_Content = $("li[id*='block_contents']");
    SelenideElement filling = $("select[id*='content_items_filling']");
    SelenideElement limit = $("input[id*='items_properties_items_limit']");
    public void clickAndType_Limit(String number){
        limit.click();
        limit.clear();
        limit.setValue(number);
    }
    SelenideElement lastDays = $("input[id*='content_items_properties_items_last_days']");
    public void clickAndType_LastDays(String number){
        lastDays.click();
        lastDays.clear();
        lastDays.setValue(number);
    }
    SelenideElement button_Save = $("input[name='dispatch[block_manager.update_block]']");


    public void set_Blog_TextLinks(){   //Заполнение не нужно, так как здесь только одно заполнение
        template.selectOptionByValue("addons/blog/blocks/text_links.tpl");
        if($("#ajax_loading_box[style*='display:']").exists()){
            $("#ajax_loading_box[style='display: none;']").shouldBe(Condition.exist);
        }
        tab_Content.click();
        clickAndType_Limit("5");
        button_Save.click();
    }
    public void set_Blog_ABBlog_RecentPostsScroller(String fillingValue){
        template.selectOptionByValue("addons/blog/blocks/abt_ut2_recent_posts.tpl");
        if($("#ajax_loading_box[style*='display:']").exists()){
            $("#ajax_loading_box[style='display: none;']").shouldBe(Condition.exist);
        }
        tab_Content.click();
        filling.selectOptionByValue(fillingValue);
        button_Save.click();
    }
    public void set_Blog_ABBlogRecentPosts(String limitNumber){
        template.selectOptionByValue("addons/blog/blocks/abt_ut2_recent_posts.tpl");
        if($("#ajax_loading_box[style*='display:']").exists()){
            $("#ajax_loading_box[style='display: none;']").shouldBe(Condition.exist);
        }
        tab_Content.click();
        filling.selectOptionByValue("blog.recent_posts");
        clickAndType_LastDays("1000");
        clickAndType_Limit(limitNumber);
        button_Save.click();
    }
}