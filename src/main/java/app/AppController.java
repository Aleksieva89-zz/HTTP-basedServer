package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class AppController {

	@GetMapping("/login")
    public String loginForm(Model model) {
		model.addAttribute("user", new User());
        return "login";
    }
	
	@GetMapping("/register")
    public String registerForm(Model model) {
		model.addAttribute("user", new User());
        return "register";
    }
}
