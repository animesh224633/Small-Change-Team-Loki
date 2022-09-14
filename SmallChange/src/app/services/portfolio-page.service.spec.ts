import { fakeAsync, inject, TestBed, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Observable, of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

import { PortfolioPageService } from './portfolio-page.service';
import { PortfolioStocks } from '../models/portfolio-stocks.model';
import { PortfolioMutualFunds } from '../models/portfolio-mutual-funds.model';
import { Wallet } from '../models/wallet.model';

describe('PortfolioPageService', () => {
  const mockStocks: PortfolioStocks[] = [{
    "name": "Amazon",
    "code": "AMZN",
    "quantity": 2,
    "buyPrice": 130.5,
    "currentPrice": 135
  },
  {
    "name": "Microsoft",
    "code": "MSFT",
    "quantity": 3,
    "buyPrice": 150,
    "currentPrice": 170
  }];

  const mockMf: PortfolioMutualFunds[] = [{
    "name": "Amazon",
    "code": "AMZN",
    "quantity": 2,
    "buyPrice": 130.5,
    "currentPrice": 135
  },
  {
    "name": "Microsoft",
    "code": "MSFT",
    "quantity": 3,
    "buyPrice": 150,
    "currentPrice": 170
  }];
  const mockwallet:Wallet={
    amount: 3000
  }
  

  let service: PortfolioPageService;
  let httpTestingController: HttpTestingController;
  const serviceUrl = 'http://localhost:3000/';


  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [
      HttpClientTestingModule,
    ]});
    service = TestBed.inject(PortfolioPageService);
    httpTestingController = TestBed.inject(HttpTestingController);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should return Portfolio Mutual Funds', inject([PortfolioPageService], fakeAsync((service: PortfolioPageService) => {
    let mfs:  PortfolioMutualFunds[] = [];
    service.getPortfolioMutualFunds()
        .subscribe(data => mfs = data);
    const req = httpTestingController.expectOne(serviceUrl + 'portfolioMutualFunds');
    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');
    // Respond with mock data, causing Observable to resolve.
    req.flush(mockMf);
    // Assert that there are no outstanding requests.
    httpTestingController.verify();
    // Cause all Observables to complete and check the results
    tick();
    expect(mfs).toBeTruthy();
    expect(mfs.length).toBe(2);
    expect(mfs[0].name).toBe('Amazon');
})));


it('should return Portfolio Stocks', inject([PortfolioPageService], fakeAsync((service: PortfolioPageService) => {
  let stocks: PortfolioStocks[] = [];
  service.getPortfolioStocks()
      .subscribe(data => stocks = data);
  const req = httpTestingController.expectOne(serviceUrl + 'portfolioStocks');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('GET');
  // Respond with mock data, causing Observable to resolve.
  req.flush(mockStocks);
  // Assert that there are no outstanding requests.
  httpTestingController.verify();
  // Cause all Observables to complete and check the results
  tick();
  expect(stocks).toBeTruthy();
  expect(stocks.length).toBe(2);
  expect(stocks[0].name).toBe('Amazon');
})));




it('should return wallet amount', inject([PortfolioPageService], fakeAsync((service: PortfolioPageService) => {
  let wallet: Wallet = {
    amount: 0
  };
  service.getWalletAmount()
      .subscribe(data => wallet = data);
  const req = httpTestingController.expectOne(serviceUrl + 'walletMoney');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('GET');
  // Respond with mock data, causing Observable to resolve.
  req.flush(mockwallet);
  // Assert that there are no outstanding requests.
  httpTestingController.verify();
  // Cause all Observables to complete and check the results
  tick();
  expect(wallet).toBeTruthy();
  expect(wallet.amount).toBe(3000);
})));


it('should handle a 404 error', inject([PortfolioPageService], fakeAsync((service: PortfolioPageService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.getPortfolioStocks()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne(serviceUrl + 'portfolioStocks');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('GET');
  // Respond with error
  req.flush('Forced 404', {
      status: 404,
      statusText: 'Not Found'
  });
  // Assert that there are no outstanding requests.
  httpTestingController.verify();
  // Cause all Observables to complete and check the results
  tick();
  expect(errorReply).toBe('Unable to contact service; please try again later.');
  expect(errorHandlerSpy).toHaveBeenCalled();
  errorResp = errorHandlerSpy.calls.argsFor(0)[0];
  expect(errorResp.status).toBe(404);
})));

it('should handle network error', inject([PortfolioPageService], fakeAsync((service: PortfolioPageService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.getPortfolioStocks()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne(serviceUrl + 'portfolioStocks');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('GET');
  // Create mock ErrorEvent, raised when something goes wrong at the network level.
  // Connection timeout, DNS error, offline, etc
  const mockError = new ErrorEvent('Network error', {
      message: 'simulated network error',
  });
  // Respond with mock error
  req.error(mockError);
  // Respond with error
  // Assert that there are no outstanding requests.
  httpTestingController.verify();
  // Cause all Observables to complete and check the results
  tick();
  expect(errorReply).toBe('Unable to contact service; please try again later.');
  expect(errorHandlerSpy).toHaveBeenCalled();
  errorResp = errorHandlerSpy.calls.argsFor(0)[0];
  expect(errorResp.error.message).toBe('simulated network error');
})));

});
