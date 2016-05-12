package br.com.eits.boot.test.domain.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.directwebremoting.io.FileTransfer;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eits.boot.test.domain.AbstractIntegrationTests;
import br.com.eits.common.infrastructure.jcr.IMetaFileRepository;
import br.com.eits.common.infrastructure.jcr.MetaFile;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 09/05/2013
 * @version 1.0
 * @category
 
 */
public class MetaFileRepositoryIntegrationTests extends AbstractIntegrationTests
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/	
	/**
	 * 
	 */
	@Autowired
	private IMetaFileRepository metaFileRepository;

	/*-------------------------------------------------------------------
	 *				 		     TESTS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void insert() throws RepositoryException, IOException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("example.pdf");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		Assert.assertNotNull( metaFile );
		Assert.assertNotNull( metaFile.getId() );
		Assert.assertNotNull( metaFile.getCreated() );
		Assert.assertNotNull( metaFile.getPath() );
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void insertWithCreator() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		Assert.assertNotNull( metaFile );
		Assert.assertNotNull( metaFile.getId() );
		Assert.assertNotNull( metaFile.getCreated() );
		Assert.assertNotNull( metaFile.getCreatedBy() );
		Assert.assertNotNull( metaFile.getPath() );
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void removeByPath() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		try
		{
			this.metaFileRepository.removeByPath( "/wrogn/pathx" );
			Assert.fail("Should throw a PathNotFoundException");
		}
		catch ( PathNotFoundException e )
		{
			this.metaFileRepository.removeByPath( metaFile.getPath() );
		}
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void removeByFolder() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		try
		{
			this.metaFileRepository.removeByFolder( "/wrogn/pathx" );
			Assert.fail("Deveria lançar a exceção PathNotFoundException");
		}
		catch ( PathNotFoundException e )
		{
			this.metaFileRepository.removeByFolder( metaFile.getFolder() );
		}
		
		final List<MetaFile> metaFiles = this.metaFileRepository.listByFolder( metaFile.getFolder() );
		System.out.println( metaFiles );
		
		Assert.assertTrue( metaFiles.isEmpty() );
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void remove() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		try
		{
			this.metaFileRepository.remove( "1203102391WrgonID123012309" );
			Assert.fail("Deveria lançar a exceção PathNotFoundException");
		}
		catch ( PathNotFoundException e )
		{
			this.metaFileRepository.remove( metaFile.getId() );
		}
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void findByPath() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		try
		{
			this.metaFileRepository.findByPath( "/wrogn/pathx/10293019239021091", false );
			Assert.fail("Deveria lançar a exceção PathNotFoundException");
		}
		catch ( PathNotFoundException e )
		{
			MetaFile m = this.metaFileRepository.findByPath( metaFile.getPath(), true );
			Assert.assertNotNull(m);
			Assert.assertNotNull( m.getName() );
			Assert.assertNotNull( m.getContentType() );
			Assert.assertNotNull( m.getCreatedBy() );
			Assert.assertNotNull( m.getDescription() );
			Assert.assertNotNull( m.getFolder() );
			Assert.assertNotNull( m.getPath() );
			Assert.assertNotNull( m.getCreated() );
			Assert.assertNotNull( m.getInputStream() );
		}
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void findById() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		try
		{
			this.metaFileRepository.findById( "10293019239021091", false );
			Assert.fail("Deveria lançar a exceção PathNotFoundException");
		}
		catch ( PathNotFoundException e )
		{
			MetaFile m = this.metaFileRepository.findById( metaFile.getId(), true );
			Assert.assertNotNull(m);
			Assert.assertNotNull( m.getName() );
			Assert.assertNotNull( m.getContentType() );
			Assert.assertNotNull( m.getCreatedBy() );
			Assert.assertNotNull( m.getDescription() );
			Assert.assertNotNull( m.getFolder() );
			Assert.assertNotNull( m.getPath() );
			Assert.assertNotNull( m.getCreated() );
			Assert.assertNotNull( m.getInputStream() );
		}
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws RepositoryException 
	 * @throws FileSizeLimitExceededException 
	 */
	@Test
	public void listByFolder() throws RepositoryException, IOException, FileSizeLimitExceededException
	{
		final FileInputStream file = new FileInputStream(this.getClass().getResource("/example.pdf").getPath());
		final FileTransfer fileTransfer = new FileTransfer("test.pdf", "application/pdf", file);
		
		MetaFile metaFile = new MetaFile();
		metaFile.setContentType("application/pdf");
		metaFile.setDescription("Description");
		metaFile.setFolder("/test/files");
		metaFile.setInputStream(fileTransfer.getInputStream());
		metaFile.setName("test.pdf");
		metaFile.setCreatedBy("Rodrigo P. Fraga");
		
		metaFile = this.metaFileRepository.insert( metaFile );
		
		final List<MetaFile> metaFiles = this.metaFileRepository.listByFolder("/test/files");
		Assert.assertNotNull(metaFiles);
		Assert.assertTrue(metaFiles.size() > 0);
	}
}
