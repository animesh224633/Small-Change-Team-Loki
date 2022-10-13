import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { NavBarComponent } from './nav-bar/nav-bar/nav-bar.component';
import { ActivityComponent } from './activity/activity-page/activity.component';
import { PortfolioComponent } from './portfolio/portfolio-page/portfolio.component';
import { PreferencesComponent } from './preferences/preferences/preferences.component';
import { TradeComponent } from './trade/trade-page/trade.component';
import { BuysellComponent } from './tradeBuySell/buysell/buysell.component';
import { SellComponent } from './tradeBuySell/sell/sell.component';
import { BuyComponent } from './tradeBuySell/buy/buy.component';
import { PreferencesPageComponent } from './preferences-page/preferences-page.component';
import { PreferencesSubmitPageComponent } from './preferences-submit-page/preferences-submit-page.component';


const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginPageComponent }, 
  { path: 'portfolio', component: PortfolioComponent},
  { path: 'activity', component: ActivityComponent },
  { path: 'trade', component: TradeComponent },
  { path: 'tradeBuySell', component: BuysellComponent},

  { path: 'landing', component:LandingPageComponent},
  { path: 'loginPage', component:NavBarComponent},
  { path: 'preferences', component:PreferencesComponent},
  { path: 'preferencesPage', component: PreferencesPageComponent },
  {path:'preferencesSubmitPage',component:PreferencesSubmitPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  
})
export class AppRoutingModule { }
