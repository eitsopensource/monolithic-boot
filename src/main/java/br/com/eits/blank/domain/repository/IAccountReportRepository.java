package br.com.eits.blank.domain.repository;

import java.io.ByteArrayOutputStream;

import br.com.eits.common.infrastructure.report.ReportFormat;

/**
 * @author rodrigo
 *
 */
public interface IAccountReportRepository
{
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
