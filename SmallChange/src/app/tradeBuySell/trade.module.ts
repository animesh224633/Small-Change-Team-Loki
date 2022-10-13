import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuysellComponent } from './buysell/buysell.component';
import { RouterModule } from '@angular/router';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { MatTabsModule } from '@angular/material/tabs';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSelectModule} from '@angular/material/select';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDividerModule} from '@angular/material/divider';
import {MatRadioModule} from '@angular/material/radio';
import { MatOptionModule } from '@angular/material/core';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    BuysellComponent,
    BuyComponent,
    SellComponent
  ],
  imports: [
    CommonModule,
    MatCardModule,
    MatTabsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatDividerModule,
    MatOptionModule,
    MatSelectModule,
    MatRadioModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'trade', component: BuysellComponent}
    ])
  ],
  exports:[BuysellComponent]
  
})
export class TradeModule { }
