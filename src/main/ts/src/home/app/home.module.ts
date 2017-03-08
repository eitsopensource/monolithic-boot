import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

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
        BrowserModule,
        FormsModule,
        HttpModule,
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
