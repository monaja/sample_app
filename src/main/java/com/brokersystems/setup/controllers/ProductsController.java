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
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.Organization;
import com.brokersystems.setups.model.Product;
import com.brokersystems.setups.model.ProductGroup;
import com.brokersystems.setups.service.ProductService;

@Controller
@RequestMapping(value="/protected/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String productsHome(Model model){
		return "productsdefinition";
	}
	
	@RequestMapping(value = "productGroups", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<ProductGroup> productGroups(@DataTable DataTablesRequest pageable)
            throws IllegalAccessException {
        return productService.findProductGroups(pageable);
    }
	
	 @RequestMapping(value = "products/{prodCode}", method = RequestMethod.GET)
	    @ResponseBody
	    public DataTablesResult<Product> counties(@DataTable DataTablesRequest pageable,@PathVariable Long prodCode)
	            throws IllegalAccessException {
	           return productService.findProducts(prodCode, pageable);
	    }
	
	
	@RequestMapping(value = "createProductGroup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrUpdatePrgGroup(ProductGroup group) throws IllegalAccessException
      {
		
		productService.saveProductGroup(group);

      }

}
