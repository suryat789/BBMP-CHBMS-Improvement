import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPatientAudit, PatientAudit } from 'app/shared/model/patient-audit.model';
import { PatientAuditService } from './patient-audit.service';

@Component({
  selector: 'jhi-patient-audit-update',
  templateUrl: './patient-audit-update.component.html',
})
export class PatientAuditUpdateComponent implements OnInit {
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
    auditedOn: [],
  });

  constructor(protected patientAuditService: PatientAuditService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patientAudit }) => {
      if (!patientAudit.id) {
        const today = moment().startOf('day');
        patientAudit.timeAddedToQueue = today;
        patientAudit.createdOn = today;
        patientAudit.updatedOn = today;
        patientAudit.auditedOn = today;
      }

      this.updateForm(patientAudit);
    });
  }

  updateForm(patientAudit: IPatientAudit): void {
    this.editForm.patchValue({
      id: patientAudit.id,
      patientId: patientAudit.patientId,
      phone: patientAudit.phone,
      srfNumber: patientAudit.srfNumber,
      bucode: patientAudit.bucode,
      category: patientAudit.category,
      zone: patientAudit.zone,
      queueType: patientAudit.queueType,
      queueName: patientAudit.queueName,
      timeAddedToQueue: patientAudit.timeAddedToQueue ? patientAudit.timeAddedToQueue.format(DATE_TIME_FORMAT) : null,
      createdOn: patientAudit.createdOn ? patientAudit.createdOn.format(DATE_TIME_FORMAT) : null,
      updatedOn: patientAudit.updatedOn ? patientAudit.updatedOn.format(DATE_TIME_FORMAT) : null,
      updatedByMsgId: patientAudit.updatedByMsgId,
      auditedOn: patientAudit.auditedOn ? patientAudit.auditedOn.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const patientAudit = this.createFromForm();
    if (patientAudit.id !== undefined) {
      this.subscribeToSaveResponse(this.patientAuditService.update(patientAudit));
    } else {
      this.subscribeToSaveResponse(this.patientAuditService.create(patientAudit));
    }
  }

  private createFromForm(): IPatientAudit {
    return {
      ...new PatientAudit(),
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
      auditedOn: this.editForm.get(['auditedOn'])!.value ? moment(this.editForm.get(['auditedOn'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatientAudit>>): void {
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
