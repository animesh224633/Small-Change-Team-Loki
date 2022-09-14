import { TestBed } from '@angular/core/testing';

import { PortfolioPageService } from './portfolio-page.service';

describe('PortfolioPageService', () => {
  let service: PortfolioPageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PortfolioPageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
