import { Injectable, OnInit } from '@angular/core';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Login } from './models/login';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginService  {

  
  logins= new Login();
  public loginForm!: UntypedFormGroup
  constructor(private formbuilder: UntypedFormBuilder,private http: HttpClient, private router: Router) { }

  
  login(userName:String, password:String){
    this.http.get<any>("http://localhost:3000/signup")
    .subscribe(res=>{
      const user = res.find((a:any)=>{
        return a.email == userName && a.password == password 
      });
      if(user){
        alert('Login Succesful');
        this.router.navigate(["loginPage"])
      }else{
        alert("user not found")
      }
    },err=>{
      alert("Something went wrong")
    })
  }



  register(name:String, userName:String, password:String): Observable<any>{
    this.logins.name=name;
    this.logins.email=userName;
    this.logins.password=password;
   
    return this.http.post("http://localhost:3000/signup",this.logins)
  }
}
