package br.com.eits.boot.domain.entity.account;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @since 02/06/2014
 * @version 1.0
 * @category
 */
@Data
@Entity
@Audited
@Table(name = "\"user\"")
@EqualsAndHashCode(callSuper=true)
@DataTransferObject(javascript = "User")
public class User extends AbstractEntity implements Serializable, UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4052986759552589018L;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	// Basic
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String name;
	/**
	 * 
	 */
	@Email
	@NotNull
	@Column(nullable = false, length = 144, unique = true)
	private String email;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean disabled;
	/**
	 * 
	 */
	@NotBlank
	@Length(min = 8)
	@Column(nullable = false, length = 100)
	private String password;
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;
	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar lastLogin;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public User()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public User( Long id )
	{
		super( id );
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param enabled
	 * @param role
	 * @param password
	 */
	public User( Long id, String name, String email, Boolean disabled, UserRole role, String password )
	{
		super( id );
		this.email = email;
		this.name = name;
		this.disabled = disabled;
		this.password = password;
		this.role = role;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( this.role == null )
		{
			return null;
		}
		
		authorities.addAll( this.role.getAuthorities() );

		return authorities;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isEnabled()
	{
		return !this.disabled;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	@Transient
	public String getPassword()
	{
		return this.password;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	@Transient
	public String getUsername()
	{
		return this.email;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
}