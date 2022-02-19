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
public class District {
    private String name;
    private int code;
    private String codename;
    private String division_type;
    private String short_codename;
    private List<Ward> wards;
}
