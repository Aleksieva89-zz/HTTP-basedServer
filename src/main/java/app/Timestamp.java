package app;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Timestamp {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private Long date;
    
    private Integer user_id;
    
    public Long getDate() {
    	return date;
    }
    
    public void setDate(Long date) {
    	this.date = date;
    }
    
    public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getDateString() {
		return new Date(date).toString();
	}
}