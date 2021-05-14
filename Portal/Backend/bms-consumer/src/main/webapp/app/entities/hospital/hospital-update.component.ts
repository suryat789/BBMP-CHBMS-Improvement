import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IHospital, Hospital } from 'app/shared/model/hospital.model';
import { HospitalService } from './hospital.service';

@Component({
  selector: 'jhi-hospital-update',
  templateUrl: './hospital-update.component.html',
})
export class HospitalUpdateComponent implements OnInit {
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
  });

  constructor(protected hospitalService: HospitalService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hospital }) => {
      if (!hospital.id) {
        const today = moment().startOf('day');
        hospital.createdOn = today;
        hospital.updatedOn = today;
      }

      this.updateForm(hospital);
    });
  }

  updateForm(hospital: IHospital): void {
    this.editForm.patchValue({
      id: hospital.id,
      hospitalId: hospital.hospitalId,
      type: hospital.type,
      name: hospital.name,
      address: hospital.address,
      pincode: hospital.pincode,
      phone: hospital.phone,
      zone: hospital.zone,
      status: hospital.status,
      createdOn: hospital.createdOn ? hospital.createdOn.format(DATE_TIME_FORMAT) : null,
      updatedOn: hospital.updatedOn ? hospital.updatedOn.format(DATE_TIME_FORMAT) : null,
      updatedByMsgId: hospital.updatedByMsgId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hospital = this.createFromForm();
    if (hospital.id !== undefined) {
      this.subscribeToSaveResponse(this.hospitalService.update(hospital));
    } else {
      this.subscribeToSaveResponse(this.hospitalService.create(hospital));
    }
  }

  private createFromForm(): IHospital {
    return {
      ...new Hospital(),
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
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHospital>>): void {
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
