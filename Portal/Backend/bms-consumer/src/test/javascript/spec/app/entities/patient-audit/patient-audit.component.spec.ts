import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BmsTestModule } from '../../../test.module';
import { PatientAuditComponent } from 'app/entities/patient-audit/patient-audit.component';
import { PatientAuditService } from 'app/entities/patient-audit/patient-audit.service';
import { PatientAudit } from 'app/shared/model/patient-audit.model';

describe('Component Tests', () => {
  describe('PatientAudit Management Component', () => {
    let comp: PatientAuditComponent;
    let fixture: ComponentFixture<PatientAuditComponent>;
    let service: PatientAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [PatientAuditComponent],
      })
        .overrideTemplate(PatientAuditComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PatientAuditComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PatientAuditService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PatientAudit(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.patientAudits && comp.patientAudits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
