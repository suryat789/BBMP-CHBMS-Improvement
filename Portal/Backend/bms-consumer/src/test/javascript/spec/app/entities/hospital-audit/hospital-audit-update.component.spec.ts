import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BmsTestModule } from '../../../test.module';
import { HospitalAuditUpdateComponent } from 'app/entities/hospital-audit/hospital-audit-update.component';
import { HospitalAuditService } from 'app/entities/hospital-audit/hospital-audit.service';
import { HospitalAudit } from 'app/shared/model/hospital-audit.model';

describe('Component Tests', () => {
  describe('HospitalAudit Management Update Component', () => {
    let comp: HospitalAuditUpdateComponent;
    let fixture: ComponentFixture<HospitalAuditUpdateComponent>;
    let service: HospitalAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [HospitalAuditUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(HospitalAuditUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HospitalAuditUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HospitalAuditService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HospitalAudit(123);
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
        const entity = new HospitalAudit();
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
