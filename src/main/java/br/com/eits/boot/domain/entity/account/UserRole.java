package br.com.eits.boot.domain.entity.account;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author rodrigo@eits.com.br
 * @since 02/06/2014
 * @version 1.0
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
}