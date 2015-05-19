package org.xia.www.springWeb.web.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping
	public String login(String name,String password,String ckcode,ModelMap model){
		log.info("name:"+name+";password:"+password+";ckcode:"+ckcode);
		return "main";
	}
}
