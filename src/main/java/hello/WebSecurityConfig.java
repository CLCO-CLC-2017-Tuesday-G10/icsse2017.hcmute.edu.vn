package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import DAO.CustomUserDetailsService;
//@Configuration xác định lớp WebSecurityConfig của ta là một lớp dùng để cấu hình.
@Configuration
//@EnableWebSecurity sẽ kích hoạt việc tích hợp Spring Security với Spring MVC.
@EnableWebSecurity	
//Spring Data @EnableJpaRepositories : Annocation enable JPA repositories.
//Nó sẽ scan xác định packages cho Spring Data repositories.
@EnableJpaRepositories(basePackages = "DAO")
@EntityScan(basePackages = "DAO")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired 
//Trong lớp WebSecurityConfig, ta cần phải gọi đến interface UserDetailsService để cấu hình. 
//Do đó ta sẽ inject UserDetailsService.
 private UserDetailsService userDetailsService;
 
 @Autowired
 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {    
	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
 } 
 
 //Trong Spring Security, việc mã hóa mật khẩu sẽ do interface PasswordEncoder đảm nhận. 
 //PasswordEncoder có implementation là BCryptPasswordEncoder sẽ giúp chúng ta mã hóa mật khẩu bằng thuật toán BCrypt. 
 //Nhưng để sử dụng được PasswordEncoder, ta phải cấu hình để PasswordEncoder trở thành một Bean.
 @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
     return new BCryptPasswordEncoder();
    }
 
 @Override
//Trong phương thức configure(HttpSecurity http), ta sẽ cấu hình các chi tiết về bảo mật:
 protected void configure(HttpSecurity http) throws Exception {					
   http.authorizeRequests()
 //antMatchers(): khai báo đường dẫn của request
 //hasRole(ROLE_ADMIN): chỉ cho phép các user có GrantedAuthority là ROLE_ADMIN mới được phép truy cập 
  .antMatchers("/edit").access("hasRole('ROLE_ADMIN')")							
  .antMatchers("/edit-webpage-{pageID}").access("hasRole('ROLE_ADMIN')")	
 //permitAll(): cho phép tất cả các user đều được phép truy cập.
  .anyRequest().permitAll()														
  .and()
 //loginPage(): đường dẫn tới trang chứa form đăng nhập
    .formLogin().loginPage("/login")		
 //usernameParameter() và passwordParameter(): gía trị của thuộc tính name của 2 input nhập username và password
    .usernameParameter("username").passwordParameter("password")
 //defaultSuccessUrl(): đường dẫn tới trang đăng nhập thành công
    .defaultSuccessUrl("/User")	
  .and()
    .logout().logoutSuccessUrl("/login?logout") 								//
   .and()
 //Khi người dùng không đủ quyền để truy cập vào một trang, ta sẽ redirect người dùng về một trang 403
   .exceptionHandling().accessDeniedPage("/403")
  .and()
    .csrf();
 }
}