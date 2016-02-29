package com.brokersystems.setups.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setup.repository.UserRepository;
import com.brokersystems.setups.model.QUser;
import com.brokersystems.setups.model.User;
import com.brokersystems.setups.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;
	
	@Resource
	private BCryptPasswordEncoder encoder;
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<User> findDatatables(DataTablesRequest request)
			throws IllegalAccessException {
		Page<User> page =userRepository.findAll(request.searchPredicate(QUser.user),request);
		return new DataTablesResult<User>(request, page);
	}


	@Override
	public Page<User> findForSelect(String term, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Modifying
	@Transactional(readOnly = false)
	public User saveOrUpdate(User user){
		if(user.getId()==null){
			user.setEnabled("1");
			user.setPassword(encoder.encode(user.getUsername()));
		}
		userRepository.save(user);
		return user;
	}


	@Override
	public User findById(Integer id) {
		return userRepository.findOne(id);
	}
	


}
