package br.com.eits.blank.domain.repository;
import java.util.concurrent.Future;

import br.com.eits.blank.domain.entity.account.User;
 
/**
 * Interface para o envio de e-mails
 *
 * @author rodrigo@eits.com.br
 * @since 02/10/2014
 * @version 1.0
 */
public interface IAccountMailRepository
{
    /*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/
    /**
     * @param user
     */
    public Future<Void> sendNewUserAccount( User user );
}