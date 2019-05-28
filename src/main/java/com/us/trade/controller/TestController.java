package com.us.trade.controller;

import com.us.trade.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/notice")
    public Object create() throws Exception {
        return "OK";
    }
}
