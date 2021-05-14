import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBedAudit, BedAudit } from 'app/shared/model/bed-audit.model';
import { BedAuditService } from './bed-audit.service';
import { BedAuditComponent } from './bed-audit.component';
import { BedAuditDetailComponent } from './bed-audit-detail.component';
import { BedAuditUpdateComponent } from './bed-audit-update.component';

@Injectable({ providedIn: 'root' })
export class BedAuditResolve implements Resolve<IBedAudit> {
  constructor(private service: BedAuditService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBedAudit> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bedAudit: HttpResponse<BedAudit>) => {
          if (bedAudit.body) {
            return of(bedAudit.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BedAudit());
  }
}

export const bedAuditRoute: Routes = [
  {
    path: '',
    component: BedAuditComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BedAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BedAuditDetailComponent,
    resolve: {
      bedAudit: BedAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BedAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BedAuditUpdateComponent,
    resolve: {
      bedAudit: BedAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BedAudits',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BedAuditUpdateComponent,
    resolve: {
      bedAudit: BedAuditResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BedAudits',
    },
    canActivate: [UserRouteAccessService],
  },
];
