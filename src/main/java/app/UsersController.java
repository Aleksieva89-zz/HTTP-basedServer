package app;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/users") 
public class UsersController {
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping(path="/register")
	public @ResponseBody String addNewUser(@ModelAttribute User user) {
		if(getUserIfExists(user.getUserName(), user.getPassword()) != null) {
			return "User alredy exists";
		} else {
			userRepository.save(user);
			return "Saved";
		}
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping(path="/login")
	public @ResponseBody Boolean loginUser(@ModelAttribute User user) {
		User u = getUserIfExists(user.getUserName(), user.getPassword());
		if(u != null) {
			//add timeStamp
			return true;
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

