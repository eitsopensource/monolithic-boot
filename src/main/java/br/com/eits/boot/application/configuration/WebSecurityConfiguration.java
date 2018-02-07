package br.com.eits.boot.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import br.com.eits.boot.application.security.AuthenticationFailureHandler;
import br.com.eits.boot.application.security.AuthenticationSuccessHandler;

/**
 * 
 * @author rodrigo
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private final AuthenticationFailureHandler authenticationFailureHandler;
	/**
	 * 
	 */
	private final AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public WebSecurityConfiguration( AuthenticationFailureHandler authenticationFailureHandler, AuthenticationSuccessHandler authenticationSuccessHandler )
	{
		this.authenticationFailureHandler = authenticationFailureHandler;
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

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
	
	/**
	 * Override this method to configure {@link WebSecurity}. For example, if you wish to
	 * ignore certain requests.
	 */
	@Override
	public void configure( WebSecurity web ) throws Exception 
	{
		web.ignoring()
			.antMatchers( "/**/favicon.ico", "/static/**", "/modules/**", "/broker/**/*.js", "/bundles/**", "/webjars/**");
	}
}