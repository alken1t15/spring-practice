package alken1t.shop.controller;

import alken1t.shop.entity.Category;
import alken1t.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class CreateProduct {

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public String productPage(@RequestParam long categoryId, Model model){
       Category category = categoryRepository.findById(categoryId).orElseThrow();
       model.addAttribute("category",category);
        return "create_product_page";
    }
@PostMapping
    public String createProductAction(
        @RequestParam long categoryId,
        @RequestParam String name,
        @RequestParam int price,
        @RequestParam(name = "option") List<Long> optionIds,
        @RequestParam(name = "value") List<String> values
        ){
    System.out.println(categoryId);
    System.out.println(name);
    System.out.println(price);
    System.out.println(optionIds);
    System.out.println(values);

return "redirect:/create_product_page";
    }
}