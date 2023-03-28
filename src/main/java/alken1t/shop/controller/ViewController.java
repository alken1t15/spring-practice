package alken1t.shop.controller;

import alken1t.shop.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/view_controller")
public class ViewController {

    @GetMapping(path = "/first_resource")
    public String firstResource(Model model){
        String message = "Message from ViewController.firstResource";
        model.addAttribute("message",message);
        model.addAttribute("number",777);
        return "first_resource_page";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource(Model model){
        User user = new User("Bill", 66);
        model.addAttribute("user", user);
        return "second_resource_page";
    }
}
