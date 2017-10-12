package com.columbusstate.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.columbusstate.web.model.Login;
import com.columbusstate.web.model.UserRegistration;

@Controller
public class MainContoller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(ModelMap model) {
		return "home";

	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model) {
		return "home";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {

		model.addAttribute("userRegistration", new UserRegistration());
		return "registration";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		model.addAttribute("login", new Login());
		return "login";

	}
	
	@RequestMapping(value = "/viewFailList", method = RequestMethod.GET)
	public String failList(Model model) {
		
		
		return "viewFailList";

	}
	
	@RequestMapping(value = "/loginFailt", method = RequestMethod.GET)
	public String loginFail(Model model) {
		
		
		return "loginFail";

	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		
		
		return "logout";

	}


}