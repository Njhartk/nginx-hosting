package edu.ilstu.lai.RegistrationDemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
	@Autowired
	private JavaMailSender mailSender;


	@GetMapping("/contact")
	public String showContactForm() {
	return"contact";
	}
	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request) {
	//get variable values from the form on the contact page
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String content = request.getParameter("message");

	//do something here to send the email
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom(email);
	message.setTo("it353lai@gmail.com");
	String mailsubject = name + " "+subject;


	message.setSubject(mailsubject);
	message.setText(content);
	mailSender.send(message);


	return "feedback";
	}

}
