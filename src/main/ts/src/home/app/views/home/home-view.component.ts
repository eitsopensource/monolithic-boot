import { Component } from '@angular/core';
import { Broker } from 'eits-ng2';

/**
 * 
 */
@Component( {
    selector: 'app-root',
    templateUrl: './home-view.component.html',
})
export class HomeView 
{
    /*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
    /**
     * 
     */
    private user:any = {};

    /*-------------------------------------------------------------------
     *                           BEHAVIORS
     *-------------------------------------------------------------------*/
    /**
     * 
     */
    public onFindUserById():void
    {
        Broker.of("accountService").promise("findUserById", 1)
            .then( (result) => {
                console.log(result);
                this.user = result;
            })
            .catch( (message) => {
                console.log(message);
            }
        );
    }
}
