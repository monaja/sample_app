package com.brokersystems.nav.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping("/protected/home")
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String entry() {
        
        return "home";
    }
}
