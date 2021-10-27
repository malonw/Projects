package com.malonwright.DisneyCollection.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.malonwright.DisneyCollection.models.Item;
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
	//Create Item Page
	@GetMapping("/admin/addItem")
	public String addItem(@ModelAttribute("item")Item item) {
		return "/admin/addItem.jsp";
	}
	
	//Post to Create Item
	@PostMapping("/admin/new")
	public String createItem(@Valid @ModelAttribute("item")Item item, BindingResult result) {
		if(result.hasErrors()) {
			return "/admin/addItem.jsp";
		}
		this.iService.createItem(item);
		return "redirect:/admin";
	}
	//Edit an Item Page
	@GetMapping("/admin/editItem/{id}")
	public String editItem(@PathVariable("id")Long id,@ModelAttribute("item")Item item,Model viewModel) {
		viewModel.addAttribute("item",this.iService.getOneItem(id));
		return "/admin/editItem.jsp";
	}
	//Edit an Item POST
	@PostMapping("/admin/edit/{id}")
	public String edit(@Valid @ModelAttribute("item")Item item,BindingResult result, @PathVariable("id")Long id, Model viewModel) {
		if(result.hasErrors()) {
		viewModel.addAttribute("item",this.iService.getOneItem(id));
		return "/admin/edit.jsp";
		}
		this.iService.editItem(item);
		return "redirect:/admin";
	}
	
	
	

}
