import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPatientAudit } from 'app/shared/model/patient-audit.model';
import { PatientAuditService } from './patient-audit.service';

@Component({
  templateUrl: './patient-audit-delete-dialog.component.html',
})
export class PatientAuditDeleteDialogComponent {
  patientAudit?: IPatientAudit;

  constructor(
    protected patientAuditService: PatientAuditService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.patientAuditService.delete(id).subscribe(() => {
      this.eventManager.broadcast('patientAuditListModification');
      this.activeModal.close();
    });
  }
}
