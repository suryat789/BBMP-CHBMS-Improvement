import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BmsTestModule } from '../../../test.module';
import { HospitalAuditComponent } from 'app/entities/hospital-audit/hospital-audit.component';
import { HospitalAuditService } from 'app/entities/hospital-audit/hospital-audit.service';
import { HospitalAudit } from 'app/shared/model/hospital-audit.model';

describe('Component Tests', () => {
  describe('HospitalAudit Management Component', () => {
    let comp: HospitalAuditComponent;
    let fixture: ComponentFixture<HospitalAuditComponent>;
    let service: HospitalAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [HospitalAuditComponent],
      })
        .overrideTemplate(HospitalAuditComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HospitalAuditComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HospitalAuditService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HospitalAudit(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.hospitalAudits && comp.hospitalAudits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
