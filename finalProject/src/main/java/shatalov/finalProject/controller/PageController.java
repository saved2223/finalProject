package shatalov.finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("loadArt")
    public String loadArt() {
        return "loadArt";
    }



    @GetMapping("/showArticle/{articleId}")
    public String showArticle(@PathVariable(name = "articleId") String id, Model model) {
        model.addAttribute("articleId", id);
        return "showArticle";
    }
}
