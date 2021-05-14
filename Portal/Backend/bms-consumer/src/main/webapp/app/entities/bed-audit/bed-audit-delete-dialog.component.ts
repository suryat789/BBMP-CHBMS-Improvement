import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBedAudit } from 'app/shared/model/bed-audit.model';
import { BedAuditService } from './bed-audit.service';

@Component({
  templateUrl: './bed-audit-delete-dialog.component.html',
})
export class BedAuditDeleteDialogComponent {
  bedAudit?: IBedAudit;

  constructor(protected bedAuditService: BedAuditService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bedAuditService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bedAuditListModification');
      this.activeModal.close();
    });
  }
}
