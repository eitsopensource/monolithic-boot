import { Component } from '@angular/core';
import { Broker } from 'eits-ng2';

/**
 * 
 */
@Component( {
    selector: 'app-root',
    templateUrl: './sample.view.html'
})
export class SampleView
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
