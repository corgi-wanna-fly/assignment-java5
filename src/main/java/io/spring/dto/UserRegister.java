package io.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegister {
    @NotEmpty(message = "Không để trống họ tên!")
    private String fullname;
    @NotEmpty(message = "Không để trống email!")
    @Email(message = "Không đúng định dạng email!")
    private String email;
    @NotEmpty(message = "Không để trống mật khẩu!")
    private String password;
    @NotEmpty(message = "Không để trống mật khẩu xác nhận!")
    private String confirm;
    private boolean gender;
}
