import { ComponentFixture, fakeAsync, TestBed } from '@angular/core/testing';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';

import { LoginFormComponent } from './login-form.component';


describe('LoginFormComponent', () => {
  let component: LoginFormComponent;
  let fixture: ComponentFixture<LoginFormComponent>;
  let routerSpy: jasmine.SpyObj<Router>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginFormComponent ],
      providers: [
        { provide: LoginService, useValue: LoginService },
        { provide: Router, useValue: routerSpy }
    ]
    })
    .compileComponents();
  });
  routerSpy = jasmine.createSpyObj('Router', ['navigateByUrl']);
     
  beforeEach(() => {
    fixture = TestBed.createComponent(LoginFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
 

  function updateForm(userName: any,password: any ) {
    component.loginForm.controls['userName'].setValue(userName);
    component.loginForm.controls['password'].setValue(password);
  }

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  
  it('component initial state', () => {
    expect(component.submitted).toBeFalsy();
    expect(component.loginForm).toBeDefined();
    expect(component.loginForm.invalid).toBeTruthy();
    //expect(component.authError).toBeFalsy();
   // expect(component.authErrorMsg).toBeUndefined();
  });
​
  it('submitted should be true when onSubmit()', () => {
   component.onSubmit();
    expect(component.submitted).toBeTruthy();
   // expect(component.authError).toBeFalsy();
  });

​
  it('form value should update from when you change the input', (() => {
    updateForm('validUsername', 'validPassword123');
    expect(component.loginForm.value).toEqual({userName:"validUsername", password:"validPassword123"});
  }));
​
  it('Form invalid should be true when form is invalid', (() => {
    updateForm('', '');
    expect(component.loginForm.invalid).toBeTruthy();
  }));
});


