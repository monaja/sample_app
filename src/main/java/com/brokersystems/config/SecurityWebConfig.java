package com.brokersystems.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.brokersystems.security.*;

@Configuration
@Import({ AppWebMVCConfig.class })
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWebConfig {

	@Configuration
	public static class FormSecurityConfigurationAdapter extends
			WebSecurityConfigurerAdapter {
		
		@Autowired
		AuthenticationSuccessHandler authenticationSuccessHandler;
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth,CustomAuthenticationProvider customAuthenticationProvider) throws Exception {
			auth.authenticationProvider(customAuthenticationProvider);
		}
		
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/login*");
			web.ignoring().antMatchers("/css/**");
			web.ignoring().antMatchers("/js/**");
			web.ignoring().antMatchers("/images/**");
			web.ignoring().antMatchers("/libs/**");
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			.csrf()
				.disable()
			.headers()
                .disable()
            .authorizeRequests()
				.antMatchers("/protected/**").authenticated()
				.anyRequest().authenticated()
			.and()
            .formLogin()
            	.loginProcessingUrl("/j_spring_security_check")
            	.usernameParameter("j_username")
            	.passwordParameter("j_password")
                .loginPage("/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)
				.failureUrl("/login?error=true")
			.and()
			.logout()
				.logoutSuccessUrl("/login")
				.logoutUrl("/logout");
			
			
			
			

		}
	}

}
