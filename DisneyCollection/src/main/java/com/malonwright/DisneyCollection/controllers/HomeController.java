package com.malonwright.DisneyCollection.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.malonwright.DisneyCollection.models.Comment;
import com.malonwright.DisneyCollection.models.Item;
import com.malonwright.DisneyCollection.models.Location;
import com.malonwright.DisneyCollection.models.Picture;
import com.malonwright.DisneyCollection.models.User;
import com.malonwright.DisneyCollection.services.CommentService;
import com.malonwright.DisneyCollection.services.ItemService;
import com.malonwright.DisneyCollection.services.LocationService;
import com.malonwright.DisneyCollection.services.PictureService;
import com.malonwright.DisneyCollection.services.UserService;



@Controller
public class HomeController {
	
	@Autowired
	private UserService uService;
	@Autowired
	private ItemService iService;
	@Autowired
	private LocationService lService;
	@Autowired
	private PictureService pService;
	@Autowired
	private CommentService cService;
	
	private static String UPLOADED_FOLDER ="src/main/resources/static/images/";
	
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
	//Create Item Page
	@GetMapping("/admin/addItem")
	public String addItem(@ModelAttribute("item")Item item,Model viewModel) {
		viewModel.addAttribute("allLocations", this.lService.getAllLocations());
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
		viewModel.addAttribute("allLocations", this.lService.getAllLocations());
		return "/admin/editItem.jsp";
	}
	
	//Edit an Item POST
	@PostMapping("/admin/edit/{id}")
	public String edit(@Valid @ModelAttribute("item")Item item, BindingResult result, @PathVariable("id")Long id, Model viewModel) {
		if(result.hasErrors()) {
			viewModel.addAttribute("item", this.iService.getOneItem(id));
		return "/admin/editItem.jsp";
		}
		this.iService.editItem(item);
		return "redirect:/admin";
	}
//	Post to uploading an Image !
	@PostMapping("/admin/upload/{id}")
	public String upload(@RequestParam("pic") MultipartFile file, @PathVariable("id") Long id, RedirectAttributes redirectAttr) {
		Item itemPic = this.iService.getOneItem(id);
		//Save File to static Folder
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message","Field cannot be empty!!!!");
			return "/admin/editItem.jsp";
		}
		try {
			
			//get the file and throw it into the db
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			// Get URL of the uploaded file
			String url = "/images/" + file.getOriginalFilename();
			
			this.pService.uploadPic(url, itemPic);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin";
}

	// Locations Page
	@GetMapping("/admin/locations")
	public String locations(@ModelAttribute("location")Location location, Model viewModel) {
		viewModel.addAttribute("allLocations", this.lService.getAllLocations());
		return "/admin/locations.jsp";
	}
	//Location Add Post
	@PostMapping("/admin/addLocation")
	public String addLocation(@Valid @ModelAttribute("location")Location location, BindingResult result) {
		if(result.hasErrors()) {
			return "/admin/locations.jsp";
		}
		this.lService.createLocation(location);
		return "redirect:/admin/locations";
	}
	
	//Details Page
	@GetMapping("/details/{id}")
	public String ItemDetails(@PathVariable("id")Long id, @ModelAttribute("item")Item item,@ModelAttribute("comment")Comment comment,@ModelAttribute("pic")Picture pic,Model viewModel) {
		viewModel.addAttribute("item", this.iService.getOneItem(id));
		viewModel.addAttribute("allPics", this.pService.itemPictures(item));
		
		
		return "itemDetails.jsp";
	}
	
	//Add a Comment 
	@PostMapping("/addComment/{id}")
	public String addComment(@Valid @ModelAttribute("comment") Comment comment, @ModelAttribute("id") Long id, BindingResult result, Principal principal) {
		
		if(result.hasErrors()) {
		return "/dashboard.jsp";
		}
		Item ItemToCommentOn = this.iService.getOneItem(id);
		User user = this.uService.findByUsername(principal.getName());
		comment.setAuthor(user);
		comment.setItem(ItemToCommentOn);
		this.cService.create(comment);
		return "redirect:/details/"+id;
	}
	
//	Delete an Item
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id")Long id) {
		this.iService.deleteItem(id);
		return "redirect:/admin";
	}
}
