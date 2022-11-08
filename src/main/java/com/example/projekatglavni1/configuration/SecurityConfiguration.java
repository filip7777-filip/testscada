package com.example.projekatglavni1.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.projekatglavni1.constants.SecurityConstants;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration{
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	private UserDetailsService userDetailsService;
//	
//	
//	
//	public SecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) {
//		super();
//		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//		this.userDetailsService = userDetailsService;
//	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable().cors().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests().antMatchers("/user/register").permitAll()
			.antMatchers("/user/login").permitAll()
			.antMatchers("/user/all").permitAll()
			.antMatchers("/center/all").permitAll()
			.antMatchers("/center/find/{id}").permitAll()
			.antMatchers("/centertype/all").permitAll()
			.antMatchers("/centertype/add").permitAll()
			.antMatchers("/responseperson/all").permitAll()
			.antMatchers("/responseperson/findemail/{email}").permitAll()
			.antMatchers("/responseperson/findnumber/{number}").permitAll()
			.antMatchers("/responseperson/add").permitAll()
			.antMatchers("/lum/all").permitAll()
			.antMatchers("/lum/add").permitAll()
			.antMatchers("/eeotype/all").permitAll()
			.antMatchers("/eeotype/add").permitAll()
			.antMatchers("/rtu/all").permitAll()
			.antMatchers("/rtu/add").permitAll()
			.anyRequest().authenticated()
			;
		return http.build();
		

		
		
		
		//			.authorizeHttpRequests((authz) -> authz.authorizeRequests().antMatchers("/user/register").permitAll()
//					.anyRequest().authenticated()
//					)
//			.httpBasic(withDefaults());
//		return http.build();
			
		
		
	}
	
}
