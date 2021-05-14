import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BmsTestModule } from '../../../test.module';
import { PatientAuditUpdateComponent } from 'app/entities/patient-audit/patient-audit-update.component';
import { PatientAuditService } from 'app/entities/patient-audit/patient-audit.service';
import { PatientAudit } from 'app/shared/model/patient-audit.model';

describe('Component Tests', () => {
  describe('PatientAudit Management Update Component', () => {
    let comp: PatientAuditUpdateComponent;
    let fixture: ComponentFixture<PatientAuditUpdateComponent>;
    let service: PatientAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [PatientAuditUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PatientAuditUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PatientAuditUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PatientAuditService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PatientAudit(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new PatientAudit();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
