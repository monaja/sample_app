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
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.Landlord;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.service.SetupsService;
import com.brokersystems.setups.service.LandlordService;

@Controller
@RequestMapping({"/protected/setups"})
public class SetupsController {
	
	@Autowired
	private SetupsService setupsService;
	
	
	
	@RequestMapping(value="currency",method = RequestMethod.GET)
	public String currencyHome(Model model){
		return "currency";
	}
	
	@RequestMapping(value="countries",method = RequestMethod.GET)
	public String countryHome(Model model){
		return "countries";
	}
	
	@RequestMapping(value="paymentmodes",method = RequestMethod.GET)
	public String paymentModesHome(Model model){
		return "paymodesList";
	}
	
	@RequestMapping(value={"currencies"}, method={RequestMethod.GET})
	@ResponseBody
	public DataTablesResult<Currencies> getCurrencies(@DataTable DataTablesRequest pageable)
	    throws IllegalAccessException
	{
	    return setupsService.findAllCurrencies(pageable);
	}
	
	 @RequestMapping(value={"createCurrency"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdateCurrency(Currencies currency)
	    throws IllegalAccessException
	  {
	    setupsService.defineCurrency(currency);
	  }
	 
	 @RequestMapping(value={"deleteCurrency/{currCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteCurrency(@PathVariable Long currCode)
	  {
	    setupsService.deleteCurrency(currCode);
	  }
	  
	 @RequestMapping(value={"allCountries"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<Country> getCountries(@DataTable DataTablesRequest pageable)
		    throws IllegalAccessException
		{
		    return setupsService.findAllCountries(pageable);
		}
	 
	 @RequestMapping(value={"createCountry"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdateCountry(Country country)
	    throws IllegalAccessException
	  {
	    setupsService.defineCountry(country);
	  }
	 
	 @RequestMapping(value={"deleteCountry/{couCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteCountry(@PathVariable Long couCode)
	  {
	    setupsService.deleteCountry(couCode);
	  }
	 
	 @RequestMapping(value={"allCounties/{couCode}"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<County> getCounties(@DataTable DataTablesRequest pageable,@PathVariable Long couCode)
		    throws IllegalAccessException
		{
		    return setupsService.findCountiesByCountry(couCode, pageable);
		}
	 
	 @RequestMapping(value={"createCounty"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdateCounty(County county)
	    throws IllegalAccessException
	  {
	    setupsService.defineCounty(county);
	  }
	 
	 @RequestMapping(value={"deleteCounty/{countyCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteCounty(@PathVariable Long countyCode)
	  {
	    setupsService.deleteCounty(countyCode);
	  }
	 
	 @RequestMapping(value={"allTowns/{countyCode}"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<Town> getTowns(@DataTable DataTablesRequest pageable,@PathVariable Long countyCode)
		    throws IllegalAccessException
		{
		    return setupsService.findTownsByCounty(countyCode, pageable);
		}
	 
	 @RequestMapping(value={"createTown"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdateTown(Town town)
	    throws IllegalAccessException
	  {
	    setupsService.defineTown(town);
	  }
	 
	 @RequestMapping(value={"deleteTown/{townCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteTown(@PathVariable Long townCode)
	  {
	    setupsService.deleteTown(townCode);
	  }
	 
	 @RequestMapping(value={"allpaymentModes"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<PaymentModes> getPayemtModes(@DataTable DataTablesRequest pageable)
		    throws IllegalAccessException
		{
		    return setupsService.findAllPaymentModes(pageable);
		}
	 
	 @RequestMapping(value={"createPaymentModes"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdatePaymentModes(PaymentModes mode) throws BadRequestException
	  {
	    setupsService.definePaymentMode(mode);
	  }

}
