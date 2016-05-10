package br.com.eits.blank.domain.repository;

import java.io.ByteArrayOutputStream;

import br.com.eits.common.infrastructure.report.ReportFormat;

/**
 * @author rodrigo
 *
 */
public interface IAccountReportRepository
{
	/**
	 * 
	 */
	public static final String USERS_BY_FILTER_REPORT = "users-by-filter.pdf";
	
    /*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param filters
	 * @return
	 */
	public ByteArrayOutputStream generateByFilters( String filters, ReportFormat reportFormat );
}
