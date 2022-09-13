import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PortfolioStocks } from 'src/app/models/portfolio-stocks.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';

@Component({
  selector: 'app-portfolio-stock-table',
  templateUrl: './portfolio-stock-table.component.html',
  styleUrls: ['./portfolio-stock-table.component.css'],
})
export class PortfolioStockTableComponent implements OnInit {
  currentInvestment: any;
  totalInvestment: any;
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
      this.getTotalInvestment();
    });
  }

  getTotalInvestment() {
    let totalInvestment = 0;
    this.stocks.forEach(function (value) {
      totalInvestment = totalInvestment + value.buyPrice * value.quantity;
    });
    this.totalInvestment = totalInvestment;
  }

  getCurrentValue() {
    let currentInvestment = 0;
    this.stocks.forEach(function (value) {
      currentInvestment =
        currentInvestment + value.currentPrice * value.quantity;
    });
    this.currentInvestment = currentInvestment;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
