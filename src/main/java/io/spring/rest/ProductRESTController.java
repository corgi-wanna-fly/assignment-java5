package io.spring.rest;

import io.spring.entity.Product;
import io.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ProductRESTController {
    ProductService productService;

    @Autowired
    public ProductRESTController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @GetMapping("/product")
    public String getById(@RequestParam("index")Optional<Integer> id,
                          Model model){
        Integer id_product = id.orElse(5);
        Product product = productService.findById(id_product).get();
        model.addAttribute("product", product);
        model.addAttribute("index", id_product);
        return "site/index";
    }
}
