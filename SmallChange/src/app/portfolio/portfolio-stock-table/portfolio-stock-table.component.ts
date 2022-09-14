import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PortfolioStocks } from 'src/app/models/portfolio-stocks.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';

@Component({
  selector: 'app-portfolio-stock-table',
  templateUrl: './portfolio-stock-table.component.html',
  styleUrls: ['./portfolio-stock-table.component.css'],
})
export class PortfolioStockTableComponent implements OnInit {
  currentStocksValue: any;
  totalStockInvestment: any;
  @Output() stockInvestment = new EventEmitter<number>();
  @Output() stockCurrentValue = new EventEmitter<number>();

  stocks: PortfolioStocks[] = [];
  dataSource: any;

  displayedColumns: string[] = [
    'name',
    'code',
    'quantity',
    'buy-price',
    'current-price',
    'invested-amount',
    'current-value',
    'profit/loss',
    'percent-change',
  ];

  constructor(private portfolioPageService: PortfolioPageService) {}

  ngOnInit(): void {
    this.getStocks();
  }

  getStocks() {
    this.portfolioPageService.getPortfolioStocks().subscribe((data) => {
      this.stocks = data;
      this.dataSource = new MatTableDataSource(this.stocks);
      this.getCurrentValue();
      this.gettotalStockInvestment();
      this.sendData();
    });
  }

  gettotalStockInvestment() {
    let totalStockInvestment = 0;
    this.stocks.forEach(function (value) {
      totalStockInvestment =
        totalStockInvestment + value.buyPrice * value.quantity;
    });
    this.totalStockInvestment = totalStockInvestment;
  }

  getCurrentValue() {
    let currentStocksValue = 0;
    this.stocks.forEach(function (value) {
      currentStocksValue =
        currentStocksValue + value.currentPrice * value.quantity;
    });
    this.currentStocksValue = currentStocksValue;
  }

  sendData() {
    this.stockCurrentValue.emit(this.currentStocksValue);
    this.stockInvestment.emit(this.totalStockInvestment);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
