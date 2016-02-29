package com.brokersystems.nav.controllers;


import org.springframework.security.core.annotation.AuthenticationPrincipal ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.brokersystems.setups.model.User;

import java.io.IOException;


@Controller
public class AuthController {

    private final static String DEFAULT = "index";

    @RequestMapping("login")
    public String login(@RequestParam(required = false) Boolean error, ModelMap model) throws IOException {
        model.addAttribute("error", error);
        return "login";
    }

    @RequestMapping({"index.html", "index", ""})
    public String index(@AuthenticationPrincipal User user) throws IOException {

        if (user != null) {
            return "redirect:protected/home";
        }
        return DEFAULT;
    }


}
