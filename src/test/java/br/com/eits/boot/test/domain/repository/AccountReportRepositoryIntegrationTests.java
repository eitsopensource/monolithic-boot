package br.com.eits.boot.test.domain.repository;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import org.directwebremoting.io.FileTransfer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.repository.IAccountReportRepository;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;
import br.com.eits.common.infrastructure.file.MimeType;
import br.com.eits.common.infrastructure.report.ReportFormat;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 09/05/2013
 * @version 1.0
 */
public class AccountReportRepositoryIntegrationTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *                           ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
     * 
     */
	@Autowired
	private IAccountReportRepository accountReportRepository;

	/*-------------------------------------------------------------------
	 *                           TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * @throws ExecutionException 
	 * @throws InterruptedException 
     * 
     */
	@Test
	@Sql({
		"/dataset/account/users.sql"
	})
	public void generateByFiltersTestMustPass()
	{
		final ByteArrayOutputStream reportOutputStream = this.accountReportRepository.generateByFilters( null, ReportFormat.PDF );
		Assert.assertNotNull( reportOutputStream );
		
		final FileTransfer fileTransfer = new FileTransfer( IAccountReportRepository.USERS_BY_FILTER_REPORT, MimeType.PDF.value, reportOutputStream.toByteArray());
		Assert.assertNotNull( fileTransfer );
		Assert.assertEquals( 2140, fileTransfer.getSize() );
	}
}
