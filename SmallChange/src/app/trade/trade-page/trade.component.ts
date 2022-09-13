import { Component, OnInit } from '@angular/core';
import { TradeHistory } from 'src/app/models/trade-history';
import { TradeHistoryService } from 'src/app/trades/trade-history.service';


@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrls: ['./trade.component.css']
})
export class TradeComponent implements OnInit {

  trade:TradeHistory[]=[];
  queryType:string='';
  displayedColumns: string[] = ['name', 'code', 'quantity', 'type','price','asset_class'];
  dataSource=this.trade

  constructor(private tradeHistoryServie:TradeHistoryService) { }

  ngOnInit(): void {

    this.getTradeHistory();
  }
  setToBuy(){
    this.queryType="buy";
    this.getTradeHistory();
  }
  setToSell(){
    this.queryType="sell";
    
    console.log(this.queryType)
    this.getTradeHistory();
  }
  setToAll(){
    console.log("all called")
    this.queryType="All"
    this.getTradeHistory();
  }
  getTradeHistory(){
    console.log("intialized")
    this.tradeHistoryServie.getTradeHistory(this.queryType).subscribe(data=>this.trade=data)
    this.dataSource=this.trade
  }

}
