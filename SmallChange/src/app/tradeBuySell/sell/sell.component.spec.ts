import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BuySellService } from 'src/app/services/buy-sell.service';

import { SellComponent } from './sell.component';

describe('SellComponent', () => {
  let component: SellComponent;
  let fixture: ComponentFixture<SellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SellComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /*it('should create', () => {
    expect(component).toBeTruthy();
  });*/
});
