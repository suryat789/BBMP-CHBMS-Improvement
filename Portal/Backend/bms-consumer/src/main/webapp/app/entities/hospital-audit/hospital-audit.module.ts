import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BmsSharedModule } from 'app/shared/shared.module';
import { HospitalAuditComponent } from './hospital-audit.component';
import { HospitalAuditDetailComponent } from './hospital-audit-detail.component';
import { HospitalAuditUpdateComponent } from './hospital-audit-update.component';
import { HospitalAuditDeleteDialogComponent } from './hospital-audit-delete-dialog.component';
import { hospitalAuditRoute } from './hospital-audit.route';

@NgModule({
  imports: [BmsSharedModule, RouterModule.forChild(hospitalAuditRoute)],
  declarations: [HospitalAuditComponent, HospitalAuditDetailComponent, HospitalAuditUpdateComponent, HospitalAuditDeleteDialogComponent],
  entryComponents: [HospitalAuditDeleteDialogComponent],
})
export class BmsHospitalAuditModule {}
