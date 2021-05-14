import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IHospitalAudit, HospitalAudit } from 'app/shared/model/hospital-audit.model';
import { HospitalAuditService } from './hospital-audit.service';

@Component({
  selector: 'jhi-hospital-audit-update',
  templateUrl: './hospital-audit-update.component.html',
})
export class HospitalAuditUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    hospitalId: [],
    type: [],
    name: [],
    address: [],
    pincode: [],
    phone: [],
    zone: [],
    status: [],
    createdOn: [],
    updatedOn: [],
    updatedByMsgId: [],
    auditedOn: [],
  });

  constructor(protected hospitalAuditService: HospitalAuditService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hospitalAudit }) => {
      if (!hospitalAudit.id) {
        const today = moment().startOf('day');
        hospitalAudit.createdOn = today;
        hospitalAudit.updatedOn = today;
        hospitalAudit.auditedOn = today;
      }

      this.updateForm(hospitalAudit);
    });
  }

  updateForm(hospitalAudit: IHospitalAudit): void {
    this.editForm.patchValue({
      id: hospitalAudit.id,
      hospitalId: hospitalAudit.hospitalId,
      type: hospitalAudit.type,
      name: hospitalAudit.name,
      address: hospitalAudit.address,
      pincode: hospitalAudit.pincode,
      phone: hospitalAudit.phone,
      zone: hospitalAudit.zone,
      status: hospitalAudit.status,
      createdOn: hospitalAudit.createdOn ? hospitalAudit.createdOn.format(DATE_TIME_FORMAT) : null,
      updatedOn: hospitalAudit.updatedOn ? hospitalAudit.updatedOn.format(DATE_TIME_FORMAT) : null,
      updatedByMsgId: hospitalAudit.updatedByMsgId,
      auditedOn: hospitalAudit.auditedOn ? hospitalAudit.auditedOn.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hospitalAudit = this.createFromForm();
    if (hospitalAudit.id !== undefined) {
      this.subscribeToSaveResponse(this.hospitalAuditService.update(hospitalAudit));
    } else {
      this.subscribeToSaveResponse(this.hospitalAuditService.create(hospitalAudit));
    }
  }

  private createFromForm(): IHospitalAudit {
    return {
      ...new HospitalAudit(),
      id: this.editForm.get(['id'])!.value,
      hospitalId: this.editForm.get(['hospitalId'])!.value,
      type: this.editForm.get(['type'])!.value,
      name: this.editForm.get(['name'])!.value,
      address: this.editForm.get(['address'])!.value,
      pincode: this.editForm.get(['pincode'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      zone: this.editForm.get(['zone'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdOn: this.editForm.get(['createdOn'])!.value ? moment(this.editForm.get(['createdOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedOn: this.editForm.get(['updatedOn'])!.value ? moment(this.editForm.get(['updatedOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedByMsgId: this.editForm.get(['updatedByMsgId'])!.value,
      auditedOn: this.editForm.get(['auditedOn'])!.value ? moment(this.editForm.get(['auditedOn'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHospitalAudit>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
