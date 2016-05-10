package br.com.eits.blank.infrastructure.report;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.eits.blank.domain.repository.IAccountReportRepository;
import br.com.eits.common.infrastructure.report.IReportManager;
import br.com.eits.common.infrastructure.report.ReportFormat;

/**
 * @author rodrigo
 *
 */
@Component
public class AccountReportRepository implements IAccountReportRepository
{
	/**
	 * 
	 */
	private static final String USERS_BY_FILTERS_REPORT_PATH = "/reports/account/users-by-filters.jasper";
	
	/*-------------------------------------------------------------------
     *                          ATTRIBUTES
     *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private IReportManager reportManager;

	/*-------------------------------------------------------------------
     *                          REPORTS
     *-------------------------------------------------------------------*/
	/* (non-Javadoc)
	 * @see br.com.eits.blank.domain.repository.IAccountReportRepository#generateByFilters(java.lang.String)
	 */
	@Override
	public ByteArrayOutputStream generateByFilters( String filters, ReportFormat reportFormat )
	{
		Assert.notNull( reportFormat );
		
		final Map<String, Object> parameters = new HashMap<>(); 
        parameters.put( "filters", filters);
        
        if ( reportFormat.equals( ReportFormat.PDF ) )
        {
        	return this.reportManager.exportToPDF( parameters, USERS_BY_FILTERS_REPORT_PATH );
        }
        
        throw new IllegalArgumentException( "Not supported format" );
	}
}
