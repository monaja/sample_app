package com.brokersystems.setups.service;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Product;
import com.brokersystems.setups.model.ProductGroup;

public interface ProductService {
	
	DataTablesResult<ProductGroup> findProductGroups(DataTablesRequest request) throws IllegalAccessException;
	
	
	DataTablesResult<Product> findProducts(Long prodGroup,DataTablesRequest request) throws IllegalAccessException;
	
	
	void saveProductGroup(ProductGroup group);

}
