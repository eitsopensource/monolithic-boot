import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CovalentCoreModule, CovalentLayoutModule } from '@covalent/core';

import { AuthenticationRoutingModule } from './authentication-routing.module';

import { SigninView } from './views/signin/signin-view.component';

/**
 * 
 */
@NgModule( {
    declarations: [
        SigninView,
    ],
    imports: [
        CovalentCoreModule.forRoot(),
        CovalentLayoutModule.forRoot(),
        BrowserModule,
        FormsModule,
        HttpModule,
        AuthenticationRoutingModule
    ],
    providers: [],
    bootstrap: [SigninView]
})
export class AuthenticationModule 
{
    /*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
}
