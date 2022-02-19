package io.spring.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import io.spring.config.PaypalPaymentIntent;
import io.spring.config.PaypalPaymentMethod;
import io.spring.entity.CartItem;
import io.spring.entity.Order;
import io.spring.entity.OrderDetail;
import io.spring.entity.User;
import io.spring.service.CartService;
import io.spring.service.OrderDetailService;
import io.spring.service.OrderService;
import io.spring.service.PaypalService;
import io.spring.utils.MailUtil;
import io.spring.utils.PaypalUtil;
import io.spring.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class PaypalController {
    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    MailUtil mailUtil;

    @GetMapping("/site/pay")
    public String pay(HttpServletRequest request
                      ) {
        String successUrl = PaypalUtil.getBaseUrl(request) + "/" + URL_PAYPAL_SUCCESS;
        String cancelUrl = PaypalUtil.getBaseUrl(request) + "/" + URL_PAYPAL_CANCEL;

        Order order = orderService.getById(sessionUtil.getAttribute("orderId"));

        try {
            Payment payment = paypalService.createPayment(
                   order.getAmount(),
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "Thanh toán qua Paypal",
                    cancelUrl,
                    successUrl
            );
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/site/index";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancel() {
        Integer id = sessionUtil.getAttribute("orderId");
        orderService.deleteById(id);
        sessionUtil.removeAttribute("orderId");
        return "redirect:/site/cart";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String success(@RequestParam("paymentId") String paymentId,
                          @RequestParam("PayerID") String payerId
                          ) {
        Integer id = sessionUtil.getAttribute("orderId");
        try {

            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                User user = (User) sessionUtil.getAttribute("user");

                Order order = orderService.getById(id);

                List<CartItem> cartItems = cartService.getCartItemByUser(user);

                for(CartItem item: cartItems){
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setQuantity(item.getQuantity());
                    orderDetail.setProduct(item.getProduct());
                    orderDetail.setPrice(item.getProduct().getPrice() * (100 - item.getProduct().getDiscount().getPercent()) / 100);
                    orderDetailService.save(orderDetail);

                    cartService.deleteById(item.getId());
                }

                order.setPayment(true);

                orderService.save(order);

                sessionUtil.removeAttribute("orderId");

                mailUtil.sendmail(user.getEmail(), order.getId());
                return "redirect:/site/order";
            }
        } catch (PayPalRESTException | MessagingException | IOException e) {
            orderService.deleteById(id);
            sessionUtil.removeAttribute("orderId");
            e.printStackTrace();
        }

        return "redirect:/site/index";
    }
}
