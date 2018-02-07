package br.com.eits.boot.domain.repository.account;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.account.User;

/**
 *
 */
public interface IUserRepository extends JpaRepository<User, Long>
{
	/**
	 *
	 */
	@Query("FROM User user WHERE lower(user.email) = lower(:email)")
	Optional<User> findByEmail( @Param("email") String email );

	/**
	 *
	 */
	Optional<User> findByPasswordResetTokenAndPasswordResetTokenExpirationAfter( String token, OffsetDateTime time );

	/**
	 *
	 */
	@Query("FROM User user " +
			"WHERE filter(:filter, user.id, user.name, user.email) = TRUE ")
	Page<User> listByFilters( @Param("filter") String filter, Pageable pageable );
}
