package com.brokersystems.setup.controllers;

import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.AccountDef;
import com.brokersystems.setups.model.AccountTypes;
import com.brokersystems.setups.model.ChargeRatesGroup;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.RentalStructForm;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.Landlord;
import com.brokersystems.setups.model.ModelHelperForm;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.UnitTypes;
import com.brokersystems.setups.service.SetupsService;
import com.brokersystems.setups.service.LandlordService;

@Controller
@RequestMapping({ "/protected/setups" })
public class SetupsController {

	@Autowired
	private SetupsService setupsService;
	
	@InitBinder({ "account" })
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ModelAttribute
	public ModelHelperForm createHelperForm() {
		return new ModelHelperForm();
	}

	@RequestMapping(value = "currency", method = RequestMethod.GET)
	public String currencyHome(Model model) {
		return "currency";
	}
	
	
	@RequestMapping(value = "chargegroups", method = RequestMethod.GET)
	public String chargeGroups(Model model) {
		return "chargegroup";
	}

	@RequestMapping(value = "countries", method = RequestMethod.GET)
	public String countryHome(Model model) {
		return "countries";
	}

	@RequestMapping(value = "paymentmodes", method = RequestMethod.GET)
	public String paymentModesHome(Model model) {
		return "paymodesList";
	}

	@RequestMapping(value = { "currencies" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<Currencies> getCurrencies(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return setupsService.findAllCurrencies(pageable);
	}

	@RequestMapping(value = { "createCurrency" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateCurrency(Currencies currency) throws IllegalAccessException {
		setupsService.defineCurrency(currency);
	}

	@RequestMapping(value = { "deleteCurrency/{currCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCurrency(@PathVariable Long currCode) {
		setupsService.deleteCurrency(currCode);
	}

	@RequestMapping(value = { "allCountries" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<Country> getCountries(@DataTable DataTablesRequest pageable) throws IllegalAccessException {
		return setupsService.findAllCountries(pageable);
	}

	@RequestMapping(value = { "createCountry" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateCountry(Country country) throws IllegalAccessException {
		setupsService.defineCountry(country);
	}

	@RequestMapping(value = { "deleteCountry/{couCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCountry(@PathVariable Long couCode) {
		setupsService.deleteCountry(couCode);
	}

	@RequestMapping(value = { "allCounties/{couCode}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<County> getCounties(@DataTable DataTablesRequest pageable, @PathVariable Long couCode)
			throws IllegalAccessException {
		return setupsService.findCountiesByCountry(couCode, pageable);
	}

	@RequestMapping(value = { "createCounty" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateCounty(County county) throws IllegalAccessException {
		setupsService.defineCounty(county);
	}

	@RequestMapping(value = { "deleteCounty/{countyCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCounty(@PathVariable Long countyCode) {
		setupsService.deleteCounty(countyCode);
	}

	@RequestMapping(value = { "allTowns/{countyCode}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<Town> getTowns(@DataTable DataTablesRequest pageable, @PathVariable Long countyCode)
			throws IllegalAccessException {
		return setupsService.findTownsByCounty(countyCode, pageable);
	}

	@RequestMapping(value = { "createTown" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateTown(Town town) throws IllegalAccessException {
		setupsService.defineTown(town);
	}

	@RequestMapping(value = { "deleteTown/{townCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTown(@PathVariable Long townCode) {
		setupsService.deleteTown(townCode);
	}

	@RequestMapping(value = { "allpaymentModes" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<PaymentModes> getPayemtModes(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return setupsService.findAllPaymentModes(pageable);
	}

	@RequestMapping(value = { "createPaymentModes" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdatePaymentModes(PaymentModes mode) throws BadRequestException {
		setupsService.definePaymentMode(mode);
	}

	@RequestMapping(value = { "deletePayMode/{pmId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePaymentMode(@PathVariable Long pmId) {
		setupsService.deletePaymentMode(pmId);
	}

	@RequestMapping(value = { "acctypes" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<AccountTypes> getAccountTypes(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return setupsService.findAllAccountTypes(pageable);
	}
	
	@RequestMapping(value = "accttypes", method = RequestMethod.GET)
	public String accountTypesHome(Model model) {
		return "accounttypes";
	}
	
	@RequestMapping(value = { "createAcctTypes" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateAcctTypes(AccountTypes acctypes) throws BadRequestException {
		setupsService.defineAccountType(acctypes);
	}
	
	@RequestMapping(value = { "deleteAcctype/{accId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccType(@PathVariable Long accId) {
		setupsService.deleteAccountType(accId);
	}
	
	@RequestMapping(value = "accts", method = RequestMethod.GET)
	public String accountsHome(Model model) {
		return "accounts";
	}
	
	@RequestMapping(value = { "selAcctTypes" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<AccountTypes> selAccountTypes(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return setupsService.findAccountTypesforSelect(term, pageable);
	}
	
	
	@RequestMapping(value = { "allaccounts/{accId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<AccountDef> getAccounts(@DataTable DataTablesRequest pageable, @PathVariable Long accId)
			throws IllegalAccessException {
		return setupsService.findAllAccounts(accId, pageable);
	}
	
	@RequestMapping(value = "acctsform", method = RequestMethod.GET)
	public String accountsform(Model model) {
		model.addAttribute("accId", -2000);
		return "acctsform";
	}
	
	@RequestMapping(value = "/accountImage/{acctId}")
	public void getImage(HttpServletResponse response, @PathVariable Long acctId)
			throws IOException, ServletException {
		AccountDef account = setupsService.getAccountDetails(acctId);
		if (account != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(account.getPhoto());
			response.getOutputStream().close();
		}
	}
	
	@RequestMapping(value = { "createAccount" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateAccount(AccountDef account) throws BadRequestException, IOException {
		
		if ((account.getFile() != null) && (!account.getFile().isEmpty())) {
			if (account.getFile().getSize() != 0) {
				account.setPhoto(account.getFile().getBytes());
			} else {

				if (account.getAcctId() != null) {
					account.setPhoto(
							setupsService.getAccountDetails(account.getAcctId()).getPhoto());

				}
			}
		} else {

			if (account.getAcctId() != null) {
				account.setPhoto(setupsService.getAccountDetails(account.getAcctId()).getPhoto());

			}
		}
		 setupsService.defineAccount(account);
	}
	
	@RequestMapping(value = "/editAcctForm", method = RequestMethod.POST)
	public String editRentalForm(@Valid @ModelAttribute ModelHelperForm helperForm, Model model) {
		model.addAttribute("accId", helperForm.getId());
		return "acctsform";
	}
	
	@RequestMapping(value = { "accounts/{acctId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public AccountDef getAccountDetails(@PathVariable Long acctId) {
		return setupsService.getAccountDetails(acctId);
	}
	
	@RequestMapping(value = { "deleteAccount/{acctId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccount(@PathVariable Long acctId) {
		setupsService.deleteAccount(acctId);
	}
	
	
	@RequestMapping(value = { "chargegroupings" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<ChargeRatesGroup> getChargeGroups(@DataTable DataTablesRequest pageable) throws IllegalAccessException {
		return setupsService.findAllChargeGroups(pageable);
	}
	
	@RequestMapping(value = { "rentalCharges/{groupCode}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<RentalUnitCharges> getRentalUnitCharges(@DataTable DataTablesRequest pageable,
			@PathVariable Long groupCode) throws IllegalAccessException {
		return setupsService.findRentalUnitCharges(groupCode, pageable);
	}
	
	@RequestMapping(value = { "deleteChargeGroup/{groupCode}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteChargeGroup(@PathVariable Long groupCode) {
		setupsService.deleteChargeGroup(groupCode);
	}
	
	@RequestMapping(value = { "createChargeGroup" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOrUpdateChargeGroup(ChargeRatesGroup group)  throws BadRequestException {
		setupsService.createChargeGroup(group);
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
	
	@RequestMapping(value = { "selRatesGroups" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<ChargeRatesGroup> selRatesGroups(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return setupsService.findGroupsForSelect(term, pageable);
	}

}
