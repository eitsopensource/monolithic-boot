import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';
import { environment } from '../environments/environment';
import { AuthenticationModule } from './app/authentication.module';

if ( environment.production ) 
{
    enableProdMode();
}

platformBrowserDynamic().bootstrapModule( AuthenticationModule );