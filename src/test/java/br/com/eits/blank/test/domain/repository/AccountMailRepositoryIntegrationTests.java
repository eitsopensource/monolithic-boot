package br.com.eits.blank.test.domain.repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eits.blank.domain.entity.account.User;
import br.com.eits.blank.domain.repository.IAccountMailRepository;
import br.com.eits.blank.test.domain.AbstractIntegrationTests;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 09/05/2013
 * @version 1.0
 */
public class AccountMailRepositoryIntegrationTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Autowired
	private IAccountMailRepository accountMailRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * @throws ExecutionException 
	 * @throws InterruptedException 
     * 
     */
	@Test
	public void sendNewUserAccountTestMustPass() throws InterruptedException, ExecutionException
	{
		final User user = new User();
		user.setEmail( "eits@mailinator.com" );
		user.setName( "Suporte da eits" );

		final Future<Void> emailSent = this.accountMailRepository.sendNewUserAccount( user );
		
		Assert.assertNotNull( emailSent );
		Assert.assertNull( emailSent.get() );
	}
}
