import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { BuyInstrument } from '../models/buyInstrument.model';
import { BuyOrder } from '../models/buyOrder.model';
import { ClientWallet } from '../models/clientWallet.model';
import { PortfolioMutualFunds } from '../models/portfolio-mutual-funds.model';
import { PortfolioStocks } from '../models/portfolio-stocks.model';
import { SellInstrument } from '../models/sellInstrument.model';
import { SellOrder } from '../models/sellOrder.model';
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
  constructor(private http: HttpClient) { }
  private Url = 'http://localhost:8080/orderMapper/';
  private smallChangeWalletEndPoint='http://localhost:8080/smallChangeWallet/';





  getAllAssetsForBuyTrade(): Observable<BuyInstrument[]> {
    console.log('Fetching all Assets for buying');
    return this.http
      .get<BuyInstrument[]>(this.Url + 'buy')
      .pipe(catchError(this.handleError));
  }
  getAllAssetsForSellTrade(clientId: string): Observable<SellInstrument[]> {
    console.log('Fetching all Assets for selling');
    return this.http
      .get<SellInstrument[]>(this.Url + 'sell/'+ clientId)
      .pipe(catchError(this.handleError));
  }

  executeBuyOrder(data: BuyOrder): Observable<any> {
    console.log('Sending buyOrder');
    return this.http
      .post<any>(this.Url + 'buyUpdate',data)
      .pipe(catchError(this.handleError));
  }
  executeSellOrder(data: SellOrder): Observable<any> {
    console.log('Sending buyOrder');
    return this.http
      .post<any>(this.Url + 'sellUpdate',data)
      .pipe(catchError(this.handleError));
  }
  getClientWalletAmount(clientId: string):Observable<ClientWallet> {
    return this.http.get<ClientWallet>(this.smallChangeWalletEndPoint + clientId).pipe(catchError(this.handleError));
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
