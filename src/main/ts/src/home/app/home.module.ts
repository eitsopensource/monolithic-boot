import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CovalentCoreModule, CovalentLayoutModule } from '@covalent/core';

import { HomeRoutingModule } from './home-routing.module';

import { HomeView } from './views/home/home-view.component';

/**
 * 
 */
@NgModule( {
    declarations: [
        HomeView,
    ],
    imports: [
        CovalentCoreModule.forRoot(),
        CovalentLayoutModule.forRoot(),
        BrowserModule,
        FormsModule,
        HomeRoutingModule
    ],
    providers: [],
    bootstrap: [HomeView]
})
export class HomeModule 
{
    /*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
}
