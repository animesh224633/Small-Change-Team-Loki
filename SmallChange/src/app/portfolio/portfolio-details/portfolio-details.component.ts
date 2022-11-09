import { Component, Input, OnInit } from '@angular/core';
import { ClientIdService } from 'src/app/client-id.service';
import { Wallet } from 'src/app/models/wallet.model';
import { PortfolioPageService } from 'src/app/services/portfolio-page.service';

@Component({
  selector: 'app-portfolio-details',
  templateUrl: './portfolio-details.component.html',
  styleUrls: ['./portfolio-details.component.css']
})
export class PortfolioDetailsComponent implements OnInit {
@Input() totalInvestment=0;
@Input() currentValue=0 ;
walletAmount=0;

constructor(private portfolioPageService: PortfolioPageService, private clientIdService: ClientIdService) { }

  ngOnInit(): void {
    this.getwalletAmount();
    
  }
  getwalletAmount(){
    this.portfolioPageService.getClientWalletAmount(this.clientIdService.clientId).subscribe((data) => {
      console.log(data);
      
      this.walletAmount = data.clientSmallChangeWallet;
     
  });}

}
