package com.brokersystems.setup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.RateTypes;
import com.brokersystems.setups.service.SetupsService;

@Controller
@RequestMapping({"/protected/rental/setups"})
public class RentalSetupsController {
	
	@Autowired
	private SetupsService setupsService;

	@RequestMapping(value="ratetypes",method = RequestMethod.GET)
	public String rateTypeHome(Model model){
		return "ratetypes";
	}
	
	@RequestMapping(value="unittypes",method = RequestMethod.GET)
	public String unitTypeHome(Model model){
		return "unittypes";
	}
	
	
	@RequestMapping(value={"allratetypes"}, method={RequestMethod.GET})
	@ResponseBody
	public DataTablesResult<RateTypes> getRateTypes(@DataTable DataTablesRequest pageable)
	    throws IllegalAccessException
	{
	    return setupsService.findAllRateTypes(pageable);
	}
	
	 @RequestMapping(value={"createRateType"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdateRateType(RateTypes rateType)
	    throws IllegalAccessException
	  {
	    setupsService.defineRateType(rateType);
	  }
	 
	 @RequestMapping(value={"deleteRateType/{rateTypeCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteRateType(@PathVariable Long rateTypeCode)
	  {
	    setupsService.deleteRateType(rateTypeCode);
	  }
	
}
