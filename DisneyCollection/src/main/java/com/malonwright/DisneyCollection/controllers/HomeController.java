package com.malonwright.DisneyCollection.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.models.User;
import com.malonwright.DisneyCollection.services.ItemService;
import com.malonwright.DisneyCollection.services.UserService;



@Controller
public class HomeController {
	
	@Autowired
	private UserService uService;
	@Autowired
	private ItemService iService;
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model viewModel) {
		 if(error != null) {
            viewModel.addAttribute("error", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            viewModel.addAttribute("error", "Logout Successful!");
        }
		
		return "login.jsp";
	}
	
	@GetMapping("/registration")
	public String registration(@Valid @ModelAttribute("user")User user) {
		return "registration.jsp";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("user")User user, BindingResult result, Model viewModel,HttpSession session) {
		if (result.hasErrors()) {
			return "registration.jsp";
		}
		uService.saveWithUserRole(user);
		return "redirect:/login";
	}
	
	@GetMapping(value = {"/", "/dashboard"})
    public String home(Principal principal, Model viewModel) {
        
        String username = principal.getName();
        viewModel.addAttribute("user", uService.findByUsername(username));
        viewModel.addAttribute("allItems", this.iService.getAllItems());
        return "dashboard.jsp";
    }
	//Like Item
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, Principal principal) {
		User userToLikeItem = this.uService.findByUsername(principal.getName());
		Item itemToLike = this.iService.getOneItem(id);
		
		this.iService.likeItem(userToLikeItem, itemToLike);
		return "redirect:/dashboard";
	}
	//Unlike Item
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, Principal principal) {
		User userToLikeItem = this.uService.findByUsername(principal.getName());
		Item itemToLike = this.iService.getOneItem(id);
		
		this.iService.unlikeItem(userToLikeItem, itemToLike);
		return "redirect:/dashboard";
	}

}
