package app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/timestamp") 
public class TimestampController {
	
	@Autowired(required=true)
	private TimestampRepository timestampRepository;
	
	@GetMapping(path="/userLoggedIn")
	public Boolean userLoggedIn(User user) {
		Timestamp t = new Timestamp();
		t.setUserId(user.getId());
		Date now = new Date();
		t.setDate(now.getTime());
		timestampRepository.save(t);
		return true;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Timestamp> getAllUsers() {
		// This returns a JSON or XML with the users
		return timestampRepository.findAll();
	}
}


