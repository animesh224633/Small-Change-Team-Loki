import { Component, Input, OnInit } from '@angular/core';
import { Wallet } from 'src/app/models/wallet.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';

@Component({
  selector: 'app-portfolio-details',
  templateUrl: './portfolio-details.component.html',
  styleUrls: ['./portfolio-details.component.css']
})
export class PortfolioDetailsComponent implements OnInit {
@Input() totalInvestment:number | undefined;
@Input() currentValue:number | undefined ;
walletAmount:number | undefined;

constructor(private portfolioPageService: PortfolioPageService) { }

  ngOnInit(): void {
    this.getwalletAmount();
    
  }
  getwalletAmount(){
    this.portfolioPageService.getWalletAmount().subscribe((data) => {
      console.log(data);
      
      this.walletAmount = data.amount;
     
  });}

}
