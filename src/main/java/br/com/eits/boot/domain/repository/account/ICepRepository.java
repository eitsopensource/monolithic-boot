package br.com.eits.boot.domain.repository.account;

import br.com.eits.boot.domain.entity.account.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICepRepository extends JpaRepository<Cep, Long>
{
}
