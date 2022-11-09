import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { ClientWallet } from '../models/clientWallet.model';
import { PortfolioAsset } from '../models/portfolio-asset.model';
import { PortfolioMutualFunds } from '../models/portfolio-mutual-funds.model';
import { PortfolioStocks } from '../models/portfolio-stocks.model';
import { Wallet } from '../models/wallet.model';

@Injectable({
  providedIn: 'root'
})
export class PortfolioPageService {
mfs:PortfolioMutualFunds[]=[];
  constructor(private http: HttpClient) { }
  private Url = 'http://localhost:3000/';
  private portfolioEndPoint='http://localhost:8080/portfolio/';
  private smallChangeWalletEndPoint='http://localhost:8080/smallChangeWallet/';



  //methods to return stocks data
  /***********************************************************/
  getPortfolioStocks(): Observable<PortfolioStocks[]> {
    console.log('Fetching portfolio stocks');

    return this.http.get<PortfolioStocks[]>(this.Url + 'portfolioStocks').pipe(catchError(this.handleError));
  }


  getPortfolioAssets(clientId: string): Observable<PortfolioAsset[]> {
    console.log('Fetching portfolio Assets');

    return this.http.get<PortfolioAsset[]>(this.portfolioEndPoint + clientId).pipe(catchError(this.handleError));
  }

  getPortfolioMutualFunds(): Observable<PortfolioMutualFunds[]> {
    console.log('Fetching portfolio mutual funds');

    return this.http.get<PortfolioMutualFunds[]>(this.Url + 'portfolioMutualFunds').pipe(catchError(this.handleError));
  }

  getWalletAmount():Observable<Wallet> {
    return this.http.get<Wallet>(this.Url + 'walletMoney').pipe(catchError(this.handleError));
  }


  getClientWalletAmount(clientId: string):Observable<ClientWallet> {
    return this.http.get<ClientWallet>(this.smallChangeWalletEndPoint + clientId).pipe(catchError(this.handleError));
  }
  /***********************************************************/


  /********************************************** */




  //errors scenarios
  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues 
      console.error(`Backend returned code ${error.status}, body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(() => 'Unable to contact service; please try again later.');
  };
}
