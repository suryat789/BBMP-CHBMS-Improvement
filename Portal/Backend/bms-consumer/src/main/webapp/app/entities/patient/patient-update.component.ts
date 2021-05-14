import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPatient, Patient } from 'app/shared/model/patient.model';
import { PatientService } from './patient.service';

@Component({
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html',
})
export class PatientUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    patientId: [],
    phone: [],
    srfNumber: [],
    bucode: [],
    category: [],
    zone: [],
    queueType: [],
    queueName: [],
    timeAddedToQueue: [],
    createdOn: [],
    updatedOn: [],
    updatedByMsgId: [],
  });

  constructor(protected patientService: PatientService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patient }) => {
      if (!patient.id) {
        const today = moment().startOf('day');
        patient.timeAddedToQueue = today;
        patient.createdOn = today;
        patient.updatedOn = today;
      }

      this.updateForm(patient);
    });
  }

  updateForm(patient: IPatient): void {
    this.editForm.patchValue({
      id: patient.id,
      patientId: patient.patientId,
      phone: patient.phone,
      srfNumber: patient.srfNumber,
      bucode: patient.bucode,
      category: patient.category,
      zone: patient.zone,
      queueType: patient.queueType,
      queueName: patient.queueName,
      timeAddedToQueue: patient.timeAddedToQueue ? patient.timeAddedToQueue.format(DATE_TIME_FORMAT) : null,
      createdOn: patient.createdOn ? patient.createdOn.format(DATE_TIME_FORMAT) : null,
      updatedOn: patient.updatedOn ? patient.updatedOn.format(DATE_TIME_FORMAT) : null,
      updatedByMsgId: patient.updatedByMsgId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patient = this.createFromForm();
    if (patient.id !== undefined) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  private createFromForm(): IPatient {
    return {
      ...new Patient(),
      id: this.editForm.get(['id'])!.value,
      patientId: this.editForm.get(['patientId'])!.value,
      phone: this.editForm.get(['phone'])!.value,
      srfNumber: this.editForm.get(['srfNumber'])!.value,
      bucode: this.editForm.get(['bucode'])!.value,
      category: this.editForm.get(['category'])!.value,
      zone: this.editForm.get(['zone'])!.value,
      queueType: this.editForm.get(['queueType'])!.value,
      queueName: this.editForm.get(['queueName'])!.value,
      timeAddedToQueue: this.editForm.get(['timeAddedToQueue'])!.value
        ? moment(this.editForm.get(['timeAddedToQueue'])!.value, DATE_TIME_FORMAT)
        : undefined,
      createdOn: this.editForm.get(['createdOn'])!.value ? moment(this.editForm.get(['createdOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedOn: this.editForm.get(['updatedOn'])!.value ? moment(this.editForm.get(['updatedOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedByMsgId: this.editForm.get(['updatedByMsgId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>): void {
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
