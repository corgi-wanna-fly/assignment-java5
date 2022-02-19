package io.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.spring.domain.PageInfo;
import io.spring.domain.PageType;
import io.spring.dto.Dashboard;
import io.spring.entity.Admin;
import io.spring.entity.Order;
import io.spring.entity.Product;
import io.spring.service.AdminService;
import io.spring.service.OrderService;
import io.spring.service.ProductService;
import io.spring.service.UserService;
import io.spring.utils.OrderUtil;
import io.spring.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    AdminService adminService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    OrderUtil orderUtil;

    @ModelAttribute("status")
    public List<String> status(){
        return List.of("Chờ duyệt", "Đã duyệt", "Giao thành công", "Đã huỷ");
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model){
        model.addAttribute("dashboard", new Dashboard(userService.findAll().size(),
                productService.findAll().size(), orderService.findAll().size()));
        PageInfo.routeSite(request, PageType.ADMIN_INDEX);
        return "admin-layout";
    }

    @GetMapping("/admin/product")
    public String product(Model model,
                          @RequestParam("page")Optional<Integer> page){
        int no_page = page.orElse(1);

        Pageable pageable = PageRequest.of(no_page - 1, 8);

        Page<Product> productPage = productService.findAll(pageable);

        model.addAttribute("productPage", productPage);

        PageInfo.routeSite(request, PageType.ADMIN_PRODUCT);

        return "admin-layout";
    }

    @GetMapping("/admin/order")
    public String order(Model model,
                        @RequestParam("page")Optional<Integer> page){

        int no_page = page.orElse(1);

        Pageable pageable = PageRequest.of(no_page - 1, 8);

        Page<Order> orderPage = orderService.findAll(pageable);

        model.addAttribute("orderPage", orderPage);

        PageInfo.routeSite(request, PageType.ADMIN_ORDER);

        return "admin-layout";
    }

    @GetMapping("/admin/signin")
    public String signin(
                         Model model){

        model.addAttribute("admin", new Admin());
        return "/admin-signin";
    }

    @PostMapping("/admin/signin")
    public String postSignin(@ModelAttribute("admin") Admin admin,
            Model model){

        Admin valid = adminService.findById(admin.getId()).orElse(null);

        if(valid == null){
            model.addAttribute("error", "Đăng nhập thất bại!");

            return "/admin-signin";
        }else{
            if(valid.getPassword().equals(admin.getPassword())){

                sessionUtil.setAttribute("admin", admin);
                return "redirect:/admin/dashboard";
            }else{
                model.addAttribute("error", "Đăng nhập thất bại!");

                return "/admin-signin";
            }

        }
    }

    @GetMapping("/admin/logout")
    public String logout(){
        sessionUtil.removeAttribute("admin");
        return "redirect:/admin/signin";
    }

    @GetMapping("/admin/statistical")
    public String statistical(Model model) throws JsonProcessingException {
        Map<String, Integer> map = orderUtil.getInfo();

        String json = new ObjectMapper().writeValueAsString(map);

        model.addAttribute("json", json);

        PageInfo.routeSite(request, PageType.ADMIN_STATISTICAL);

        return "admin-layout";
    }
}
