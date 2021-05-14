import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BmsTestModule } from '../../../test.module';
import { PatientAuditDetailComponent } from 'app/entities/patient-audit/patient-audit-detail.component';
import { PatientAudit } from 'app/shared/model/patient-audit.model';

describe('Component Tests', () => {
  describe('PatientAudit Management Detail Component', () => {
    let comp: PatientAuditDetailComponent;
    let fixture: ComponentFixture<PatientAuditDetailComponent>;
    const route = ({ data: of({ patientAudit: new PatientAudit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [PatientAuditDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PatientAuditDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PatientAuditDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load patientAudit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.patientAudit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
