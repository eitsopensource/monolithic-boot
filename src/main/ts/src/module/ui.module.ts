import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { SampleView } from './views/sample.view';

/**
 * 
 */
@NgModule( {
    declarations: [
        SampleView
    ],
    imports: [
        BrowserModule,
        FormsModule,
    ],
    bootstrap: [SampleView]
})
export class UIModule 
{
    /*-------------------------------------------------------------------
     *                           ATTRIBUTES
     *-------------------------------------------------------------------*/
}