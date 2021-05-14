import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BmsSharedModule } from 'app/shared/shared.module';
import { PatientAuditComponent } from './patient-audit.component';
import { PatientAuditDetailComponent } from './patient-audit-detail.component';
import { PatientAuditUpdateComponent } from './patient-audit-update.component';
import { PatientAuditDeleteDialogComponent } from './patient-audit-delete-dialog.component';
import { patientAuditRoute } from './patient-audit.route';

@NgModule({
  imports: [BmsSharedModule, RouterModule.forChild(patientAuditRoute)],
  declarations: [PatientAuditComponent, PatientAuditDetailComponent, PatientAuditUpdateComponent, PatientAuditDeleteDialogComponent],
  entryComponents: [PatientAuditDeleteDialogComponent],
})
export class BmsPatientAuditModule {}
