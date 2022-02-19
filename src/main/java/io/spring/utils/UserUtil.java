package io.spring.utils;

import io.spring.dto.UserRegister;
import io.spring.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {
    public User getUser(UserRegister userRegister){
        User user = new User();
        user.setFullname(userRegister.getFullname());
        user.setEmail(userRegister.getEmail());
        user.setPassword(userRegister.getPassword());
        user.setGender(userRegister.isGender());
        return user;
    }
}
