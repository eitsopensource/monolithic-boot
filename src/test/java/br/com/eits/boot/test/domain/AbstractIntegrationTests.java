package br.com.eits.boot.test.domain;

import java.util.Locale;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.eits.boot.Application;
import br.com.eits.boot.test.TestApplication;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, Application.class})
public abstract class AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
     *                           BEHAVIORS
     *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Before
	public void beforeTest()
	{
		Locale.setDefault( new Locale("pt", "BR") );
	}
}
