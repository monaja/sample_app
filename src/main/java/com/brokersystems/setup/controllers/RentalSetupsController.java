package com.brokersystems.setup.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
import com.brokersystems.setups.model.RentalStructForm;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.Landlord;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.UnitTypes;
import com.brokersystems.setups.service.SetupsService;
import com.brokersystems.setups.service.LandlordService;

@Controller
@RequestMapping({ "/protected/rental/setups" })
public class RentalSetupsController {

	@Autowired
	private SetupsService setupsService;

	@Autowired
	RentalStructValidator validator;
	
	@Autowired
	private LandlordService tenantService;

	@InitBinder({ "rentalStructure" })
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(this.validator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute
	public RentalStructure createRentalForm() {
		return new RentalStructure();
	}

	@ModelAttribute
	public RentalStructForm createRentalStructForm() {
		return new RentalStructForm();
	}

	@RequestMapping(value = "ratetypes", method = RequestMethod.GET)
	public String rateTypeHome(Model model) {
		return "ratetypes";
	}

	@RequestMapping(value = "unittypes", method = RequestMethod.GET)
	public String unitTypeHome(Model model) {
		return "unittypes";
	}

	@RequestMapping(value = "rentalstruct", method = RequestMethod.GET)
	public String rentalStructuresHome(Model model) {
		return "rentalstruct";
	}

	@RequestMapping(value = "rentalform", method = RequestMethod.GET)
	public String rentalStructuresEntry(Model model) {
		model.addAttribute("rentalId", -2000);
		return "rentalform";
	}
	
	@RequestMapping(value = "tenantForm", method = RequestMethod.GET)
	public String tenantsForm(Model model) {
		model.addAttribute("tenantId", -2000);
		return "tenantform";
	}
	
	@RequestMapping(value = "tenantList", method = RequestMethod.GET)
	public String tenantsHome(Model model) {
		return "tenantList";
	}

	@RequestMapping(value = { "allratetypes" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<RateTypes> getRateTypes(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return setupsService.findAllRateTypes(pageable);
	}

	@RequestMapping(value = { "createRateType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateRateType(RateTypes rateType) throws IllegalAccessException {
		setupsService.defineRateType(rateType);
	}

	@RequestMapping(value = { "deleteRateType/{rateTypeCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRateType(@PathVariable Long rateTypeCode) {
		setupsService.deleteRateType(rateTypeCode);
	}

	@RequestMapping(value = { "allunittypes" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<UnitTypes> getUnitTypes(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return setupsService.findAllUnitTypes(pageable);
	}

	@RequestMapping(value = { "createUnitType" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateUnitType(UnitTypes unitType) throws IllegalAccessException {
		setupsService.defineUnitType(unitType);
	}

	@RequestMapping(value = { "deleteUnitType/{unitTypeCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUnitType(@PathVariable Long unitTypeCode) {
		setupsService.deleteUnitType(unitTypeCode);
	}

	@RequestMapping(value = { "rentalstructures/{branchId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<RentalStructure> getRentalStruct(@DataTable DataTablesRequest pageable,
			@PathVariable Long branchId) throws IllegalAccessException {
		return setupsService.findAllStructures(branchId, pageable);
	}

	@RequestMapping(value = { "rentalUnits/{rentalId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<RentalUnits> getRentalUnits(@DataTable DataTablesRequest pageable,
			@PathVariable Long rentalId) throws IllegalAccessException {
		return setupsService.findAllRentalUnits(rentalId, pageable);
	}

	@RequestMapping(value = { "createRentalStruct" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Long createRentalStruct(RentalStructure rentalForm) throws IllegalAccessException, IOException {
		if ((rentalForm.getFile() != null) && (!rentalForm.getFile().isEmpty())) {
			if (rentalForm.getFile().getSize() != 0) {
				rentalForm.setHouse_image(rentalForm.getFile().getBytes());
			} else {

				if (rentalForm.getRentalId() != null) {
					rentalForm.setHouse_image(
							setupsService.getStructureDetails(rentalForm.getRentalId()).getHouse_image());

				}
			}
		} else {

			if (rentalForm.getRentalId() != null) {
				rentalForm.setHouse_image(setupsService.getStructureDetails(rentalForm.getRentalId()).getHouse_image());

			}
		}
		RentalStructure s = setupsService.defineRentalStruct(rentalForm);
		return s.getRentalId();
	}
	
	@RequestMapping(value = { "deleteRentalStruct/{rentalCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRentalStruct(@PathVariable Long rentalCode) {
		setupsService.deleteRentalStruct(rentalCode);
	}
	
	

	@RequestMapping(value = "/houseImage/{rentalId}")
	public void getImage(HttpServletResponse response, @PathVariable Long rentalId)
			throws IOException, ServletException {
		RentalStructure rentalForm = setupsService.getStructureDetails(rentalId);
		if (rentalForm != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(rentalForm.getHouse_image());
			response.getOutputStream().close();
		}
	}

	@RequestMapping(value = { "branches" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<OrgBranch> orgBranches(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return setupsService.findBranchForSelect(term, pageable);
	}

	@RequestMapping(value = { "selunitTypes" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<UnitTypes> unitTypes(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return setupsService.findUnitsForSelect(term, pageable);
	}

	@RequestMapping(value = "/editRentalForm", method = RequestMethod.POST)
	public String editRentalForm(@Valid @ModelAttribute RentalStructForm rentalForm, Model model) {
		model.addAttribute("rentalId", rentalForm.getRentalId());
		return "rentalform";
	}

	@RequestMapping(value = { "rentalStructure/{rentalId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public RentalStructure getStructureDetails(@PathVariable Long rentalId) {
		return setupsService.getStructureDetails(rentalId);
	}
	
	@RequestMapping(value = { "createRentalUnit" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateRentalUnit(RentalUnits unit)  throws BadRequestException {
		setupsService.defineRentalUnits(unit);
	}
	
	@RequestMapping(value = { "deleteRentalUnit/{unitCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRentalCode(@PathVariable Long unitCode) {
		setupsService.deleteRentalUnit(unitCode);
	}
	
	@RequestMapping(value = { "rentalCharges/{renId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<RentalUnitCharges> getRentalUnitCharges(@DataTable DataTablesRequest pageable,
			@PathVariable Long renId) throws IllegalAccessException {
		return setupsService.findRentalUnitCharges(renId, pageable);
	}
	
	@RequestMapping(value = { "createRentalCharge" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateRentalCharge(RentalUnitCharges charge) throws BadRequestException {
		setupsService.defineRentalCharges(charge);
	}
	
	@RequestMapping(value = { "deleteRentalCharge/{chargeId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRentalCharge(@PathVariable Long chargeId) {
		setupsService.deleteRentalCharge(chargeId);
	}
	
	@RequestMapping(value = { "unitratetypes" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<RateTypes> unitRateTypes(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return setupsService.findRatesForSelect(term, pageable);
	}
	

	 @RequestMapping(value={"tenants"}, method={RequestMethod.GET})
		@ResponseBody
		public DataTablesResult<Landlord> getTenants(@DataTable DataTablesRequest pageable)
		    throws IllegalAccessException
		{
		    return tenantService.findAllLandlords(pageable);
		}
	 
	 @RequestMapping(value = { "createTenant" }, method = {
				org.springframework.web.bind.annotation.RequestMethod.POST })
		@ResponseStatus(HttpStatus.CREATED)
		public void saveLandlord(Landlord tenant) throws BadRequestException {
		       tenantService.defineLandlord(tenant);
		}
	 
	 @RequestMapping(value = "/editTenantForm", method = RequestMethod.POST)
		public String editTenantForm(@Valid @ModelAttribute Landlord tenant, Model model) {
			model.addAttribute("tenantId", tenant.getTenantId());
			return "tenantform";
		}
	 
	 
	    @RequestMapping(value = { "tenantDetails/{tenantId}" }, method = { RequestMethod.GET })
		@ResponseBody
		public Landlord getTenantDetails(@PathVariable Long tenantId) throws IllegalAccessException {
			return tenantService.getLandlordDetails(tenantId);
		}
	    
	    
	    @RequestMapping(value = { "deleteTenant/{tenantId}" }, method = {
				org.springframework.web.bind.annotation.RequestMethod.GET })
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void deleteTenant(@PathVariable Long tenantId) {
	    	tenantService.deleteLandlord(tenantId);
		}
		

}
