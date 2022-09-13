import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavBarComponent } from './nav-bar/nav-bar/nav-bar.component';
import { NavLinkComponent } from './Shared/nav-link/nav-link.component';
import { PortfolioComponent } from './portfolio/portfolio-page/portfolio.component';
import { ActivityComponent } from './activity/activity-page/activity.component';
import { TradeComponent } from './trade/trade-page/trade.component';
import { PreferencesComponent } from './preferences/preferences/preferences.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input'
import {MatFormFieldModule, MatLabel, MAT_FORM_FIELD_DEFAULT_OPTIONS} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ParatextComponent } from './paratext/paratext.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTabsModule} from '@angular/material/tabs';
import { HttpClientModule } from '@angular/common/http';
import {MatDividerModule} from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { PortfolioStockTableComponent } from './portfolio/portfolio-stock-table/portfolio-stock-table.component';
import { PortfolioMfTableComponent } from './portfolio/portfolio-mf-table/portfolio-mf-table.component';
import { PortfolioDetailsComponent } from './portfolio/portfolio-details/portfolio-details.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HeaderComponent,
    FooterComponent,
    ParatextComponent,
    LoginFormComponent,
    LandingPageComponent,
    NavBarComponent,
    NavLinkComponent,
    PortfolioComponent,
    ActivityComponent,
    TradeComponent,
    PreferencesComponent,
    PortfolioStockTableComponent,
    PortfolioMfTableComponent,
    PortfolioDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatTabsModule,
    HttpClientModule,
    MatDividerModule,
    MatIconModule,
    MatTableModule
  ],
  providers: [{provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'fill'}}],
  bootstrap: [AppComponent]
})
export class AppModule { }
