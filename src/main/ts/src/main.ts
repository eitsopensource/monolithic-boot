import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { environment } from './environments/environment';
import { UIModule } from './module/ui.module';

if ( environment.production ) 
{
    enableProdMode();
}

if ( environment.pathToDwrServlet ) 
{
    window["dwr"].engine._pathToDwrServlet = environment.pathToDwrServlet;
}

platformBrowserDynamic().bootstrapModule( UIModule );