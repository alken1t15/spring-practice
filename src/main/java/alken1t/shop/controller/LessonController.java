package alken1t.shop.controller;

import alken1t.shop.pojo.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping(path = "/lesson_controller")
public class LessonController {

//    @RequestMapping(path = "/first_resource")
//    public List<Product> firstResource(HttpServletResponse response){
//        response.setCharacterEncoding("utf-8");
//        List<String> list = new ArrayList<>();
//        list.add("Values #1");
//        list.add("Values #2");
//        list.add("Values #3");
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("Смартфоны","Apple Iphone 12", 950));
//        products.add(new Product("Наушники","Samsung Galaxy Buds 2", 250));
//        return products;
//    }

    @RequestMapping(path = "/first_resource", produces = "text/plain")
    public String firstResource(){
        return "Test message";
    }
}
