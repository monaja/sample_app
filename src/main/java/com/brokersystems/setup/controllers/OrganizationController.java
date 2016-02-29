package com.brokersystems.setup.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.utils.FileUploadValidator;
import com.brokersystems.setups.model.Bank;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.User;
import com.brokersystems.setups.service.OrganizationService;



/**
 * Controller for organizing and managing organization screen
 * This depends on OrganizationService as the main dependency.
 * Product Manager to advise on validation of the screen
 * @author Peter
 *
 */

@Controller
@RequestMapping(value = "/protected/organization")
public class OrganizationController {
	
	
	 private static final String SYSTEM_IMAGES = "images";
	 private static final String TEMP_FOLDER_PATH = System.getProperty("java.io.tmpdir");
	 private static final String IMAGES_SYSTEM_PATH = TEMP_FOLDER_PATH + File.separator + SYSTEM_IMAGES; 
	 private static final File  IMAGES_SYSTEM_DIR = new File(IMAGES_SYSTEM_PATH);
	 private static final String IMAGES_SYSTEM_DIR_ABSOLUTE_PATH = IMAGES_SYSTEM_DIR.getAbsolutePath() + File.separator;
	

	@Autowired
	private OrganizationService orgService;
	
	 @Autowired
	 FileUploadValidator fileValidator;
	 
	 @InitBinder("organization")
	    protected void initBinder(WebDataBinder binder) {
	       binder.setValidator(fileValidator);
	    }
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String organizationHome(Model model){
		return "orgdefinition";
	}
	
	
	@ModelAttribute
	public Organization setOrganizationForm(){
		Organization organization = orgService.getOrganizationDetails();
		if(organization.getOrgCode()==null){
			organization.setFormAction("A");
		}else
		organization.setFormAction("E");
		return organization;
	}
	
	@RequestMapping(value="/editOrganization",method=RequestMethod.GET)
	public String createOrganization(Model model) throws IOException{
		Organization organization = orgService.getOrganizationDetails();
		organization.setFormAction("A");
		model.addAttribute("organization",organization);
		return  organizationHome(model);
	}
	
	@RequestMapping(value="/createOrganization",method=RequestMethod.POST)
	public String createOrganization( Organization organization,Model model) throws IOException{
		if(organization.getFile()!=null){
			if(!organization.getFile().isEmpty()) 
		     organization.setOrgLogo(organization.getFile().getBytes());
		}
		orgService.createOrganization(organization);
		organization.setFormAction("E");
		return  organizationHome(model);
	}
	
	
	
	/**
	 * This gets the system logo from the database and saves in the temporary folder of the system
	 * 
	 * @return
	 * @throws IOException
	 */
	 @RequestMapping(value = "/logo")
	    @ResponseBody
	    public byte[] getImage() throws IOException {
		 Organization organization = orgService.getOrganizationDetails();
		    File temporaryLogo = new File(IMAGES_SYSTEM_DIR_ABSOLUTE_PATH+ "logo.jpg");
		    if(temporaryLogo.exists()) temporaryLogo.delete();
		    byte[] readAllBytes =null;
		    
		   if(organization.getOrgLogo()!=null){
		   FileUtils.writeByteArrayToFile(temporaryLogo, organization.getOrgLogo());
		   readAllBytes = Files.readAllBytes(temporaryLogo.toPath());
		   }
	       return readAllBytes;
	    }
	
	 
	    @RequestMapping(value = "countries", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<Country> countries(@DataTable DataTablesRequest pageable)
	            throws IllegalAccessException {
	        return orgService.findCountryDatatables(pageable);
	    }
	
	    
	    @RequestMapping(value = "counties/{couCode}", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<County> counties(@DataTable DataTablesRequest pageable,@PathVariable Long couCode)
	            throws IllegalAccessException {
	           return orgService.findCountiesByCountry(couCode,pageable);
	    }
	    
	    
	    @RequestMapping(value = "towns/{countyCode}", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<Town> towns(@DataTable DataTablesRequest pageable,@PathVariable Long countyCode)
	            throws IllegalAccessException {
	           return orgService.findTownsByCounty(countyCode, pageable);
	    }
	    
	    @RequestMapping(value = "currencies", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<Currencies> currencies(@DataTable DataTablesRequest pageable)
	            throws IllegalAccessException {
	        return orgService.findCurrencies(pageable);
	    }
	    
	    @RequestMapping(value = "branches/{orgCode}", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<OrgBranch> orgBranch(@DataTable DataTablesRequest pageable,@PathVariable Long orgCode)
	            throws IllegalAccessException {
	           return orgService.findOrgBranches(orgCode, pageable);
	    }
	    
	    
	    @RequestMapping(value = "banks/{orgCode}", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<Bank> orgBanks(@DataTable DataTablesRequest pageable,@PathVariable Long orgCode)
	            throws IllegalAccessException {
	           return orgService.findOrgBanks(orgCode, pageable);
	    }
	    
	    @RequestMapping(value = "createOrgBranch", method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    public void saveOrUpdateBranch(OrgBranch branch) throws IllegalAccessException
	      {
	    	 Organization org  = setOrganizationForm();
	    	 if(org==null) 
	    		 throw new IllegalArgumentException("Cannot create branch without Organization");
	    	 branch.setOrganization(org);
	        orgService.createOrgBranch(branch);

	      }
	    
	    
	    
	    @RequestMapping(value = "createOrgBank", method = RequestMethod.POST)
	    @ResponseStatus(HttpStatus.CREATED)
	    public void saveOrUpdateBank(Bank bank) 
	     {
	    	Organization org  = setOrganizationForm();
	    	 if(org==null) 
	    		 throw new IllegalArgumentException("Cannot create branch without Organization");
	    	 bank.setOrganization(org);
	        orgService.createOrgBank(bank);

	     }
	    
	    
	    @RequestMapping(value = "deleteBranch/{branchCode}", method = RequestMethod.GET)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteOrgBranch(@PathVariable Long branchCode) 
	     {
	        orgService.deleteOrgBranch(branchCode);

	     }
	    
	    
	    @RequestMapping(value = "deleteBank/{bankCode}", method = RequestMethod.GET)
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void deleteOrgBank(@PathVariable Long bankCode) 
	     {
	        orgService.deleteOrgBank(bankCode);

	     }
	

}
