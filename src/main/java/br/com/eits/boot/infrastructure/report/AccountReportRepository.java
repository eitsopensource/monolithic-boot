package br.com.eits.boot.infrastructure.report;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.eits.boot.domain.repository.IAccountReportRepository;
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
	private final IReportManager reportManager;

	@Autowired
	public AccountReportRepository( IReportManager reportManager )
	{
		this.reportManager = reportManager;
	}

	/*-------------------------------------------------------------------
     *                          REPORTS
     *-------------------------------------------------------------------*/
	/* (non-Javadoc)
	 * @see br.com.eits.boot.domain.repository.IAccountReportRepository#generateByFilters(java.lang.String)
	 */
	@Override
	public ByteArrayOutputStream generateByFilters( String filters, ReportFormat reportFormat )
	{
		final Map<String, Object> parameters = new HashMap<>(); 
        parameters.put( "filters", filters);
        
        if ( reportFormat.equals( ReportFormat.PDF ) )
        {
        	return this.reportManager.exportToPDF( parameters, USERS_BY_FILTERS_REPORT_PATH );
        }
        
        throw new IllegalArgumentException( "Not supported format" );
	}
}
