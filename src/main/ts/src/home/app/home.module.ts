import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CovalentLayoutModule, CovalentStepsModule } from '@covalent/core';

import { HomeRoutingModule } from './home-routing.module';

import { HomeView } from './views/home/home-view.component';

/**
 * 
 */
@NgModule({
    declarations: [
        HomeView,
    ],
    imports: [
        CovalentLayoutModule,
        CovalentStepsModule,
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
