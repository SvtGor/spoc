package pl.hirely.springboot.store.model.dto;

import pl.hirely.springboot.store.model.domain.Product;

public class ProductDto {
    private String name;
    private String category;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.category = product.getCategory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
