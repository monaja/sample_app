package com.brokersystems.setups.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.User;

/**
 * User Service
 * Mainly used for crud service on user setups
 * @author Peter
 *
 */
public interface UserService {

    User findByUserName(String userName);
    
    DataTablesResult<User> findDatatables(DataTablesRequest request) throws IllegalAccessException;
    
    Page<User> findForSelect(String term, Pageable pageable);
    
    User saveOrUpdate(User user) ;
    
    User findById(Integer id);
    
  

}
