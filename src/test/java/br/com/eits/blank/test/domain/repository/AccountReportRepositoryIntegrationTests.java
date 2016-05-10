package br.com.eits.blank.test.domain.repository;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import org.directwebremoting.io.FileTransfer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import br.com.eits.blank.domain.repository.IAccountReportRepository;
import br.com.eits.blank.test.domain.AbstractIntegrationTests;
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
	@DatabaseSetup(type = DatabaseOperation.INSERT, value = {
		"/dataset/account/UserDataSet.xml",
	})
	public void generateByFiltersTestMustPass()
	{
		final ByteArrayOutputStream reportOutputStream = this.accountReportRepository.generateByFilters( null, ReportFormat.PDF );
		Assert.assertNotNull( reportOutputStream );
		
		final FileTransfer fileTransfer = new FileTransfer("users-by-filter.pdf", MimeType.PDF.value, reportOutputStream.toByteArray());
		Assert.assertNotNull( fileTransfer );
		Assert.assertEquals( 2087, fileTransfer.getSize() );
	}
}
