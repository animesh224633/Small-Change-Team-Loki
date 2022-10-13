import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioStockTableComponent } from './portfolio-stock-table.component';

describe('PortfolioStockTableComponent', () => {
  let component: PortfolioStockTableComponent;
  let fixture: ComponentFixture<PortfolioStockTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortfolioStockTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortfolioStockTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /*it('should create', () => {
    expect(component).toBeTruthy();
  });*/
});
