import { AfterViewInit, Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PortfolioMutualFunds } from 'src/app/models/portfolio-mutual-funds.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { ClientIdService } from 'src/app/client-id.service';
import { PortfolioAsset } from 'src/app/models/portfolio-asset.model';
@Component({
  selector: 'app-portfolio-mf-table',
  templateUrl: './portfolio-mf-table.component.html',
  styleUrls: ['./portfolio-mf-table.component.css'],
})
export class PortfolioMfTableComponent implements OnInit {
  currentMfValue: any;
  totalMfInvestment: any;
  mfs: PortfolioAsset[] = [];
  @Output() mfInvestment = new EventEmitter<number>();
  @Output() mfCurrentValue = new EventEmitter<number>();
  isMfTableEmpty=false;
  
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
input: any;

  constructor(private portfolioPageService: PortfolioPageService ,private clientIdService: ClientIdService) {

  }


  ngOnInit(): void {
    this.getMfs();
  }

  getMfs() {
    this.portfolioPageService.getPortfolioAssets(this.clientIdService.clientId).subscribe((data) => {
      this.mfs = data.filter(asset => asset.category.toUpperCase()=="MUTUALFUND");
      if(this.mfs.length==0){
        this.isMfTableEmpty=true;
      }
      else{
        this.isMfTableEmpty=false;
      }
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
