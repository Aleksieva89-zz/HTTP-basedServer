package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/timestamp") 
public class TimestampController {
	
	@Autowired(required=true)
	private TimestampRepository timestampRepository;
	
	@GetMapping(path="/userLoggedIn")
	public String userLoggedIn(@ModelAttribute("userId") Integer userId, Model model) {
		Timestamp t = new Timestamp();
		t.setUserId(userId);
		Date now = new Date();
		t.setDate(now.getTime());
		timestampRepository.save(t);
		model.addAttribute("overview", getUserOverview(userId));
		return "overview";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Timestamp> getAllTimeStamps() {
		// This returns a JSON or XML with the users
		return timestampRepository.findAll();
	}
	
	private List<Timestamp> getTimeStampsForUser(Integer userId) {
		Iterator<Timestamp> timestamps = timestampRepository.findAll().iterator();
		int getTheLast = 5;
		ArrayList<Timestamp> usersTimestamps = new ArrayList<>();
		while(timestamps.hasNext()) {
			Timestamp thisTimestamp = timestamps.next();
			if(thisTimestamp.getUserId().equals(userId)) {
				usersTimestamps.add(thisTimestamp);
			}
		}
		int numebrOfLogins = usersTimestamps.size();
		if(numebrOfLogins > 5) {
			return usersTimestamps.subList(numebrOfLogins - getTheLast, numebrOfLogins);
		} else {
			return usersTimestamps;
		}
	}
	
	private UserOverview getUserOverview(Integer userId) {
		UserOverview overview = new UserOverview();
		List<Timestamp> usersLoginList = getTimeStampsForUser(userId);
		String noLoginDate = "No more logins";
		for(int i = 0; i<5; i++) {
			switch (i) {
			case 0:
				if(usersLoginList.size() == 0) {
					overview.setDateOne(noLoginDate); 
				} else {
					Timestamp t = usersLoginList.get(i);
					overview.setDateOne(t.getDateString());
				}
				break;
			case 1:
				if(usersLoginList.size() == 1) {
					overview.setDateTwo(noLoginDate);
				} else {
					Timestamp t = usersLoginList.get(i);
					overview.setDateTwo(t.getDateString());
				}
				break;
			case 2:
				if(usersLoginList.size() == 2) {
					overview.setDateThree(noLoginDate);
				} else {
					Timestamp t = usersLoginList.get(i);
					overview.setDateThree(t.getDateString());
				}
				break;
			case 3:
				if(usersLoginList.size() == 3) {
					overview.setDateFour(noLoginDate);
				} else {
					Timestamp t = usersLoginList.get(i);
					overview.setDateFour(t.getDateString());
				}
				break;
			case 4:
				if(usersLoginList.size() == 4) {
					overview.setDateFive(noLoginDate);
				} else {
					Timestamp t = usersLoginList.get(i);
					overview.setDateFive(t.getDateString());
				}
				break;
			default:
				break;
			}
		}
		return overview;
	}
}


