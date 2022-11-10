import { HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { async, ComponentFixture, fakeAsync, inject, TestBed, tick } from '@angular/core/testing';
import { BuySellService } from 'src/app/services/buy-sell.service';
import {HttpClientModule} from '@angular/common/http';

import { BuysellComponent } from './buysell.component';

describe('BuysellComponent', () => {
  let component: BuysellComponent;
  let fixture: ComponentFixture<BuysellComponent>;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule], 
      declarations: [ BuysellComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuysellComponent);
    component = fixture.componentInstance;
  //  fixture.detectChanges();
  });

  
  it('should create', () => {
    expect(component).toBeTruthy();
  });

});
