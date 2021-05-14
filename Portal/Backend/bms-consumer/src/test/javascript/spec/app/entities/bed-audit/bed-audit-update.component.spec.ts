import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { BmsTestModule } from '../../../test.module';
import { BedAuditUpdateComponent } from 'app/entities/bed-audit/bed-audit-update.component';
import { BedAuditService } from 'app/entities/bed-audit/bed-audit.service';
import { BedAudit } from 'app/shared/model/bed-audit.model';

describe('Component Tests', () => {
  describe('BedAudit Management Update Component', () => {
    let comp: BedAuditUpdateComponent;
    let fixture: ComponentFixture<BedAuditUpdateComponent>;
    let service: BedAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [BedAuditUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BedAuditUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BedAuditUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BedAuditService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BedAudit(123);
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
        const entity = new BedAudit();
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
