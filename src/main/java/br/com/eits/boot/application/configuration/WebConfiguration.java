/**
 * 
 */
package br.com.eits.boot.application.configuration;

import java.util.Locale;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.spring.DwrAnnotationPostProcessor;
import org.directwebremoting.spring.DwrClassPathBeanDefinitionScanner;
import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import br.com.eits.boot.application.configuration.settings.DWRSettings;

/**
 * @author rodrigo
 *
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter
{
	/*-------------------------------------------------------------------
	 * 		 						BEANS
	 *-------------------------------------------------------------------*/
	//---------
	// Locale
	//---------
	/**
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver()
	{
		final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale( new Locale( "pt", "BR" ) );
		localeResolver.setCookieMaxAge( 604800 ); // 1 month
		return localeResolver;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName( "lang" );
		return localeChangeInterceptor;
	}
	
	//---------
	// DWR
	//---------
	/**
	 * Process all spring beans with @RemoteProxy
	 * @return
	 */
	@Bean
	public DwrAnnotationPostProcessor dwrAnnotationPostProcessor( ApplicationContext applicationContext )
	{
		final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
		final ClassPathBeanDefinitionScanner scanner = new DwrClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(DataTransferObject.class));
        scanner.scan("br.com.eits.boot.domain");
        
		return new DwrAnnotationPostProcessor();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dwrSpringServletRegistration( DWRSettings dwrSettings )
	{
		final ServletRegistrationBean registration = new ServletRegistrationBean( new DwrSpringServlet(), "/broker/*" );
		registration.addInitParameter( "debug", String.valueOf(dwrSettings.isDebug()) );
		registration.addInitParameter( "scriptCompressed", String.valueOf(dwrSettings.isScriptCompressed()) );
		registration.setName( "dwrSpringServlet" );
		return registration;
	}
	
	/*-------------------------------------------------------------------
	 * 		 						OVERRIDES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public void addInterceptors( InterceptorRegistry registry )
	{
		registry.addInterceptor( this.localeChangeInterceptor() );
	}
}