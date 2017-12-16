package com.prestigePoint.AWT.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prestigePoint.AWT.Model.LoginModel;
import com.prestigePoint.AWT.Model.RegistrationModel;
import com.prestigePoint.AWT.service.LoginService;

@Controller
public class LoginController {
	
@Autowired	
private LoginService loginservice;

//nikita
	@RequestMapping("/login")
			public String showLogin(Map<String, Object> map) {
		System.out.println("LoginController in");
				LoginModel lm=new LoginModel();
				map.put("list", lm);
				return "login";
			}
	
	@RequestMapping(value="/LoginSubmit", method=RequestMethod.POST)
			public String submitLogin(@ModelAttribute("list") LoginModel lm ,ModelMap model,  HttpServletRequest request, HttpServletResponse response)
			{
			
		lm= loginservice.usersignIn(lm);
		if (lm == null) {
			model.addAttribute("invalid", "Invalid user name and password");
			return "redirect:/login.do";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("lm", lm);
			return "redirect:/welcome.do";
		}
		
				/*lm=loginservice.usersignIn(lm);
				if (lm == null)
					
				{
					
					return "welcome";
				}
				else {
					return "login";
				}*/
			}
	

		
}