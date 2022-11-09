import { Component, OnInit } from '@angular/core';
import { PortfolioMutualFunds } from '../../models/portfolio-mutual-funds.model';
import { BuySellService } from 'src/app/services/buy-sell.service';
import { PortfolioStocks } from '../../models/portfolio-stocks.model';
import { SellInstrument } from 'src/app/models/sellInstrument.model';
import { BuyInstrument } from 'src/app/models/buyInstrument.model';
import { SellOrder } from 'src/app/models/sellOrder.model';
import { ClientIdService } from 'src/app/client-id.service';

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
  new_wallet_money: number = 0;
  stocks: SellInstrument[] = [];
  mFunds: SellInstrument[] = [];
  sellOrder!: SellOrder;
  selected: number = 0;// indicates selected stock or mfund to sell




  constructor(private buyService: BuySellService, private clientIdService: ClientIdService) { }
  toggle(event: any) {
    console.log(event);
    this.check = !this.check;
    if (event.checked == true) {
      this.buyService.getAllAssetsForSellTrade(this.clientIdService.clientId).subscribe(data => {
        console.log(data);
        this.mFunds = data.filter(asset => asset.category.toUpperCase() == "MUTUALFUND");
        
        this.cNumber = 2;
      })
    }
    else {
      this.buyService.getAllAssetsForSellTrade(this.clientIdService.clientId).subscribe(data => {
        console.log(data);
        this.stocks = data.filter(asset => asset.category.toUpperCase() == "STOCK");;
        this.cNumber = 1;
      })
    }
  }

  ngOnInit(): void {
    // this.getWalletMoney();
    this.buyService.getAllAssetsForSellTrade(this.clientIdService.clientId).subscribe(data => {

      console.log(data);
      this.stocks = data.filter(asset => asset.category.toUpperCase() == "STOCK");
    })
  }
 
  done() {

    if (this.cNumber == 1 && this.quantity != 0) {
      this.total_price = this.quantity * this.stocks[this.selected].currentPrice;
      console.log(this.total_price);



      if (this.quantity > this.stocks[this.selected].quantity) {

        console.log(this.total_price);
        this.transaction = 0;
      }
      else {
        this.transaction = 1;
      }
    }
    if (this.cNumber == 2 && this.quantity != 0) {

      this.total_price = this.quantity * this.mFunds[this.selected].currentPrice;
      console.log(this.total_price);

      if (this.quantity > this.mFunds[this.selected].quantity) {

        console.log(this.total_price);
        this.transaction = 0;
      }
      else {
        this.transaction = 1;
      }
    }

  }

  submit() {
    if (this.transaction === 1) {

      if (this.cNumber == 1) {


        this.sellOrder = new SellOrder(this.stocks[this.selected].name, this.stocks[this.selected].code, "SELL"
          ,this.quantity, this.stocks[this.selected].currentPrice, this.clientIdService.clientId, "null", new Date('December 17, 1995 03:24:00'), "null")

        this.buyService.executeSellOrder(this.sellOrder).subscribe(data => {
          alert("Trade: Sell executed successfully");

          console.log(data);
        });

      }

      if (this.cNumber == 2) {


        this.sellOrder = new SellOrder(this.mFunds[this.selected].name, this.mFunds[this.selected].code, "SELL"
          , this.quantity, this.mFunds[this.selected].currentPrice, this.clientIdService.clientId, "null", new Date('December 17, 1995 03:24:00'), "null");


        this.buyService.executeSellOrder(this.sellOrder).subscribe(data => {
          alert("Trade: Sell executed successfully");
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
