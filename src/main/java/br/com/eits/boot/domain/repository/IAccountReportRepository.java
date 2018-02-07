package br.com.eits.boot.domain.repository;

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
	String USERS_BY_FILTER_REPORT = "users-by-filter.pdf";
	
    /*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/
	/**
	 * 
	 * @param filters
	 * @return
	 */
	ByteArrayOutputStream generateByFilters( String filters, ReportFormat reportFormat );
}
