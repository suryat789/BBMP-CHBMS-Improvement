import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPatientAudit, PatientAudit } from 'app/shared/model/patient-audit.model';
import { PatientAuditService } from './patient-audit.service';
import { PatientAuditComponent } from './patient-audit.component';
import { PatientAuditDetailComponent } from './patient-audit-detail.component';
import { PatientAuditUpdateComponent } from './patient-audit-update.component';

@Injectable({ providedIn: 'root' })
export class PatientAuditResolve implements Resolve<IPatientAudit> {
  constructor(private service: PatientAuditService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPatientAudit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((patientAudit: HttpResponse<PatientAudit>) => {
          if (patientAudit.body) {
            return of(patientAudit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PatientAudit());
  }
}

export const patientAuditRoute: Routes = [
  {
    path: '',
    component: PatientAuditComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PatientAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PatientAuditDetailComponent,
    resolve: {
      patientAudit: PatientAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PatientAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PatientAuditUpdateComponent,
    resolve: {
      patientAudit: PatientAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PatientAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PatientAuditUpdateComponent,
    resolve: {
      patientAudit: PatientAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'PatientAudits',
    },
    canActivate: [UserRouteAccessService],
  },
];
