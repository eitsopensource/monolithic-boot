package br.com.eits.boot;

import javax.validation.Validator;

import br.com.eits.common.application.i18n.ResourceBundleMessageSource;
import br.com.eits.common.infrastructure.report.IReportManager;
import br.com.eits.common.infrastructure.report.jasper.JasperReportManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@EnableAsync
@SpringBootApplication
public class Application
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
		var messageSource = new ResourceBundleMessageSource();
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
}