import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BmsTestModule } from '../../../test.module';
import { BedAuditComponent } from 'app/entities/bed-audit/bed-audit.component';
import { BedAuditService } from 'app/entities/bed-audit/bed-audit.service';
import { BedAudit } from 'app/shared/model/bed-audit.model';

describe('Component Tests', () => {
  describe('BedAudit Management Component', () => {
    let comp: BedAuditComponent;
    let fixture: ComponentFixture<BedAuditComponent>;
    let service: BedAuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [BedAuditComponent],
      })
        .overrideTemplate(BedAuditComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BedAuditComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BedAuditService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BedAudit(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.bedAudits && comp.bedAudits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
