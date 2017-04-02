package user;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import user.User;
import user.UserRepository;

@Controller
@RequestMapping(path="/users") 
public class UsersController {
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping(path="/register") // Map ONLY GET Requests
	public @ResponseBody String addNewUser(@RequestParam String user_name
			, @RequestParam String password) {
		if(getUserIfExists(user_name, password) != null) {
			return "User alredy exists";
		} else {
			User u = new User();
			u.setUserName(user_name);
			u.setPassword(password);
			userRepository.save(u);
			return "Saved";
		}
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@GetMapping(path="/login")
	public @ResponseBody Boolean loginUser(@RequestParam String user_name
			, @RequestParam String password) {
		User u = getUserIfExists(user_name, password);
		if(u != null) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/timestamp/userLoggedIn");
			mv.addObject("user", u);
			return modelAndView;
		} else {
			return false;
		}
 	}
	
	private User getUserIfExists(String user_name, String password) {
		User u = null;
		Iterator<User> users = userRepository.findAll().iterator();
		while(users.hasNext()) {
			User thisUser = users.next();
			if(thisUser.getUserName().equals(user_name) &&
					thisUser.getPassword().equals(password)) {
				u = thisUser;
			}
		}
		return u;
	}
}

