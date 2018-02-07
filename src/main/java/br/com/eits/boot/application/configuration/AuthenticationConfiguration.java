/**
 * 
 */
package br.com.eits.boot.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.eits.boot.application.security.AuthenticationFailureHandler;
import br.com.eits.boot.application.security.AuthenticationSuccessHandler;

/**
 * @author rodrigo
 *
 */
@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
    private final UserDetailsService userDetailsService;

	@Autowired
	public AuthenticationConfiguration( UserDetailsService userDetailsService )
	{
		this.userDetailsService = userDetailsService;
	}
    
	/*-------------------------------------------------------------------
	 * 		 						BEANS
	 *-------------------------------------------------------------------*/	
	//---------
	// Beans
	//---------

    /**
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
    	return new BCryptPasswordEncoder();
    }
    
	/*-------------------------------------------------------------------
	 * 		 					 OVERRIDES
	 *-------------------------------------------------------------------*/
	//---------
	// Authentication Manager
	//---------
	/**
	 * 
	 */
	@Override
	public void init( AuthenticationManagerBuilder builder ) throws Exception
	{
		builder
			.userDetailsService( this.userDetailsService )
			.passwordEncoder( this.passwordEncoder() );
	}
}