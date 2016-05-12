package br.com.eits.boot.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.eits.boot.Application;
import br.com.eits.common.domain.entity.IEntity;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 06/12/2012
 * @version 1.0
 * @category Entity
 */
@Entity
@Table(schema=Application.AUDIT_SCHEMA)
@org.hibernate.envers.RevisionEntity(EntityTrackingRevisionListener.class)
public class Revision<T extends IEntity<ID>, ID extends Serializable> implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4193623660483050410L;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * id da {@link Revision}
	 */
	@Id
	@RevisionNumber
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * data da {@link Revision}
	 */
	@RevisionTimestamp
	private long timestamp;
	
	/**
	 * id do usuário da {@link Revision}
	 */
	private Long userId;

	//---Metadata
	/**
	 * entidade da {@link Revision}
	 */
	@Transient
	private T entity;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param id
	 */
	public Revision()
	{

		this.entity = null;
	}

	/**
	 * 
	 *
	 * @param entity
	 */
	public Revision( T entity )
	{

		this.entity = entity;
	}

	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( entity == null ) ? 0 : entity.hashCode() );
		result = prime * result + ( int ) ( id ^ ( id >>> 32 ) );
		result = prime * result + ( int ) ( timestamp ^ ( timestamp >>> 32 ) );
		result = prime * result + ( ( userId == null ) ? 0 : userId.hashCode() );
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		Revision other = ( Revision ) obj;
		if ( entity == null )
		{
			if ( other.entity != null ) return false;
		}
		else if ( !entity.equals( other.entity ) ) return false;
		if ( id != other.id ) return false;
		if ( timestamp != other.timestamp ) return false;
		if ( userId == null )
		{
			if ( other.userId != null ) return false;
		}
		else if ( !userId.equals( other.userId ) ) return false;
		return true;
	}

	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 *
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}
	/**
	 *
	 * @param id the id to set
	 */
	public void setId( long id )
	{
		this.id = id;
	}

	/**
	 *
	 * @return the timestamp
	 */
	public long getTimestamp()
	{
		return timestamp;
	}
	/**
	 *
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp( long timestamp )
	{
		this.timestamp = timestamp;
	}

	/**
	 *
	 * @return the userId
	 */
	public long getUserId()
	{
		return userId;
	}
	/**
	 *
	 * @param userId the userId to set
	 */
	public void setUserId( long userId )
	{
		this.userId = userId;
	}

	//---Metadata
	/**
	 *
	 * @return the entity
	 */
	public T getEntity()
	{
		return entity;
	}
	/**
	 *
	 * @param entity the entity to set
	 */
	public void setEntity( T entity )
	{
		this.entity = entity;
	}
}