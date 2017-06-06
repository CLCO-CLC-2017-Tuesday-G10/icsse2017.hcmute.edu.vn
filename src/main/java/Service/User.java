package Service;

import java.io.Serializable;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userid;

	

	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Column(name = "username")
    private String userName;   

	@Column(name = "password")
    private String password;   

	@Column(name = "email")
    private String email;
    
	@Column(name ="enabled")
	private int enabled;
	
	public User(){
		
	}
	
	public User(User user) {
	       
	        this.userName = user.userName;
	        this.email = user.email;       
	        this.password = user.password;
	        this.enabled=user.enabled;        
	}
	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}	


	
	public String getPassword() {			//getPassword(): trả về password đã dùng trong qúa trình xác thực
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	//getUsername(): trả về username đã dùng trong qúa trình xác thực
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
  
}