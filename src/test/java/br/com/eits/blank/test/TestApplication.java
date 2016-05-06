package br.com.eits.blank.test;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

/**
 * 
 * @author rodrigo@eits.com.br
 */
@Configuration
public class TestApplication
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Autowired
	private DataSource dataSource;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 * 		 					CONFIGURATIONS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@Bean
	public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseDataSourceConnectionFactoryBean()
	{
		final DatabaseConfigBean databaseConfigBean = new DatabaseConfigBean();
		databaseConfigBean.setQualifiedTableNames( true );
		
		final DatabaseDataSourceConnectionFactoryBean databaseDataSourceConnectionFactoryBean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
		databaseDataSourceConnectionFactoryBean.setDatabaseConfig( databaseConfigBean );
		return databaseDataSourceConnectionFactoryBean;
	}
}