import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBed, Bed } from 'app/shared/model/bed.model';
import { BedService } from './bed.service';
import { IHospital } from 'app/shared/model/hospital.model';
import { HospitalService } from 'app/entities/hospital/hospital.service';

@Component({
  selector: 'jhi-bed-update',
  templateUrl: './bed-update.component.html',
})
export class BedUpdateComponent implements OnInit {
  isSaving = false;
  hospitals: IHospital[] = [];

  editForm = this.fb.group({
    id: [],
    bedId: [],
    type: [],
    capacity: [],
    occupied: [],
    blocked: [],
    vacant: [],
    createdOn: [],
    updatedOn: [],
    updatedByMsgId: [],
    hospitalId: [],
  });

  constructor(
    protected bedService: BedService,
    protected hospitalService: HospitalService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bed }) => {
      if (!bed.id) {
        const today = moment().startOf('day');
        bed.createdOn = today;
        bed.updatedOn = today;
      }

      this.updateForm(bed);

      this.hospitalService.query().subscribe((res: HttpResponse<IHospital[]>) => (this.hospitals = res.body || []));
    });
  }

  updateForm(bed: IBed): void {
    this.editForm.patchValue({
      id: bed.id,
      bedId: bed.bedId,
      type: bed.type,
      capacity: bed.capacity,
      occupied: bed.occupied,
      blocked: bed.blocked,
      vacant: bed.vacant,
      createdOn: bed.createdOn ? bed.createdOn.format(DATE_TIME_FORMAT) : null,
      updatedOn: bed.updatedOn ? bed.updatedOn.format(DATE_TIME_FORMAT) : null,
      updatedByMsgId: bed.updatedByMsgId,
      hospitalId: bed.hospitalId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bed = this.createFromForm();
    if (bed.id !== undefined) {
      this.subscribeToSaveResponse(this.bedService.update(bed));
    } else {
      this.subscribeToSaveResponse(this.bedService.create(bed));
    }
  }

  private createFromForm(): IBed {
    return {
      ...new Bed(),
      id: this.editForm.get(['id'])!.value,
      bedId: this.editForm.get(['bedId'])!.value,
      type: this.editForm.get(['type'])!.value,
      capacity: this.editForm.get(['capacity'])!.value,
      occupied: this.editForm.get(['occupied'])!.value,
      blocked: this.editForm.get(['blocked'])!.value,
      vacant: this.editForm.get(['vacant'])!.value,
      createdOn: this.editForm.get(['createdOn'])!.value ? moment(this.editForm.get(['createdOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedOn: this.editForm.get(['updatedOn'])!.value ? moment(this.editForm.get(['updatedOn'])!.value, DATE_TIME_FORMAT) : undefined,
      updatedByMsgId: this.editForm.get(['updatedByMsgId'])!.value,
      hospitalId: this.editForm.get(['hospitalId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBed>>): void {
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

  trackById(index: number, item: IHospital): any {
    return item.id;
  }
}
