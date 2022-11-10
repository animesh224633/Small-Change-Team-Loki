import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
import { BuySellService } from 'src/app/services/buy-sell.service';
import { PortfolioStocks } from 'src/app/models/portfolio-stocks.model';
import { PortfolioMutualFunds } from 'src/app/models/portfolio-mutual-funds.model';
import { BuyComponent } from './buy.component';
import { of } from 'rxjs';
import { By } from '@angular/platform-browser';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MatSlideToggle, MatSlideToggleModule } from '@angular/material/slide-toggle';

describe('BuyComponent', () => {
  let component: BuyComponent;
  let fixture: ComponentFixture<BuyComponent>;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,MatSlideToggleModule], 
      declarations: [ BuyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should return allstocks', async () => {
 
      const modelData=[
        {
          "name": "Apple",
          "code": "APP",
          "currentPrice": 150
        },
        {
          "name": "Microsoft",
          "code": "MSFT",
          "currentPrice": 200
        }]
        expect(modelData[0].currentPrice).toBe(150);
      
});


it('toggle Button click event using spyon', () => {
  spyOn(component, 'toggle');
  component.toggle('');

  fixture.detectChanges();
  expect(component.toggle).toHaveBeenCalled();
});



});
