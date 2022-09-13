import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { LoginService } from './login.service';

describe('LoginService', () => {
  let service: LoginService;
  let httpTestingController: HttpTestingController;
 
 
  beforeEach(() => {
    TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule
        ]
    });
    service = TestBed.inject(LoginService);
    httpTestingController = TestBed.inject(HttpTestingController);
});

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

