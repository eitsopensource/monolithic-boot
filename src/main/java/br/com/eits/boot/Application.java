package br.com.eits.boot;

import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javax.jcr.Repository;
import javax.validation.Validator;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.eits.common.application.i18n.ResourceBundleMessageSource;
import br.com.eits.common.infrastructure.jcr.IMetaFileRepository;
import br.com.eits.common.infrastructure.jcr.modeshape.MetaFileRepository;
import br.com.eits.common.infrastructure.jcr.modeshape.ModeShapeRepositoryFactory;
import br.com.eits.common.infrastructure.jcr.modeshape.ModeShapeSessionFactory;
import br.com.eits.common.infrastructure.report.IReportManager;
import br.com.eits.common.infrastructure.report.jasper.JasperReportManager;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@EnableAsync
@SpringBootApplication
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
	 * 		 					OVERRIDES
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
	 * 		 						BEANS
	 *-------------------------------------------------------------------*/
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
    
    /**
     * 
     * @return
     */
    @Bean
    public Validator validator() 
    {
    	return new LocalValidatorFactoryBean();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public IReportManager reportManager() 
    {
    	return new JasperReportManager();
    }
    
	//---------
	// JCR - JAVA CONTENT REPOSITORY
    // With Modeshape
	//---------
    /**
	 * @return
	 */
    @Bean
    public IMetaFileRepository metaFileRepository() 
    {
        return new MetaFileRepository();
    }
    
    /**
     * 
     * @param environment
     * @return
     * @throws MalformedURLException 
     */
    @Bean
    public FactoryBean<Repository> modeShapeRepositoryFactory( @Value("${jcr.configuration-path}") String jcrConfigurationPath ) throws MalformedURLException 
    {
    	final ModeShapeRepositoryFactory modeShapeRepositoryFactory = new ModeShapeRepositoryFactory();
    	
    	if ( jcrConfigurationPath.contains( "classpath:" ) )
    	{
    		jcrConfigurationPath = jcrConfigurationPath.split("classpath:")[1];
    		modeShapeRepositoryFactory.setConfiguration( new ClassPathResource(jcrConfigurationPath) );
    	}
    	else
    	{
    		modeShapeRepositoryFactory.setConfiguration( new UrlResource( jcrConfigurationPath ) );
    	}
    	return modeShapeRepositoryFactory;
    }

    /**
     * 
     * @return
     */
    @Bean
    public ModeShapeSessionFactory modeShapeSessionFactory() 
    {
    	return new ModeShapeSessionFactory();
    }
}