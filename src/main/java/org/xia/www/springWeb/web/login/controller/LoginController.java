package org.xia.www.springWeb.web.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private Logger log = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private  HttpServletRequest request;
	
	@RequestMapping
	public String login(String name,String password,String ckcode,ModelMap model){
		log.info("name:"+name+";password:"+password+";ckcode:"+ckcode);
		request.getSession().setAttribute("sessionName", name);
		return "main";
	}
	@RequestMapping
	public String logout(){
		log.info(request.getSession().getAttribute("sessionName")+",退出系统");
		return "../../login";
	}
}
