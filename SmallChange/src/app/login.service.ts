import { Injectable, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginService  {

  public loginForm!: FormGroup
  constructor(private formbuilder: FormBuilder,private http: HttpClient, private router: Router) { }

  
  login(userName:String, password:String){
    this.http.get<any>("http://localhost:3000/signupUsersList")
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

  register(name:String, userName:String, password:String){
    console.log("Enroll new member into the db");
  }
}
