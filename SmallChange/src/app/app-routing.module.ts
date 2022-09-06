import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NavBarComponent } from './nav-bar/nav-bar/nav-bar.component';
import { ActivityComponent } from './routing-nav-bar/activity/activity.component';
import { PortfolioComponent } from './routing-nav-bar/portfolio/portfolio.component';
import { PreferencesComponent } from './routing-nav-bar/preferences/preferences.component';
import { TradeComponent } from './routing-nav-bar/trade/trade.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent }, 
  { path: 'portfolio', component: PortfolioComponent},
  { path: 'activity', component: ActivityComponent },
  { path: 'trade', component: TradeComponent },
  { path: 'preferences', component: PreferencesComponent },
  { path: 'landing', component:LandingPageComponent},
  { path: 'loginPage', component:NavBarComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  
})
export class AppRoutingModule { }
