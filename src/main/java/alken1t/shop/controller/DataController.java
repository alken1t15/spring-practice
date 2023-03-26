package alken1t.shop.controller;

import alken1t.shop.entity.Category;
import alken1t.shop.entity.Product;
import alken1t.shop.repository.CategoryRepository;
import alken1t.shop.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/data_controller")
public class DataController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/first_resource")
    public Object firstResource(){
//        TypedQuery<Product> query = entityManager.createQuery("select p from Product p", Product.class);
//        System.out.println(query.getResultList());
//        return query.getResultList();
//       List<Category> categories = categoryRepository.findAll();
//        return categories;
//      Optional<Category> category = categoryRepository.findById(1L);
//      Category categories = category.get();
//        Category category = categoryRepository.findById(1L).orElseThrow();
//        return category;
        Optional<Category> optional = categoryRepository.findById(5L);
        if(optional.isPresent()){
            return optional.get().getName();
        }
        else {
            return "Category ont found";
        }
    }

    @GetMapping(path = "/secondResource",produces = "application/json; charset=utf-8")
    public List<String> secondResource(){
        Sort sort = Sort.by(
                Sort.Order.asc("category.name"),
                Sort.Order.desc("price")
        );
        List<Product> products = productRepository.findAll(sort);
       return products
               .stream()
               .map(product -> product.getName() + " ("+ product.getPrice() + ") ")
               .toList();
    }

    @GetMapping(path = "/thirdResource",produces = "application/json; charset=utf-8")
    public List<String> thirdResource(@RequestParam(required = false) Integer page){
        Pageable pageable = PageRequest.of(page-1,3);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        return products
                .stream()
                .map(product -> product.getName() + " ("+ product.getPrice() + ") ")
                .toList();
    }


}
