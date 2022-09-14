import { fakeAsync, inject, TestBed, tick } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Observable, of } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

import { LoginService } from './login.service';
import { Login } from './models/login';

describe('LoginService', () => {
  let service: LoginService;
  let httpTestingController: HttpTestingController;
  let MockData = new Login();
  MockData.email = "spidy@gmail.com";
  MockData.name = 'Animesh';
  MockData.password = 'idc7yc9ewydewyc9wc7ewc';
  const serviceUrl = 'http://localhost:3000/signup';
  const mockUsers: Login[] = [{
    "name": "Animesh",
    "email": "spidy@gmail.com",
    "password": 'idc7yc9ewydewyc9wc7ewc',
    "id": 130
   
  },
  {
    "name": "Animesh",
    "email": "spidy@gmail.com",
    "password": 'idc7yc9ewydewyc9wc7ewc',
    "id": 130
   
  }];


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ]
    });
    service = TestBed.inject(LoginService);
    httpTestingController = TestBed.inject(HttpTestingController);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });



  it('should GET to get all users', inject([LoginService], fakeAsync((service: LoginService) => {
    let users:  Login[] = [];
    service.login()
        .subscribe(data => users = data);
    const req = httpTestingController.expectOne(serviceUrl);
    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');
    // Respond with mock data, causing Observable to resolve.
    req.flush(mockUsers);
    // Assert that there are no outstanding requests.
    httpTestingController.verify();
    // Cause all Observables to complete and check the results
    tick();
    expect(users).toBeTruthy();
    expect(users.length).toBe(2);
    expect(users[0].name).toBe('Animesh');
})));


  it('should POST call and add user', inject([LoginService], fakeAsync((service: LoginService) => {
    const expected = new Login();
    expected.email="spidy@gmail.com";
    expected.name="Animesh";
    expected.password="idc7yc9ewydewyc9wc7ewc";
    expected.id=30;
    service.register('Animesh', 'spidy@gmail.com', 'idc7yc9ewydewyc9wc7ewc')
        .subscribe();
    const req = httpTestingController.expectOne(serviceUrl);
    // Assert that the request is a POST.
    expect(req.request.method).toEqual('POST');
    // Assert that it was called with the right data
    expect(req.request.body).toEqual(expected);
    // Respond with empty data.
    req.flush(null);
    // Assert that there are no outstanding requests.
    httpTestingController.verify();
    tick();
})));
  
  it('should handle a 404 error', inject([LoginService], fakeAsync((service: LoginService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service.login()
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne(serviceUrl);
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

it('should handle network error', inject([LoginService], fakeAsync((service: LoginService) => {
    let errorResp: HttpErrorResponse;
    let errorReply: string = '';
    const errorHandlerSpy = spyOn(service, 'handleError').and.callThrough();
    service.login()
        .subscribe({
            next: () => fail('Should not succeed'),
            error: (e) => errorReply = e
        });
    const req = httpTestingController.expectOne(serviceUrl);
    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');
    // Create mock ErrorEvent, raised when something goes wrong at the network level.
    // Connection timeout, DNS error, offline, etc
    const mockError = new ProgressEvent('simulated network error');
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
    expect(errorResp.error.type).toBe('simulated network error');
})));



});

