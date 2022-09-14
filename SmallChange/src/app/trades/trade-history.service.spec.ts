import { HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { fakeAsync, inject, TestBed, tick } from '@angular/core/testing';

import { TradeHistoryService } from './trade-history.service';

describe('TradeHistoryService', () => {
  let service: TradeHistoryService;
  
  let httpTestingController: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
    ]
    });
    
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(TradeHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should handle a 404 error', inject([TradeHistoryService], fakeAsync((service: TradeHistoryService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service.getTradeHistory('')
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne('http://localhost:3000/tradeHistory');
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

it('should handle network error', inject([TradeHistoryService], fakeAsync((service: TradeHistoryService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service.getTradeHistory('')
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne('http://localhost:3000/tradeHistory');
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
