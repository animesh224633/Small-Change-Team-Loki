import { ComponentFixture, fakeAsync, inject, TestBed, tick, waitForAsync } from '@angular/core/testing';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
import { BuySellService } from 'src/app/services/buy-sell.service';
import { PortfolioStocks } from 'src/app/models/portfolio-stocks.model';
import { PortfolioMutualFunds } from 'src/app/models/portfolio-mutual-funds.model';

import { of } from 'rxjs';
import { BrowserModule, By } from '@angular/platform-browser';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MatSlideToggle, MatSlideToggleModule } from '@angular/material/slide-toggle';
;
import { TradeComponent } from './trade.component';
import { TradeHistoryService } from 'src/app/trades/trade-history.service';
import { AppRoutingModule } from 'src/app/app-routing.module';

describe('TradeComponent', () => {
    let service: TradeHistoryService; 
    let httpTestingController: HttpTestingController;
beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        BrowserModule,
        AppRoutingModule
      ],
      providers:[TradeHistoryService]
    });

    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(TradeHistoryService);
  }));
    it('service should be called', () => {

        const service: TradeHistoryService = TestBed.get(TradeHistoryService);
        expect(service).toBeTruthy();
      
      });
      
      it('should return tradehistory',fakeAsync(()=>{
        
        service. getTradeHistory('','').subscribe()
        const req=httpTestingController.expectOne('http://localhost:8080/tradeHistory/')
        expect(req.request.method).toBe('GET')
        httpTestingController.verify()
        tick()
       
        
    }));
    
  

});

