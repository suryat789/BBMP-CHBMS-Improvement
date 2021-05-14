import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPatientAudit } from 'app/shared/model/patient-audit.model';

@Component({
  selector: 'jhi-patient-audit-detail',
  templateUrl: './patient-audit-detail.component.html',
})
export class PatientAuditDetailComponent implements OnInit {
  patientAudit: IPatientAudit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patientAudit }) => (this.patientAudit = patientAudit));
  }

  previousState(): void {
    window.history.back();
  }
}
