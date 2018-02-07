/**
 * 
 */
package br.com.eits.boot.application.restful;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.eits.common.application.i18n.ResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 */
@RestController
public class BundleResource
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 *
	 */
	private final ResourceBundleMessageSource messageSource;

	@Autowired
	public BundleResource( ResourceBundleMessageSource messageSource )
	{
		this.messageSource = messageSource;
	}

	/*-------------------------------------------------------------------
	 * 		 					RESOURCES
	 *-------------------------------------------------------------------*/

	/**
	 *
	 */
	@GetMapping("/bundles")
	public Map<String, String> listMessageBundles( Locale locale )
	{
		return this.messageSource.getProperties( locale ).entrySet().stream().map( entry ->
		{
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			return new HashMap.SimpleImmutableEntry<>(
					key,
					value.replaceAll( "\\{(.+?)\\}", "{$1}" )
			);
		} ).collect( Collectors.toMap( AbstractMap.SimpleImmutableEntry::getKey, AbstractMap.SimpleImmutableEntry::getValue ) );
	}
}
