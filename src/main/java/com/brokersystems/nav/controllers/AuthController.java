package com.brokersystems.nav.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.User;
import com.brokersystems.setups.service.OrganizationService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AuthController {

	
    private final static String DEFAULT = "index";
    
    @Autowired
    private OrganizationService orgService;

    @RequestMapping("login")
    public String login(@RequestParam(required = false) Boolean error, ModelMap model) throws IOException {
        model.addAttribute("error", error);
        return "login";
    }
    
    @RequestMapping(value = "/logo")
    public void getImage(HttpServletResponse response) throws IOException,ServletException {
  	 Organization organization = orgService.getOrganizationDetails();
  	 response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
  	 response.getOutputStream().write(organization.getOrgLogo());
  	 response.getOutputStream().close();
    }

    @RequestMapping({"index.html", "index", ""})
    public String index(@AuthenticationPrincipal User user) throws IOException {

        if (user != null) {
            return "redirect:protected/home";
        }
        return DEFAULT;
    }


}
