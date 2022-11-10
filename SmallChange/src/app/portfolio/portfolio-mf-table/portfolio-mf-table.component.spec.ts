import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioMfTableComponent } from './portfolio-mf-table.component';

describe('PortfolioMfTableComponent', () => {
  let component: PortfolioMfTableComponent;
  let fixture: ComponentFixture<PortfolioMfTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PortfolioMfTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PortfolioMfTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
