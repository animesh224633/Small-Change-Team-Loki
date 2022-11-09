import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientIdService } from '../client-id.service';
import { LoginService } from '../login.service';
import { Login } from '../models/login';
import { LoginClientDetails } from '../models/loginClientDetails';
import { RegistrationClientDetails } from '../models/registrationClientDetails';
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
  loginClientDetails?: LoginClientDetails;
  registrationClientDetails?: RegistrationClientDetails;

  errorMessage: string = '';
  showError: boolean = false;
  showErrorRegistration: boolean = false;

  constructor(private router: Router, private loginService: LoginService, private clientIdService: ClientIdService) {}

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
    if (this.loginValid == true) {
      this.submitted = true;
      this.loginValid = true;
      console.log('');
    }
  }

  loginFormSubmit() {
    this.showErrorRegistration = false;
    this.submitted = true;
    this.loginValid = true;
    // console.log(this.loginForm.value);

    this.loginClientDetails = new LoginClientDetails(
      this.loginForm.value.userName,
      this.loginForm.value.password
    );
    // console.log(this.loginClientDetails);

    this.loginService.login(this.loginClientDetails).subscribe((data) => {
      console.log('looooo', data);
      if (data.clientId == null) {
        this.showError = true;
      } else {
        this.clientIdService.clientId = data.clientId;
        console.log('injectable client id is ', this.clientIdService.clientId);
        this.router.navigate(['portfolio']);
      }
    });
  }

  registrationFormSubmit() {
    this.showErrorRegistration = false;
    console.log(this.registrationForm.value);

    this.registrationClientDetails = new RegistrationClientDetails(
      this.registrationForm.value.userName,
      this.registrationForm.value.password,
      this.registrationForm.value.name
    );

    console.log(this.registrationClientDetails);
    this.loginRegistrationCardSwitch = false;
    this.loginService
      .register(this.registrationClientDetails).subscribe((data) => {
        console.log('message ',data.message);
        if(data.message ==='Already Present'){
          this.showErrorRegistration = true;
          console.log('inside ',this.showErrorRegistration);
        }
      });
  }
  toggleToRegistrationForm() {
      this.showError = false;
      this.loginRegistrationCardSwitch = true;
  }
}
