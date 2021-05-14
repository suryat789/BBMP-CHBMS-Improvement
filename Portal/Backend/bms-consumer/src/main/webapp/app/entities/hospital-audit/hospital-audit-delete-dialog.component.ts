import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHospitalAudit } from 'app/shared/model/hospital-audit.model';
import { HospitalAuditService } from './hospital-audit.service';

@Component({
  templateUrl: './hospital-audit-delete-dialog.component.html',
})
export class HospitalAuditDeleteDialogComponent {
  hospitalAudit?: IHospitalAudit;

  constructor(
    protected hospitalAuditService: HospitalAuditService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hospitalAuditService.delete(id).subscribe(() => {
      this.eventManager.broadcast('hospitalAuditListModification');
      this.activeModal.close();
    });
  }
}
