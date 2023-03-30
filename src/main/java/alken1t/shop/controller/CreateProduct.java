package alken1t.shop.controller;

import alken1t.shop.entity.Category;
import alken1t.shop.entity.Option;
import alken1t.shop.entity.Product;
import alken1t.shop.entity.Value;
import alken1t.shop.repository.CategoryRepository;
import alken1t.shop.repository.OptionRepository;
import alken1t.shop.repository.ProductRepository;
import alken1t.shop.repository.ValueRepository;
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

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ValueRepository valueRepository;


    @GetMapping
    public String productPage(@RequestParam long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        model.addAttribute("category", category);
        return "create_product_page";
    }

    @PostMapping
    public String createProductAction(
            @RequestParam long categoryId,
            @RequestParam String name,
            @RequestParam int price,
            @RequestParam(name = "option") List<Long> optionIds,
            @RequestParam(name = "value") List<String> values
    ) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = new Product(category, name, price);
        productRepository.save(product);
        for (int i = 0; i < optionIds.size(); i++) {
            Option option = optionRepository.findById(optionIds.get(i)).orElseThrow();
            Value value = new Value(product, option, values.get(i));
            valueRepository.save(value);
        }
        return "redirect:/product?categoryId=" + categoryId;
    }
}