package pl.hirely.springboot.store.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hirely.springboot.store.model.dto.ProductDetailsDto;
import pl.hirely.springboot.store.model.dto.ProductDto;
import pl.hirely.springboot.store.model.repository.ProductDetailsRepository;
import pl.hirely.springboot.store.model.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductRepository productRepository;

    public StoreController(ProductDetailsRepository productDetailsRepository, ProductRepository productRepository) {
        this.productDetailsRepository = productDetailsRepository;
        this.productRepository = productRepository;
    }

    @GetMapping( "/product")
    public List<ProductDto> allProducts(@RequestParam(required = false, defaultValue = "0") Integer page,
                                        @RequestParam(required = false, defaultValue = "10") Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return productRepository.findAll(pageable).stream()
                .map(ProductDto::new)
                .toList();
    }

    @GetMapping("/product-details")
//    @Transactional // naprawia LazyInitializationException
    public List<ProductDetailsDto> allProductDetails() {
        // demonstracja LAZY
//        return productDetailsRepository.findAll().stream()
//                .map(ProductDetailsDto::new)
//                .toList();

//         naprawienie N+1 i LazyInitializationException
        return productDetailsRepository.findAllWithDetails().stream()
                .map(ProductDetailsDto::new)
                .toList();
    }

    @GetMapping("/product/category/{category}")
    public List<ProductDto> getProductsByCategory(@PathVariable("category") String category) {
        return productRepository.findAllByCategory(category).stream()
                .map(ProductDto::new)
                .toList();
    }

    @GetMapping("/product/price")
    public List<ProductDetailsDto> getProductsCheaperThan(@RequestParam("maxPrice") BigDecimal maxPrice) {
        return productDetailsRepository.findAllCheaperThan(maxPrice).stream()
                .map(ProductDetailsDto::new)
                .toList();
    }
}
