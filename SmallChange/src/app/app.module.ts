import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavBarComponent } from './nav-bar/nav-bar/nav-bar.component';
import { NavLinkComponent } from './Shared/nav-link/nav-link.component';
import { PortfolioComponent } from './routing-nav-bar/portfolio/portfolio.component';
import { ActivityComponent } from './routing-nav-bar/activity/activity.component';
import { TradeComponent } from './routing-nav-bar/trade/trade.component';
import { PreferencesComponent } from './routing-nav-bar/preferences/preferences.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LandingPageComponent } from './landing-page/landing-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    LoginFormComponent,
    LandingPageComponent,
    NavBarComponent,
    NavLinkComponent,
    PortfolioComponent,
    ActivityComponent,
    TradeComponent,
    PreferencesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
