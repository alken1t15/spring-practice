package alken1t.shop.repository;

import alken1t.shop.entity.Category;
import alken1t.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBefore(int value);

    List<Product> findAllByPriceAfter(int value);

    List<Product> findAllByPriceIsBetween(int from, int to);

    List<Product> findAllByCategoryName(String categoryName);

    List<Product> findAllByCategoryNameAndPriceBetween(String categoryName,int from, int to);

    @Query("select p from  Product  p where p.category.name = ?1 and p.price between ?2 and  ?3 ")
    List<Product> findAllByCategoryAndPrice(String categoryName,int from, int to);
}