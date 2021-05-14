import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHospitalAudit } from 'app/shared/model/hospital-audit.model';

type EntityResponseType = HttpResponse<IHospitalAudit>;
type EntityArrayResponseType = HttpResponse<IHospitalAudit[]>;

@Injectable({ providedIn: 'root' })
export class HospitalAuditService {
  public resourceUrl = SERVER_API_URL + 'api/hospital-audits';

  constructor(protected http: HttpClient) {}

  create(hospitalAudit: IHospitalAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hospitalAudit);
    return this.http
      .post<IHospitalAudit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(hospitalAudit: IHospitalAudit): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hospitalAudit);
    return this.http
      .put<IHospitalAudit>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHospitalAudit>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHospitalAudit[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(hospitalAudit: IHospitalAudit): IHospitalAudit {
    const copy: IHospitalAudit = Object.assign({}, hospitalAudit, {
      createdOn: hospitalAudit.createdOn && hospitalAudit.createdOn.isValid() ? hospitalAudit.createdOn.toJSON() : undefined,
      updatedOn: hospitalAudit.updatedOn && hospitalAudit.updatedOn.isValid() ? hospitalAudit.updatedOn.toJSON() : undefined,
      auditedOn: hospitalAudit.auditedOn && hospitalAudit.auditedOn.isValid() ? hospitalAudit.auditedOn.toJSON() : undefined,
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
      res.body.forEach((hospitalAudit: IHospitalAudit) => {
        hospitalAudit.createdOn = hospitalAudit.createdOn ? moment(hospitalAudit.createdOn) : undefined;
        hospitalAudit.updatedOn = hospitalAudit.updatedOn ? moment(hospitalAudit.updatedOn) : undefined;
        hospitalAudit.auditedOn = hospitalAudit.auditedOn ? moment(hospitalAudit.auditedOn) : undefined;
      });
    }
    return res;
  }
}
