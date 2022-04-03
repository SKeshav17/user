package com.genesys.managment.app.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesys.managment.app.user.model.LoginData;
import com.genesys.managment.app.user.model.User;
import com.genesys.managment.app.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginData loginData) {
		String apiname = "LoginUserAPI";
		userService.LoginUser(loginData,apiname);
		return ResponseEntity.ok().body("Successfully Logged In");
	}
	
	@PostMapping()
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
		String apiname = "CreateUserAPI";
		User result = userService.createUser(user,apiname);
		return new ResponseEntity<User>(result, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
		
		String apiname = "GetUserDetailsAPI";
		User user = userService.findUserById(userId,apiname);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

	@GetMapping()
	public ResponseEntity<List<User>> getUsers() {

		List<User> users = userService.getUsers();

		if (users == null || users.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(users);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@RequestBody User userDetails) {

		String apiname = "UpdateUserDetailsAPI";
		User updatedUser = userService.updateUser(userId,userDetails,apiname);
		return ResponseEntity.ok().body(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId) {
		String apiname = "DeleteUserDetailsAPI";
		userService.deleteUser(userId,apiname);
		return ResponseEntity.ok().build();
	}

}
