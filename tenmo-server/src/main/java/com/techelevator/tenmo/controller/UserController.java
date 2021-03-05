package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.User;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/user")
public class UserController {
	
private UserDAO dao;
	
	public UserController(UserDAO userDao) {
		this.dao = userDao;
	}
	@RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> findAll(){
		return dao.findAll();
	};
    
    @RequestMapping(path = "", method = RequestMethod.GET)
	public User findByUsername(@RequestParam String username){
    	return dao.findByUsername(username);
    };
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public int findIdByUsername(@RequestParam String username){
		return dao.findIdByUsername(username);
	};
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public boolean create(@RequestParam String username, @RequestParam String password){
		return dao.create(username, password);
	};
}