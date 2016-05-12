package br.com.eits.boot.application.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.eits.boot.domain.entity.account.User;

/**
 *
 * @author rodrigo@eits.com.br
 * @since 24/07/2013
 * @version 1.0
 * @category
 */
public class ContextHolder
{
	/*-------------------------------------------------------------------
	 * 		 						BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 *
	 * @return
	 */
	public static User getAuthenticatedUser()
	{
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if ( authentication != null && authentication.getPrincipal() instanceof User )
		{
			return ( User ) authentication.getPrincipal();
		}

		throw new AuthenticationCredentialsNotFoundException( "There is no user authenticated." );
	}
}
