package io.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ward {
    private String name;
    private int code;
    private String codename;
    private String division_type;
    private String short_codename;
}
