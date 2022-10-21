import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {
  public loginValid = true;
  public submitted = false;
  public username = '';
  public password = '';
  loginForm!: FormGroup;
  registrationForm!: FormGroup;
  public loginRegistrationCardSwitch: boolean = false;

  errorMessage: string = '';
  showError: boolean = false;

  constructor(private router: Router, private loginService: LoginService) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      userName: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(20),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9a-zA-Z]*$'),
        Validators.minLength(6),
        Validators.maxLength(24),
      ]),
    });

    this.registrationForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      userName: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(20),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9a-zA-Z]*$'),
        Validators.minLength(6),
        Validators.maxLength(24),
      ]),
    });
  }
  get userNameField(): any {
    return this.loginForm.get('userName');
  }
  get passwordField(): any {
    return this.loginForm.get('password');
  }

  get userNameFieldRegister(): any {
    return this.registrationForm.get('userName');
  }
  get passwordFieldRegister(): any {
    return this.registrationForm.get('password');
  }

  public onSubmit(): void {
    if(this.loginValid == true){
    this.submitted=true;
    this.loginValid = true;
    console.log('');
    }
  }

  loginFormSubmit() {
    this.submitted=true;
    this.loginValid=true;
    console.log(this.loginForm.value);
    this.loginService.login().subscribe((data) => {
        data.find((a: any) => {
          if (
            a.email == this.loginForm.value.userName &&
            a.password == this.loginForm.value.password
          )
            this.router.navigate(['portfolio']);
          else this.showError = true;
        });
      });
  }

  registrationFormSubmit() {
    console.log(this.registrationForm.value);
    this.loginRegistrationCardSwitch = false;
    this.loginService
      .register(
        this.registrationForm.value.name,
        this.registrationForm.value.userName,
        this.registrationForm.value.password
      )
      .subscribe((data) => {
        console.log(data);
      });
  }
  toggleToRegistrationForm() {
    this.showError = false;
    this.loginRegistrationCardSwitch = true;
  }
}
