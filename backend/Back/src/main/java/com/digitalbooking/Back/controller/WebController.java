package com.digitalbooking.Back.controller;

import com.digitalbooking.Back.services.serviceInterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class WebController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
