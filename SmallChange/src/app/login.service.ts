import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Login } from './models/login';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  logins = new Login();
  constructor(private http: HttpClient, private router: Router) {}

  login(userName: String, password: String) {
    this.http.get<any>('http://localhost:3000/signup').subscribe(
      (res) => {
        const user = res.find((a: any) => {
          return a.email == userName && a.password == password;
        });
        if (user) {
          alert('Login Succesful');
          this.router.navigate(['portfolio']);
        } else {
          alert('user not found');
        }
      },
      (err) => {
        alert('The service is not up. Please check again');
      }
    );
  }

  register(name: String, userName: String, password: String): Observable<any> {
    this.logins.name = name;
    this.logins.email = userName;
    this.logins.password = password;

    return this.http.post<Observable<any>>(
      'http://localhost:3000/signup',
      this.logins
    );
  }

  trial(){
    this.http.get('http://localhost:3000/portfolio').subscribe(res => {
      console.log('ccc ', res)
  });
  }
}
