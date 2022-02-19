package io.spring.controller;

import io.spring.domain.PageInfo;
import io.spring.domain.PageType;
import io.spring.dto.ProductDto;
import io.spring.dto.ProductInfo;
import io.spring.entity.Brand;
import io.spring.entity.Category;
import io.spring.entity.Discount;
import io.spring.entity.Product;
import io.spring.service.BrandService;
import io.spring.service.CategoryService;
import io.spring.service.DiscountService;
import io.spring.service.ProductService;
import io.spring.utils.ProductUtil;
import io.spring.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    ProductService service;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    @Autowired
    DiscountService discountService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    ProductUtil productUtil;

    @GetMapping("/site/products")
    public String getAll(@RequestParam("page") Optional<Integer> no,
                         @RequestParam("id") Optional<Integer> id,
                         Model model) {

        PageInfo.routeSite(request, PageType.SITE_PRODUCT);

        //get product by Pageable
        int page_no = no.orElse(1);

        int id_category = id.orElse(0);

        Pageable pageable = PageRequest.of(page_no - 1, 8);

        if (id_category == 0) {
            Page<Product> productPage = service.findAll(pageable);

            model.addAttribute("products", productPage.getContent());

            model.addAttribute("pages", productPage.getTotalPages());

            model.addAttribute("current", page_no);


        } else {

            List<Product> products = service.getByCategory(id_category, pageable);

            model.addAttribute("products", products);

            model.addAttribute("current", page_no);

            int count = service.getByCategory(id_category).size() % 8 == 0 ? service.getByCategory(id_category).size() / 8 : service.getByCategory(id_category).size() / 8 + 1;

            model.addAttribute("pages", count);
        }

        model.addAttribute("id_category", id_category);

        //get list category
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);

        sessionUtil.setAttribute("navbar", "Sản phẩm");
        return "layout";
    }

    @GetMapping("/site/product/search")
    public String search(@RequestParam("page") Optional<Integer> no,
                         Model model) {

        String tag_search = request.getParameter("keyword");

        if(tag_search.isEmpty()){
            return "redirect:/products";
        }else{
            PageInfo.routeSite(request, PageType.SITE_SEARCH);

            //get product by Pageable
            int page_no = no.orElse(1);

            Pageable pageable = PageRequest.of(page_no - 1, 8);

            List<Product> products = service.getByName("%" + tag_search + "%", pageable);

            if(products.size() == 0){
                model.addAttribute("message", "Không tìm thấy sản phẩm!");
            }else{
                model.addAttribute("products", products);

                model.addAttribute("current", page_no);

                int len = service.getByName("%" + tag_search + "%").size();

                int count = len % 8 == 0 ? len / 8 : (len / 8) + 1;

                model.addAttribute("pages", count);
            }
            model.addAttribute("tag", tag_search);

            sessionUtil.setAttribute("navbar", "Sản phẩm");

            return "layout";
        }
    }

    @GetMapping("/admin/product/delete")
    public String delete(@RequestParam("id") Optional<Integer> id) {
        int id_product = id.orElse(0);
        if (service.existsById(id_product)) {
            Product product = service.findById(id_product).get();
            product.setActive(false);
            service.save(product);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/restore")
    public String restore(@RequestParam("id") Optional<Integer> id) {
        int id_product = id.orElse(0);
        if (service.existsById(id_product)) {
            Product product = service.findById(id_product).get();
            product.setActive(true);
            service.save(product);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/add")
    public String add(@ModelAttribute("product") ProductDto productDto, Model model
    ) {
        model.addAttribute("product", new ProductDto());
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Discount> discounts = discountService.findAll();
        model.addAttribute("info", new ProductInfo(brands, discounts, categories));
        PageInfo.routeSite(request, PageType.ADMIN_PRODUCT_FORM);
        return "admin-layout";
    }

    @PostMapping("/admin/product/add")
    public String postAdd(@Validated @ModelAttribute("product") ProductDto productDto,
                          BindingResult result,
                          Model model) {
        if(result.hasErrors()){
            List<Brand> brands = brandService.findAll();
            List<Category> categories = categoryService.findAll();
            List<Discount> discounts = discountService.findAll();
            model.addAttribute("info", new ProductInfo(brands, discounts, categories));
            PageInfo.routeSite(request, PageType.ADMIN_PRODUCT_FORM);
            return "admin-layout";
        }else{
            Product product = productUtil.getFromDto(productDto);

            service.save(product);

            return "redirect:/admin/product";
        }
    }

    @GetMapping("/admin/product/update")
    public String update(@RequestParam("id") Optional<Integer> id,
                         Model model) {
        int id_product = id.orElse(0);
        if (service.existsById(id_product)) {
            Product product = service.findById(id_product).get();
            ProductDto productDto = productUtil.getFromProduct(product);
            model.addAttribute("product", productDto);
        } else {
            model.addAttribute("product", new ProductDto());

        }
        List<Brand> brands = brandService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Discount> discounts = discountService.findAll();
        model.addAttribute("info", new ProductInfo(brands, discounts, categories));
        PageInfo.routeSite(request, PageType.ADMIN_PRODUCT_FORM);
        return "admin-layout";
    }
}
