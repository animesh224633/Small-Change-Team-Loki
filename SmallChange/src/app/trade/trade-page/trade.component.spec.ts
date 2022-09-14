import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { Observable } from 'rxjs';
import { TradeHistory } from 'src/app/models/trade-history';
import { TradeHistoryService } from 'src/app/trades/trade-history.service';

import { TradeComponent } from './trade.component';

describe('TradeComponent', () => {
  let component: TradeComponent;
  let fixture: ComponentFixture<TradeComponent>;

  function configureMockTradeService(TadeHistoryObservable: Observable<any>) {
      beforeEach(waitForAsync(() => {
          const mockTradeService = jasmine.createSpyObj('TradeHistoryService', ['getTradeHistory']);
          mockTradeService.getTradeHistory.and.returnValue(TadeHistoryObservable);

          TestBed.configureTestingModule({
              declarations: [TradeComponent],
              providers: [
                  { provide: TradeHistoryService, useValue: mockTradeService }
              ]
          })
              .compileComponents();
      }));

      beforeEach(() => {
          fixture = TestBed.createComponent(TradeComponent);
          component = fixture.componentInstance;
          fixture.detectChanges();
      });
  }
});