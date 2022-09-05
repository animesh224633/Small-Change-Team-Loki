import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParatextComponent } from './paratext.component';

describe('ParatextComponent', () => {
  let component: ParatextComponent;
  let fixture: ComponentFixture<ParatextComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParatextComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParatextComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
