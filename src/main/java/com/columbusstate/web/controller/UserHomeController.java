package com.columbusstate.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.columbusstate.pgp.PGPFileProcessor;
import com.columbusstate.web.model.Login;
import com.columbusstate.web.model.MailDetails;
import com.columbusstate.web.model.MailList;
import com.columbusstate.web.util.FileUtils;

@Controller
public class UserHomeController {

	@RequestMapping(value = "/viewList", method = RequestMethod.POST)
	public ModelAndView viewMails(@ModelAttribute("login") Login login) {

		if(login.getEmail()==null || login.getEmail().length()==0){
			ModelAndView model = new ModelAndView("loginFail");
			return model;
			
		}else if(checkIfUserExist(login.getEmail())){
			List<String> mails = getAllMails(login.getEmail());
			MailList mailList = new MailList();
			mailList.setMails(mails);
			mailList.setUserEmail(login.getEmail());
			

			ModelAndView model = new ModelAndView("userHome");
			model.addObject("mailList", mailList);
			return model;
		}else{
			
			ModelAndView model = new ModelAndView("loginFail");
			return model;
		}
		
	}
	
	
	@RequestMapping(value = "/viewList", method = RequestMethod.GET)
	public ModelAndView viewMailsGet(@ModelAttribute("login") Login login) {
		
		if(login.getEmail()==null || login.getEmail().length()==0){
			ModelAndView model = new ModelAndView("loginFail");
			return model;
			
		}else if(checkIfUserExist(login.getEmail())){

		List<String> mails = getAllMails(login.getEmail());
		MailList mailList = new MailList();
		mailList.setMails(mails);
		mailList.setUserEmail(login.getEmail());

		ModelAndView model = new ModelAndView("userHome");
		model.addObject("mailList", mailList);
		return model;
		}else{
			
			ModelAndView model = new ModelAndView("loginFail");
			return model;
		}
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public String sendMailView(ModelMap model) {

		model.addAttribute("mailDetails", new MailDetails());
		return "sendMail";

	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public ModelAndView sendMail(@ModelAttribute("mailDetails") MailDetails mailDetails, RedirectAttributes redir) {

		System.out.println("mailDetails --> " + mailDetails);
		
		// check if to user exist
		boolean userExists = checkIfUserExist(mailDetails.getTo());

		if (!userExists) {

			System.out.println("User does not exist");
			// give error message to user
			
		} else {

			String plainTextFileName = "C:\\Users\\Public\\Documents\\app-data/" + mailDetails.getTo() + "/" +
					mailDetails.getSubject() + Long.toString(System.currentTimeMillis())+  ".txt";
			
			FileUtils fileUtils = new FileUtils();
			fileUtils.writeFileContent(plainTextFileName, mailDetails.toString());
			System.out.println(mailDetails.toString());
			PGPFileProcessor p = new PGPFileProcessor();
			p.setInputFileName(plainTextFileName);
			p.setOutputFileName(StringUtils.replace(plainTextFileName, ".txt", ".pgp"));
			p.setPublicKeyFileName("C:\\Users\\Public\\Documents\\app-data/public/" + mailDetails.getTo().split("@")[0] + ".pkr");
			try {
				boolean isEncrypted = p.encrypt();
				if(isEncrypted)fileUtils.deleteFile(plainTextFileName);
				ModelAndView modelAndView = new ModelAndView();
				modelAndView.setViewName("redirect:viewList");
				Login login = new Login();
				login.setEmail(mailDetails.getFrom());
				redir.addFlashAttribute("login", login);
				return modelAndView;
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:viewFailList");
		return modelAndView;

		

	}

	@RequestMapping(value = "/viewDetails", method = RequestMethod.GET)
	public ModelAndView viewMailDetails(@RequestParam(value = "mailId", required = true) String mailId,
			@RequestParam(value = "passphrase", required = true) String passphrase,
			@RequestParam(value = "userEmail", required = true) String userEmail) {

		System.out.println("Mailid --> " + mailId);
		System.out.println("passphrase --> " + passphrase);
		System.out.println("userEmail --> " + userEmail);

		FileUtils fileUtils = new FileUtils();
		
		PGPFileProcessor p = new PGPFileProcessor();
		String encryptMailFileName = "C:\\Users\\Public\\Documents\\app-data/" + userEmail + "/" + mailId;
		
		p.setInputFileName(encryptMailFileName);
		p.setOutputFileName(StringUtils.replace(encryptMailFileName, ".pgp", ".txt"));
		p.setPassphrase(passphrase);
		p.setSecretKeyFileName("C:\\Users\\Public\\Documents\\app-data/" + userEmail +"/"+userEmail.split("@")[0] + ".skr");
		String mailDetailString = null;
		try {
			System.out.println(p.decrypt());
			mailDetailString = fileUtils.readFile(StringUtils.replace(encryptMailFileName, ".pgp", ".txt"));
			File file =new File(StringUtils.replace(encryptMailFileName, ".pgp", ".txt"));
			file.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MailDetails mailDetails = prepareMailDetailObjectFromStringData(mailDetailString);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("viewDetails");
		

		return new ModelAndView("viewDetails","mailDetails",mailDetails);
		

	}

	private MailDetails prepareMailDetailObjectFromStringData(String mailDetailString) {
		System.out.println("MailDetailString--> " + mailDetailString);
		
		String[] tokens = StringUtils.split(mailDetailString, "|");
		for (int i = 0; i < tokens.length; i++) {
			System.out.println("Token [" + i + "] = " + tokens[i]);
		}
		MailDetails mailDetails = new MailDetails();
	
		System.out.println("To --> " +StringUtils.substringAfter(tokens[0], "to="));
		mailDetails.setTo(StringUtils.substringAfter(tokens[0], "to="));
		System.out.println("From --> " +StringUtils.substringAfter(tokens[1], "from="));
		mailDetails.setFrom(StringUtils.substringAfter(tokens[1], "from="));
		System.out.println("Subject --> " +StringUtils.substringAfter(tokens[2], "subject="));
		mailDetails.setSubject(StringUtils.substringAfter(tokens[2], "subject="));
		System.out.println("Content --> " +StringUtils.substringAfter(tokens[3], "content="));
		mailDetails.setContent(StringUtils.substringAfter(tokens[3], "content="));
		return mailDetails;
	}

	private List<String> getAllMails(String email) {

		List<String> mailList = new ArrayList<String>();

		File folder = new File("C:\\Users\\Public\\Documents\\app-data/" + email);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				if (listOfFiles[i].getName().contains(".pgp"))
				mailList.add(listOfFiles[i].getName());
			}
		}
		return mailList;
	}

	private boolean checkIfUserExist(String email) {

		File folder = new File("C:\\Users\\Public\\Documents\\app-data/public");
		
		String userPublicKeyFileName = StringUtils.split(email,"@")[0];
		
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				if (listOfFiles[i].getName().contains(userPublicKeyFileName))
					return true;
			}
		}
		return false;
	}
}
