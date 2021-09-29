package com.cognizant.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.cognizant.authorization.service.UserService;

/**
 * This class is used for the security configuration. It extends the class
 * WebSecurityConfigurerAdapter. It is responsible for the authentication and
 * authorization based on the user credentials.
 */
@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

	private UserService customer;
	
	@Autowired
	public WebConfig(UserService customer) {
		this.customer = customer;
	}

	/**
	 * Adding the user details from database to be used for authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(customer);
	}

	/**
	 * This method is used for the configuration of authorization part.And all the
	 * requests will be authenticated and then permitted.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/login", "/h2-console/**", "/configuration/ui", "/configuration/security",
				"/webjars/**", "/manage/health/**");
	}
}
