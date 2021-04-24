import { TestBed } from '@angular/core/testing';

import { PhotoServicesService } from './photo-services.service';

describe('PhotoServicesService', () => {
  let service: PhotoServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PhotoServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
