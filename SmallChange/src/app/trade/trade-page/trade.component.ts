import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ClientIdService } from 'src/app/client-id.service';
import { TradeHistory } from 'src/app/models/trade-history';
import { TradeHistoryService } from 'src/app/trades/trade-history.service';


@Component({
  selector: 'app-trade',
  templateUrl: './trade.component.html',
  styleUrls: ['./trade.component.css']
})
export class TradeComponent implements OnInit {

  trades: TradeHistory[] = [];
  dataSource: any;

  displayedColumns: string[] = [
    'name',
    'code',
    'quantity',
    'type',
    'price',
    'assetClass'
  ];

  constructor(private tradeHistoryService: TradeHistoryService, private clientIdService: ClientIdService) {}

  ngOnInit(): void {
   // console.log('on trade history ', this.clientIdService.clientId);
    this.getTrades();
  }

  getTrades() {
    this.tradeHistoryService.getTradeHistory('', this.clientIdService.clientId).subscribe((data) => {
      this.trades = data;
      console.log("helllo seneha");
      console.log(data);
      this.dataSource = new MatTableDataSource(this.trades);
    });
  }
  setToBuy(){
    this.tradeHistoryService.getTradeHistory('buy', this.clientIdService.clientId).subscribe((data) => {
      this.trades = data;
      this.dataSource = new MatTableDataSource(this.trades);
    });
  }
  setToSell(){
    this.tradeHistoryService.getTradeHistory('sell', this.clientIdService.clientId).subscribe((data) => {
      this.trades = data;
      this.dataSource = new MatTableDataSource(this.trades);
    });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
