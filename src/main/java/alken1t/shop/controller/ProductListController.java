package alken1t.shop.controller;

import alken1t.shop.entity.Category;
import alken1t.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/product_list_controller")
public class ProductListController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public String productListPage(@RequestParam long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        model.addAttribute("category", category);
        return "product_resource_page";
    }
}