import { AfterViewInit, Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PortfolioMutualFunds } from 'src/app/models/portfolio-mutual-funds.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
@Component({
  selector: 'app-portfolio-mf-table',
  templateUrl: './portfolio-mf-table.component.html',
  styleUrls: ['./portfolio-mf-table.component.css'],
})
export class PortfolioMfTableComponent implements OnInit {
  currentMfValue: any;
  totalMfInvestment: any;
  mfs: PortfolioMutualFunds[] = [];
  @Output() mfInvestment = new EventEmitter<number>();
  @Output() mfCurrentValue = new EventEmitter<number>();
  
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

  constructor(private portfolioPageService: PortfolioPageService) {

  }


  ngOnInit(): void {
  

    this.getMfs();
  }

  getMfs() {
    this.portfolioPageService.getPortfolioMutualFunds().subscribe((data) => {
      this.mfs = data;
      this.dataSource = new MatTableDataSource(this.mfs);

      this.getCurrentValue();
      this.gettotalMfInvestment();
      this.sendData();
    });
  }

  gettotalMfInvestment() {
    let totalMfInvestment = 0;
    this.mfs.forEach(function (value) {
      totalMfInvestment = totalMfInvestment + value.buyPrice * value.quantity;
    });
    this.totalMfInvestment = totalMfInvestment;
  }

  getCurrentValue() {
    let currentMfValue = 0;
    this.mfs.forEach(function (value) {
      currentMfValue =
        currentMfValue + value.currentPrice * value.quantity;
    });
    this.currentMfValue = currentMfValue;
  }
  sendData() {
    this.mfCurrentValue.emit(this.currentMfValue);
    this.mfInvestment.emit(this.totalMfInvestment);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
