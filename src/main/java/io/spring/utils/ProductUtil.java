package io.spring.utils;

import io.spring.dto.ProductDto;
import io.spring.entity.Product;
import io.spring.service.BrandService;
import io.spring.service.CategoryService;
import io.spring.service.DiscountService;
import io.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductUtil {
    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DiscountService discountService;

    public ProductDto getFromProduct(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand().getId().toString());
        productDto.setCategory(product.getCategory().getId().toString());
        productDto.setDiscount(product.getDiscount().getId().toString());
        productDto.setImage(product.getImage());
        return productDto;
    }

    public Product getFromDto(ProductDto productDto){
        Product product = new Product();

        if(productDto.getId() != 0){
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setBrand(brandService.getById(Integer.parseInt(productDto.getBrand())));
        product.setCategory(categoryService.getById(Integer.parseInt(productDto.getCategory())));
        product.setDiscount(discountService.getById(Integer.parseInt(productDto.getDiscount())));

        return product;
    }
}
