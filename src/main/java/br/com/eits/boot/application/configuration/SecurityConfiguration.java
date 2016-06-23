package br.com.eits.boot.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.eits.boot.application.security.AuthenticationFailureHandler;
import br.com.eits.boot.application.security.AuthenticationSuccessHandler;

/**
 * 
 * @author rodrigo
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	/**
	 * 
	 */
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	/*-------------------------------------------------------------------
	 * 		 					 OVERRIDES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	protected void configure( HttpSecurity httpSecurity ) throws Exception
	{
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
		
		httpSecurity
			.authorizeRequests()
				.anyRequest()
					.authenticated()
					.and()
						.formLogin()
							.usernameParameter( "email" )
							.passwordParameter( "password" )
							.loginPage( "/authentication" )
							.loginProcessingUrl( "/authenticate" )
							.failureHandler( this.authenticationFailureHandler )
							.successHandler( this.authenticationSuccessHandler )
						.permitAll()
					.and()
						.logout()
							.logoutUrl( "/logout" );
	}
}