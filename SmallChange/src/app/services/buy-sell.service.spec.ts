import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';
import { TestBed, waitForAsync } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from '../app-routing.module';
import { BuysellComponent } from '../tradeBuySell/buysell/buysell.component';

import { BuySellService } from './buy-sell.service';

describe('BuySellService', () => {
  let service: BuySellService;
let httpTestingController: HttpTestingController;

beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        BrowserModule,
        AppRoutingModule
      ],
      providers:[BuySellService]
    });

    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(BuySellService);
  }));

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
