package user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Timestamp {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	private Long date;
    
//    @JoinColumn(name = "id")
//    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private Integer user_id;
    
    public Long getDate() {
    	return date;
    }
    
    public void setDate(Long date) {
    	this.date = date;
    }
    
    public int getId() {
		return id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

}