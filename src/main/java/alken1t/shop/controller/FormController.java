package alken1t.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/form")
public class FormController {

    @GetMapping
    public String formPage() {
        return "form_page";
    }

    @PostMapping
    @ResponseBody
    public String formAction(@RequestParam String country, @RequestParam String login, @RequestParam String password) {
        System.out.printf("Country: %s%n", country);
        System.out.printf("Login: %s%n", login);
        System.out.printf("Password: %s%n", password);
        return "Успешно";
    }
}