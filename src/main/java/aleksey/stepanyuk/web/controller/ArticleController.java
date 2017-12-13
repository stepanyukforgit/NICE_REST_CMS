package aleksey.stepanyuk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ArticleController {

    @GetMapping("/a_article_create")
    public String article() {
        return "admin/components/article/a_article_create";
    }

    @GetMapping("/a_article_edit")
    public String articleEdit() {
        return "admin/components/article/a_article_edit";
    }

    @GetMapping("/a_article_list")
    public String articleList() {
        return "admin/components/article/a_article_list";
    }
}
