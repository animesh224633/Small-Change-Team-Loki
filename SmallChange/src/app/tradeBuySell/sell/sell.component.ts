import { Component, OnInit } from '@angular/core';
import {PortfolioMutualFunds} from '../../models/portfolio-mutual-funds.model';
import { BuySellService } from 'src/app/services/buy-sell.service';
import {PortfolioStocks} from '../../models/portfolio-stocks.model';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {
  check: boolean = false;
  chosen:string='';
  cNumber:number=1;
  quantity:number=0;
  transaction:number=1;
  total_price:number=0;
  wallet_money:number=0;
  wallet_limit:number=0;
  new_wallet_money:number=0;
//  stocks:any;
  stocks: PortfolioStocks[]=[{
    name: "Amazon",
    code: "AMZN",
    quantity: 2,
    buyPrice: 130.5,
    currentPrice: 135
  }];
  switch:boolean=false;
  
mFunds: PortfolioMutualFunds[]=[{
  name: "Amazon3",
  code: "AMZN",
  quantity: 2,
  buyPrice: 130.5,
  currentPrice: 135
}];
  constructor(private buyService: BuySellService) { }
  toggle(event:any){
    console.log(event);
    this.check = !this.check;
    if(event.checked==true){
this.buyService.getPortfolioMutualFunds().subscribe(data=>{
  console.log(data);
  this.mFunds=data;
  this.cNumber=2;
})
}
else{
  this.buyService.getPortfolioStocks().subscribe(data=>{
    console.log(data);
    this.stocks=data;
    this.cNumber=1;
  })
  }
  }

  ngOnInit(): void {
    this.getWalletMoney();
    this.buyService.getPortfolioStocks().subscribe(data=>{
    
      console.log(data);
      this.stocks=data;
      this.switch=true;
    })        
  }
  getWalletMoney(){
    this.buyService.getWalletAmount().subscribe(data=>{
      console.log(data);
      this.wallet_limit=data.amount;
      //this.stocks=data;
     // this.cNumber=1;
    })
  }
  done(){
   
    if(this.cNumber==1){
    this.total_price= this.quantity*this.stocks[0].currentPrice;
    console.log(this.total_price);
    
   
   
    if(this.quantity > this.stocks[0].quantity ){
  // if(this.total_price > 90){
    
    console.log(this.total_price);
this.transaction=0;
   }
   else{
    this.transaction=1;
    this.new_wallet_money=this.wallet_limit+this.total_price;
    console.log("new wallet money",this.new_wallet_money);
   }
  }
  if(this.cNumber==2){

    this.total_price= this.quantity*this.mFunds[0].currentPrice;
    console.log(this.total_price);
    
    if(this.quantity > this.mFunds[0].quantity ){
      // if(this.total_price > 90){
        
        console.log(this.total_price);
    this.transaction=0;
       }
       else{
        this.transaction=1;
        this.new_wallet_money=this.wallet_limit+this.total_price;
        console.log("new wallet money",this.new_wallet_money);
       }
    }

  }
 
  submit(){
    this.buyService.updateWalletAmount(this.new_wallet_money).subscribe(data=>{
      console.log(data);
      alert("Trade: Sell executed successfully")
  })
  
}

  }
