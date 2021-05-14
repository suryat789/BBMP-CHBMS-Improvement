import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'patient',
        loadChildren: () => import('./patient/patient.module').then(m => m.BmsPatientModule),
      },
      {
        path: 'hospital',
        loadChildren: () => import('./hospital/hospital.module').then(m => m.BmsHospitalModule),
      },
      {
        path: 'bed',
        loadChildren: () => import('./bed/bed.module').then(m => m.BmsBedModule),
      },
      {
        path: 'patient-audit',
        loadChildren: () => import('./patient-audit/patient-audit.module').then(m => m.BmsPatientAuditModule),
      },
      {
        path: 'hospital-audit',
        loadChildren: () => import('./hospital-audit/hospital-audit.module').then(m => m.BmsHospitalAuditModule),
      },
      {
        path: 'bed-audit',
        loadChildren: () => import('./bed-audit/bed-audit.module').then(m => m.BmsBedAuditModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class BmsEntityModule {}
