package com.brokersystems.setup.controllers;

import static com.brokersystems.server.utils.AppConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.User;
import com.brokersystems.setups.service.UserService;


@Controller
@RequestMapping(CLIENT_MANAGE_USER)
public class ManageUserController {

    public static final String SHOW = "show";
    public static final String DATATABLE = "datatable";
    private static final Logger logger = LoggerFactory
            .getLogger(ManageUserController.class);
    @Autowired
    private UserService userService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String entry() {
        return "users";
    }


    @RequestMapping(value = DATATABLE, method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<User> users(@DataTable DataTablesRequest pageable)
            throws IllegalAccessException {
    	logger.info("Entering findUsers...");
        return userService.findDatatables(pageable);
    }
    
    
    @RequestMapping(value = "save_or_update", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrUpdate(User user) 
      {
        logger.debug( "Entering save user( name=" + user.getName()
                + " , userName =" + user.getUsername() + ")");
        userService.saveOrUpdate(user);

    }
    
    @RequestMapping(value = SHOW, method = RequestMethod.GET)
    public String show(@RequestParam("id") Integer userId, ModelMap model) {

        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return  "user";
    }
}
