package br.com.eits.boot.domain.repository.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.account.User;

/**
 * 
 * @author rodrigo@eits.com.br 
 * @since 22/04/2014
 * @version 1.0
 * @category Repository
 */
public interface IUserRepository extends JpaRepository<User, Long>
{
	/**
	 * @param username
	 * @return
	 */
	public User findByEmail(String email);
	
	/**
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query(value="SELECT new User(user.id, user.name, user.email, user.enabled, user.role) " +
				   "FROM User user " +
				  "WHERE ( FILTER(user.id, :filter) = TRUE "
				  	 + "OR FILTER(user.name, :filter) = TRUE "
				  	 + "OR FILTER(user.email, :filter) = TRUE )" )
	public Page<User> listByFilters( @Param("filter") String filter, Pageable pageable );
}
