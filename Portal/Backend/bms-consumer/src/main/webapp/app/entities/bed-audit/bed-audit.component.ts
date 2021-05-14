import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBedAudit } from 'app/shared/model/bed-audit.model';
import { BedAuditService } from './bed-audit.service';
import { BedAuditDeleteDialogComponent } from './bed-audit-delete-dialog.component';

@Component({
  selector: 'jhi-bed-audit',
  templateUrl: './bed-audit.component.html',
})
export class BedAuditComponent implements OnInit, OnDestroy {
  bedAudits?: IBedAudit[];
  eventSubscriber?: Subscription;

  constructor(protected bedAuditService: BedAuditService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.bedAuditService.query().subscribe((res: HttpResponse<IBedAudit[]>) => (this.bedAudits = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBedAudits();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBedAudit): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBedAudits(): void {
    this.eventSubscriber = this.eventManager.subscribe('bedAuditListModification', () => this.loadAll());
  }

  delete(bedAudit: IBedAudit): void {
    const modalRef = this.modalService.open(BedAuditDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bedAudit = bedAudit;
  }
}
