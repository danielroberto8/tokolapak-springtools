package com.cimb.tokolapak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.UserRepo;
import com.cimb.tokolapak.entity.User;
import com.cimb.tokolapak.util.EmailUtil;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EmailUtil emailUtil;
	
	private PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
	
	@PostMapping
	public User registerUser(@RequestBody User user){
//		Optional<User> findUser = userRepo.findByUsername(user.getUsername()).get();
//		if(findUser.toString()=="Optional.empty") {
//			throw new RuntimeException("Username Exist");
//		}
		 
		String encodedPassword = pwEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		return userRepo.save(user);
	}
	
	@PostMapping("/login")
	public User loginUser(@RequestBody User user) {
		User findUser = userRepo.findByUsername(user.getUsername()).get();
		if(pwEncoder.matches(user.getPassword(), findUser.getPassword())){
			return findUser;
		}
		return null;
	}
	
	@GetMapping("/login")
	public User getLoginUser(@RequestParam String username, @RequestParam String password) {
		User findUser = userRepo.findByUsername(username).get();
		if(pwEncoder.matches(password, findUser.getPassword())) {
			return findUser;
		}
		return null;
	}
	
	@PostMapping("/sendEmail")
	public String sendEmailTesting(){
		String linkVerify = "<a href='http://localhost:8080/users/verify/danieloberto81@gmail.com'>Klik aku!</a>";
		this.emailUtil.sendEmail("danieloberto81@gmail.com", "Email Verification for Tokolapak Apps", linkVerify);
		return "email sent!";
	}
	
	@GetMapping("/verify/{email}")
	public String verifyByEmail(@PathVariable String email){
		System.out.println("Email sudah diverifikasi");
		User findUserEmail = userRepo.findByEmail(email).get();
		if(findUserEmail==null) {
			throw new RuntimeException("User not found");
		}
		
		findUserEmail.setVerified(1);
		userRepo.save(findUserEmail);
		return "email "+email+" berhasil diverfikasi";
	}
	
	@GetMapping
	public String halo() {
		return "HALOOO";
	}
}