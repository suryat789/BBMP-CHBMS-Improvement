import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BmsSharedModule } from 'app/shared/shared.module';
import { BedAuditComponent } from './bed-audit.component';
import { BedAuditDetailComponent } from './bed-audit-detail.component';
import { BedAuditUpdateComponent } from './bed-audit-update.component';
import { BedAuditDeleteDialogComponent } from './bed-audit-delete-dialog.component';
import { bedAuditRoute } from './bed-audit.route';

@NgModule({
  imports: [BmsSharedModule, RouterModule.forChild(bedAuditRoute)],
  declarations: [BedAuditComponent, BedAuditDetailComponent, BedAuditUpdateComponent, BedAuditDeleteDialogComponent],
  entryComponents: [BedAuditDeleteDialogComponent],
})
export class BmsBedAuditModule {}
