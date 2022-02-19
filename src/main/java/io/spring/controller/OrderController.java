package io.spring.controller;

import io.spring.domain.PageInfo;
import io.spring.domain.PageType;
import io.spring.dto.OrderDto;
import io.spring.entity.CartItem;
import io.spring.entity.Order;
import io.spring.entity.OrderDetail;
import io.spring.entity.User;
import io.spring.model.District;
import io.spring.model.Province;
import io.spring.model.Ward;
import io.spring.service.CartService;
import io.spring.service.OrderDetailService;
import io.spring.service.OrderService;
import io.spring.utils.JsonUtil;
import io.spring.utils.MailUtil;
import io.spring.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    MailUtil mailUtil;


    @GetMapping("/site/order")
    public String order(Model model){
        User user = (User)sessionUtil.getAttribute("user");

        List<Order> orders  = orderService.getOrderByUser(user.getId());

        if(orders.size() == 0){
            model.addAttribute("message", "Danh sách đơn hàng trống!");
        }else{
            model.addAttribute("orders", orders);
        }

        PageInfo.routeSite(request, PageType.SITE_ORDER);

        sessionUtil.setAttribute("navbar", "Đơn hàng");

        return "/layout";
    }

    @GetMapping("/site/checkout")
    public String checkout(Model model,
                           @RequestParam("province")Optional<Integer> pid,
                           @RequestParam("district")Optional<Integer> did
                   ) throws IOException {

        User user = (User)sessionUtil.getAttribute("user");

        List<CartItem> cartItems = cartService.getCartItemByUser(user);

        double amount = 0.0;

        for (CartItem i : cartItems) {
            double flag = i.getProduct().getPrice() * (100 - i.getProduct().getDiscount().getPercent()) / 100 * i.getQuantity();
            amount += flag;
        }

        model.addAttribute("cart", cartItems);

        model.addAttribute("amount", amount);

        List<Province> provinces = jsonUtil.getProvince();

        model.addAttribute("province", provinces);

        OrderDto orderDto = new OrderDto();
        orderDto.setName(user.getFullname());
        orderDto.setPhone(request.getParameter("phone"));
        orderDto.setAddress(request.getParameter("address"));
        int id_province = pid.orElse(0);

        if(id_province != 0){
            orderDto.setProvince(id_province);
            List<District> districts = new ArrayList<>();
            for(Province province: provinces){
                if(province.getCode() == id_province){
                    districts  = province.getDistricts();
                    model.addAttribute("district", districts);
                    break;
                }
            }
            int id_district = did.orElse(0);
            if(id_district != 0){
                orderDto.setDistrict(id_district);
                for(District district: districts){
                    if(district.getCode() == id_district){
                        List<Ward> wards = district.getWards();
                        model.addAttribute("ward", wards);
                        break;
                    }
                }
            }
        }

        model.addAttribute("order", orderDto);

        PageInfo.routeSite(request, PageType.SITE_CHECKOUT);

        return "/layout";
    }

    @PostMapping("/site/checkout")
    public String postCheckout() throws IOException, MessagingException {
        OrderDto orderDto = new OrderDto();

        orderDto.setPhone(request.getParameter("phone"));
        orderDto.setAddress(request.getParameter("address"));
        String province = request.getParameter("province");
        orderDto.setProvince(Integer.parseInt(province.substring(province.lastIndexOf("=")+1)));
        String district = request.getParameter("district");
        orderDto.setDistrict(Integer.parseInt(district.substring(district.lastIndexOf("=")+1)));
        orderDto.setWard(Integer.parseInt(request.getParameter("ward")));

        User user = (User) sessionUtil.getAttribute("user");

        Order order = new Order();
        order.setAddress(orderDto.getAddress() + ", " + jsonUtil.getAddress(orderDto.getProvince(),
                orderDto.getDistrict(), orderDto.getWard()));
        
        List<CartItem> cartItems = cartService.getCartItemByUser(user);

        if(cartItems.size() == 0){
            return "redirect:/site/cart";
        }else{
            double amount = 0.0;

            for (CartItem i : cartItems) {
                double flag = i.getProduct().getPrice() * (100 - i.getProduct().getDiscount().getPercent()) / 100 * i.getQuantity();
                amount += flag;
            }

            order.setAmount(amount);
            order.setCreated(LocalDate.now());
            order.setPhone(orderDto.getPhone());
            order.setUser(user);

            order = orderService.save(order);

           boolean payment_method = Boolean.parseBoolean(request.getParameter("paymentMethod"));

           if(!payment_method){
               for(CartItem item: cartItems){
                   OrderDetail orderDetail = new OrderDetail();
                   orderDetail.setOrder(order);
                   orderDetail.setQuantity(item.getQuantity());
                   orderDetail.setProduct(item.getProduct());
                   orderDetail.setPrice(item.getProduct().getPrice() * (100 - item.getProduct().getDiscount().getPercent()) / 100);
                   orderDetailService.save(orderDetail);

                   cartService.deleteById(item.getId());
               }

               mailUtil.sendmail(user.getEmail(), order.getId());

               return "redirect:/site/order";
           }else{
                sessionUtil.setAttribute("orderId", order.getId());

                return "redirect:/site/pay";
           }


        }
    }

    @GetMapping("/site/order/cancel")
    public String cancelSite(@RequestParam("id")Optional<Integer> id){
        int id_order = id.orElse(0);
        if(orderService.existsById(id_order)){
            Order order = orderService.getById(id_order);
            order.setActive(false);
            order.setStatus("Đã huỷ");
            orderService.save(order);
        }
        return "redirect:/site/order";
    }
    @GetMapping("/admin/order/cancel")
    public String cancelAdmin(@RequestParam("id")Optional<Integer> id){
        int id_order = id.orElse(0);
        if(orderService.existsById(id_order)){
            Order order = orderService.getById(id_order);
            order.setActive(false);
            order.setStatus("Đã huỷ");
            orderService.save(order);
        }

        return "redirect:/admin/order";
    }

    @GetMapping("/site/order/detail")
    public String detail(@RequestParam("id") Optional<Integer> id,
                         Model model){
        int id_order = id.orElse(0);
        if(orderService.existsById(id_order)){
            Order order = orderService.getById(id_order);
            List<OrderDetail> orderDetails = orderDetailService.getOrderDetailsByOrder(order.getId());
            model.addAttribute("order", orderDetails);
        }

        PageInfo.routeSite(request, PageType.SITE_ORDER_DETAIL);
        return "/layout";
    }

    @PostMapping("/admin/order/update")
    public String update(@RequestParam("id")Optional<Integer> id){
        int id_order = id.orElse(-1);

        String status = request.getParameter("status");

        if(orderService.existsById(id_order)){
            Order order = orderService.getById(id_order);

            order.setStatus(status);

            orderService.save(order);
        }
        return "redirect:/admin/order";
    }
}
