package com.columbusstate.web.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.columbusstate.pgp.RSAGen;
import com.columbusstate.web.model.UserRegistration;

@Controller
public class RegistrationController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userRegistration") UserRegistration userRegistration) {

		System.out.println(userRegistration);
		// Create App data directory
		createAppDataDirectory();

		// Create public folder if it does not exist
		createPublicKeyDirectory();
		// create user specific folder - use users email for this

		boolean created=createUserDataDirectory(userRegistration.getEmail());

		// create public/private key pair
		if(created){
			RSAGen rsaGen = new RSAGen();
			try {
				rsaGen.generateKeyPair(userRegistration.getEmail(), userRegistration.getPassphrase(), "C:\\Users\\Public\\Documents\\app-data/public",
						"C:\\Users\\Public\\Documents\\app-data/" + userRegistration.getEmail());
			} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView();
			model.setViewName("success");
			return model;
		}else{
			ModelAndView model = new ModelAndView();
			model.setViewName("fail");
			return model;
		}

		

	}

	private void createPublicKeyDirectory() {

		File file = new File("C:\\Users\\Public\\Documents\\app-data/public");
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Public key Directory is created!");
			} else {
				System.out.println("Failed to create user data directory!");
			}
		}

	}

	private void createAppDataDirectory() {

		File file = new File("C:\\Users\\Public\\Documents\\app-data");
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("App Data Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}

	}

	private boolean createUserDataDirectory(String email) {

		File file = new File("C:\\Users\\Public\\Documents\\app-data/" + email);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("User Data Directory is created!");
				return true;
			} else {
				System.out.println("Failed to create user data directory!");
				return false;
			}
		}else{
			return false;
		}

	}

}
