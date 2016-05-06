package br.com.eits.blank.domain.entity;

import java.io.Serializable;

import org.hibernate.envers.RevisionType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import br.com.eits.blank.application.security.ContextHolder;
import br.com.eits.blank.domain.entity.account.User;

/**
 * @author rodrigo@eits.com.br
 * @since 06/12/2012
 * @version 1.0
 * @category Entity
 */
public class EntityTrackingRevisionListener implements org.hibernate.envers.EntityTrackingRevisionListener
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public void newRevision( Object revisionEntity )
	{
		try
		{
			final User user = ContextHolder.getAuthenticatedUser();
			( ( Revision<?, ?> ) revisionEntity ).setUserId( user.getId() );
		}
		catch ( AuthenticationCredentialsNotFoundException e )
		{
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.envers.EntityTrackingRevisionListener#entityChanged(java.lang.Class, java.lang.String, java.io.Serializable, org.hibernate.envers.RevisionType, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void entityChanged( Class entityClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionEntity )
	{
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
}