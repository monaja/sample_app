package com.brokersystems.setups.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.ProductGroupRepository;
import com.brokersystems.setup.repository.ProductsRepository;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Product;
import com.brokersystems.setups.model.ProductGroup;
import com.brokersystems.setups.model.QCounty;
import com.brokersystems.setups.model.QProduct;
import com.brokersystems.setups.model.QProductGroup;
import com.brokersystems.setups.service.ProductService;
import com.mysema.query.types.expr.BooleanExpression;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductGroupRepository groupRepo;
	
	@Autowired
	private ProductsRepository prodRepo;

	@Transactional(readOnly = true)
	@Override
	public DataTablesResult<ProductGroup> findProductGroups(DataTablesRequest request) throws IllegalAccessException {
		Page<ProductGroup> page =groupRepo.findAll(request.searchPredicate(QProductGroup.productGroup),request);
		return new DataTablesResult<ProductGroup>(request, page);
	}
	
	@Modifying
	@Transactional(readOnly = false)
	@Override
	public void saveProductGroup(ProductGroup group) {
		groupRepo.save(group);
		
	}

	@Override
	public DataTablesResult<Product> findProducts(Long prodGroup, DataTablesRequest request)
			throws IllegalAccessException {
		QProduct prd = QProduct.product;
		BooleanExpression pred = prd.productGroup.prgCode.eq(prodGroup);
		 Page<Product> page = prodRepo.findAll(pred.and(request.searchPredicate(QProduct.product)),request);
		 return new DataTablesResult<Product>(request, page);
	}

}
