import { Component } from '@angular/core';
import { Http, URLSearchParams } from '@angular/http';

/**
 * 
 */
@Component( {
    selector: 'app-root',
    templateUrl: './signin-view.component.html',
})
export class SigninView
{
    /**
     * 
     */
    constructor(private http:Http)
    { 
    }
    
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
    public onSignIn():void
    {
        const body = new URLSearchParams();
        body.set('email', this.user.email);
        body.set('password', this.user.password);
        
        this.http.post("authenticate", body)
            .subscribe( result => {
                window.location.replace('/');
            });
    }
}
