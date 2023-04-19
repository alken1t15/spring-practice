package alken1t.shop.controller;

import alken1t.shop.entity.Product;
import alken1t.shop.pojo.User;
import alken1t.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/view_controller")
public class ViewController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(path = "/first_resource")
    public String firstResource(Model model) {
        String message = "Message from ViewController.firstResource";
        model.addAttribute("message", message);
        model.addAttribute("number", 777);
        return "first_resource_page";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource(Model model) {
        User user = new User("Bill", 16);
        model.addAttribute("user", user);
        return "second_resource_page";
    }

    @GetMapping(path = "/third_resource")
    public String thirdResource(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User("Bill", 66));
        users.add(new User("Jeff", 35));
        users.add(new User("Mick", 17));
        users.add(new User("Max", 25));
        model.addAttribute("users", users);
        return "third_resource_page";
    }

    @GetMapping(path = "/fourth_resource")
    public String fourthResource(Model model) {
        Sort sort = Sort.by(
                Sort.Order.desc("price")
        );
        List<Product> products = productRepository.findAll(sort);
        model.addAttribute("products", products);
        return "fourth_resource_page";
    }
}