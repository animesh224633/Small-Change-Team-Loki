import { Component, OnInit } from '@angular/core';
import { PortfolioMutualFunds } from '../../models/portfolio-mutual-funds.model';
import { BuySellService } from 'src/app/services/buy-sell.service';
import { PortfolioStocks } from '../../models/portfolio-stocks.model';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {
  check: boolean = false;
  chosen: string = '';
  cNumber: number = 1;// to indicate if mutualfund or stock toggle is selected
  quantity: number = 0;
  transaction: number | undefined;// 1 indicates a valid transaction// 0 indicates invalid details// undefined indicates transaction is not initiated
  total_price: number = 0;
  wallet_money: number = 0;
  wallet_limit: number = 0;
  new_wallet_money: number = 0;
  stocks: PortfolioStocks[] = [];
  mFunds: PortfolioMutualFunds[] = [];
selected : number =0;// indicates selected stock or mfund to sell




  constructor(private buyService: BuySellService) { }
  toggle(event: any) {
    console.log(event);
    this.check = !this.check;
    if (event.checked == true) {
      this.buyService.getPortfolioMutualFunds().subscribe(data => {
        console.log(data);
        this.mFunds = data;
        this.cNumber = 2;
      })
    }
    else {
      this.buyService.getPortfolioStocks().subscribe(data => {
        console.log(data);
        this.stocks = data;
        this.cNumber = 1;
      })
    }
  }

  ngOnInit(): void {
    this.getWalletMoney();
    this.buyService.getPortfolioStocks().subscribe(data => {

      console.log(data);
      this.stocks = data;
    })
  }
  getWalletMoney() {
    this.buyService.getWalletAmount().subscribe(data => {
      console.log(data);
      this.wallet_limit = data.amount;
      //this.stocks=data;
      // this.cNumber=1;
    })
  }
  done() {

    if (this.cNumber == 1 && this.quantity != 0) {
      this.total_price = this.quantity * this.stocks[this.selected].currentPrice;
      console.log(this.total_price);



      if (this.quantity > this.stocks[this.selected].quantity) {
        // if(this.total_price > 90){


        console.log(this.total_price);
        this.transaction = 0;
      }
      else {
        this.transaction = 1;
        this.new_wallet_money = this.wallet_limit + this.total_price;
        console.log("new wallet money", this.new_wallet_money);
      }
    }
    if (this.cNumber == 2 && this.quantity != 0) {

      this.total_price = this.quantity * this.mFunds[0].currentPrice;
      console.log(this.total_price);

      if (this.quantity > this.mFunds[0].quantity) {
        // if(this.total_price > 90){

        console.log(this.total_price);
        this.transaction = 0;
      }
      else {
        this.transaction = 1;
        this.new_wallet_money = this.wallet_limit + this.total_price;
        console.log("new wallet money", this.new_wallet_money);
      }
    }

  }

  submit() {
    if (this.transaction === 1) {

      if (this.cNumber == 1) {


        console.log(this.stocks[this.selected].name, this.stocks[this.selected].quantity - this.quantity);
        const updatedStock = this.stocks[this.selected];
        updatedStock.quantity = updatedStock.quantity - this.quantity;
        console.log(updatedStock);

        this.buyService.updatePortfolioStocks(updatedStock).subscribe(data => {

          console.log(data);
        });

      }
      
      if (this.cNumber == 2) {


        console.log(this.mFunds[this.selected].name, this.mFunds[this.selected].quantity - this.quantity);
        const updatedMfund = this.mFunds[this.selected];
        updatedMfund.quantity = updatedMfund.quantity - this.quantity;
        console.log(updatedMfund);

        this.buyService.updatePortfolioMutualFunds(updatedMfund).subscribe(data => {

          console.log(data);
        });

      }
      this.buyService.updateWalletAmount(this.new_wallet_money).subscribe(data => {
        console.log(data);
        alert("Trade: Sell executed successfully");
      });


      this.transaction = undefined;



    }
    else {
      alert("Invalid Transaction details");
    }

  }
}
