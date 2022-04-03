package com.genesys.managment.app.user.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesys.managment.app.user.exception.BadRequestException;
import com.genesys.managment.app.user.exception.ResourceNotException;
import com.genesys.managment.app.user.exception.UserAuthenticationException;
import com.genesys.managment.app.user.model.LoginData;
import com.genesys.managment.app.user.model.User;
import com.genesys.managment.app.user.repository.UserRepository;





@Service
public class UserService {

	
	
	@Autowired
	UserRepository userRepository;

	
	
	public User updateUser(Long userId,User userDetails,String apiname) {
		User user = findUserById(userId,apiname);
		updateUserValidation(userDetails);
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		/**
		 * Ignore email and password update
		*/
		user.setUpdatedDate(getCurrentDate());
		return userRepository.save(user);
	}
	
	
	public User createUser(User user, String apiname) {
		createUserValidation(user);
	
	     if(existsByEmail(user.getEmail())) throw new BadRequestException(apiname,"Requested Email Id is already exist");
	     user.setId(null);
	     user.setCreatedDate(getCurrentDate());
	     user.setUpdatedDate(getCurrentDate());
		return userRepository.save(user);
	}
	
	public boolean LoginUser(LoginData loginData, String apiname) {
		
		User user = findUserByEmail(loginData.getUsername(),apiname);
		
		if(!user.getPassword().equals(loginData.getPassword())) throw new UserAuthenticationException(apiname,"Password is incorrect");
		user.setLastLoggedInDate(getCurrentDate());
		userRepository.save(user);
		return true;
	}
	
	private boolean createUserValidation(User user) {
		/**
		 * Add Email validation
		 */
		
		/**
		 * Add Password validation
		 */
		return true;
	}
	
	private boolean updateUserValidation(User user) {
		/**
		 * Add Email validation to check mail value is same value from the back end
		 */
		
		return true;
	}
	
	


	public User findUserById(Long userId,String apiname) {
		Optional<User>  result = userRepository.findById(userId); 
		if(!result.isPresent())throw new ResourceNotException(apiname,"Requested User Details does not exist");
		return result.get();
	}
	
	public User findUserByEmail(String email,String apiname) {
		Optional<User>  result = userRepository.findByEmail(email); 
		if(!result.isPresent()) throw new ResourceNotException(apiname,"Requested User Details does not exist");
		return result.get();
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public void deleteUser(Long userId, String apiname) {
		User user = findUserById(userId,apiname);
		userRepository.delete(user);
	}

	private Date getCurrentDate() {
		LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
}
