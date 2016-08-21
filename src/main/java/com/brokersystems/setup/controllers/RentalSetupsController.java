package com.brokersystems.setup.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.server.utils.FileUploadValidator;
import com.brokersystems.server.validator.RentalStructValidator;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.RateTypes;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.UnitTypes;
import com.brokersystems.setups.service.SetupsService;

@Controller
@RequestMapping({"/protected/rental/setups"})
public class RentalSetupsController {
	
	@Autowired
	private SetupsService setupsService;
	
	  @Autowired
	  RentalStructValidator validator;
	  
	  @InitBinder({"rentalStructure"})
	  protected void initBinder(WebDataBinder binder)
	  {
	    binder.setValidator(this.validator);   
	  } 
	
	@ModelAttribute
	  public RentalStructure createRentalForm()
	  {
	     return new RentalStructure();
	  }

	@RequestMapping(value="ratetypes",method = RequestMethod.GET)
	public String rateTypeHome(Model model){
		return "ratetypes";
	}
	
	@RequestMapping(value="unittypes",method = RequestMethod.GET)
	public String unitTypeHome(Model model){
		return "unittypes";
	}
	
	
	@RequestMapping(value="rentalstruct",method = RequestMethod.GET)
	public String rentalStructuresHome(Model model){
		return "rentalstruct";
	}
	
	@RequestMapping(value="rentalform",method = RequestMethod.GET)
	public String rentalStructuresEntry(Model model){
		return "rentalform";
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
	 
	 @RequestMapping(value={"allunittypes"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<UnitTypes> getUnitTypes(@DataTable DataTablesRequest pageable)
		    throws IllegalAccessException
		{
		    return setupsService.findAllUnitTypes(pageable);
		}
	 
	 @RequestMapping(value={"createUnitType"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  @ResponseStatus(HttpStatus.CREATED)
	  public void saveOrUpdateUnitType(UnitTypes unitType)
	    throws IllegalAccessException
	  {
	    setupsService.defineUnitType(unitType);
	  }
	 
	 @RequestMapping(value={"deleteUnitType/{unitTypeCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void deleteUnitType(@PathVariable Long unitTypeCode)
	  {
	    setupsService.deleteUnitType(unitTypeCode);
	  }
	 
	 @RequestMapping(value={"rentalstructures/{branchId}"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<RentalStructure> getRentalStruct(@DataTable DataTablesRequest pageable,@PathVariable Long branchId)
		    throws IllegalAccessException
		{
		    return setupsService.findAllStructures(branchId,pageable);
		}
	 
	    @RequestMapping(value={"rentalUnits/{rentalId}"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<RentalUnits> getRentalUnits(@DataTable DataTablesRequest pageable,@PathVariable Long rentalId)
		    throws IllegalAccessException
		{
		    return setupsService.findAllRentalUnits(rentalId, pageable);
		}
	 
	 @RequestMapping(value={"createRentalStruct"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public void createRentalStruct(RentalStructure rentalForm
	          )
	    throws IllegalAccessException
	  {
		 //System.out.println(rentalForm.getHouseName());
		 setupsService.defineRentalStruct(rentalForm);
		
	  }
	 
	 
	 @RequestMapping(value = "/houseImage")
	  public void getImage(HttpServletResponse response) throws IOException,ServletException {
		 RentalStructure rentalForm = createRentalForm();
		 response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		 response.getOutputStream().write(rentalForm.getHouse_image());
		 response.getOutputStream().close();
	  }
	 
	 @RequestMapping(value={"branches"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
	  @ResponseBody
	  public Page<OrgBranch> orgBranches(@RequestParam(value="term", required=false) String term, Pageable pageable)
	    throws IllegalAccessException
	  {
	    return setupsService.findBranchForSelect(term, pageable);
	  }
	 
	 
	 
	
}
