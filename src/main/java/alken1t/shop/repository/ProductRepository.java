package alken1t.shop.repository;

import alken1t.shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Category, Long> {
}