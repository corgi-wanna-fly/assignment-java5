package io.spring.controller;

import io.spring.config.PaypalConfig;
import io.spring.domain.PageInfo;
import io.spring.domain.PageType;
import io.spring.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    SessionUtil sessionUtil;

    @GetMapping("/site/index")
    public String index(){
        sessionUtil.setAttribute("navbar", "Trang chủ");
        PageInfo.routeSite(request, PageType.SITE_INDEX);
        return "layout";
    }
}
