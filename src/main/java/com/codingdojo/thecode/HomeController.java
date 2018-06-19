package com.codingdojo.thecode;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index(HttpSession session) {
		session.setAttribute("guess", "wrong");
		return "index.jsp";
	}
	
	
	@RequestMapping(value="/guess", method=RequestMethod.POST) 
	public String guess(HttpSession session, @RequestParam("guess1") String guess1, RedirectAttributes redirectAttributes) {
		if(guess1.equals("bushido")) {
			session.setAttribute("guess", "correct");
			return "redirect:/code";
		} else {
			return "redirect:/createError";
		}
	}
	
	@RequestMapping("/code")
	public String isItCorrect(HttpSession session, RedirectAttributes redirectAttributes) {
		if(session.getAttribute("guess") == "correct") {
			return "code.jsp";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/createError")
	public String flashMessages(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "Wrong code! You must train harder!");
		return "redirect:/";
	}

}
