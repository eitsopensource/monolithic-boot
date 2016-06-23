package br.com.eits.boot.application.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eits.boot.domain.entity.account.User;
import br.com.eits.boot.domain.repository.account.IUserRepository;

/**
 * 
 * @author rodrigo@eits.com.br
 */
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger( AuthenticationSuccessHandler.class.getName() );
	
	/*-------------------------------------------------------------------
	 * 		 					 ATTRIBUTES
	 *-------------------------------------------------------------------*/
	//Repositories
	/**
	 * 
	 */
	@Autowired
	private IUserRepository userRepository;

	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException
	{
		try
		{
			final User user = this.userRepository.findOne( ContextHolder.getAuthenticatedUser().getId() );
			user.setLastLogin( Calendar.getInstance() );
			this.userRepository.save( user );
			
			//add the user in the response
			user.setPassword( null );
			response.getWriter().write( new ObjectMapper().writeValueAsString(user) );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			LOG.severe( "Ocorreu um problema ao atualizar o ultimo login do usuário" );
		}
	}
}
