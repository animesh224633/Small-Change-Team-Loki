import { Component, OnInit } from '@angular/core';
import {PortfolioStocks} from '../../models/portfolio-stocks.model';
import {PortfolioMutualFunds} from '../../models/portfolio-mutual-funds.model';
@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {
  check: boolean = false;
  chosen:string='';
  cNumber:number=0;
  quantity:number=0;
  transaction:number=1;
  total_price:number=0;
  stocks: PortfolioStocks[]=[{
    name: "Amazon",
    code: "AMZN",
    quantity: 2,
    buyPrice: 130.5,
    currentPrice: 135
  }];
mFunds: PortfolioMutualFunds[]=[{
  name: "Amazon",
  code: "AMZN",
  quantity: 2,
  buyPrice: 130.5,
  currentPrice: 135
}];
  constructor() { }
  toggle(){
    this.check = !this.check;
  }

  ngOnInit(): void {
    if(this.chosen=='Stocks'){
      this.cNumber=1;
        }
        else(this.chosen=='Mutual Funds')
        {
          this.cNumber=2;
        }
        
  }
  done(){
    if(this.chosen=='Stocks'){
    this.total_price= this.quantity*this.stocks[0].currentPrice;
    }
   // if(this.total_price > walletMoney )
   if(this.total_price > 9000){
this.transaction=0;
   }
  }
 
  submit(){}
}
