import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHospitalAudit, HospitalAudit } from 'app/shared/model/hospital-audit.model';
import { HospitalAuditService } from './hospital-audit.service';
import { HospitalAuditComponent } from './hospital-audit.component';
import { HospitalAuditDetailComponent } from './hospital-audit-detail.component';
import { HospitalAuditUpdateComponent } from './hospital-audit-update.component';

@Injectable({ providedIn: 'root' })
export class HospitalAuditResolve implements Resolve<IHospitalAudit> {
  constructor(private service: HospitalAuditService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHospitalAudit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((hospitalAudit: HttpResponse<HospitalAudit>) => {
          if (hospitalAudit.body) {
            return of(hospitalAudit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HospitalAudit());
  }
}

export const hospitalAuditRoute: Routes = [
  {
    path: '',
    component: HospitalAuditComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HospitalAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HospitalAuditDetailComponent,
    resolve: {
      hospitalAudit: HospitalAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HospitalAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HospitalAuditUpdateComponent,
    resolve: {
      hospitalAudit: HospitalAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HospitalAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HospitalAuditUpdateComponent,
    resolve: {
      hospitalAudit: HospitalAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'HospitalAudits',
    },
    canActivate: [UserRouteAccessService],
  },
];
