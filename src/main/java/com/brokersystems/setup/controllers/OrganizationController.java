package com.brokersystems.setup.controllers;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.server.utils.FileUploadValidator;
import com.brokersystems.server.validator.OrganizationValidator;
import com.brokersystems.setups.model.Address;
import com.brokersystems.setups.model.Bank;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.service.OrganizationService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/protected/organization"})
public class OrganizationController
{
  private static final String SYSTEM_IMAGES = "images";
  private static final String TEMP_FOLDER_PATH = System.getProperty("java.io.tmpdir");
  private static final String IMAGES_SYSTEM_PATH = TEMP_FOLDER_PATH + File.separator + "images";
  private static final File IMAGES_SYSTEM_DIR = new File(IMAGES_SYSTEM_PATH);
  private static final String IMAGES_SYSTEM_DIR_ABSOLUTE_PATH = IMAGES_SYSTEM_DIR.getAbsolutePath() + File.separator;
  @Autowired
  private OrganizationService orgService;
  @Autowired
  FileUploadValidator fileValidator;
  @Autowired
  OrganizationValidator organizationValidator;
  
  @InitBinder({"organization"})
  protected void initBinder(WebDataBinder binder)
  {
    binder.setValidator(this.fileValidator);
  }
  
  @RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String organizationHome(Model model)
  {
    return "orgdefinition";
  }
  
  @ModelAttribute
  public Organization setOrganizationForm()
  {
    Organization organization = this.orgService.getOrganizationDetails();
    if (organization.getOrgCode() == null) {
      organization.setFormAction("A");
    } else {
      organization.setFormAction("E");
    }
    return organization;
  }
  
  @RequestMapping(value={"/editOrganization"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String createOrganization(Model model)
    throws IOException
  {
    Organization organization = this.orgService.getOrganizationDetails();
    organization.setFormAction("A");
    model.addAttribute("organization", organization);
    return organizationHome(model);
  }
  
  @RequestMapping(value={"/createOrganization"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String createOrganization(Organization organization, Model model)
    throws IOException, BadRequestException
  {
    this.organizationValidator.validateSelectCountiesForCountry(organization.getAddress().getCountry().getCouCode());
    this.organizationValidator.validateSelectTownsForCounty(organization.getAddress().getCounty().getCountyId());
    this.organizationValidator.validateCity(organization.getAddress().getTown().getCtCode());
    this.organizationValidator.validateCurrency(organization.getCurrency().getCurCode());
    if ((organization.getFile() != null) && 
      (!organization.getFile().isEmpty())) {
      organization.setOrgLogo(organization.getFile().getBytes());
    }
    
    this.orgService.createOrganization(organization);
    organization.setFormAction("E");
    return "redirect:/protected/organization/";
  }
  
  @RequestMapping(value = "/logo")
  public void getImage(HttpServletResponse response) throws IOException,ServletException {
	 Organization organization = orgService.getOrganizationDetails();
	 response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	 response.getOutputStream().write(organization.getOrgLogo());
	 response.getOutputStream().close();
  }
  
  @RequestMapping(value={"countries"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Page<Country> select(@RequestParam(value="term", required=false) String term, Pageable pageable)
    throws IllegalAccessException
  {
    return this.orgService.findCountryForSelect(term, pageable);
  }
  
  @RequestMapping(value={"counties"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Page<County> selectCounties(@RequestParam(value="term", required=false) String term, @RequestParam("couCode") Long couCode, Pageable pageable)
    throws IllegalAccessException, BadRequestException
  {
    this.organizationValidator.validateSelectCountiesForCountry(couCode);
    return this.orgService.findCountyForSelect(term, pageable, couCode.longValue());
  }
  
  @RequestMapping(value={"towns"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Page<Town> towns(@RequestParam(value="term", required=false) String term, @RequestParam("countyId") Long countyId, Pageable pageable)
    throws IllegalAccessException, BadRequestException
  {
    this.organizationValidator.validateSelectTownsForCounty(countyId);
    return this.orgService.findTownForSelect(term, pageable, countyId.longValue());
  }
  
  @RequestMapping(value={"currencies"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Page<Currencies> currencies(@RequestParam(value="term", required=false) String term, Pageable pageable)
    throws IllegalAccessException
  {
    return this.orgService.findCurrenciesForSelect(term, pageable);
  }
  
  @RequestMapping(value={"branches/{orgCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public DataTablesResult<OrgBranch> orgBranch(@DataTable DataTablesRequest pageable, @PathVariable Long orgCode)
    throws IllegalAccessException
  {
    return this.orgService.findOrgBranches(orgCode.longValue(), pageable);
  }
  
  @RequestMapping(value={"banks/{orgCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public DataTablesResult<Bank> orgBanks(@DataTable DataTablesRequest pageable, @PathVariable Long orgCode)
    throws IllegalAccessException
  {
    return this.orgService.findOrgBanks(orgCode.longValue(), pageable);
  }
  
  @RequestMapping(value={"createOrgBranch"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseStatus(HttpStatus.CREATED)
  public void saveOrUpdateBranch(OrgBranch branch)
    throws IllegalAccessException
  {
    Organization org = setOrganizationForm();
    if (org == null) {
      throw new IllegalArgumentException("Cannot create branch without Organization");
    }
    branch.setOrganization(org);
    this.orgService.createOrgBranch(branch);
  }
  
  @RequestMapping(value={"createOrgBank"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseStatus(HttpStatus.CREATED)
  public void saveOrUpdateBank(Bank bank)
  {
    Organization org = setOrganizationForm();
    if (org == null) {
      throw new IllegalArgumentException("Cannot create branch without Organization");
    }
    bank.setOrganization(org);
    this.orgService.createOrgBank(bank);
  }
  
  @RequestMapping(value={"deleteBranch/{branchCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrgBranch(@PathVariable Long branchCode)
  {
    this.orgService.deleteOrgBranch(branchCode);
  }
  
  @RequestMapping(value={"deleteBank/{bankCode}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrgBank(@PathVariable Long bankCode)
  {
    this.orgService.deleteOrgBank(bankCode);
  }
  
  @ExceptionHandler({BadRequestException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public String handleBadRequestException(BadRequestException ex)
  {
    return ex.getMessage();
  }
}
