import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Login } from './models/login';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  logins = new Login();

  constructor(private http: HttpClient, private router: Router) {}

  login(): Observable<Login[]> {
    return this.http
      .get<Login[]>('http://localhost:3000/signup')
      .pipe(catchError(this.handleError));
  }

  register(name: String, userName: String, password: String): Observable<any> {
    this.logins.name = name;
    this.logins.email = userName;
    this.logins.password = password;

    return this.http
      .post<Observable<any>>('http://localhost:3000/signup', this.logins)
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
