package br.com.eits.boot.domain.entity.account;

import java.util.HashSet;
import java.util.Set;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 */
@DataTransferObject(type = "enum")
public enum UserRole implements GrantedAuthority
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	ADMINISTRATOR, // 0
	MANAGER, // 1
	USER; // 2

	public static final String ADMINISTRATOR_VALUE 	= "ADMINISTRATOR";
	public static final String MANAGER_VALUE 		= "MANAGER";
	public static final String USER_VALUE 			= "USER";
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority()
	{
		return this.name();
	}

	/**
	 * @return
	 */
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		authorities.add( this );

		if ( this.equals( UserRole.ADMINISTRATOR ) )
		{
			authorities.add( UserRole.MANAGER );
		}

		authorities.add( UserRole.USER );

		return authorities;
	}
}