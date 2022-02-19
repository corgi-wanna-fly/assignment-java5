package io.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Province {
    private String name;
    private int code;
    private String codename;
    private String division_type;
    private int phone_code;
    private List<District> districts;
}
