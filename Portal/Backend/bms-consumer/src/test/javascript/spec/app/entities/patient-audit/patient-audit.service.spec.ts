import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PatientAuditService } from 'app/entities/patient-audit/patient-audit.service';
import { IPatientAudit, PatientAudit } from 'app/shared/model/patient-audit.model';

describe('Service Tests', () => {
  describe('PatientAudit Service', () => {
    let injector: TestBed;
    let service: PatientAuditService;
    let httpMock: HttpTestingController;
    let elemDefault: IPatientAudit;
    let expectedResult: IPatientAudit | IPatientAudit[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PatientAuditService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PatientAudit(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
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
            timeAddedToQueue: currentDate.format(DATE_TIME_FORMAT),
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

      it('should create a PatientAudit', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            timeAddedToQueue: currentDate.format(DATE_TIME_FORMAT),
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeAddedToQueue: currentDate,
            createdOn: currentDate,
            updatedOn: currentDate,
            auditedOn: currentDate,
          },
          returnedFromService
        );

        service.create(new PatientAudit()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PatientAudit', () => {
        const returnedFromService = Object.assign(
          {
            patientId: 'BBBBBB',
            phone: 'BBBBBB',
            srfNumber: 'BBBBBB',
            bucode: 'BBBBBB',
            category: 'BBBBBB',
            zone: 'BBBBBB',
            queueType: 'BBBBBB',
            queueName: 'BBBBBB',
            timeAddedToQueue: currentDate.format(DATE_TIME_FORMAT),
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            updatedByMsgId: 'BBBBBB',
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeAddedToQueue: currentDate,
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

      it('should return a list of PatientAudit', () => {
        const returnedFromService = Object.assign(
          {
            patientId: 'BBBBBB',
            phone: 'BBBBBB',
            srfNumber: 'BBBBBB',
            bucode: 'BBBBBB',
            category: 'BBBBBB',
            zone: 'BBBBBB',
            queueType: 'BBBBBB',
            queueName: 'BBBBBB',
            timeAddedToQueue: currentDate.format(DATE_TIME_FORMAT),
            createdOn: currentDate.format(DATE_TIME_FORMAT),
            updatedOn: currentDate.format(DATE_TIME_FORMAT),
            updatedByMsgId: 'BBBBBB',
            auditedOn: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeAddedToQueue: currentDate,
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

      it('should delete a PatientAudit', () => {
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
