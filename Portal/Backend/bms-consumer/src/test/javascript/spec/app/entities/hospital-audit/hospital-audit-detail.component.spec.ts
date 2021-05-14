import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BmsTestModule } from '../../../test.module';
import { HospitalAuditDetailComponent } from 'app/entities/hospital-audit/hospital-audit-detail.component';
import { HospitalAudit } from 'app/shared/model/hospital-audit.model';

describe('Component Tests', () => {
  describe('HospitalAudit Management Detail Component', () => {
    let comp: HospitalAuditDetailComponent;
    let fixture: ComponentFixture<HospitalAuditDetailComponent>;
    const route = ({ data: of({ hospitalAudit: new HospitalAudit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [HospitalAuditDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(HospitalAuditDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HospitalAuditDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load hospitalAudit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.hospitalAudit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
