package io.spring.controller;

import io.spring.dto.UserLogin;
import io.spring.dto.UserRegister;
import io.spring.entity.User;
import io.spring.service.UserService;
import io.spring.utils.CookieUtil;
import io.spring.utils.SessionUtil;
import io.spring.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CookieUtil cookieUtil;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    UserUtil userUtil;

    @GetMapping("/site/user/signin")
    public String userSignIn(Model model, @RequestParam("error")Optional<String> error){
        User session_user = (User) sessionUtil.getAttribute("user");
        if(session_user != null){
            return "redirect:/site/index";
        }else{
            Cookie cookie = cookieUtil.get("user");
            if(cookie != null){
                String email = cookie.getValue();
                if(!email.isEmpty()){
                    User user = userService.getByEmail(email).orElse(null);
                    if(user != null){
                        sessionUtil.setAttribute("user", user);
                        return "redirect:/site/index";
                    }
                }
            }
            model.addAttribute("login", new UserLogin());
            model.addAttribute("error", error.orElse(""));
            return "user_signin";
        }
    }

    @PostMapping("/site/user/signin")
    public String signIn(@ModelAttribute("login") UserLogin userLogin,
                         RedirectAttributes params){

            User user = userService.findByEmail(userLogin.getEmail(), userLogin.getPassword()).orElse(null);
            if(user != null){
                if(userLogin.isRemember()){
                    cookieUtil.create("user", userLogin.getEmail(), 1);
                }

                sessionUtil.setAttribute("user", user);

                return "redirect:/site/index";
            }else{
                params.addAttribute("error", "Email hoặc mật khẩu không đúng!");
                return "redirect:/site/user/signin";
            }
        }

    @GetMapping("/site/user/signup")
    public String userSignUp(Model model){
        model.addAttribute("register", new UserRegister());
        return "user_signup";
    }

    @PostMapping("/site/user/signup")
    public String signUp(@Validated @ModelAttribute("register")UserRegister userRegister,
                         BindingResult result){
        if(result.hasErrors()){
            return "user_signup";
        }else{
            userService.save(userUtil.getUser(userRegister));
            return "redirect:/site/user/signin";
        }
    }

    @GetMapping("/site/user/logout")
    public String logout(){
        sessionUtil.removeAttribute("user");
        cookieUtil.create("user", "",  0);
        return "redirect:/site/user/signin";
    }
}
