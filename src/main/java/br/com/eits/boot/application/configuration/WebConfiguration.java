/**
 * 
 */
package br.com.eits.boot.application.configuration;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.spring.DwrClassPathBeanDefinitionScanner;
import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import br.com.eits.boot.application.configuration.settings.DWRSettings;
import br.com.eits.common.application.dwr.DwrAnnotationPostProcessor;

/**
 * @author rodrigo
 *
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer
{
	/*-------------------------------------------------------------------
	 * 		 						BEANS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@Bean
    public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() 
	{
        final FilterRegistrationBean<ForwardedHeaderFilter> filterRegBean = new FilterRegistrationBean<>();
        filterRegBean.setFilter(new ForwardedHeaderFilter());
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegBean;
    }
	
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
	 */
	@Bean
	public static DwrAnnotationPostProcessor dwrAnnotationPostProcessor( ApplicationContext applicationContext )
	{
		final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
		final ClassPathBeanDefinitionScanner scanner = new DwrClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(DataTransferObject.class));
        scanner.scan("br.com.eits.boot.domain.entity.**");
        
		return new DwrAnnotationPostProcessor();
	}

	/**
	 * 
	 */
	@Bean
	public ServletRegistrationBean<DwrSpringServlet> dwrSpringServletRegistration( DWRSettings dwrSettings )
	{
		final ServletRegistrationBean<DwrSpringServlet> registration = new ServletRegistrationBean<>( new DwrSpringServlet(), "/broker/*" );
		registration.setName( "dwrSpringServlet" );
		registration.addInitParameter( "debug", String.valueOf(dwrSettings.isDebug()) );
		registration.addInitParameter( "scriptCompressed", String.valueOf(dwrSettings.isScriptCompressed()) );
		return registration;
	}

	/**
	 *
	 */
	@Bean
	public FilterRegistrationBean<DwrLocaleFilter> dwrLocaleRegistrationBean( LocaleResolver localeResolver )
	{
		FilterRegistrationBean<DwrLocaleFilter> filterBean = new FilterRegistrationBean<>( new DwrLocaleFilter( localeResolver ) );
		filterBean.setOrder( Integer.MAX_VALUE );
		filterBean.addUrlPatterns( "/broker/*" );
		return filterBean;
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

	private static class DwrLocaleFilter implements Filter
	{
		private final LocaleResolver localeResolver;

		private DwrLocaleFilter( LocaleResolver localeResolver )
		{
			this.localeResolver = localeResolver;
		}

		@Override
		public void init( FilterConfig filterConfig ) throws ServletException
		{

		}

		@Override
		public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
		{
			LocaleContextHolder.setLocale( localeResolver.resolveLocale( (HttpServletRequest) request ) );
			chain.doFilter( request, response );
		}

		@Override
		public void destroy()
		{

		}
	}
}