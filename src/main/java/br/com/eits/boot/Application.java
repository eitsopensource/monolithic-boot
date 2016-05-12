package br.com.eits.boot;

import java.util.Locale;
import java.util.ResourceBundle;

import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import br.com.eits.boot.application.security.AuthenticationFailureHandler;
import br.com.eits.boot.application.security.AuthenticationSuccessHandler;
import br.com.eits.common.application.i18n.ResourceBundleMessageSource;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@EnableAsync
@SpringBootApplication
@ImportResource("classpath:/META-INF/spring/application-context.xml")
public class Application extends SpringBootServletInitializer
{
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		SpringApplication.run( Application.class, args );
	}
	
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public static final String AUDIT_SCHEMA = "auditing";
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param args
	 */
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
	{
		final SpringApplicationBuilder builder = application.sources( Application.class );
		
		final String applicationName = ResourceBundle.getBundle( "config/application" ).getString( "spring.application.name" );
		final String externalProperties = System.getProperty( applicationName+".properties" );//br.com.group.artifact.properties
		if ( externalProperties != null && !externalProperties.isEmpty() )
		{
			builder.properties( ConfigFileApplicationListener.CONFIG_LOCATION_PROPERTY+":"+externalProperties );
		}
		return builder;
	}

	/*-------------------------------------------------------------------
	 * 		 					CONFIGURATIONS
	 *-------------------------------------------------------------------*/
	//---------
	// Beans
	//---------
	/**
	 * 
	 * @return
	 */
    @Bean
    public MessageSource messageSource() 
    {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setAlwaysUseMessageFormat( true );
        messageSource.setDefaultEncoding( "UTF-8" );
        messageSource.setBasenames( "classpath:i18n/exceptions", "classpath:i18n/labels", "classpath:i18n/messages" );
        return messageSource;
    }

	//---------
	// Security Config
	//---------
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter
	{
		/**
		 * 
		 * @return
		 */
	    @Bean
	    public AuthenticationFailureHandler authenticationFailureHandler() 
	    {
	        return new AuthenticationFailureHandler();
	    }
	    
	    /**
	     * 
	     * @return
	     */
	    @Bean
	    public AuthenticationSuccessHandler authenticationSuccessHandler() 
	    {
	    	return new AuthenticationSuccessHandler();
	    }
	    
		/**
    	 * 
    	 */
		@Override
		protected void configure( HttpSecurity httpSecurity ) throws Exception
		{
			httpSecurity.csrf().disable();
			httpSecurity.headers().frameOptions().disable();
			
			httpSecurity
				.authorizeRequests()
					.anyRequest()
						.authenticated()
						.and()
							.formLogin()
								.usernameParameter( "email" )
								.loginPage( "/authentication" )
								.loginProcessingUrl( "/authenticate" )
								.failureHandler( this.authenticationFailureHandler() )
								.successHandler( this.authenticationSuccessHandler() )
							.permitAll()
						.and()
							.logout()
								.logoutUrl( "/logout" );
		}
	}
	
	//---------
	// Web Config
	//---------
	@Configuration
	protected static class WebConfiguration extends WebMvcConfigurerAdapter
	{
		/**
		 * 
		 * @return
		 */
		@Bean
		public ServletRegistrationBean dwrSpringServletRegistration()
		{
			final ServletRegistrationBean registration = new ServletRegistrationBean( new DwrSpringServlet(), "/broker/*" );
			registration.addInitParameter( "debug", "true" );
			registration.addInitParameter( "scriptCompressed", "true" );
			registration.setName( "dwrSpringServlet" );
			return registration;
		}
		
		//-----------
		// Locale
		//-----------
	    /**
	     * 
	     * @return
	     */
		@Bean
		public LocaleResolver localeResolver() 
		{
			final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
			localeResolver.setDefaultLocale( new Locale( "pt", "BR" ) );
			localeResolver.setCookieMaxAge( 604800 ); //1 month
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
			localeChangeInterceptor.setParamName("lang");
	        return localeChangeInterceptor;
	    }
		
		/**
		 * 
		 */
	    @Override
	    public void addInterceptors( InterceptorRegistry registry ) 
	    {
	        registry.addInterceptor( this.localeChangeInterceptor() );
	    }
	}
}