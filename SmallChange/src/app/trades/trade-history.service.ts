import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TradeHistory } from '../models/trade-history';

@Injectable({
  providedIn: 'root'
})
export class TradeHistoryService {

  constructor(private http:HttpClient) { }

  getTradeHistory(type:string):Observable<TradeHistory[]>{
     
      if(type!="All" && type!="all" && type!=''){
        console.log(type)
      return this.http.get<TradeHistory[]>('http://localhost:3000/tradeHistory?type='+type);
      }
      else{
        console.log("hi")
        console.log(this.http.get<TradeHistory[]>('http://localhost:3000/tradeHistory'))
      return this.http.get<TradeHistory[]>('http://localhost:3000/tradeHistory');
      }
  }
}
