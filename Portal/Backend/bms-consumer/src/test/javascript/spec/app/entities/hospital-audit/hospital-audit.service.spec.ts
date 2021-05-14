import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { HospitalAuditService } from 'app/entities/hospital-audit/hospital-audit.service';
import { IHospitalAudit, HospitalAudit } from 'app/shared/model/hospital-audit.model';

describe('Service Tests', () => {
  describe('HospitalAudit Service', () => {
    let injector: TestBed;
    let service: HospitalAuditService;
    let httpMock: HttpTestingController;
    let elemDefault: IHospitalAudit;
    let expectedResult: IHospitalAudit | IHospitalAudit[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(HospitalAuditService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new HospitalAudit(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        currentDate
      );
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

      it('should create a HospitalAudit', () => {
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

        service.create(new HospitalAudit()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a HospitalAudit', () => {
        const returnedFromService = Object.assign(
          {
            hospitalId: 'BBBBBB',
            type: 'BBBBBB',
            name: 'BBBBBB',
            address: 'BBBBBB',
            pincode: 1,
            phone: 'BBBBBB',
            zone: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should return a list of HospitalAudit', () => {
        const returnedFromService = Object.assign(
          {
            hospitalId: 'BBBBBB',
            type: 'BBBBBB',
            name: 'BBBBBB',
            address: 'BBBBBB',
            pincode: 1,
            phone: 'BBBBBB',
            zone: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should delete a HospitalAudit', () => {
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
