package io.spring.controller;

import io.spring.domain.PageInfo;
import io.spring.domain.PageType;
import io.spring.entity.CartItem;
import io.spring.entity.User;
import io.spring.service.CartService;
import io.spring.service.ProductService;
import io.spring.service.UserService;
import io.spring.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ProductService productService;

    @GetMapping("/site/cart")
    public String cart(Model model,
                       @RequestParam("quantity") Optional<Integer> quantity,
                       @RequestParam("id") Optional<Integer> id) {

        Integer id_cart = id.orElse(0);
        if (id_cart != 0) {
            int quan = quantity.get();

            CartItem cartItem = cartService.findById(id_cart).get();

            cartItem.setQuantity(quan);

            cartService.save(cartItem);
        }

        User user = (User) sessionUtil.getAttribute("user");

        List<CartItem> cartItems = cartService.getCartItemByUser(user);

        model.addAttribute("cart", cartItems);

        model.addAttribute("count", cartItems.size());

        if (cartItems.size() == 0) {
            model.addAttribute("message", "Giỏ hàng trống!");
        }

        double amount = 0.0;

        for (CartItem i : cartItems) {
            double flag = i.getProduct().getPrice() * (100 - i.getProduct().getDiscount().getPercent()) / 100 * i.getQuantity();
            amount += flag;
        }

        model.addAttribute("amount", amount);

        PageInfo.routeSite(request, PageType.SITE_CART);

        sessionUtil.setAttribute("navbar",  "Giỏ hàng");

        return "/layout";
    }

    @PostMapping("/site/cart/update")
    public String update(RedirectAttributes params, @RequestParam("id")Optional<Integer> id) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        params.addAttribute("quantity", quantity);

        params.addAttribute("id", id.get());

        return "redirect:/site/cart";
    }

    @GetMapping("/site/cart/delete")
    public String delete(@RequestParam("id") Optional<Integer> id){
        int id_cart = id.orElse(-1);
        if(cartService.existsById(id_cart)){
            cartService.deleteById(id_cart);
        }
        return "redirect:/site/cart";
    }

    @GetMapping("/site/cart/add")
    public String add(@RequestParam("id") Optional<Integer> id){
        int id_product = id.orElse(-1);
        if(productService.existsById(id_product)){
            User user = (User) sessionUtil.getAttribute("user");
            List<CartItem> cartItems = cartService.getCartItemByUser(user);
           boolean check = false;
           for(CartItem item: cartItems){
               if(item.getProduct().getId() == id_product){
                   item.setQuantity(item.getQuantity() + 1);
                   check = true;
                   cartService.save(item);
                   break;
               }
           }
           if(!check){
               CartItem cartItem = new CartItem();
               cartItem.setUser(user);
               cartItem.setProduct(productService.findById(id_product).get());
               cartItem.setQuantity(1);
               cartService.save(cartItem);
           }
        }
        return "redirect:/site/cart";
    }
}
