import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/login.service';
import { BuySellService } from 'src/app/services/buy-sell.service';
import { PortfolioStocks } from 'src/app/models/portfolio-stocks.model';
import { PortfolioMutualFunds } from 'src/app/models/portfolio-mutual-funds.model';

import { of } from 'rxjs';
import { By } from '@angular/platform-browser';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MatSlideToggle, MatSlideToggleModule } from '@angular/material/slide-toggle';
import { SellComponent } from './sell.component';

describe('SellComponent', () => {
  let component: SellComponent;
  let fixture: ComponentFixture<SellComponent>;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule,MatSlideToggleModule], 
      declarations: [ SellComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

it('toggle Button click event using spyon', () => {
  spyOn(component, 'toggle');
  component.toggle('');

  fixture.detectChanges();
  expect(component.toggle).toHaveBeenCalled();
});
it('service should be called', () => {

  const service: BuySellService = TestBed.get(BuySellService);
  expect(service).toBeTruthy();

});


});
