import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Login } from './models/login';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {


  constructor(private http: HttpClient, private router: Router) {}

  login(): Observable<Login[]> {
    return this.http
      .get<Login[]>('http://localhost:3000/signup')
      .pipe(catchError(this.handleError));
  }

  register(name: String, userName: String, password: String): Observable<Login> {
    const logins = new Login();
    logins.id=30;
    logins.name = name;
    logins.email = userName;
    logins.password = password;
    const headers = new HttpHeaders({
      'Content-type': 'application/json'
  });

    return this.http
      .post<Login>('http://localhost:3000/signup', logins ,{ headers: headers })
      .pipe(catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, body was: ${error.error}`
      );
    }
    return throwError(
      () => 'Unable to contact service; please try again later.'
    );
  }
}
