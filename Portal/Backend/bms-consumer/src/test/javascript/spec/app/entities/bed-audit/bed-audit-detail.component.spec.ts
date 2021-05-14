import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BmsTestModule } from '../../../test.module';
import { BedAuditDetailComponent } from 'app/entities/bed-audit/bed-audit-detail.component';
import { BedAudit } from 'app/shared/model/bed-audit.model';

describe('Component Tests', () => {
  describe('BedAudit Management Detail Component', () => {
    let comp: BedAuditDetailComponent;
    let fixture: ComponentFixture<BedAuditDetailComponent>;
    const route = ({ data: of({ bedAudit: new BedAudit(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [BmsTestModule],
        declarations: [BedAuditDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BedAuditDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BedAuditDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load bedAudit on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.bedAudit).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
