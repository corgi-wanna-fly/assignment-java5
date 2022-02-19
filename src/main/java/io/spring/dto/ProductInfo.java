package io.spring.dto;

import io.spring.entity.Brand;
import io.spring.entity.Category;
import io.spring.entity.Discount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductInfo {
    private List<Brand> brands;
    private List<Discount> discounts;
    private List<Category> categories;
}
