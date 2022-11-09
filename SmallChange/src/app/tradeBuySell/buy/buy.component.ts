import { Component, OnInit } from '@angular/core';
import {PortfolioStocks} from '../../models/portfolio-stocks.model';
import {PortfolioMutualFunds} from '../../models/portfolio-mutual-funds.model';
import { BuySellService } from 'src/app/services/buy-sell.service';
import { BuyInstrument } from 'src/app/models/buyInstrument.model';
import { ClientIdService } from 'src/app/client-id.service';
import { BuyOrder } from 'src/app/models/buyOrder.model';
@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {
  check: boolean = false;
  chosen:string='';
  cNumber:number=1;
  quantity:number=0;
  transaction: number | undefined;// 1 indicates a valid transaction// 0 indicates invalid details// undefined indicates transaction is not initiated
  total_price:number=0;
  wallet_limit:number=0;
  new_wallet_money:number=0;
  //stocks:any;
  stocks: BuyInstrument[]=[];
  switch:boolean=false;
  buyOrder!: BuyOrder ;


  selected : number =0;// indicates selected stock or mfund to sell

  
mFunds: BuyInstrument[]=[];
  constructor(private buyService: BuySellService, private clientIdService: ClientIdService ) { }
  toggle(event:any){
    this.getwalletAmount();
    console.log(this.wallet_limit);
    console.log(event);
    this.check = !this.check;
    if(event.checked==true){
      this.total_price=0;
      this.quantity=0;
this.buyService.getAllAssetsForBuyTrade().subscribe(data=>{
  console.log(data);
  this.mFunds=data.filter(asset => asset.category.toUpperCase()=="MUTUALFUND");
  this.cNumber=2;
})
}
else{
  this.total_price=0;
  this.quantity=0;
  this.buyService.getAllAssetsForBuyTrade().subscribe(data=>{
    console.log(data);
    this.stocks=data.filter(asset => asset.category.toUpperCase()=="STOCK");
    this.cNumber=1;
  })
  }
  }
  getwalletAmount(){
    this.buyService.getClientWalletAmount(this.clientIdService.clientId).subscribe((data) => {
      console.log(data);
      
      this.wallet_limit = data.clientSmallChangeWallet;
     
  });}

  ngOnInit(): void {
    this.getwalletAmount();
     this.buyService.getAllAssetsForBuyTrade().subscribe(data=>{
    console.log(data);
    this.stocks=data.filter(asset => asset.category.toUpperCase()=="STOCK");
      this.switch=true;
    })
   
        
  }
  
  
  done() {

    if (this.cNumber == 1 && this.quantity != 0) {
      this.total_price = this.quantity * this.stocks[this.selected].currentPrice;
      console.log(this.total_price);



      if (this.total_price > this.wallet_limit) {
        // if(this.total_price > 90){
          
        console.log(this.total_price);
        this.transaction = 0;
      }
      else {
        this.transaction = 1;
        this.new_wallet_money = this.wallet_limit - this.total_price;
        console.log("new wallet money", this.new_wallet_money);
      }
    }
    if (this.cNumber == 2 && this.quantity != 0) {

      this.total_price = this.quantity * this.mFunds[this.selected].currentPrice;
      console.log(this.total_price);

      if (this.total_price > this.wallet_limit) {
        // if(this.total_price > 90){

        console.log(this.total_price);
        this.transaction = 0;
      }
      else {
        this.transaction = 1;
        this.new_wallet_money = this.wallet_limit - this.total_price;
        console.log("new wallet money", this.new_wallet_money);
      }
    }

  }
 
  submit() {
    if (this.transaction === 1) {
      

      if (this.cNumber == 1) {
        this.buyOrder =new BuyOrder(this.stocks[this.selected].name,this.stocks[this.selected].code,"BUY"
        ,this.quantity,this.stocks[this.selected].currentPrice,this.clientIdService.clientId,"null",new Date('December 17, 1995 03:24:00'),"null");
        
        this.buyService.executeBuyOrder(this.buyOrder).subscribe(data => {
          alert("Trade: buy executed successfully");

          console.log(data);
        });

      }
      
      if (this.cNumber == 2) {
        this.buyOrder =new BuyOrder(this.mFunds[this.selected].name,this.mFunds[this.selected].code,"BUY"
        ,this.quantity,this.mFunds[this.selected].currentPrice,this.clientIdService.clientId,"null",new Date('December 17, 1995 03:24:00'),"null");
        

        this.buyService.executeBuyOrder(this.buyOrder).subscribe(data => {
          alert("Trade: buy executed successfully");
          console.log(data);
        });

      }

    
  }
  else {
    this.transaction = undefined;
    alert("Invalid Transaction details");
    }

  }
}
