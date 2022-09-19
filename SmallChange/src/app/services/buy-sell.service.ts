import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { PortfolioMutualFunds } from '../models/portfolio-mutual-funds.model';
import { PortfolioStocks } from '../models/portfolio-stocks.model';
import { Wallet } from '../models/wallet.model';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root',
})
export class BuySellService {
  
  portfolioMfs: PortfolioMutualFunds[] = [];
  portfolioStocks: PortfolioStocks[] = [];
  constructor(private http: HttpClient) {}
  private Url = 'http://localhost:3000/';

  getPortfolioStocks(): Observable<PortfolioStocks[]> {
    console.log('Fetching portfolio stocks');
    return this.http
      .get<PortfolioStocks[]>(this.Url + 'portfolioStocks')
      .pipe(catchError(this.handleError));
  }

  getPortfolioMutualFunds(): Observable<PortfolioMutualFunds[]> {
    console.log('Fetching portfolio mutual funds');
    return this.http
      .get<PortfolioMutualFunds[]>(this.Url + 'portfolioMutualFunds')
      .pipe(catchError(this.handleError));
  }

  getAllMutualFunds(): Observable<PortfolioMutualFunds[]> {
    console.log('Fetching all mutual funds');
    return this.http
      .get<PortfolioMutualFunds[]>(this.Url + 'allMutualFunds')
      .pipe(catchError(this.handleError));
  }

  getAllStocks(): Observable<PortfolioStocks[]> {
    console.log('Fetching all mutual funds');
    return this.http
      .get<PortfolioStocks[]>(this.Url + 'allStocks')
      .pipe(catchError(this.handleError));
  }

  updatePortfolioMutualFunds(): Observable<PortfolioMutualFunds[]> {
    const body = { 'sample ': 'data' };
    return this.http
      .put<PortfolioMutualFunds[]>(this.Url + 'portfolioMutualFunds', body)
      .pipe(catchError(this.handleError));
  }

  updatePortfolioStocks(stock:PortfolioStocks): Observable<any> {
    if(stock.quantity!=0){
      
    console.log(`${this.Url}portfolioStocks?name=${stock.name}`);
    console.log(stock);
    

    return this.http
      .put<PortfolioStocks>(`${this.Url}portfolioStocks?name=${stock.name}`, stock, httpOptions)
      .pipe(catchError(this.handleError));

    }
    else{
      return this.http
      .delete<PortfolioStocks>(`${this.Url}/portfolioStocks?name=${stock.name}`)
      .pipe(catchError(this.handleError));

    }
    
  
}
  updatePortfolioStocksDelete(stock:PortfolioStocks): Observable<any> {
    let url=this.Url+"portfolioStocks/name="+stock.name;
    return this.http
      .delete<PortfolioStocks>(url)
      .pipe(catchError(this.handleError));
  }

  getWalletAmount(): Observable<Wallet> {
    return this.http
      .get<Wallet>(this.Url + 'walletMoney')
      .pipe(catchError(this.handleError));
  }

  updateWalletAmount(walletMoney: any): Observable<Wallet> {
    const body = { 'amount': walletMoney };
    return this.http
      .put<Wallet>(this.Url + 'walletMoney', body)
      .pipe(catchError(this.handleError));
  }

  //errors scenarios
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues
      console.error(
        `Backend returned code ${error.status}, body was: ${error.error}`
      );
    }
    // return an observable with a user-facing error message
    return throwError(
      () => 'Unable to contact service; please try again later.'
    );
  }
}
