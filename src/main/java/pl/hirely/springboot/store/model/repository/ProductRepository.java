package pl.hirely.springboot.store.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.store.model.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM Product p JOIN FETCH p.productDetails")
//    List<Product> findAllWithDetails();

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findAllWithCategory(String category);

    List<Product> findAllByCategory(String category);
}
