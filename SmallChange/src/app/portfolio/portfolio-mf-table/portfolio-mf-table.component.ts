import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PortfolioMutualFunds } from 'src/app/models/portfolio-mutual-funds.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';

@Component({
  selector: 'app-portfolio-mf-table',
  templateUrl: './portfolio-mf-table.component.html',
  styleUrls: ['./portfolio-mf-table.component.css'],
})
export class PortfolioMfTableComponent implements OnInit {
  currentInvestment: any;
  totalInvestment: any;
  mfs: PortfolioMutualFunds[] = [];

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
  dataSource: any;

  constructor(private portfolioPageService: PortfolioPageService) {}

  ngOnInit(): void {
    this.getMfs();
  }

  getMfs() {
    this.portfolioPageService.getPortfolioMutualFunds().subscribe((data) => {
      this.mfs = data;
      this.dataSource = new MatTableDataSource(this.mfs);
      this.getCurrentValue();
      this.getTotalInvestment();
    });
  }

  getTotalInvestment() {
    let totalInvestment = 0;
    this.mfs.forEach(function (value) {
      totalInvestment = totalInvestment + value.buyPrice * value.quantity;
    });
    this.totalInvestment = totalInvestment;
  }

  getCurrentValue() {
    let currentInvestment = 0;
    this.mfs.forEach(function (value) {
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
