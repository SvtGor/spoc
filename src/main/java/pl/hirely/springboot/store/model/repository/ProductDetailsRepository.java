package pl.hirely.springboot.store.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.hirely.springboot.store.model.domain.ProductDetails;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    @Query("SELECT pd FROM ProductDetails pd JOIN FETCH pd.product")
    List<ProductDetails> findAllWithDetails();

    @Query("SELECT pd FROM ProductDetails pd JOIN FETCH pd.product WHERE pd.price < :price")
    List<ProductDetails> findAllCheaperThan(BigDecimal price);
}
