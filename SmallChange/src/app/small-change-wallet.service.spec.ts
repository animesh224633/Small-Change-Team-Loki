import { TestBed } from '@angular/core/testing';

import { SmallChangeWalletService } from './small-change-wallet.service';

describe('SmallChangeWalletService', () => {
  let service: SmallChangeWalletService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SmallChangeWalletService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
