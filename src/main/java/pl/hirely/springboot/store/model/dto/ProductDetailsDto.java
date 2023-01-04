package pl.hirely.springboot.store.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.hirely.springboot.store.model.domain.ProductDetails;

import java.math.BigDecimal;

public class ProductDetailsDto {
    private final String type;
    private final String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING) // JSON wyświetla cenę z dwoma miejscami po przecinku
    private final BigDecimal price;
    private final String name;
    private final String category;

    public ProductDetailsDto(ProductDetails pd) {
        this.type = pd.getType();
        this.description = pd.getDescription();
        this.price = pd.getPrice();
        this.category = pd.getProduct().getCategory();
        this.name = pd.getProduct().getName();
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}
