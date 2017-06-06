package Service;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="user_role_id")
	private Integer userroleid;
	
	@Column(name="userid")
	private Integer userid;
	
	@Column(name="role")
	private String role;	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(Integer userroleid) {
		this.userroleid = userroleid;
	}	
	
}
