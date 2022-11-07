import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Login } from './models/login';
import { LoginDetailsRecieve } from './models/loginDetailsRecieve';
import { catchError, Observable, throwError } from 'rxjs';
import { LoginClientDetails } from './models/loginClientDetails';
import { RegistrationClientDetails } from './models/registrationClientDetails';

@Injectable({
  providedIn: 'root',
})
export class LoginService {


  constructor(private http: HttpClient, private router: Router) {}

  login(data: LoginClientDetails): Observable<LoginDetailsRecieve> {
    return this.http
      .post<LoginDetailsRecieve>('http://localhost:8080/clientAuthentication/login', data)
      .pipe(catchError(this.handleError));
  }

  register(data: RegistrationClientDetails): Observable<Login> {
    return this.http
      .post<Login>('http://localhost:8080/clientAuthentication/registration', data)
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
