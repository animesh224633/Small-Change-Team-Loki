import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

const ELEMENT_DATA = [{
  "name": "Amazon",
  "code": "AMZN",
  "quantity": 2,
  "buyPrice": 130.5,
  "currentPrice": 135
},
{
  "name": "Microsoft",
  "code": "MSFT",
  "quantity": 3,
  "buyPrice": 150,
  "currentPrice": 170
}
];

@Component({
  selector: 'app-portfolio-mf-table',
  templateUrl: './portfolio-mf-table.component.html',
  styleUrls: ['./portfolio-mf-table.component.css']
})
export class PortfolioMfTableComponent implements OnInit {

  currentInvestment :any;
  totalInvestment :any;

  getTotalInvestment(){
    let totalInvestment = 0;
    ELEMENT_DATA.forEach(function (value) {
      totalInvestment = totalInvestment + (value.buyPrice*value.quantity);
    });
    this.totalInvestment = totalInvestment; 
  }

  getCurrentValue(){
    let currentInvestment = 0;
    ELEMENT_DATA.forEach(function (value) {
      currentInvestment = currentInvestment + (value.currentPrice*value.quantity);
    });
    this.currentInvestment =currentInvestment; 
  }


  constructor() { }

  ngOnInit(): void {
    this.getCurrentValue();
    this.getTotalInvestment();
  }

  displayedColumns: string[] = ['name', 'code', 'quantity', 'buy-price', 'current-price', 
  'invested-amount', 'current-value', 'profit/loss', 'percent-change'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
