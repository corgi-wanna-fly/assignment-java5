package io.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private int id;
    @NotEmpty(message = "Không để trống tên sản phẩm")
    private String name;
    @Min(message = "Giá sản phẩm > 0", value = 1)
    private double price;
    @NotEmpty(message = "Không để trống mô tả")
    private String description;
    @NotEmpty(message = "Vui lòng chọn thương hiệu")
    private String brand;
    @NotEmpty(message = "Vui lòng chọn loại sản phẩm")
    private String category;
    @NotEmpty(message = "Vui lòng chọn chương trình giảm giá")
    private String discount;
    @NotEmpty(message = "Không để trống đường dẫn ảnh")
    private String image;
}
