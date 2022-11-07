import { Component, OnInit } from '@angular/core';
import { ClientIdService } from 'src/app/client-id.service';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  totalInvestment=0;
  currentValue=0;
  dangerclass =false;

  constructor(private clientIdService: ClientIdService) { }

  ngOnInit(): void {
    console.log('on portfolio ', this.clientIdService.clientId);
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
