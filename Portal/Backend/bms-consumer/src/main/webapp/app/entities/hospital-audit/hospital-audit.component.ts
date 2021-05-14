import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IHospitalAudit } from 'app/shared/model/hospital-audit.model';
import { HospitalAuditService } from './hospital-audit.service';
import { HospitalAuditDeleteDialogComponent } from './hospital-audit-delete-dialog.component';

@Component({
  selector: 'jhi-hospital-audit',
  templateUrl: './hospital-audit.component.html',
})
export class HospitalAuditComponent implements OnInit, OnDestroy {
  hospitalAudits?: IHospitalAudit[];
  eventSubscriber?: Subscription;

  constructor(
    protected hospitalAuditService: HospitalAuditService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.hospitalAuditService.query().subscribe((res: HttpResponse<IHospitalAudit[]>) => (this.hospitalAudits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInHospitalAudits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IHospitalAudit): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInHospitalAudits(): void {
    this.eventSubscriber = this.eventManager.subscribe('hospitalAuditListModification', () => this.loadAll());
  }

  delete(hospitalAudit: IHospitalAudit): void {
    const modalRef = this.modalService.open(HospitalAuditDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.hospitalAudit = hospitalAudit;
  }
}
