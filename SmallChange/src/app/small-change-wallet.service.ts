import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { BankAccountDetails } from './models/BankAccountDetails';
import { Login } from './models/login';
import { WalletUpdateValues } from './models/walletUpdateValues';

@Injectable({
  providedIn: 'root',
})
export class SmallChangeWalletService {
  constructor(private http: HttpClient) {}

  getBankAccountDetails(clientId: string): Observable<BankAccountDetails[]> {
    return this.http
      .get<BankAccountDetails[]>(
        'http://localhost:8080/smallChangeWallet/getAccount/'+clientId
      )
      .pipe(catchError(this.handleError));
  }

  updateRechargeDetails(data: WalletUpdateValues): Observable<Login> {
    return this.http
      .post<Login>('http://localhost:8080/smallChangeWallet/update', data)
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
