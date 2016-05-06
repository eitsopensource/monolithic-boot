/**
 * 
 */
package br.com.eits.blank.application.restful;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eits.common.application.i18n.ResourceBundleMessageSource;

/**
 * @author rodrigo
 *
 */
@RestController
public class DefaultResource
{
	/**
	 *
	 */
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	/**
	 *
	 * @param lang
	 * @return
	 */
	@RequestMapping(value = "/bundles", method = RequestMethod.GET)
	public Properties listMessageBundles( Locale locale )
	{
		return this.messageSource.getProperties( locale );
	}
}
