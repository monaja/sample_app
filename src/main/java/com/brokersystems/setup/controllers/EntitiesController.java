package com.brokersystems.setup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.BrokerEntity;
import com.brokersystems.setups.service.EntityService;

@Controller
@RequestMapping(value = "/protected/entities")
public class EntitiesController {
	
	@Autowired
	private EntityService entityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String entitiesHome(Model model){
		return "entities";
	}
	
	@ModelAttribute
	public BrokerEntity entityForm(){
		BrokerEntity brokerEntity = new BrokerEntity();
		return brokerEntity;
	}
	
	 @RequestMapping(value = "entities", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<BrokerEntity> entities(@DataTable DataTablesRequest pageable)
	            throws IllegalAccessException {
	        return entityService.entities(pageable);
	    }
	 
	 @RequestMapping(value="entitydetails",method = RequestMethod.GET)
		public String entityDetails(Model model){
			return "createentity";
		}
	 
	 @RequestMapping(value="entitydetails/createEntity",method = RequestMethod.POST)
	   public String saveEntity(BrokerEntity entity,Model model){
		   return "redirect:/protected/entities/";
	   }

}
