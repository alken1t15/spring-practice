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
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
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
    public Object firstResource() {
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
        if (optional.isPresent()) {
            return optional.get().getName();
        } else {
            return "Category ont found";
        }
    }

    @GetMapping(path = "/secondResource", produces = "application/json; charset=utf-8")
    public List<String> secondResource() {
        Sort sort = Sort.by(
                Sort.Order.asc("category.name"),
                Sort.Order.desc("price")
        );
        List<Product> products = productRepository.findAll(sort);
        return mapProductToString(products);
    }

    private static final int PAGE_SIZE = 2;

    @GetMapping(path = "/thirdResource", produces = "application/json; charset=utf-8")
    public List<String> thirdResource(@RequestParam(required = false) Integer page) {

        Sort sort = Sort.by(
                Sort.Order.desc("price")
        );

        if (page == null) {
            return mapProductToString(productRepository.findAll(sort));
        }
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, sort);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> products = productPage.getContent();
        return mapProductToString(products);
    }

    @GetMapping(path = "/forth_resource")
    public List<String> fourthResource() {
        //  List<Product> products = productRepository.findAllByPriceBefore(150_000);
        // List<Product> products = productRepository.findAllByPriceAfter(100_000);
        // List<Product> products = productRepository.findAllByPriceIsBetween(100_000,200_000);
        //   List<Product> products = productRepository.findAllByCategoryName("Смартфоны");
        //List<Product> products = productRepository.findAllByCategoryNameAndPriceBetween("Наушники",100_000,200_000);
        List<Product> products = productRepository.findAllByCategoryAndPrice("Наушники", 100_000, 200_000);
        return mapProductToString(products);
    }

    @GetMapping(path = "/image_resource", produces = "image/jpeg")
    public byte[] imageResource() throws IOException {
        String filePath = "/Users/ylix9g/Downloads/image.jpg";
        FileInputStream inputStream = new FileInputStream(filePath);
        return inputStream.readAllBytes();
    }

    @ExceptionHandler(IOException.class)
    public String exceptionHandler(IOException e) {
        return "File not found";
    }

    @GetMapping(path = "/create_new_entity")
    public void createNewEntity() {
        Category category = new Category();
        category.setName("Мебель");
        categoryRepository.save(category);
    }

    @GetMapping(path = "/update_entity")
    public void updateNewEntity(@RequestParam long categoryId, @RequestParam String categoryName) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        category.setName(categoryName);
        categoryRepository.save(category);
    }

    @GetMapping(path = "/delete_entity")
    public void deleteEntity(@RequestParam long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @GetMapping(path = "/increase_product_price")
    public void increaseProductPrice(@RequestParam int percent, @RequestParam long categoryId) {
        productRepository.updateProductsPriceByCategory(percent, categoryId);

    }

    private List<String> mapProductToString(List<Product> products) {
        return products
                .stream()
                .map(product -> product.getName() + " (" + product.getPrice() + ") ")
                .toList();
    }


}