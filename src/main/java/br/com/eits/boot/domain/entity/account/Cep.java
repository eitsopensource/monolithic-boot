package br.com.eits.boot.domain.entity.account;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.directwebremoting.annotations.DataTransferObject;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DataTransferObject
public class Cep extends AbstractEntity
{
	@Size(min = 8, max = 8)
	private String cep;
}
