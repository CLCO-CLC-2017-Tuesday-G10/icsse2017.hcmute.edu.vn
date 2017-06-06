package DAO;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import Service.User;

public class CustomUserDetails extends Service.User implements UserDetails {	
	
	private static final long serialVersionUID = 1L;
	private List<String> userRoles;
	
	//
	public CustomUserDetails(User user,List<String> userRoles){
		super(user);
		this.userRoles=userRoles;
	}
	
	
	@Override
	//getAuthorities(): trả về danh sách các quyền của người dùng
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roles=StringUtils.collectionToCommaDelimitedString(userRoles);			
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	//isAccountNonExpired(): trả về true nếu tài khoản của người dùng chưa hết hạn
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	//isAccountNonLocked(): trả về true nếu người dùng chưa bị khóa
	public boolean isAccountNonLocked() {		
		return true;
	}
	
	@Override
	//isCredentialsNonExpired(): trả về true nếu chứng thực (mật khẩu) của người dùng chưa hết hạn
	public boolean isCredentialsNonExpired() {	
		return true;
	}
	@Override
	//isEnabled(): trả về true nếu người dùng đã được kích hoạt
	public boolean isEnabled() {				
		return true;
	}


	@Override
	//getUsername(): trả về username đã dùng trong qúa trình xác thực
	public String getUsername() {
		return super.getUserName();
	}


}
