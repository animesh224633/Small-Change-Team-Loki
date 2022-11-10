import { async, ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { Router } from '@angular/router';

import { PreferencesPageComponent } from './preferences-page.component';

describe('PreferencesPageComponent', () => {
  let component: PreferencesPageComponent;
  let fixture: ComponentFixture<PreferencesPageComponent>;
  let router = Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreferencesPageComponent ]
    })
   
    .compileComponents();

    fixture = TestBed.createComponent(PreferencesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
it('should call PreferenceSubmit when clicked', fakeAsync(() => {
  spyOn(component, 'PreferenceSubmit');
  let button = fixture.debugElement.nativeElement.querySelector('button');
  button.click();
  tick();
  expect(component.PreferenceSubmit).toHaveBeenCalled();
}));
 
});
