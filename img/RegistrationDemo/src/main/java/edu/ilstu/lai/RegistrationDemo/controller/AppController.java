package edu.ilstu.lai.RegistrationDemo.controller;


import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import Service.UserService;
import Util.FileUploadUtil;
import edu.ilstu.lai.RegistrationDemo.entity.User;
import edu.ilstu.lai.RegistrationDemo.repository.UserRepository;

@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;
	//@Autowired
	//UserService userService;
	@GetMapping("")
	public String viewHomePage() {
	return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	model.addAttribute("user", new User());
	
	return "signup_form";
	}
	
	
	@Autowired
	UserService userService;
	@PostMapping("/process_register")
	public String processRegister(User user, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        user.setPhoto(fileName);
	        try {
	            userService.saveUser(user);
	            String uploadDir = "H:/images/" + user.getId();
	            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	            
	            return "redirect:/login";
	        }catch (Exception e) {
	          model.addAttribute("message", "An account already exists for this email");
	        return "signup_form";
	        }
	}

	
	/*@PostMapping("/process_register")
	public String processRegister(User user, Model model) {

		how come userRepo can save data without any methods in it?
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		User u = userRepo.findByEmail(user.getEmail());

			if (u !=null) {
			//sent an error message 
			Map<String, Object> map = new HashMap<>();
			map.put("message", "Email already existed existed");
			model.addAllAttributes(map);
			return "signup_form";
			} else {
			userRepo.save(user);
				return "register_success";
			}
		try {
			userService.saveUser(user);
			return "redirect:/login";
			
		} catch(UserAlreadyExistException e) {
			model.addAttribute("message", "an acocunt has already existed");
			return "signup_form";
		}
			
	
	
		@GetMapping("/users")
		public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();//it is going to find all users
		model.addAttribute("listUsers", listUsers);
	
			return "users";
	}
	*/
		
		@GetMapping("/users")
		public String listUsers(Model model, HttpServletRequest request) {
		List<User> listUsers = userRepo.findAll();//it is going to find all users
		model.addAttribute("listUsers", listUsers);

		Principal principal = request.getUserPrincipal();//get authenticated user
		User user = userRepo.findByEmail(principal.getName()); //find the User object of the authenticated user

		model.addAttribute("user", user);

		return "users";
		}




}