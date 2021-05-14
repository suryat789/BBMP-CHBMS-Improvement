import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { BedAuditService } from 'app/entities/bed-audit/bed-audit.service';
import { IBedAudit, BedAudit } from 'app/shared/model/bed-audit.model';

describe('Service Tests', () => {
  describe('BedAudit Service', () => {
    let injector: TestBed;
    let service: BedAuditService;
    let httpMock: HttpTestingController;
    let elemDefault: IBedAudit;
    let expectedResult: IBedAudit | IBedAudit[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BedAuditService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BedAudit(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, currentDate, currentDate, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BedAudit', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdOn: currentDate,
            updatedOn: currentDate,
            auditedOn: currentDate,
          },
          returnedFromService
        );

        service.create(new BedAudit()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BedAudit', () => {
        const returnedFromService = Object.assign(
          {
            hospitalId: 'BBBBBB',
            bedId: 'BBBBBB',
            type: 'BBBBBB',
            capacity: 1,
            occupied: 1,
            blocked: 1,
            vacant: 1,
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            updatedByMsgId: 'BBBBBB',
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdOn: currentDate,
            updatedOn: currentDate,
            auditedOn: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BedAudit', () => {
        const returnedFromService = Object.assign(
          {
            hospitalId: 'BBBBBB',
            bedId: 'BBBBBB',
            type: 'BBBBBB',
            capacity: 1,
            occupied: 1,
            blocked: 1,
            vacant: 1,
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            updatedByMsgId: 'BBBBBB',
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdOn: currentDate,
            updatedOn: currentDate,
            auditedOn: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BedAudit', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
