import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  totalInvestment=0;
  currentValue=0;
  dangerclass =false;

  constructor() { }

  ngOnInit(): void {
    if(this.totalInvestment <= this.currentValue){
      this.dangerclass=false;
    }
    else if(this.totalInvestment > this.currentValue){
      this.dangerclass=true;
    }
  }

  getStockInvestment($event: number){
    console.log('bla bla',$event);
this.totalInvestment+=$event;
  }
  getMfInvestment($event: number){
    this.totalInvestment+=$event;
  }
  getMfCurrentValue($event: number){
this.currentValue+=$event;
  }
  getStockCurrentValue($event: number){
    this.currentValue+=$event;
  }
}
