import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBedAudit } from 'app/shared/model/bed-audit.model';

type EntityResponseType = HttpResponse<IBedAudit>;
type EntityArrayResponseType = HttpResponse<IBedAudit[]>;

@Injectable({ providedIn: 'root' })
export class BedAuditService {
  public resourceUrl = SERVER_API_URL + 'api/bed-audits';

  constructor(protected http: HttpClient) {}

  create(bedAudit: IBedAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bedAudit);
    return this.http
      .post<IBedAudit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(bedAudit: IBedAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bedAudit);
    return this.http
      .put<IBedAudit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBedAudit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBedAudit[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(bedAudit: IBedAudit): IBedAudit {
    const copy: IBedAudit = Object.assign({}, bedAudit, {
      createdOn: bedAudit.createdOn && bedAudit.createdOn.isValid() ? bedAudit.createdOn.toJSON() : undefined,
      updatedOn: bedAudit.updatedOn && bedAudit.updatedOn.isValid() ? bedAudit.updatedOn.toJSON() : undefined,
      auditedOn: bedAudit.auditedOn && bedAudit.auditedOn.isValid() ? bedAudit.auditedOn.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdOn = res.body.createdOn ? moment(res.body.createdOn) : undefined;
      res.body.updatedOn = res.body.updatedOn ? moment(res.body.updatedOn) : undefined;
      res.body.auditedOn = res.body.auditedOn ? moment(res.body.auditedOn) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((bedAudit: IBedAudit) => {
        bedAudit.createdOn = bedAudit.createdOn ? moment(bedAudit.createdOn) : undefined;
        bedAudit.updatedOn = bedAudit.updatedOn ? moment(bedAudit.updatedOn) : undefined;
        bedAudit.auditedOn = bedAudit.auditedOn ? moment(bedAudit.auditedOn) : undefined;
      });
    }
    return res;
  }
}
