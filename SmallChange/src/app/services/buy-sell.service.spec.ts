import { HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, inject, TestBed, tick, waitForAsync } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { data } from 'cypress/types/jquery';
import { AppRoutingModule } from '../app-routing.module';
import { BuyOrder } from '../models/buyOrder.model';
import { PortfolioMutualFunds } from '../models/portfolio-mutual-funds.model';
import { PortfolioStocks } from '../models/portfolio-stocks.model';
import { SellOrder } from '../models/sellOrder.model';
import { BuysellComponent } from '../tradeBuySell/buysell/buysell.component';

import { BuySellService } from './buy-sell.service';
describe('BuySellService', () => {
  let service: BuySellService;
  let serviceUrl1:'http://localhost:3000/portfolioStocks';
  let httpTestingController: HttpTestingController;


beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        BrowserModule,
        AppRoutingModule
      ],
      providers:[BuySellService]
    });

    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(BuySellService);
  }));
  /*it('should return portfoliostocks',fakeAsync(()=>{
    const modelData=[{
        "name": "Apple",
        "code": "APP",
        "quantity": 2,
        "buyPrice": 130.5,
        "currentPrice": 150,
        "id":100
      }]
    service. getPortfolioStocks().subscribe()
    const req=httpTestingController.expectOne('http://localhost:3000/portfolioStocks')
    expect(req.request.method).toBe('GET')
    req.flush(modelData)
    httpTestingController.verify()
    tick()
    expect(modelData[0].code).toBe("APP");
    
}));
*/
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should handle a 404 error for get AllAssetsForBuyTrade', inject([BuySellService], fakeAsync((service:BuySellService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service.  getAllAssetsForBuyTrade()
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/buy');
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
it('should handle network error for get AllAssetsForBuyTrade', inject([BuySellService], fakeAsync((service: BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service. getAllAssetsForBuyTrade()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e: string) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/buy');
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
it('should handle a 404 error for get AllAssetsForSellTrade', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.  getAllAssetsForSellTrade('')
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/sell/');
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
it('should handle network error for get AllAssetsForSellTrade', inject([BuySellService], fakeAsync((service: BuySellService) => {
let errorResp: HttpErrorResponse;
let errorReply: string = '';
const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
service. getAllAssetsForSellTrade('')
    .subscribe({
        next: () => fail('Should not succeed'),
        error: (e: string) => errorReply = e
    });
const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/sell/');
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
it('should handle a 404 error for  getClientWalletAmount', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.getClientWalletAmount('')
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:8080/smallChangeWallet/');
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

it('should handle network error for getClientWalletAmount', inject([BuySellService], fakeAsync((service: BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service. getClientWalletAmount('')
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:8080/smallChangeWallet/');
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
it('should handle a 404 error for pexecuteSellOrder', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.executeSellOrder(new SellOrder('','','',1,1,'','',new Date(),''))
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/sellUpdate');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('POST');
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
it('should handle network error for executeSellOrder', inject([BuySellService], fakeAsync((service: BuySellService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service. executeSellOrder(new SellOrder('','','',1,1,'','',new Date(),''))
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/sellUpdate');
    // Assert that the request is a GET.
    expect(req.request.method).toEqual('POST');
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

it('should handle a 404 error for executeBuyOrder', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.executeBuyOrder((new BuyOrder('','','',3,7,'','',new Date(),'')))
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/buyUpdate');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('POST');
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

it('should handle network error for executeBuyOrder', inject([BuySellService], fakeAsync((service: BuySellService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service. executeBuyOrder(new BuyOrder('','','',1,1,'','',new Date(),''))
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne('http://localhost:8080/orderMapper/buyUpdate');
    // Assert that the request is a GET.
    expect(req.request.method).toEqual('POST');
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
