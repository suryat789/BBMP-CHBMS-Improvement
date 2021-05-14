import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPatientAudit } from 'app/shared/model/patient-audit.model';
import { PatientAuditService } from './patient-audit.service';
import { PatientAuditDeleteDialogComponent } from './patient-audit-delete-dialog.component';

@Component({
  selector: 'jhi-patient-audit',
  templateUrl: './patient-audit.component.html',
})
export class PatientAuditComponent implements OnInit, OnDestroy {
  patientAudits?: IPatientAudit[];
  eventSubscriber?: Subscription;

  constructor(
    protected patientAuditService: PatientAuditService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.patientAuditService.query().subscribe((res: HttpResponse<IPatientAudit[]>) => (this.patientAudits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPatientAudits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPatientAudit): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPatientAudits(): void {
    this.eventSubscriber = this.eventManager.subscribe('patientAuditListModification', () => this.loadAll());
  }

  delete(patientAudit: IPatientAudit): void {
    const modalRef = this.modalService.open(PatientAuditDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.patientAudit = patientAudit;
  }
}
