import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { TradeHistory } from '../models/trade-history';

@Injectable({
  providedIn: 'root'
})
export class TradeHistoryService {

  constructor(private http:HttpClient) { }

  getTradeHistory(type:string):Observable<TradeHistory[]>{
     
      if(type!="All" && type!="all" && type!=''){
        console.log(type)
      return this.http.get<TradeHistory[]>('http://localhost:3000/tradeHistory?type='+type).pipe(catchError(this.handleError));
      }
      else{
        console.log("hi")
      return this.http.get<TradeHistory[]>('http://localhost:3000/tradeHistory').pipe(catchError(this.handleError));
      }
  }

  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
        // A client-side or network error occurred. Handle it.
        console.error('An error occurred:', error.error.message);
    } else {
        // The backend returned an unsuccessful response code.
        // The response body may contain clues 
        console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError('Unable to contact service; please try again later.');
};
}
