import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHospitalAudit } from 'app/shared/model/hospital-audit.model';

@Component({
  selector: 'jhi-hospital-audit-detail',
  templateUrl: './hospital-audit-detail.component.html',
})
export class HospitalAuditDetailComponent implements OnInit {
  hospitalAudit: IHospitalAudit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hospitalAudit }) => (this.hospitalAudit = hospitalAudit));
  }

  previousState(): void {
    window.history.back();
  }
}
