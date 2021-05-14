import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHospital } from 'app/shared/model/hospital.model';

type EntityResponseType = HttpResponse<IHospital>;
type EntityArrayResponseType = HttpResponse<IHospital[]>;

@Injectable({ providedIn: 'root' })
export class HospitalService {
  public resourceUrl = SERVER_API_URL + 'api/hospitals';

  constructor(protected http: HttpClient) {}

  create(hospital: IHospital): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hospital);
    return this.http
      .post<IHospital>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(hospital: IHospital): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hospital);
    return this.http
      .put<IHospital>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHospital>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHospital[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(hospital: IHospital): IHospital {
    const copy: IHospital = Object.assign({}, hospital, {
      createdOn: hospital.createdOn && hospital.createdOn.isValid() ? hospital.createdOn.toJSON() : undefined,
      updatedOn: hospital.updatedOn && hospital.updatedOn.isValid() ? hospital.updatedOn.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdOn = res.body.createdOn ? moment(res.body.createdOn) : undefined;
      res.body.updatedOn = res.body.updatedOn ? moment(res.body.updatedOn) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((hospital: IHospital) => {
        hospital.createdOn = hospital.createdOn ? moment(hospital.createdOn) : undefined;
        hospital.updatedOn = hospital.updatedOn ? moment(hospital.updatedOn) : undefined;
      });
    }
    return res;
  }
}
