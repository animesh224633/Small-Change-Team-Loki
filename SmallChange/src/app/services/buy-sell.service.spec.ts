import { HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule,HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, inject, TestBed, tick, waitForAsync } from '@angular/core/testing';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from '../app-routing.module';
import { PortfolioMutualFunds } from '../models/portfolio-mutual-funds.model';
import { PortfolioStocks } from '../models/portfolio-stocks.model';
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
  it('should return portfoliostocks',fakeAsync(()=>{
    const modelData=[{
        "name": "Apple",
        "code": "APP",
        "quantity": 2,
        "buyPrice": 130.5,
        "currentPrice": 150
      }]
    service. getPortfolioStocks().subscribe()
    const req=httpTestingController.expectOne('http://localhost:3000/portfolioStocks')
    expect(req.request.method).toBe('GET')
    req.flush(modelData)
    httpTestingController.verify()
    tick()
    expect(modelData[0].code).toBe("APP");
    
}));

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should handle a 404 error for get portfoliostocks', inject([BuySellService], fakeAsync((service:BuySellService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service.getPortfolioStocks()
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne('http://localhost:3000/portfolioStocks');
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
it('should handle network error for get walletMoney', inject([BuySellService], fakeAsync((service: BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service. getWalletAmount()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/walletMoney');
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
it('should handle a 404 error for put walletMoney', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service. updateWalletAmount('')
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/walletMoney');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('PUT');
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
it('should handle network error for put walletMoney', inject([BuySellService], fakeAsync((service: BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service. updateWalletAmount('')
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/walletMoney');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('PUT');
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
it('should handle a 404 error for put portfolioMutualFunds', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service. updatePortfolioMutualFunds()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/portfolioMutualFunds');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('PUT');
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
it('should handle a 404 error for get PortfolioMutualFunds', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.getPortfolioMutualFunds()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/portfolioMutualFunds');
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
it('should handle network error for put portfolioMutualFunds', inject([BuySellService], fakeAsync((service: BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.  updatePortfolioMutualFunds()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/portfolioMutualFunds');
  // Assert that the request is a GET.
  expect(req.request.method).toEqual('PUT');
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
it('should handle a 404 error for get allMutualFunds', inject([BuySellService], fakeAsync((service:BuySellService) => {
  let errorResp: HttpErrorResponse;
  let errorReply: string = '';
  const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
  service.getAllMutualFunds()
      .subscribe({
          next: () => fail('Should not succeed'),
          error: (e) => errorReply = e
      });
  const req = httpTestingController.expectOne('http://localhost:3000/allMutualFunds');
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


});
