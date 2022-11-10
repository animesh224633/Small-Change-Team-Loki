import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreferencesSubmitPageComponent } from './preferences-submit-page.component';

describe('PreferencesSubmitPageComponent', () => {
  let component: PreferencesSubmitPageComponent;
  let fixture: ComponentFixture<PreferencesSubmitPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreferencesSubmitPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreferencesSubmitPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
