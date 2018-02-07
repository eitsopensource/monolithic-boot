package br.com.eits.boot.infrastructure.mail;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.mail.internet.MimeMessage;

import br.com.eits.boot.application.configuration.settings.AppSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.eits.boot.domain.entity.account.User;
import br.com.eits.boot.domain.repository.IAccountMailRepository;

/**
 *
 */
@Component
public class AccountMailRepository implements IAccountMailRepository
{
	/*-------------------------------------------------------------------
	 *                          ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
     *
     */
	private final JavaMailSender mailSender;
	/**
     *
     */
	private final TemplateEngine templateEngine;
	/**
     *
     */
	private final AppSettings appSettings;

	@Autowired
	public AccountMailRepository( JavaMailSender mailSender, TemplateEngine templateEngine, AppSettings appSettings )
	{
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
		this.appSettings = appSettings;
	}

	/*-------------------------------------------------------------------
	 *                          BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 *
	 * @param user
	 */
	@Async
	@Override
	public Future<Void> sendNewUserAccount( final User user )
	{
		final MimeMessagePreparator preparator = new MimeMessagePreparator()
		{
			public void prepare( MimeMessage mimeMessage ) throws Exception
			{
				final String title = "[eits] Seja Bem Vindo!";
				final String logo = "logo-large.png";
				
				final MimeMessageHelper message = new MimeMessageHelper( mimeMessage, true, "UTF-8" );
				message.setSubject( title );
				message.setTo( user.getEmail() );
				message.setFrom( appSettings.getMailFrom() );

				final Context context = new Context();
				context.setVariable("title", title);
				context.setVariable("logo", logo);
				context.setVariable("uzer", user);
				
				final String content = templateEngine.process( "mail/new-account", context );
				message.setText( content, true );
				
				// Add the inline image, referenced from the HTML code as "cid:${logo}"
				message.addInline( logo, new ClassPathResource("META-INF/resources/static/images/logo-large.png"));
			}
		};

		this.mailSender.send( preparator );
		return new AsyncResult<>( null );
	}

	@Override
	public Future<Void> sendPasswordReset( User user )
	{
		return new AsyncResult<>( null );
	}

	@Override
	public Future<Void> sendPasswordResetNotice( User user )
	{
		return new AsyncResult<>( null );
	}
}