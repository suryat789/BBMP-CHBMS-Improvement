import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBedAudit, BedAudit } from 'app/shared/model/bed-audit.model';
import { BedAuditService } from './bed-audit.service';

@Component({
  selector: 'jhi-bed-audit-update',
  templateUrl: './bed-audit-update.component.html',
})
export class BedAuditUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    hospitalId: [],
    bedId: [],
    type: [],
    capacity: [],
    occupied: [],
    blocked: [],
    vacant: [],
    createdOn: [],
    updatedOn: [],
    updatedByMsgId: [],
    auditedOn: [],
  });

  constructor(protected bedAuditService: BedAuditService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bedAudit }) => {
      if (!bedAudit.id) {
        const today = moment().startOf('day');
        bedAudit.createdOn = today;
        bedAudit.updatedOn = today;
        bedAudit.auditedOn = today;
      }

      this.updateForm(bedAudit);
    });
  }

  updateForm(bedAudit: IBedAudit): void {
    this.editForm.patchValue({
      id: bedAudit.id,
      hospitalId: bedAudit.hospitalId,
      bedId: bedAudit.bedId,
      type: bedAudit.type,
      capacity: bedAudit.capacity,
      occupied: bedAudit.occupied,
      blocked: bedAudit.blocked,
      vacant: bedAudit.vacant,
      createdOn: bedAudit.createdOn ? bedAudit.createdOn.format(DATE_TIME_FORMAT) : null,
      updatedOn: bedAudit.updatedOn ? bedAudit.updatedOn.format(DATE_TIME_FORMAT) : null,
      updatedByMsgId: bedAudit.updatedByMsgId,
      auditedOn: bedAudit.auditedOn ? bedAudit.auditedOn.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bedAudit = this.createFromForm();
    if (bedAudit.id !== undefined) {
      this.subscribeToSaveResponse(this.bedAuditService.update(bedAudit));
    } else {
      this.subscribeToSaveResponse(this.bedAuditService.create(bedAudit));
    }
  }

  private createFromForm(): IBedAudit {
    return {
      ...new BedAudit(),
      id: this.editForm.get(['id'])!.value,
      hospitalId: this.editForm.get(['hospitalId'])!.value,
      bedId: this.editForm.get(['bedId'])!.value,
      type: this.editForm.get(['type'])!.value,
      capacity: this.editForm.get(['capacity'])!.value,
      occupied: this.editForm.get(['occupied'])!.value,
      blocked: this.editForm.get(['blocked'])!.value,
      vacant: this.editForm.get(['vacant'])!.value,
      createdOn: this.editForm.get(['createdOn'])!.value ? moment(this.editForm.get(['createdOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedOn: this.editForm.get(['updatedOn'])!.value ? moment(this.editForm.get(['updatedOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedByMsgId: this.editForm.get(['updatedByMsgId'])!.value,
      auditedOn: this.editForm.get(['auditedOn'])!.value ? moment(this.editForm.get(['auditedOn'])!.value, DATE_TIME_FORMAT) : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBedAudit>>): void {
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
