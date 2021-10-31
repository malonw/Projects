package com.malonwright.DisneyCollection.controllers;

import java.security.Principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.malonwright.DisneyCollection.services.ItemService;
import com.malonwright.DisneyCollection.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService uService;
	@Autowired
	private ItemService iService;
	
	@GetMapping("")
	public String admin(Principal principal, Model viewModel) {
        
        String username = principal.getName();
        viewModel.addAttribute("user", uService.findByUsername(username));
        viewModel.addAttribute("allItems", this.iService.getAllItems());

		return "admin.jsp";
		
	}
	
	
	

}
