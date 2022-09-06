import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { min } from 'rxjs';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  loginForm!: FormGroup;
  
  errorMessage: string = "";

  constructor(private router: Router, private loginService:LoginService) { }
  
  

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      userName: new FormControl('', [Validators.required, Validators.minLength(4),Validators.maxLength(20)]),
      password: new FormControl('', [Validators.required, Validators.pattern("^[0-9a-zA-Z]*$"),Validators.minLength(6), Validators.maxLength(24)])
    })


  }
  get userNameField(): any {
    return this.loginForm.get('userName');
  }
  get passwordField(): any {
    return this.loginForm.get('password');
  }
  loginFormSubmit() {
    console.log("hi")
    console.log(this.loginForm.value.userName);
    this.loginService.login(this.loginForm.value.userName,this.loginForm.value.password);
  }
  }


