package DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import Service.User;
import DAO.UserRepository;
import DAO.UserRolesRepository;

@Service("customUserDetailsService")
//UserDetailsService là một interface có duy nhất một phương thức:
public class CustomUserDetailsService implements UserDetailsService{
	private final UserRepository userRepository;
	private final UserRolesRepository userRolesRepository;
	
	@Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }
	
        
	@Override
	//Tham số truyền vào chỉ gồm có username của người dùng. Ta sẽ tìm kiếm trong CSDL, record thỏa mãn username. 
	//Nếu không tìm thấy, ta sẽ ném ra ngoại lệ UsernameNotFoundException.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username);
		if(null == user){
			throw new UsernameNotFoundException("No user present with username: "+username);
		}else{
			List<String> userRoles=userRolesRepository.findRoleByUserName(username);
			return new CustomUserDetails(user,userRoles);
		}
	}
		
}
