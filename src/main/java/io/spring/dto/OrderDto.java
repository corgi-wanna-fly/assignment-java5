package io.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String name;
    private String phone;
    private int province;
    private int district;
    private int ward;
    private String address;
}
