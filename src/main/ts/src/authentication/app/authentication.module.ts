import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CovalentLayoutModule, CovalentStepsModule } from '@covalent/core';

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
        CovalentLayoutModule,
        CovalentStepsModule,
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
