package com.hotel.bradhotel.view;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Controller
public class view {
    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/orderhistory")
    public String orderhistory(){
        return "orderhistory";
    }


}
